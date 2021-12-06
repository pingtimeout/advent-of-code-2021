package fr.pingtimeout.adventofcode2021;

import static java.util.stream.Collectors.groupingBy;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day05 implements AdventDay {
  record Line(Point from, Point to) {

    public static final Pattern LINE_REGEX = Pattern.compile("^(\\d+),(\\d+) -> (\\d+),(\\d+)$");

    static Line fromString(String s) {
      Matcher m = LINE_REGEX.matcher(s);
      if (m.find()) {
        int x1 = Integer.parseInt(m.group(1));
        int y1 = Integer.parseInt(m.group(2));
        int x2 = Integer.parseInt(m.group(3));
        int y2 = Integer.parseInt(m.group(4));
        if (x1 < x2 || y1 < y2) {
          return new Line(new Point(x1, y1), new Point(x2, y2));
        } else {
          return new Line(new Point(x2, y2), new Point(x1, y1));
        }
      } else {
        throw new IllegalArgumentException("Invalid string");
      }
    }

    boolean isVertical() {
      return from.x == to.x;
    }

    boolean isHorizontal() {
      return from.y == to.y;
    }

    public boolean isDiagonal() {
      return Math.abs(to.x - from.x) == Math.abs(to.y - from.y);
    }

    boolean isStraight() {
      return isHorizontal() || isVertical() || isDiagonal();
    }

    Collection<Point> computeAllPoints() {
      Set<Point> result = new HashSet<>();
      int xDelta = to.x - from.x;
      int yDelta = to.y - from.y;
      int numSteps = Math.max(Math.abs(xDelta), Math.abs(yDelta));
      int xStep = xDelta / numSteps;
      int yStep = yDelta / numSteps;
      for (int k = 0; k <= numSteps; k++) {
        result.add(new Point(from.x + k * xStep, from.y + k * yStep));
      }
      return result;
    }
  }

  record Point(int x, int y) {}

  @Override
  public int partOne(List<String> input) {
    return countDuplicatePoints(input, l -> l.isHorizontal() || l.isVertical());
  }

  @Override
  public long partTwo(List<String> input) {
    // The input contains either horizontal, vertical or diagonal (45°) lines, nothing else
    // Hence, for part two, all lines should be considered for the calculation
    Predicate<Line> keepAllLines = l -> true;
    return countDuplicatePoints(input, keepAllLines);
  }

  private int countDuplicatePoints(List<String> input, Predicate<Line> linePredicate) {
    return (int)
        input.stream()
            .map(Line::fromString)
            .filter(linePredicate)
            .flatMap(line -> line.computeAllPoints().stream())
            .collect(groupingBy(Function.identity(), Collectors.counting()))
            .values()
            .stream()
            .filter(entry -> entry >= 2)
            .count();
  }
}
