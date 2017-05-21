package io.newslab.worker.dispatcher;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TestReceiver {

    @RabbitListener(queues = "newslab.queue.test", concurrency = "10")
    public void processMessage(String content) {

        if (content.endsWith("10")) {
            throw new RuntimeException("xxx");
        }
/*        if (Thread.currentThread().getId() % 2 == 0) {
            throw new RuntimeException("xxx");
        }*/

        System.out.println(Thread.currentThread().getId() + "!!!!!!!!!!!!!" + content);

    }
}
