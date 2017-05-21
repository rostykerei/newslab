package io.newslab.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

    final static String queueName = "newslab.queue.test";

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange("newslab.exchange.test");
    }

    @Bean
    public Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean(name = "testRabbitTemplate")
    public RabbitTemplate testRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange("newslab.exchange.test");

        return rabbitTemplate;
    }

}
