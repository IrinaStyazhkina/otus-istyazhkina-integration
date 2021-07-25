package ru.otus.istyazhkina.integration.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.istyazhkina.integration.transformation.TransformationService;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    private final TransformationService transformationService;
    private static final String methodName = "transform";

    @Bean
    public QueueChannel caterpillarChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public PublishSubscribeChannel butterflyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).get();
    }

    @Bean
    public IntegrationFlow transformationFlow() {
        return IntegrationFlows.from( "caterpillarChannel" )
                .split()
                .handle(transformationService, methodName)
                .aggregate()
                .channel( "butterflyChannel" )
                .get();
    }

}
