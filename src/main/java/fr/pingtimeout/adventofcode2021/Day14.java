package fr.pingtimeout.adventofcode2021;

import java.util.*;
import java.util.stream.Collectors;

public class Day14 implements AdventDay {
  @Override
  public int partOne(List<String> input) {
    Map<String, Long> state = parseState(input.get(0));
    Map<String, Character> insertionRules = parseInsertionRules(input.subList(2, input.size()));
    for (int i = 0; i < 10; i++) {
      state = nextState(state, insertionRules);
    }
    return (int) countPolymerDiff(state);
  }

  Map<String, Long> parseState(String line) {
    Map<String, Long> result = new HashMap<>();
    for (int i = 1; i < line.length(); i++) {
      String pair = line.substring(i - 1, i + 1);
      result.put(pair, result.getOrDefault(pair, 0L) + 1);
    }
    return result;
  }

  Map<String, Character> parseInsertionRules(List<String> lines) {
    return lines.stream()
        .map(l -> l.split(" -> "))
        .collect(Collectors.toMap(splits -> splits[0], splits -> splits[1].charAt(0)));
  }

  Map<String, Long> nextState(Map<String, Long> cur, Map<String, Character> rules) {
    Map<String, Long> result = new HashMap<>();
    for (Map.Entry<String, Long> entry : cur.entrySet()) {
      String pair = entry.getKey();
      Long count = entry.getValue();
      List<String> newPairs = applyRule(pair, rules);
      result.put(newPairs.get(0), result.getOrDefault(newPairs.get(0), 0L) + count);
      result.put(newPairs.get(1), result.getOrDefault(newPairs.get(1), 0L) + count);
    }
    return result;
  }

  private List<String> applyRule(String pair, Map<String, Character> rules) {
    if (rules.containsKey(pair)) {
      Character newChar = rules.get(pair);
      return List.of("" + pair.charAt(0) + newChar, "" + newChar + pair.charAt(1));
    } else {
      return List.of(pair);
    }
  }

   long countPolymerDiff(Map<String, Long> state) {
     System.out.println(state);
    Map<Character, Long> counts = new HashMap<>();
    for (Map.Entry<String, Long> entry : state.entrySet()) {
      String pair = entry.getKey();
      Long count = entry.getValue();
      counts.put(pair.charAt(0), counts.getOrDefault(pair.charAt(0), 0L) + count);
    }
     System.out.println(counts);
    long mostCommon = Long.MIN_VALUE, leastCommon = Long.MAX_VALUE;
    for (long value : counts.values()) {
      mostCommon = Math.max(mostCommon, value);
      leastCommon = Math.min(leastCommon, value);
    }
    return mostCommon - leastCommon;
  }

  @Override
  public long partTwo(List<String> input) {
    Map<String, Long> state = parseState(input.get(0));
    Map<String, Character> insertionRules = parseInsertionRules(input.subList(2, input.size()));
    for (int i = 0; i < 40; i++) {
      state = nextState(state, insertionRules);
    }
    return countPolymerDiff(state);
  }
}
