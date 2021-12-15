package fr.pingtimeout.adventofcode2021;

import static fr.pingtimeout.adventofcode2021.Direction.*;

import java.util.*;
import java.util.stream.Collectors;

public class Day09 implements AdventDay {

  @Override
  public int partOne(List<String> input) {
    Grid grid = Grid.from(input);
    SortedSet<Coordinates> coordinates = buildAllCoordinates(grid);
    return coordinates.stream()
        .filter(c -> isLowPoint(grid, c))
        .mapToInt(c -> grid.getValueAt(c) + 1)
        .sum();
  }

  private SortedSet<Coordinates> buildAllCoordinates(Grid grid) {
    return grid.allCoordinates().stream()
        // points at height 9 cannot be low points nor part of any basin, ignore them
        .filter(c -> grid.getValueAt(c) != 9)
        .collect(Collectors.toCollection(TreeSet::new));
  }

  private boolean isLowPoint(Grid grid, Coordinates origin) {
    int value = grid.getValueAt(origin);
    return origin
        .neighbours(grid, N, S, E, W)
        .reduce(true, (acc, c) -> acc && (value < grid.getValueAt(c)), (b1, b2) -> b1 & b2);
  }

  @Override
  public long partTwo(List<String> input) {
    Grid grid = Grid.from(input);
    SortedSet<Coordinates> coordinates = buildAllCoordinates(grid);
    List<Set<Coordinates>> basins = new ArrayList<>();
    while (!coordinates.isEmpty()) {
      Set<Coordinates> basin = new HashSet<>();
      Stack<Coordinates> toExplore = new Stack<>();
      toExplore.push(coordinates.first());
      while (!toExplore.empty()) {
        Coordinates current = toExplore.pop();
        if (coordinates.contains(current)) {
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
      coordinates.removeAll(basin);
    }
    int[] sizes = basins.stream().mapToInt(Set::size).sorted().toArray();
    return (long) sizes[sizes.length - 1] * sizes[sizes.length - 2] * sizes[sizes.length - 3];
  }
}
