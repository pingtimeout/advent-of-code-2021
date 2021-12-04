package fr.pingtimeout.adventofcode2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Day04 implements AdventDay {
  public static final String DRAWS_INTEGERS_SEPARATOR = ",";
  public static final String GRID_INTEGERS_SEPARATOR = " +";

  record Bingo(List<Grid> grids, Draws draws) {
    Optional<Grid> winner() {
      return grids.stream().filter(Grid::isComplete).findFirst();
    }

    public int draw(int index) {
      return draws.values[index];
    }

    public void markNumber(int draw) {
      grids.forEach(grid -> grid.markNumber(draw));
    }

    public void removeGrid(Grid grid) {
      grids.remove(grid);
    }
  }

  record Grid(int[][] cells) {
    public static final int MARKED_NUMBER = -1;

    boolean isComplete() {
      // Check rows first
      for (int row = 0; row < 5; row++) {
        boolean rowWins = true;
        for (int column = 0; column < 5; column++) {
          int cell = cells[row][column];
          if (cell != MARKED_NUMBER) {
            rowWins = false;
            break;
          }
        }
        if (rowWins) {
          return true;
        }
      }
      // Then check columns
      for (int column = 0; column < 5; column++) {
        boolean columnWins = true;
        for (int row = 0; row < 5; row++) {
          int cell = cells[row][column];
          if (cell != MARKED_NUMBER) {
            columnWins = false;
            break;
          }
        }
        if (columnWins) {
          return true;
        }
      }
      return false;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Grid grid = (Grid) o;
      return Arrays.deepEquals(cells, grid.cells);
    }

    @Override
    public int hashCode() {
      return Arrays.deepHashCode(cells);
    }

    @Override
    public String toString() {
      return "Grid{" + "cells=" + Arrays.deepToString(cells) + '}';
    }

    public void markNumber(int draw) {
      for (int row = 0; row < cells.length; row++) {
        for (int column = 0; column < cells[row].length; column++) {
          if (cells[row][column] == draw) {
            cells[row][column] = MARKED_NUMBER;
            return;
          }
        }
      }
    }

    public int sumUnmarkedNumers() {
      return Arrays.stream(cells)
          .flatMapToInt(Arrays::stream)
          .filter(x -> x != MARKED_NUMBER)
          .sum();
    }
  }

  record Draws(int[] values) {}

  @Override
  public int partOne(List<String> input) {
    return playUntilWin(parseBingoInput(input), 0);
  }

  private int playUntilWin(Bingo bingo, int drawIndex) {
    Optional<Grid> winner = Optional.empty();
    int draw = 0;
    while (winner.isEmpty()) {
      draw = bingo.draw(drawIndex++);
      bingo.markNumber(draw);
      winner = bingo.winner();
    }
    return winner.get().sumUnmarkedNumers() * draw;
  }

  @Override
  public long partTwo(List<String> input) {
    // First, eliminate all boards as soon as they win, until only one remains
    Bingo bingo = parseBingoInput(input);
    int drawIndex = 0;
    int draw;
    while (bingo.grids().size() > 1) {
      draw = bingo.draw(drawIndex++);
      bingo.markNumber(draw);
      Optional<Grid> winner = bingo.winner();
      while (winner.isPresent()) {
        bingo.removeGrid(winner.get());
        winner = bingo.winner();
      }
    }
    // Then, play normally until that board wins and compute its final score
    return playUntilWin(bingo, drawIndex);
  }

  public Bingo parseBingoInput(List<String> lines) {
    String first = lines.get(0);
    Draws draws = new Draws(parseIntLine(first, DRAWS_INTEGERS_SEPARATOR));
    // Boards are always 5x5
    // The first two lines are for the draws.
    // Each board takes 6 lines (5 of data + empty line)
    // Hence if the file has n lines, it has (n-2)/6 boards
    int numGrids = lines.size() / 6;
    List<Grid> grids = new ArrayList<>(numGrids);
    for (int grid = 0; grid < numGrids; grid++) {
      grids.add(
          new Grid(
              new int[][] {
                parseIntLine(lines.get(2 + 6 * grid), GRID_INTEGERS_SEPARATOR),
                parseIntLine(lines.get(2 + 6 * grid + 1), GRID_INTEGERS_SEPARATOR),
                parseIntLine(lines.get(2 + 6 * grid + 2), GRID_INTEGERS_SEPARATOR),
                parseIntLine(lines.get(2 + 6 * grid + 3), GRID_INTEGERS_SEPARATOR),
                parseIntLine(lines.get(2 + 6 * grid + 4), GRID_INTEGERS_SEPARATOR),
              }));
    }
    return new Bingo(grids, draws);
  }

  private int[] parseIntLine(String line, String regex) {
    return Arrays.stream(line.trim().split(regex)).mapToInt(Integer::parseInt).toArray();
  }
}
