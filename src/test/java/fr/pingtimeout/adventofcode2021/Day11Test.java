package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day11Test extends DayTest {
  @Test
  public void should_calculate_flashes_after_100_steps() throws URISyntaxException, IOException {
    Day11 day = new Day11();
    List<String> lines = readInput(day);
    assertThat(day.partOne(lines)).isEqualTo(1656);
  }

  @Test
  public void should_calculate_when_simultaneous_flashes() throws URISyntaxException, IOException {
    Day11 day = new Day11();
    List<String> lines = readInput(day);
    assertThat(day.partTwo(lines)).isEqualTo(195);
  }

  @Test
  public void should_compute_next_step_when_no_blink() {
    Day11 day = new Day11();
    Grid before =
        new Grid(
            new int[][] {
              {5, 4, 8, 3, 1, 4, 3, 2, 2, 3},
              {2, 7, 4, 5, 8, 5, 4, 7, 1, 1},
              {5, 2, 6, 4, 5, 5, 6, 1, 7, 3},
              {6, 1, 4, 1, 3, 3, 6, 1, 4, 6},
              {6, 3, 5, 7, 3, 8, 5, 4, 7, 8},
              {4, 1, 6, 7, 5, 2, 4, 6, 4, 5},
              {2, 1, 7, 6, 8, 4, 1, 7, 2, 1},
              {6, 8, 8, 2, 8, 8, 1, 1, 3, 4},
              {4, 8, 4, 6, 8, 4, 8, 5, 5, 4},
              {5, 2, 8, 3, 7, 5, 1, 5, 2, 6},
            });
    Grid expectedAfter =
        new Grid(
            new int[][] {
              {6, 5, 9, 4, 2, 5, 4, 3, 3, 4},
              {3, 8, 5, 6, 9, 6, 5, 8, 2, 2},
              {6, 3, 7, 5, 6, 6, 7, 2, 8, 4},
              {7, 2, 5, 2, 4, 4, 7, 2, 5, 7},
              {7, 4, 6, 8, 4, 9, 6, 5, 8, 9},
              {5, 2, 7, 8, 6, 3, 5, 7, 5, 6},
              {3, 2, 8, 7, 9, 5, 2, 8, 3, 2},
              {7, 9, 9, 3, 9, 9, 2, 2, 4, 5},
              {5, 9, 5, 7, 9, 5, 9, 6, 6, 5},
              {6, 3, 9, 4, 8, 6, 2, 6, 3, 7},
            });
    assertThat(day.computeNextStep(before)).isEqualTo(expectedAfter);
  }

  @Test
  public void should_compute_next_step_when_blinks() {
    Day11 day = new Day11();
    Grid before =
        new Grid(
            new int[][] {
              {6, 5, 9, 4, 2, 5, 4, 3, 3, 4},
              {3, 8, 5, 6, 9, 6, 5, 8, 2, 2},
              {6, 3, 7, 5, 6, 6, 7, 2, 8, 4},
              {7, 2, 5, 2, 4, 4, 7, 2, 5, 7},
              {7, 4, 6, 8, 4, 9, 6, 5, 8, 9},
              {5, 2, 7, 8, 6, 3, 5, 7, 5, 6},
              {3, 2, 8, 7, 9, 5, 2, 8, 3, 2},
              {7, 9, 9, 3, 9, 9, 2, 2, 4, 5},
              {5, 9, 5, 7, 9, 5, 9, 6, 6, 5},
              {6, 3, 9, 4, 8, 6, 2, 6, 3, 7},
            });
    Grid expectedAfter =
        new Grid(
            new int[][] {
              {8, 8, 0, 7, 4, 7, 6, 5, 5, 5},
              {5, 0, 8, 9, 0, 8, 7, 0, 5, 4},
              {8, 5, 9, 7, 8, 8, 9, 6, 0, 8},
              {8, 4, 8, 5, 7, 6, 9, 6, 0, 0},
              {8, 7, 0, 0, 9, 0, 8, 8, 0, 0},
              {6, 6, 0, 0, 0, 8, 8, 9, 8, 9},
              {6, 8, 0, 0, 0, 0, 5, 9, 4, 3},
              {0, 0, 0, 0, 0, 0, 7, 4, 5, 6},
              {9, 0, 0, 0, 0, 0, 0, 8, 7, 6},
              {8, 7, 0, 0, 0, 0, 6, 8, 4, 8},
            });
    assertThat(day.computeNextStep(before)).isEqualTo(expectedAfter);
  }

  @Test
  public void should_compute_next_step_when_no_blink_simple()
      throws URISyntaxException, IOException {
    Day11 day = new Day11();
    Grid before =
        new Grid(
            new int[][] {
              {6, 5, 4},
              {3, 0, 5},
              {6, 3, 7},
            });
    Grid expectedAfter =
        new Grid(
            new int[][] {
              {7, 6, 5},
              {4, 1, 6},
              {7, 4, 8},
            });
    assertThat(day.computeNextStep(before)).isEqualTo(expectedAfter);
  }

  @Test
  public void should_compute_next_step_when_blink_simple() throws URISyntaxException, IOException {
    Day11 day = new Day11();
    Grid before =
        new Grid(
            new int[][] {
              {6, 5, 4},
              {3, 9, 5},
              {6, 3, 0},
            });
    Grid expectedAfter =
        new Grid(
            new int[][] {
              {8, 7, 6},
              {5, 0, 7},
              {8, 5, 2},
            });
    assertThat(day.computeNextStep(before)).isEqualTo(expectedAfter);
  }

  @Test
  public void should_compute_cascading_blinks() throws URISyntaxException, IOException {
    Day11 day = new Day11();
    Grid before =
        new Grid(
            new int[][] {
              {1, 1, 1, 1, 1},
              {1, 9, 9, 9, 1},
              {1, 9, 1, 9, 1},
              {1, 9, 9, 9, 1},
              {1, 1, 1, 1, 1},
            });
    Grid expectedAfter =
        new Grid(
            new int[][] {
              {3, 4, 5, 4, 3},
              {4, 0, 0, 0, 4},
              {5, 0, 0, 0, 5},
              {4, 0, 0, 0, 4},
              {3, 4, 5, 4, 3},
            });
    assertThat(day.computeNextStep(before)).isEqualTo(expectedAfter);
  }
}
