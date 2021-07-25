package ru.otus.istyazhkina.integration.shell;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.istyazhkina.integration.domain.Butterfly;
import ru.otus.istyazhkina.integration.domain.Caterpillar;
import ru.otus.istyazhkina.integration.integration.Lifecycle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final Lifecycle lifecycle;

    @ShellMethod(value = "getCaterpillarCount", key = "count")
    public void getCaterpillarCount(@ShellOption Integer count) {
        final List<String> colors = List.of("red", "green", "blue", "yellow", "pink", "lilla", "orange");

        List<Caterpillar> caterpillars = new ArrayList<>();
        for (int i = 0; i < count; i++ ) {
            caterpillars.add(new Caterpillar(colors.get(RandomUtils.nextInt(0, colors.size() - 1))));
        }

        System.out.println( "Born caterpillars: " +
                caterpillars.stream().map( Caterpillar::getColor)
                        .collect( Collectors.joining( "," ) ) );

        Collection<Butterfly> butterflies = lifecycle.process( caterpillars );
        System.out.println( "Transformed to butterflies: " + butterflies.stream()
                .map( Butterfly::getColor)
                .collect( Collectors.joining( "," )));
    }
}
