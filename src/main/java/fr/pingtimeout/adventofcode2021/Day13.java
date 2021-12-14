package fr.pingtimeout.adventofcode2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 implements AdventDay {
  record Grid(char[][] points) {
    @Override
    public String toString() {
      StringBuilder result = new StringBuilder();
      for (int i = 0; i < points.length; i++) {
        for (int j = 0; j < points[i].length; j++) {
          result.append(points[i][j]);
        }
        result.append('\n');
      }
      return result.toString();
    }
  }

  @Override
  public int partOne(List<String> input) {
    char[][] grid = parseGrid(input);
    //    System.out.println(new Grid(grid));
    //    System.out.println();

    List<String> foldInstructions =
        input.stream().filter(l -> l.contains("fold")).collect(Collectors.toList());
    char[][] newGrid = applyFold(grid, foldInstructions.get(0));
    //    System.out.println(new Grid(newGrid));
    //    System.out.println();

    int dots = 0;
    for (int i = 0; i < newGrid.length; i++) {
      for (int j = 0; j < newGrid[i].length; j++) {
        if (newGrid[i][j] == '#') {
          dots++;
        }
      }
    }
    return dots;
  }

  private char[][] parseGrid(List<String> input) {
    List<Coordinates> coordinates =
        input.stream()
            .filter(line -> line.contains(","))
            .map(this::parseCoordinates)
            .collect(Collectors.toList());
    int maxX = coordinates.stream().mapToInt(Coordinates::x).max().getAsInt() + 1;
    int maxY = coordinates.stream().mapToInt(Coordinates::y).max().getAsInt() + 1;
    char[][] grid = newGrid(maxY, maxX);
    coordinates.forEach(c -> grid[c.y()][c.x()] = '#');
    return grid;
  }

  private Coordinates parseCoordinates(String line) {
    String[] splits = line.split(",");
    return new Coordinates(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]));
  }

  char[][] applyFold(char[][] grid, String foldInstruction) {
    char[][] newGrid;
    int axis = Integer.parseInt(foldInstruction.split("=")[1]);
    if (foldInstruction.startsWith("fold along x=")) {
      newGrid = newSubGrid(grid.length, axis, grid);
      for (int spread = 1; spread <= axis; spread++) {
        for (int y = 0; y < newGrid.length; y++) {
          if (grid[y][axis + spread] == '#') {
            newGrid[y][axis - spread] = '#';
          }
        }
      }
    } else {
      newGrid = newSubGrid(axis, grid[0].length, grid);
      for (int spread = 1; spread <= axis; spread++) {
        for (int x = 0; x < newGrid[axis - spread].length; x++) {
          if (grid[axis + spread][x] == '#') {
            newGrid[axis - spread][x] = '#';
          }
        }
      }
    }
    return newGrid;
  }

  private char[][] newGrid(int maxY, int maxX) {
    char[][] grid = new char[maxY][maxX];
    for (char[] row : grid) {
      Arrays.fill(row, '.');
    }
    return grid;
  }

  private char[][] newSubGrid(int maxY, int maxX, char[][] fullGrid) {
    char[][] grid = new char[maxY][maxX];
    for (int i = 0, gridLength = grid.length; i < gridLength; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = fullGrid[i][j];
      }
    }
    return grid;
  }

  @Override
  public long partTwo(List<String> input) {
    char[][] grid = parseGrid(input);

    List<String> foldInstructions =
        input.stream().filter(l -> l.contains("fold")).collect(Collectors.toList());
    for (String foldInstruction : foldInstructions) {
      grid = applyFold(grid, foldInstruction);
    }
    System.out.println(new Grid(grid));
    System.out.println();

    return 0;
  }
}
