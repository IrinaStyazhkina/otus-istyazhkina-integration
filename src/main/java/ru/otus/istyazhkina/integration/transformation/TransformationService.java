package ru.otus.istyazhkina.integration.transformation;

import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.integration.domain.Butterfly;
import ru.otus.istyazhkina.integration.domain.Caterpillar;

@Service
public class TransformationService {

    public Butterfly transform(Caterpillar caterpillar) throws Exception {
        String color = caterpillar.getColor();
        System.out.println("Transforming " + color + " caterpillar");
        Thread.sleep(2000);
        System.out.println("Caterpillar is transformed to " + color + " butterfly");
        return new Butterfly(color);
    }
}
