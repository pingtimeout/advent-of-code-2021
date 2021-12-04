package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;

public class Day01Test extends DayTest {
  @Test
  public void should_find_increases() throws URISyntaxException, IOException {
    Day01 day01 = new Day01();
    int increases = day01.partOne(readInput(day01));
    assertThat(increases).isEqualTo(7);
  }

  @Test
  public void should_find_three_measurements_increases() throws URISyntaxException, IOException {
    Day01 day01 = new Day01();
    int increases = new Day01().partTwo(readInput(day01));
    assertThat(increases).isEqualTo(5);
  }
}
