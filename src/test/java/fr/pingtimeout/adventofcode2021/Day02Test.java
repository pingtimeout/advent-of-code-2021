package fr.pingtimeout.adventofcode2021;

import fr.pingtimeout.adventofcode2021.Day02.Command;
import fr.pingtimeout.adventofcode2021.Day02.Direction;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Day02Test {
    @Test
    public void should_parse_commands() throws URISyntaxException, IOException {
        Day02 day02 = new Day02();
        Stream<Command> commands = new Parser()
                .readFileAsStrings("/day02/input.txt")
                .stream()
                .map(day02::parseCommand);
        assertThat(commands).containsExactly(
                new Command(Direction.forward, 5),
                new Command(Direction.down, 5),
                new Command(Direction.forward, 8),
                new Command(Direction.up, 3),
                new Command(Direction.down, 8),
                new Command(Direction.forward, 2)
        );
    }

    @Test
    public void should_calculate_final_position() throws URISyntaxException, IOException {
        Day02 day02 = new Day02();
        int multiple = day02.partOne(new Parser().readFileAsStrings("/day02/input.txt"));
        assertThat(multiple).isEqualTo(150);
    }

    @Test
    public void should_calculate_final_position_with_aim() throws URISyntaxException, IOException {
        Day02 day02 = new Day02();
        int multiple = day02.partTwo(new Parser().readFileAsStrings("/day02/input.txt"));
        assertThat(multiple).isEqualTo(900);
    }
}