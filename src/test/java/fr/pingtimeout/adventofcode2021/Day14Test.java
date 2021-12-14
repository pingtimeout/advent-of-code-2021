package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Day14Test extends DayTest {
  @Test
  public void should_calculate_insertions_after_10_steps() throws URISyntaxException, IOException {
    Day14 day = new Day14();
    List<String> lines = readInput(day);
    assertThat(day.partOne(lines)).isEqualTo(1588);
  }

  @Test
  public void should_calculate_insertions_after_40_steps() throws URISyntaxException, IOException {
    Day14 day = new Day14();
    List<String> lines = readInput(day);
    assertThat(day.partTwo(lines)).isEqualTo(2188189693529L);
  }

  @Test
  public void should_parse_state() {
    Day14 day = new Day14();
    assertThat(day.parseState("NNCB")).isEqualTo(Map.of("NN", 1L, "NC", 1L, "CB", 1L));
  }

  @Test
  public void should_parse_state_with_duplicates() {
    Day14 day = new Day14();
    assertThat(day.parseState("NNCBNNNC"))
        .isEqualTo(Map.of("NN", 3L, "NC", 2L, "CB", 1L, "BN", 1L));
  }

  @Test
  public void should_parse_insertion_rules() {
    Day14 day = new Day14();
    assertThat(day.parseInsertionRules(List.of("CH -> B", "HH -> N", "CB -> H")))
        .isEqualTo(Map.of("CH", 'B', "HH", 'N', "CB", 'H'));
  }

  @Test
  public void should_compute_states() throws URISyntaxException, IOException {
    Day14 day = new Day14();
    List<String> lines = readInput(day);
    Map<String, Long> initialState = day.parseState(lines.get(0));
    Map<String, Character> rules = day.parseInsertionRules(lines.subList(2, lines.size()));

    Map<String, Long> afterStep1 = day.nextState(initialState, rules);
    assertThat(afterStep1).isEqualTo(day.parseState("NCNBCHB"));

    Map<String, Long> afterStep2 = day.nextState(afterStep1, rules);
    assertThat(afterStep2).isEqualTo(day.parseState("NBCCNBBBCBHCB"));

    Map<String, Long> afterStep3 = day.nextState(afterStep2, rules);
    assertThat(afterStep3).isEqualTo(day.parseState("NBBBCNCCNBBNBNBBCHBHHBCHB"));

    Map<String, Long> afterStep4 = day.nextState(afterStep3, rules);
    assertThat(afterStep4)
        .isEqualTo(day.parseState("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"));
  }
}
