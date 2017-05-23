package io.newslab.worker.dispatcher;

import io.newslab.messaging.rsscrawl.RssCrawlTaskMessage;
import io.newslab.persistence.model.RssFeed;
import io.newslab.persistence.repository.RssFeedRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RssCrawlerDispatcherTest {

    @Mock
    private RssFeedRepository rssFeedRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private RssCrawlerDispatcher rssCrawlerDispatcher;

    @Test
    public void dispatchRssCrawl() {
        rssCrawlerDispatcher.setDispatchFeedsNumber(5);

        List<RssFeed> rssFeeds = new ArrayList<>();
        RssFeed feed1 = new RssFeed();
        feed1.setId(1);

        RssFeed feed2 = new RssFeed();
        feed2.setId(2);

        rssFeeds.add(feed1);
        rssFeeds.add(feed2);

        when(rssFeedRepository.pollFeedsToProcess(5)).thenReturn(rssFeeds);

        rssCrawlerDispatcher.dispatchRssCrawl();

        verify(rssFeedRepository).pollFeedsToProcess(anyInt());
        verify(rabbitTemplate, times(2)).convertAndSend(any(RssCrawlTaskMessage.class));
    }

    @Test
    public void resetStuckFeeds() {
        rssCrawlerDispatcher.setMaxProcessingTime(5000);

        rssCrawlerDispatcher.resetStuckFeeds();

        verify(rssFeedRepository, times(1)).resetIdleFeeds(5000);
    }

}