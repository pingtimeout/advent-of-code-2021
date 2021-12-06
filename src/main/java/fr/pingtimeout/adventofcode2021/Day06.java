package fr.pingtimeout.adventofcode2021;

import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day06 implements AdventDay {
  @Override
  public int partOne(List<String> input) {
    return (int) numberOfFishAfterDays(input, 80);
  }

  @Override
  public long partTwo(List<String> input) {
    return numberOfFishAfterDays(input, 256);
  }

  private long numberOfFishAfterDays(List<String> input, int days) {
    Map<Integer, Long> ageFrequencies = parseInput(input.get(0));
    for (int i = 0; i < days; i++) {
      ageFrequencies = calculateNextFrequencies(ageFrequencies);
    }
    int totalNumFish = 0;
    for (long n : ageFrequencies.values()) {
      totalNumFish += n;
    }
    return totalNumFish;
  }

  Map<Integer, Long> calculateNextFrequencies(Map<Integer, Long> ageFrequencies) {
    Map<Integer, Long> next = new HashMap<>();
    for (int age : ageFrequencies.keySet()) {
      if (age == 0) continue;
      next.put(age - 1, ageFrequencies.get(age));
    }
    if (ageFrequencies.containsKey(0)) {
      Long n = ageFrequencies.get(0);
      next.put(6, (long) n + next.getOrDefault(6, 0L)); // All fish that reproduced go back to 6
      next.put(8, n); // All new fish start their counter at 8
    }
    return next;
  }

  Map<Integer, Long> parseInput(String s) {
    return Arrays.stream(s.split(","))
        .map(Integer::parseInt)
        .collect(groupingBy(Function.identity(), Collectors.counting()));
  }
}
