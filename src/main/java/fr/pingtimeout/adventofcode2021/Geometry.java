package fr.pingtimeout.adventofcode2021;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Direction {
  N(false),
  NE(true),
  E(false),
  SE(true),
  S(false),
  SW(true),
  W(false),
  NW(true);

  private final boolean isDiagonal;

  private Direction(boolean isDiagonal) {
    this.isDiagonal = isDiagonal;
  }

  public boolean isDiagonal() {
    return isDiagonal;
  }
}

record Coordinates(int x, int y) implements Comparable<Coordinates> {
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

  public static final Comparator<Coordinates> COMPARATOR =
      Comparator.comparing(Coordinates::x).thenComparing(Coordinates::y);

  @Override
  public int compareTo(Coordinates that) {
    return COMPARATOR.compare(this, that);
  }

  public Optional<Coordinates> neighbour(Grid grid, Direction d) {
    int maxX = grid.columns() - 1;
    int maxY = grid.rows() - 1;
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

  public Stream<Coordinates> neighbours(Grid grid, Direction... dirs) {
    return Arrays.stream(dirs).flatMap(d -> neighbour(grid, d).stream());
  }

  public List<Coordinates> allNeighbours(Grid grid) {
    return neighbours(grid, Direction.values()).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "[" + x + "," + y + ']';
  }
}

class Grid {
  static Grid from(List<String> input) {
    int rows = input.size();
    int columns = input.get(0).length();
    int[][] cells = new int[rows][columns];
    for (int row = 0; row < rows; row++) {
      String line = input.get(row);
      for (int col = 0; col < line.length(); col++) {
        cells[row][col] = line.charAt(col) - '0';
      }
    }
    return new Grid(cells);
  }

  static Grid copyOf(Grid that) {
    int[][] cells = new int[that.rows()][];
    for (int row = 0; row < that.rows(); row++) {
      cells[row] = that.cells[row].clone();
    }
    return new Grid(cells);
  }

  private final int[][] cells;

  Grid(int[][] cells) {
    this.cells = cells;
  }

  Grid(int rows, int columns) {
    this(new int[rows][columns]);
  }

  Grid(int rows, int columns, int initialValue) {
    this(rows, columns);
    for (int[] cell : cells) {
      Arrays.fill(cell, initialValue);
    }
  }

  void setValueAt(Coordinates coordinates, int value) {
    cells[coordinates.y()][coordinates.x()] = value;
  }

  int getValueAt(Coordinates coordinates) {
    return cells[coordinates.y()][coordinates.x()];
  }

  int rows() {
    return cells.length;
  }

  int columns() {
    return cells[0].length;
  }

  List<Coordinates> allCoordinates() {
    ArrayList<Coordinates> result = new ArrayList<>();
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        result.add(new Coordinates(x, y));
      }
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int[] row : cells) {
      for (int cell : row) {
        sb.append(cell);
      }
      sb.append('\n');
    }
    return sb.toString();
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
}
