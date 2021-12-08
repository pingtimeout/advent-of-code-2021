package fr.pingtimeout.adventofcode2021;

import static java.util.stream.Collectors.toList;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day08 implements AdventDay {
  @Override
  public int partOne(List<String> input) {
    return (int)
        input.stream()
            .map(this::parseLine)
            .flatMap(entry -> entry.output().stream())
            .filter(this::isUniqueNumberOfSegments)
            .count();
  }

  record Entry(List<String> signals, List<String> output) {}

  private Entry parseLine(String line) {
    String[] parts = line.split(" \\| ");
    return new Entry(Arrays.asList(parts[0].split(" ")), Arrays.asList(parts[1].split(" ")));
  }

  private boolean isUniqueNumberOfSegments(String s) {
    return switch (s.length()) {
      case 2, 3, 4, 7 -> true;
      default -> false;
    };
  }

  @Override
  public long partTwo(List<String> input) {
    return input.stream().map(this::parseLine).mapToInt(this::decodeEntry).sum();
  }

  private int decodeEntry(Entry entry) {
    List<Set<Character>> signals = parseSignals(entry.signals);
    final Set<Character> zero, one, two, three, four, five, six, seven, eight, nine;
    one = removeFirst(signals, chars -> chars.size() == 2);
    seven = removeFirst(signals, chars -> chars.size() == 3);
    four = removeFirst(signals, chars -> chars.size() == 4);
    eight = removeFirst(signals, chars -> chars.size() == 7);
    // 9 is the only remaining character that fully intersects with 4
    nine = removeFirst(signals, chars -> chars.intersect(four).equals(four));
    // 3 is the only remaining character with 5 segments that has the two segments of 1 turned on
    three = removeFirst(signals, chars -> chars.size() == 5 && chars.intersect(one).equals(one));
    // 0 is the only remaining character with 6 segments that has the two segments of 1 turned on
    zero = removeFirst(signals, chars -> chars.size() == 6 && chars.intersect(one).equals(one));
    // 6 is the only remaining character with 6 segments
    six = removeFirst(signals, chars -> chars.size() == 6);
    // Five has only one segment difference with 6
    five = removeFirst(signals, chars -> chars.intersect(six).size() == 5);
    two = signals.get(0);

    Map<Set<Character>, String> decoder =
        Map.of(
            zero, "0", //
            one, "1", //
            two, "2", //
            three, "3", //
            four, "4", //
            five, "5", //
            six, "6", //
            seven, "7", //
            eight, "8", //
            nine, "9");

    return Integer.parseInt(
        parseSignals(entry.output).stream().map(decoder::get).collect(Collectors.joining()));
  }

  private List<Set<Character>> parseSignals(List<String> l) {
    return l.stream().map(s -> HashSet.ofAll(s.toCharArray())).collect(toList());
  }

  private Set<Character> removeFirst(List<Set<Character>> signals, Predicate<Set<Character>> p) {
    Set<Character> result = signals.stream().filter(p).findFirst().orElseThrow();
    signals.remove(result);
    return result;
  }
}
