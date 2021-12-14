package fr.pingtimeout.adventofcode2021;

import static fr.pingtimeout.adventofcode2021.Direction.*;

import java.util.*;

public class Day09 implements AdventDay {

  @Override
  public int partOne(List<String> input) {
    int[][] grid = parseGrid(input);
    SortedSet<Coordinates> coordinates = buildAllCoordinates(grid);
    return coordinates.stream()
        .filter(c -> isLowPoint(grid, c))
        .mapToInt(c -> grid[c.x()][c.y()] + 1)
        .sum();
  }

  public int[][] parseGrid(List<String> input) {
    int[][] grid = new int[input.size()][];
    for (int i = 0; i < input.size(); i++) {
      String line = input.get(i);
      grid[i] = new int[line.length()];
      for (int j = 0; j < line.length(); j++) {
        grid[i][j] = line.charAt(j) - '0';
      }
    }
    return grid;
  }

  private SortedSet<Coordinates> buildAllCoordinates(int[][] grid) {
    SortedSet<Coordinates> coordinates = new TreeSet<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] != 9) {
          // points at height 9 cannot be low points nor part of any basin, ignore them
          coordinates.add(new Coordinates(i, j));
        }
      }
    }
    return coordinates;
  }

  private boolean isLowPoint(int[][] grid, Coordinates origin) {
    int value = grid[origin.x()][origin.y()];
    return origin
        .neighbours(grid, N, S, E, W)
        .reduce(true, (acc, c) -> acc && (value < grid[c.x()][c.y()]), (b1, b2) -> b1 & b2);
  }

  @Override
  public long partTwo(List<String> input) {
    int[][] grid = parseGrid(input);
    SortedSet<Coordinates> allCoordinates = buildAllCoordinates(grid);
    List<Set<Coordinates>> basins = new ArrayList<>();
    while (!allCoordinates.isEmpty()) {
      Set<Coordinates> basin = new HashSet<>();
      Stack<Coordinates> toExplore = new Stack<>();
      toExplore.push(allCoordinates.first());
      while (!toExplore.empty()) {
        Coordinates current = toExplore.pop();
        if (allCoordinates.contains(current)) {
          // Coordinate does not point to a 9
          // Add it to the current basin and explore the neighbours not explored yet
          basin.add(current);
          current
              .neighbours(grid, N, S, W, E)
              .filter(c -> !basin.contains(c))
              .forEach(toExplore::push);
        }
      }
      basins.add(basin);
      allCoordinates.removeAll(basin);
    }
    int[] sizes = basins.stream().mapToInt(Set::size).sorted().toArray();
    return (long) sizes[sizes.length - 1] * sizes[sizes.length - 2] * sizes[sizes.length - 3];
  }
}
