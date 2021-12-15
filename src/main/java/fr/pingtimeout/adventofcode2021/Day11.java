package fr.pingtimeout.adventofcode2021;

import java.util.*;

public class Day11 implements AdventDay {
  @Override
  public int partOne(List<String> input) {
    Grid grid = Grid.from(input);
    int totalFlashes = 0;
    for (int n = 0; n < 100; n++) {
      grid = computeNextStep(grid);
      for (Coordinates coordinates : grid.allCoordinates()) {
        totalFlashes += grid.getValueAt(coordinates) == 0 ? 1 : 0;
      }
    }
    return totalFlashes;
  }

  private void maybeIncrement(Grid grid, Coordinates c) {
    if (grid.getValueAt(c) != 10) {
      grid.setValueAt(c, grid.getValueAt(c) + 1);
    }
  }

  Grid computeNextStep(Grid grid) {
    // Create an array to hold the next step
    Grid nextStep = Grid.copyOf(grid);
    // First, increment all cells except the ones that are already at 9
    grid.allCoordinates().forEach(c -> this.maybeIncrement(nextStep, c));
    // Then, handle flashes and cascading flashes
    Map<Integer, List<Coordinates>> index = indexGrid(nextStep);
    while (index.containsKey(10)) {
      // There are 10's, meaning we have to increase their neighbours
      index.get(10).stream()
          .flatMap(c -> c.allNeighbours(nextStep).stream())
          .forEach(c -> maybeIncrement(nextStep, c));
      // Since they have blinked during this step, replace them with -1
      index.get(10).forEach(c -> nextStep.setValueAt(c, -1));
      // Cells that have already blinked during this step may have been incremented, reset them
      index.getOrDefault(-1, Collections.emptyList()).forEach(c -> nextStep.setValueAt(c, -1));
      // Reindex the array in case there are new cells at 9
      index = indexGrid(nextStep);
    }
    // Finally, set all cells that have blinked during this step to 0
    index.getOrDefault(-1, Collections.emptyList()).forEach(c -> nextStep.setValueAt(c, 0));
    return nextStep;
  }

  private Map<Integer, List<Coordinates>> indexGrid(Grid grid) {
    Map<Integer, List<Coordinates>> result = new HashMap<>();
    for (int i = 0; i < grid.rows(); i++) {
      for (int j = 0; j < grid.columns(); j++) {
        Coordinates coord = new Coordinates(i, j);
        int value = grid.getValueAt(coord);
        result.computeIfAbsent(value, v -> new ArrayList<>());
        result.get(value).add(coord);
      }
    }
    return result;
  }

  @Override
  public long partTwo(List<String> input) {
    Grid grid = Grid.from(input);
    int totalSteps = 0;
    while (!allZeroes(grid)) {
      grid = computeNextStep(grid);
      totalSteps++;
    }
    return totalSteps;
  }

  private boolean allZeroes(Grid grid) {
    return grid.allCoordinates().stream().allMatch(c -> grid.getValueAt(c) == 0);
  }
}
