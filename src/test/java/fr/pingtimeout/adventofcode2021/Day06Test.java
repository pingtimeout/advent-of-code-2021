package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Day06Test extends DayTest {
  @Test
  public void should_count_lanternfish_after_80_days() throws URISyntaxException, IOException {
    Day06 day06 = new Day06();
    List<String> lines = readInput(day06);
    assertThat(day06.partOne(lines)).isEqualTo(5934);
  }

  @Test
  public void should_calculate_next_age_frequencies() {
    Day06 day06 = new Day06();
    Map<Integer, Long> ageFrequencies =
        Map.of(
            1, 1L, //
            3, 2L);
    assertThat(day06.calculateNextFrequencies(ageFrequencies))
        .containsExactlyInAnyOrderEntriesOf(
            Map.of(
                0, 1L,
                2, 2L));
  }

  @Test
  public void should_parse_input_into_map() {
    Day06 day06 = new Day06();
    Map<Integer, Long> parsedInput = day06.parseInput("3,4,3,1,2");
    assertThat(parsedInput)
        .containsExactlyInAnyOrderEntriesOf(
            Map.of(
                3, 2L,
                2, 1L,
                1, 1L,
                4, 1L));
    assertThat(frequenciesToArray(parsedInput)).containsOnly(3, 4, 3, 1, 2);
  }

  @Test
  public void should_calculate_new_spawns() {
    Day06 day06 = new Day06();
    Map<Integer, Long> ageFrequencies = day06.parseInput("0,0,0,1,3,3");
    assertThat(day06.calculateNextFrequencies(ageFrequencies))
        .containsExactlyInAnyOrderEntriesOf(
            Map.of(
                0, 1L,
                2, 2L,
                6, 3L,
                8, 3L));
  }

  @Test
  public void should_calculate_new_spawns_per_advent_of_code_samples() {
    assertExampleCaseAfter(0, "3,4,3,1,2");
    assertExampleCaseAfter(1, "2,3,2,0,1");
    assertExampleCaseAfter(2, "1,2,1,6,0,8");
    assertExampleCaseAfter(3, "0,1,0,5,6,7,8");
    assertExampleCaseAfter(4, "6,0,6,4,5,6,7,8,8");
    assertExampleCaseAfter(5, "5,6,5,3,4,5,6,7,7,8");
    assertExampleCaseAfter(6, "4,5,4,2,3,4,5,6,6,7");
    assertExampleCaseAfter(7, "3,4,3,1,2,3,4,5,5,6");
    assertExampleCaseAfter(8, "2,3,2,0,1,2,3,4,4,5");
    assertExampleCaseAfter(9, "1,2,1,6,0,1,2,3,3,4,8");
    assertExampleCaseAfter(10, "0,1,0,5,6,0,1,2,2,3,7,8");
    assertExampleCaseAfter(11, "6,0,6,4,5,6,0,1,1,2,6,7,8,8,8");
    assertExampleCaseAfter(12, "5,6,5,3,4,5,6,0,0,1,5,6,7,7,7,8,8");
    assertExampleCaseAfter(13, "4,5,4,2,3,4,5,6,6,0,4,5,6,6,6,7,7,8,8");
    assertExampleCaseAfter(14, "3,4,3,1,2,3,4,5,5,6,3,4,5,5,5,6,6,7,7,8");
    assertExampleCaseAfter(15, "2,3,2,0,1,2,3,4,4,5,2,3,4,4,4,5,5,6,6,7");
    assertExampleCaseAfter(16, "1,2,1,6,0,1,2,3,3,4,1,2,3,3,3,4,4,5,5,6,8");
    assertExampleCaseAfter(17, "0,1,0,5,6,0,1,2,2,3,0,1,2,2,2,3,3,4,4,5,7,8");
    assertExampleCaseAfter(18, "6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8");
  }

  private void assertExampleCaseAfter(int days, String expected) {
    Day06 day06 = new Day06();
    Map<Integer, Long> frequencies = day06.parseInput("3,4,3,1,2");
    for (int i = 0; i < days; i++) {
      frequencies = day06.calculateNextFrequencies(frequencies);
    }
    assertThat(frequenciesToArray(frequencies)).isEqualTo(parse(expected));
  }

  private int[] parse(String s) {
    return Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).sorted().toArray();
  }

  private int[] frequenciesToArray(Map<Integer, Long> frequencies) {
    int[] result = new int[frequencies.values().stream().mapToInt(Long::intValue).sum()];
    int pointer = 0;
    for (Map.Entry<Integer, Long> frequencyPerAge : frequencies.entrySet()) {
      for (int i = 0; i < frequencyPerAge.getValue(); i++) {
        result[pointer++] = frequencyPerAge.getKey();
      }
    }
    Arrays.sort(result);
    return result;
  }
}
