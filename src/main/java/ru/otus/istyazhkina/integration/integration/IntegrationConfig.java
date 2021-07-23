package ru.otus.istyazhkina.integration.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    @Bean
    public QueueChannel caterpillarChannel() {
        return MessageChannels.queue( 50 ).get();
    }

    @Bean
    public PublishSubscribeChannel butterflyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 ).get();
    }

    @Bean
    public IntegrationFlow transformationFlow() {
        return IntegrationFlows.from( "caterpillarChannel" )
                .split()
                .handle("transformationService", "transform")
                .aggregate()
                .channel( "butterflyChannel" )
                .get();
    }

}
