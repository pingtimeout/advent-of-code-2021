package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day00Test extends DayTest {
  @Test
  public void should_do_something() throws URISyntaxException, IOException {
    Day00 day = new Day00();
    List<String> lines = readInput(day);
    assertThat(day.partOne(lines)).isEqualTo(0);
  }
}
