package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import fr.pingtimeout.adventofcode2021.Day12.Edge;
import fr.pingtimeout.adventofcode2021.Day12.Node;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Day12Test extends DayTest {
  @Test
  public void should_calculate_all_paths() throws URISyntaxException, IOException {
    Day12 day = new Day12();
    List<String> lines = readInput(day);
    assertThat(day.partOne(lines)).isEqualTo(10);
  }

  @Test
  public void should_calculate_all_paths_with_one_dup() throws URISyntaxException, IOException {
    Day12 day = new Day12();
    List<String> lines = readInput(day);
    assertThat(day.partTwo(lines)).isEqualTo(36);
  }

  @Test
  public void should_parse_tree() throws URISyntaxException, IOException {
    Day12 day = new Day12();
    Node start = new Node("start");
    Node A = new Node("A");
    Node b = new Node("b");
    assertThat(day.parsePaths(List.of("start-A", "A-b")))
        .isEqualTo(
            Map.of(
                start, List.of(new Edge(start, A)),
                A, List.of(new Edge(A, start), new Edge(A, b)),
                b, List.of(new Edge(b, A))));
  }
}
