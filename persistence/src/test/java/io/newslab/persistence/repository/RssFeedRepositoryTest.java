package io.newslab.persistence.repository;

import io.newslab.persistence.TestConfig;
import io.newslab.persistence.model.Publisher;
import io.newslab.persistence.model.RssFeed;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { TestConfig.class })
@Transactional @Rollback
public class RssFeedRepositoryTest {

    @Autowired
    private RssFeedRepository rssFeedRepository;

    @Test
    public void findByUrl() {
        Assert.assertTrue(rssFeedRepository.findByUrl("http://test-feed.com/").isPresent());
        Assert.assertFalse(rssFeedRepository.findByUrl("non-existent-feed").isPresent());
    }

    @Test
    public void pollFeedsToProcess() {
        Optional<RssFeed> feed1 = rssFeedRepository.pollFeedToProcess();
        Optional<RssFeed> feed2 = rssFeedRepository.pollFeedToProcess();

        Assert.assertTrue(feed1.isPresent());
        Assert.assertFalse(feed2.isPresent());

        Assert.assertNotNull(feed1.get().getInProgressSince());

        rssFeedRepository.releaseFeedInProgress(feed1.get());

        Assert.assertNull(feed1.get().getInProgressSince());
        Assert.assertNotNull(feed1.get().getLastCheck());
    }

    @Test
    public void resetIdleFeeds() {
        RssFeed feed = rssFeedRepository.findByUrl("http://test-feed.com/").orElseThrow(AssertionError::new);

        feed.setInProgressSince(new Date(System.currentTimeMillis() - 10000));

        rssFeedRepository.save(feed);

        Assert.assertEquals(1, rssFeedRepository.resetIdleFeeds(5000));
    }

}