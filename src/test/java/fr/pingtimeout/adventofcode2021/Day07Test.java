package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day07Test extends DayTest {
  @Test
  public void should_calculate_optimal_fuel_consumption() throws URISyntaxException, IOException {
    Day07 day07 = new Day07();
    List<String> lines = readInput(day07);
    assertThat(day07.partOne(lines)).isEqualTo(37);
  }

  @Test
  public void should_calculate_revised_fuel_consumption() throws URISyntaxException, IOException {
    Day07 day07 = new Day07();
    List<String> lines = readInput(day07);
    assertThat(day07.partTwo(lines)).isEqualTo(168);
  }

  @Test
  public void should_calculate_median() {
    Day07 day07 = new Day07();
    assertThat(day07.medianOf(new int[] {0, 1, 1, 2, 2, 2, 4, 7, 14, 16})).isEqualTo(2);
  }
}
