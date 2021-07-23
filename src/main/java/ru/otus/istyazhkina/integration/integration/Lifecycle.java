package ru.otus.istyazhkina.integration.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.istyazhkina.integration.domain.Butterfly;
import ru.otus.istyazhkina.integration.domain.Caterpillar;


import java.util.Collection;

@MessagingGateway
public interface Lifecycle {

    @Gateway(requestChannel = "caterpillarChannel", replyChannel = "butterflyChannel")
    Collection<Butterfly> process(Collection<Caterpillar> caterpillar);
}
