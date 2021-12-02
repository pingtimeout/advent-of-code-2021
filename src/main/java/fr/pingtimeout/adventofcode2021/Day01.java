package fr.pingtimeout.adventofcode2021;

import io.vavr.collection.Stream;
import java.util.List;

public class Day01 implements AdventDay {
  public int partOne(List<String> nums) {
    return Stream.ofAll(nums)
        .map(Integer::parseInt)
        .sliding(2)
        .map(tuple -> tuple.get(1) > tuple.get(0) ? 1 : 0)
        .sum()
        .intValue();
  }

  public int partTwo(List<String> nums) {
    return Stream.ofAll(nums)
        .map(Integer::parseInt)
        .sliding(3)
        .map(objects -> objects.sum().intValue())
        .sliding(2)
        .map(tuple -> tuple.get(1) > tuple.get(0) ? 1 : 0)
        .sum()
        .intValue();
  }
}
