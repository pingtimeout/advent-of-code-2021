package fr.pingtimeout.adventofcode2021;

import java.util.*;

public class Day11 implements AdventDay {
  @Override
  public int partOne(List<String> input) {
    int[][] grid = parseGrid(input);
    int totalFlashes = 0;
    for (int n = 0; n < 100; n++) {
      grid = computeNextStep(grid);
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          if (grid[i][j] == 0) totalFlashes++;
        }
      }
    }
    return totalFlashes;
  }

  private void maybeIncrement(int[][] grid, Coordinates c) {
    if (grid[c.x()][c.y()] != 10) {
      grid[c.x()][c.y()]++;
    }
  }

  int[][] computeNextStep(int[][] grid) {
    // Create an array to hold the next step
    int[][] nextStep = new int[grid.length][];
    for (int i = 0; i < grid.length; i++) {
      nextStep[i] = grid[i].clone();
    }
    // First, increment all cells except the ones that are already at 9
    Coordinates.allCoordinates(grid).forEach(c -> this.maybeIncrement(nextStep, c));
    // Then, handle flashes and cascading flashes
    Map<Integer, List<Coordinates>> index = indexGrid(nextStep);
    while (index.containsKey(10)) {
      // There are 10's, meaning we have to increase their neighbours
      index.get(10).stream()
          .flatMap(c -> c.allNeighbours(nextStep).stream())
          .forEach(c -> maybeIncrement(nextStep, c));
      // Since they have blinked during this step, replace them with -1
      index.get(10).forEach(c -> nextStep[c.x()][c.y()] = -1);
      // Cells that have already blinked during this step may have been incremented, reset them
      index.getOrDefault(-1, Collections.emptyList()).forEach(c -> nextStep[c.x()][c.y()] = -1);
      // Reindex the array in case there are new cells at 9
      index = indexGrid(nextStep);
    }
    // Finally, set all cells that have blinked during this step to 0
    index.getOrDefault(-1, Collections.emptyList()).forEach(c -> nextStep[c.x()][c.y()] = 0);
    return nextStep;
  }

  private int[][] parseGrid(List<String> input) {
    int[][] grid = new int[10][];
    for (int i = 0; i < input.size(); i++) {
      String line = input.get(i);
      grid[i] = new int[10];
      for (int j = 0; j < line.length(); j++) {
        grid[i][j] = line.charAt(j) - '0';
      }
    }
    return grid;
  }

  private Map<Integer, List<Coordinates>> indexGrid(int[][] grid) {
    Map<Integer, List<Coordinates>> result = new HashMap<>();
    for (int i = 0; i < grid.length; i++) {
      int[] ints = grid[i];
      for (int j = 0; j < ints.length; j++) {
        result.computeIfAbsent(grid[i][j], value -> new ArrayList<>());
        result.get(grid[i][j]).add(new Coordinates(i, j));
      }
    }
    return result;
  }

  @Override
  public long partTwo(List<String> input) {
    int[][] grid = parseGrid(input);
    int totalSteps = 0;
    while (!allZeroes(grid)) {
      grid = computeNextStep(grid);
      totalSteps++;
    }
    return totalSteps;
  }

  private boolean allZeroes(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] != 0) return false;
      }
    }
    return true;
  }
}
