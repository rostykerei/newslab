package io.newslab.worker.dispatcher;

import io.newslab.persistence.model.RssFeed;
import io.newslab.persistence.repository.RssFeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "newslab.dispatcher.rss-crawler")
@EnableScheduling
public class RssCrawlerDispatcher {

    private final Logger logger = LoggerFactory.getLogger(RssCrawlerDispatcher.class);

    private int dispatchInterval;

    private int dispatchFeedsNumber;

    private long maxProcessingTime;

    private int resetInterval;

    private final RssFeedRepository rssFeedRepository;

    private final RabbitTemplate rabbitTemplate;

    public static int i = 0;

    @Autowired
    public RssCrawlerDispatcher(RssFeedRepository rssFeedRepository,
                                @Qualifier("testRabbitTemplate") RabbitTemplate rabbitTemplate) {
        this.rssFeedRepository = rssFeedRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostConstruct
    public void logProps() {
        logger.info("RssCrawlerDispatcher started");

        logger.info("Dispatch to crawl interval [newslab.dispatcher.rss-crawler.dispatch-interval]: " + getDispatchInterval() + "ms");
        logger.info("Dispatch feeds to crawl [newslab.dispatcher.rss-crawler.dispatch-feeds-number]: " + getDispatchFeedsNumber());
        logger.info("Maximum processing time [newslab.dispatcher.rss-crawler.max-processing-time]: " + getMaxProcessingTime() + "ms");
        logger.info("Reset interval [newslab.dispatcher.rss-crawler.reset-interval]: " + getResetInterval() + "ms");
    }

    @Scheduled(fixedDelayString = "${newslab.dispatcher.rss-crawler.dispatch-interval}")
    public void dispatchRssCrawl() {
        logger.debug("dispatchRssCrawl fires");

        List<RssFeed> feeds = rssFeedRepository.pollFeedsToProcess(getDispatchFeedsNumber());

        for (RssFeed feed : feeds) {

        }

        if (i < 12) {
            rabbitTemplate.convertAndSend("newslab.exchange.test", "Hello from RabbitMQ! " + i);
            i++;
        }


        /*List<Feed> feedList = feedDao.pollFeedsToProcess(feedsToCrawl);

        logger.debug(feedList.size() + " feeds to be crawled");

        for (Feed feed : feedList) {
            CrawlMessage message = new CrawlMessage(feed.getId());
            crawlMessaging.convertAndSend(message);
        }*/
    }

    @Scheduled(fixedDelayString = "${newslab.dispatcher.rss-crawler.reset-interval}")
    public void resetStuckFeeds() {
        logger.debug("resetStuckFeeds fires");

        int num = rssFeedRepository.resetIdleFeeds(getMaxProcessingTime());

        if (num > 0) {
            logger.warn(num + " feed" + (num == 1 ? " has" : "s have") + " been stuck and reset" );
        }
    }


    public int getDispatchInterval() {
        return dispatchInterval;
    }

    public void setDispatchInterval(int dispatchInterval) {
        this.dispatchInterval = dispatchInterval;
    }

    public int getDispatchFeedsNumber() {
        return dispatchFeedsNumber;
    }

    public void setDispatchFeedsNumber(int dispatchFeedsNumber) {
        this.dispatchFeedsNumber = dispatchFeedsNumber;
    }

    public long getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public void setMaxProcessingTime(long maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
    }

    public int getResetInterval() {
        return resetInterval;
    }

    public void setResetInterval(int resetInterval) {
        this.resetInterval = resetInterval;
    }
}
