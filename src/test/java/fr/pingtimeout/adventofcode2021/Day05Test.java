package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day05Test extends DayTest {
  @Test
  public void should_parse_coordinates() {
    Day05.Line line = Day05.Line.fromString("0,9 -> 5,9");
    assertThat(line.from().x()).isEqualTo(0);
    assertThat(line.from().y()).isEqualTo(9);
    assertThat(line.to().x()).isEqualTo(5);
    assertThat(line.to().y()).isEqualTo(9);
    assertThat(line.isHorizontal()).isTrue();
    assertThat(line.isStraight()).isTrue();
    assertThat(line.isVertical()).isFalse();
  }

  @Test
  public void should_support_horizontal_lines() {
    Day05.Line line = Day05.Line.fromString("0,0 -> 4,0");
    assertThat(line.isDiagonal()).isFalse();
    assertThat(line.isHorizontal()).isTrue();
    assertThat(line.isVertical()).isFalse();
    assertThat(line.isStraight()).isTrue();
  }

  @Test
  public void should_support_vertical_lines() {
    Day05.Line line = Day05.Line.fromString("0,0 -> 0,4");
    assertThat(line.isDiagonal()).isFalse();
    assertThat(line.isHorizontal()).isFalse();
    assertThat(line.isVertical()).isTrue();
    assertThat(line.isStraight()).isTrue();
  }

  @Test
  public void should_support_diagonal_lines() {
    Day05.Line l1 = Day05.Line.fromString("0,0 -> 8,8");
    assertThat(l1.isDiagonal()).isTrue();
    assertThat(l1.isHorizontal()).isFalse();
    assertThat(l1.isVertical()).isFalse();
    assertThat(l1.isStraight()).isTrue();
    Day05.Line l2 = Day05.Line.fromString("8,0 -> 0,8");
    assertThat(l2.isDiagonal()).isTrue();
    assertThat(l2.isHorizontal()).isFalse();
    assertThat(l2.isVertical()).isFalse();
    assertThat(l2.isStraight()).isTrue();
    Day05.Line l3 = Day05.Line.fromString("6,4 -> 2,0");
    assertThat(l3.isDiagonal()).isTrue();
    assertThat(l3.isHorizontal()).isFalse();
    assertThat(l3.isVertical()).isFalse();
    assertThat(l3.isStraight()).isTrue();
  }

  @Test
  public void should_calculate_intermediate_points() {
    Day05.Line horizontalLine = Day05.Line.fromString("0,9 -> 5,9");
    assertThat(horizontalLine.computeAllPoints())
        .hasSize(6)
        .containsOnly(
            new Day05.Point(0, 9),
            new Day05.Point(1, 9),
            new Day05.Point(2, 9),
            new Day05.Point(3, 9),
            new Day05.Point(4, 9),
            new Day05.Point(5, 9));
    Day05.Line verticalLine = Day05.Line.fromString("1,1 -> 1,3");
    assertThat(verticalLine.computeAllPoints())
        .hasSize(3)
        .containsOnly(new Day05.Point(1, 1), new Day05.Point(1, 2), new Day05.Point(1, 3));
    Day05.Line diagonalLine = Day05.Line.fromString("1,1 -> 4,4");
    assertThat(diagonalLine.computeAllPoints())
        .hasSize(4)
        .containsOnly(
            new Day05.Point(1, 1),
            new Day05.Point(2, 2),
            new Day05.Point(3, 3),
            new Day05.Point(4, 4));
  }

  @Test
  public void should_calculate_h_and_v_intersections() throws URISyntaxException, IOException {
    Day05 day05 = new Day05();
    List<String> lines = readInput(day05);
    assertThat(day05.partOne(lines)).isEqualTo(5);
  }

  @Test
  public void should_calculate_all_straight_lines_intersections()
      throws URISyntaxException, IOException {
    Day05 day05 = new Day05();
    List<String> lines = readInput(day05);
    assertThat(day05.partTwo(lines)).isEqualTo(12);
  }

  @Test
  public void should_calculate_points_in_diagonal_lines() {
    assertThat(Day05.Line.fromString("0,8 -> 8,0").computeAllPoints())
        .containsOnly(
            new Day05.Point(0, 8),
            new Day05.Point(1, 7),
            new Day05.Point(2, 6),
            new Day05.Point(3, 5),
            new Day05.Point(4, 4),
            new Day05.Point(5, 3),
            new Day05.Point(6, 2),
            new Day05.Point(7, 1),
            new Day05.Point(8, 0));
    assertThat(Day05.Line.fromString("8,0 -> 0,8").computeAllPoints())
        .containsOnly(
            new Day05.Point(0, 8),
            new Day05.Point(1, 7),
            new Day05.Point(2, 6),
            new Day05.Point(3, 5),
            new Day05.Point(4, 4),
            new Day05.Point(5, 3),
            new Day05.Point(6, 2),
            new Day05.Point(7, 1),
            new Day05.Point(8, 0));
  }
}
