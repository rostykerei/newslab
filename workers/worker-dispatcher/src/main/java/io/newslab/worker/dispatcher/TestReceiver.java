package io.newslab.worker.dispatcher;

import io.newslab.messaging.rsscrawl.RssCrawlMessagingConfiguration;
import io.newslab.messaging.rsscrawl.RssCrawlTaskMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TestReceiver {

    @RabbitListener(queues = RssCrawlMessagingConfiguration.QUEUE_NAME, concurrency = "10")
    public void processMessage(RssCrawlTaskMessage content) {

        System.out.println(Thread.currentThread().getId() + "!!!!!!!!!!!!!" + content.getFeedId());

    }
}
