package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;

public class Day01Test extends DayTest {
  @Test
  public void should_find_increases() throws URISyntaxException, IOException {
    Day01 day = new Day01();
    int increases = day.partOne(readInput(day));
    assertThat(increases).isEqualTo(7);
  }

  @Test
  public void should_find_three_measurements_increases() throws URISyntaxException, IOException {
    Day01 day = new Day01();
    long increases = day.partTwo(readInput(day));
    assertThat(increases).isEqualTo(5);
  }
}
