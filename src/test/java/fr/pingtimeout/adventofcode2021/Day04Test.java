package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day04Test extends DayTest {
  @Test
  public void should_parse_bingo_data() throws URISyntaxException, IOException {
    Day04 day = new Day04();
    List<String> lines = readInput(day);
    Day04.Bingo bingo = day.parseBingoInput(lines);
    assertThat(bingo.draws().values())
        .containsExactly(
            7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19,
            3, 26, 1);
    assertThat(bingo.grids())
        .containsExactly(
            new Day04.Grid(
                new int[][] {
                  new int[] {22, 13, 17, 11, 0},
                  new int[] {8, 2, 23, 4, 24},
                  new int[] {21, 9, 14, 16, 7},
                  new int[] {6, 10, 3, 18, 5},
                  new int[] {1, 12, 20, 15, 19}
                }),
            new Day04.Grid(
                new int[][] {
                  new int[] {3, 15, 0, 2, 22},
                  new int[] {9, 18, 13, 17, 5},
                  new int[] {19, 8, 7, 25, 23},
                  new int[] {20, 11, 10, 24, 4},
                  new int[] {14, 21, 16, 12, 6}
                }),
            new Day04.Grid(
                new int[][] {
                  new int[] {14, 21, 17, 24, 4},
                  new int[] {10, 16, 15, 9, 19},
                  new int[] {18, 8, 23, 26, 20},
                  new int[] {22, 11, 13, 6, 5},
                  new int[] {2, 0, 12, 3, 7}
                }));
  }

  @Test
  public void should_calculate_final_score_of_best_board() throws URISyntaxException, IOException {
    Day04 day = new Day04();
    List<String> lines = readInput(day);
    assertThat(day.partOne(lines)).isEqualTo(4512);
  }

  @Test
  public void should_calculate_final_score_of_worst_board() throws URISyntaxException, IOException {
    Day04 day = new Day04();
    List<String> lines = readInput(day);
    assertThat(day.partTwo(lines)).isEqualTo(1924);
  }
}
