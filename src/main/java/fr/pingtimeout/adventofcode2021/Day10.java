package fr.pingtimeout.adventofcode2021;

import java.util.*;

public class Day10 implements AdventDay {
  public static final Set<Character> OPEN_CHARS = Set.of('{', '(', '[', '<');
  public static final Map<Character, Character> CLOSE_CHARS =
      Map.of(
          '(', ')',
          '[', ']',
          '{', '}',
          '<', '>');
  public static final Map<Character, Integer> ERROR_SCORE =
      Map.of(
          ')', 3,
          ']', 57,
          '}', 1197,
          '>', 25137);
  public static final Map<Character, Integer> COMPLETION_SCORE =
      Map.of(
          ')', 1,
          ']', 2,
          '}', 3,
          '>', 4);

  @Override
  public int partOne(List<String> input) {
    return input.stream().mapToInt(this::scoreOf).sum();
  }

  private int scoreOf(String line) {
    Stack<Character> chunks = new Stack<>();
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if (OPEN_CHARS.contains(c)) {
        chunks.push(c);
      } else {
        char expected = CLOSE_CHARS.get(chunks.pop());
        if (c != expected) {
          return ERROR_SCORE.get(c);
        }
      }
    }
    return 0;
  }

  @Override
  public long partTwo(List<String> input) {
    long[] scores =
        input.stream()
            .filter(line -> scoreOf(line) == 0)
            .mapToLong(this::completionScoreOf)
            .sorted()
            .toArray();
    return scores[scores.length / 2];
  }

  private long completionScoreOf(String line) {
    Stack<Character> chunks = new Stack<>();
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if (OPEN_CHARS.contains(c)) {
        chunks.push(c);
      } else {
        chunks.pop();
      }
    }
    long score = 0;
    while (!chunks.empty()) {
      score = score * 5 + COMPLETION_SCORE.get(CLOSE_CHARS.get(chunks.pop()));
    }
    return score;
  }
}
