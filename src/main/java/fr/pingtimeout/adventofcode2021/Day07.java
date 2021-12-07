package fr.pingtimeout.adventofcode2021;

import java.util.*;
import java.util.stream.IntStream;

public class Day07 implements AdventDay {
  @Override
  public int partOne(List<String> input) {
    String[] line = input.get(0).split(",");
    int[] ints = Arrays.stream(line).mapToInt(Integer::parseInt).sorted().toArray();
    int median = medianOf(ints);
    int fuelConsumption = 0;
    for (int i = 0; i < ints.length; i++) {
      int position = ints[i];
      fuelConsumption += Math.abs(position - median);
    }
    return fuelConsumption;
  }

  int medianOf(int[] ints) {
    return (ints[ints.length / 2 - 1] + ints[ints.length / 2]) / 2;
  }

  @Override
  public long partTwo(List<String> input) {
    int[] ints =
        Arrays.stream(input.get(0).split(",")).mapToInt(Integer::parseInt).sorted().toArray();
    int minFuelConsumption =
        IntStream.range(ints[0], ints[ints.length - 1] + 1)
            .map(x -> exponentialCostToMove(ints, x))
            .min()
            .getAsInt();
    return minFuelConsumption;
  }

  private int exponentialCostToMove(int[] ints, int target) {
    int fuelConsumption = 0;
    for (int pos : ints) {
      int steps = Math.abs(pos - target);
      int cost = steps * (steps + 1) / 2;
      fuelConsumption += cost;
    }
    return fuelConsumption;
  }
}
