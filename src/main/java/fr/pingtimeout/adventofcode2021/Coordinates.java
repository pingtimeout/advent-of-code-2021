package fr.pingtimeout.adventofcode2021;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record Coordinates(int x, int y) implements Comparable<Coordinates> {
  public static final Comparator<Coordinates> COMPARATOR =
      Comparator.comparing(Coordinates::x).thenComparing(Coordinates::y);

  @Override
  public int compareTo(Coordinates that) {
    return COMPARATOR.compare(this, that);
  }

  public Optional<Coordinates> neighbour(int[][] grid, Direction d) {
    int maxX = grid.length - 1;
    int maxY = grid[0].length - 1;
    if (d == Direction.NW && x > 0 && y > 0) return Optional.of(new Coordinates(x - 1, y - 1));
    if (d == Direction.N && x > 0) return Optional.of(new Coordinates(x - 1, y));
    if (d == Direction.NE && x > 0 && y < maxY) return Optional.of(new Coordinates(x - 1, y + 1));
    if (d == Direction.W && y > 0) return Optional.of(new Coordinates(x, y - 1));
    if (d == Direction.E && y < maxY) return Optional.of(new Coordinates(x, y + 1));
    if (d == Direction.SW && x < maxX && y > 0) return Optional.of(new Coordinates(x + 1, y - 1));
    if (d == Direction.S && x < maxX) return Optional.of(new Coordinates(x + 1, y));
    if (d == Direction.SE && x < maxX && y < maxY)
      return Optional.of(new Coordinates(x + 1, y + 1));
    return Optional.empty();
  }

  public Stream<Coordinates> neighbours(int[][] grid, Direction... dirs) {
    return Arrays.stream(dirs).flatMap(d -> neighbour(grid, d).stream());
  }

  public static List<Coordinates> allCoordinates(int[][] grid) {
    ArrayList<Coordinates> result = new ArrayList<>();
    for (int i = 0; i < grid.length; i++) {
      int[] ints = grid[i];
      for (int j = 0; j < ints.length; j++) {
        result.add(new Coordinates(i, j));
      }
    }
    return result;
  }

  public List<Coordinates> allNeighbours(int[][] grid) {
    return neighbours(grid, Direction.values()).collect(Collectors.toList());
  }
}
