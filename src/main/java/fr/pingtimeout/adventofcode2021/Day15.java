package fr.pingtimeout.adventofcode2021;

import static fr.pingtimeout.adventofcode2021.Direction.*;

import java.util.*;

public class Day15 implements AdventDay {
  @Override
  public int partOne(List<String> input) {
    return diskjstra(Grid.from(input));
  }

  @Override
  public long partTwo(List<String> input) {
    return diskjstra(scaleGrid(input));
  }

  int diskjstra(Grid grid) {
    Coordinates start = new Coordinates(0, 0);
    Coordinates end = new Coordinates(grid.rows() - 1, grid.columns() - 1);
    Set<Coordinates> sptSet = new HashSet<>();
    Grid dist = new Grid(grid.rows(), grid.columns(), Integer.MAX_VALUE);
    dist.setValueAt(start, 0);
    Queue<Coordinates> queue = new PriorityQueue<>(Comparator.comparingInt(dist::getValueAt));
    queue.add(start);
    while (!queue.isEmpty()) {
      Coordinates cur = queue.poll();
      sptSet.add(cur);
      cur.neighbours(grid, N, S, E, W)
          // Keep cells that have not been finalized yet
          .filter(n -> !sptSet.contains(n))
          // Keep cells for which the updated distance is lower than the current one
          .filter(n -> dist.getValueAt(cur) + grid.getValueAt(n) < dist.getValueAt(n))
          .forEach(
              n -> {
                dist.setValueAt(n, dist.getValueAt(cur) + grid.getValueAt(n));
                queue.add(n);
              });
    }
    return dist.getValueAt(end);
  }

  Grid scaleGrid(List<String> input) {
    int repeats = 5;
    Grid smolGrid = Grid.from(input);
    int smolRows = smolGrid.rows();
    int smolColumns = smolGrid.columns();
    Grid scaledGrid = new Grid(smolRows * repeats, smolColumns * repeats);
    for (int row = 0; row < scaledGrid.rows(); row++) {
      for (int col = 0; col < scaledGrid.columns(); col++) {
        Coordinates smolCoord = new Coordinates(col % smolColumns, row % smolRows);
        Coordinates scaledCoord = new Coordinates(col, row);
        int delta = (row / smolRows) + (col / smolColumns);
        int valuePlusDelta = smolGrid.getValueAt(smolCoord) + delta;
        int wrappedValue = valuePlusDelta >= 10 ? valuePlusDelta - 9 : valuePlusDelta;
        scaledGrid.setValueAt(scaledCoord, wrappedValue);
      }
    }
    return scaledGrid;
  }
}
