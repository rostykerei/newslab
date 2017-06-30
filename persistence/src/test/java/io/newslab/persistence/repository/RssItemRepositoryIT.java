package io.newslab.persistence.repository;

import io.newslab.persistence.TestConfig;
import io.newslab.persistence.model.RssFeed;
import io.newslab.persistence.model.RssItem;
import io.newslab.persistence.util.KeyGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { TestConfig.class })
@Transactional @Rollback
public class RssItemRepositoryIT {

    @Autowired
    RssItemRepository rssItemRepository;

    @Autowired
    RssFeedRepository rssFeedRepository;

    @Test
    public void findByPublisherAndGuid() throws Exception {

        RssFeed feed = rssFeedRepository.findByUrl("http://test-feed.com/").orElseThrow(AssertionError::new);

        Assert.assertFalse(rssItemRepository.findByPublisherAndGuid(feed.getPublisher(), "XXX").isPresent());

        RssItem rssItem = new RssItem();

        rssItem.setUid(KeyGenerator.generateKey());
        rssItem.setPublisher(feed.getPublisher());
        rssItem.setRssFeed(feed);
        rssItem.setTitle("test-item");
        rssItem.setLink("http://test-feed.com/test.html");
        rssItem.setGuid("XXX");
        rssItem.setPublicationDate(new Date());
        rssItem.setAdjustedPublicationDate(new Date());
        rssItem.setCreatedDate(new Date());

        rssItemRepository.save(rssItem);

        Assert.assertTrue(rssItemRepository.findByPublisherAndGuid(feed.getPublisher(), "XXX").isPresent());
    }
}