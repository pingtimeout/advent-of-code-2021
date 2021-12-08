package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day08Test extends DayTest {
  @Test
  public void should_calculate_number_of_1_4_7_and_8() throws URISyntaxException, IOException {
    Day08 day = new Day08();
    List<String> lines = readInput(day);
    assertThat(day.partOne(lines)).isEqualTo(26);
  }

  @Test
  public void should_decode_output() throws URISyntaxException, IOException {
    Day08 day = new Day08();
    List<String> lines = readInput(day);
    assertThat(day.partTwo(lines)).isEqualTo(61229);
  }
}
