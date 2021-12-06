package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import fr.pingtimeout.adventofcode2021.Day02.Command;
import fr.pingtimeout.adventofcode2021.Day02.Direction;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;
import org.junit.Test;

public class Day02Test extends DayTest {
  @Test
  public void should_parse_commands() throws URISyntaxException, IOException {
    Day02 day02 = new Day02();
    Stream<Command> commands = readInput(day02).stream().map(day02::parseCommand);
    assertThat(commands)
        .containsExactly(
            new Command(Direction.forward, 5),
            new Command(Direction.down, 5),
            new Command(Direction.forward, 8),
            new Command(Direction.up, 3),
            new Command(Direction.down, 8),
            new Command(Direction.forward, 2));
  }

  @Test
  public void should_calculate_final_position() throws URISyntaxException, IOException {
    Day02 day02 = new Day02();
    int multiple = day02.partOne(readInput(day02));
    assertThat(multiple).isEqualTo(150);
  }

  @Test
  public void should_calculate_final_position_with_aim() throws URISyntaxException, IOException {
    Day02 day02 = new Day02();
    long multiple = day02.partTwo(readInput(day02));
    assertThat(multiple).isEqualTo(900);
  }
}
