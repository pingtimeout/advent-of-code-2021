package fr.pingtimeout.adventofcode2021;

import io.vavr.collection.Stream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day03 implements AdventDay {
  @Override
  public int partOne(List<String> input) {
    String gammaRateStr = countMostCommonBits(input).map(n -> n > 0 ? '1' : '0').mkString();
    int gammaRate = Integer.parseInt(gammaRateStr, 2);
    int epsilonRate = flipAllBits(gammaRate, input.get(0).length());
    return gammaRate * epsilonRate;
  }

  private Stream<Integer> countMostCommonBits(List<String> input) {
    int numBitsPerLine = input.get(0).length();
    int[] bitCounts = Stream.ofAll(input).foldLeft(new int[numBitsPerLine], this::accumulateBits);
    return Stream.ofAll(bitCounts).map(Integer::signum);
  }

  private int flipAllBits(int x, int numBits) {
    int mask = (1 << numBits) - 1;
    return ~x & mask;
  }

  private int[] accumulateBits(int[] acc, String s) {
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') {
        acc[i]--;
      } else {
        acc[i]++;
      }
    }
    return acc;
  }

  @Override
  public long partTwo(List<String> input) {
    int oxygenGeneratorRating = Integer.parseInt(calculateOxygenGeneratorRating(input), 2);
    int co2ScrubberRating = Integer.parseInt(calculateCo2ScrubberRating(input), 2);
    return (long) oxygenGeneratorRating * co2ScrubberRating;
  }

  private String calculateOxygenGeneratorRating(List<String> input) {
    ArrayList<String> lines = new ArrayList<>(input);
    int filterColumn = 0;
    while (lines.size() > 1) {
      List<Character> mcb = countMostCommonBits(lines).map(n -> n >= 0 ? '1' : '0').toJavaList();
      for (Iterator<String> iterator = lines.iterator(); iterator.hasNext(); ) {
        if (iterator.next().charAt(filterColumn) != mcb.get(filterColumn)) {
          iterator.remove();
        }
      }
      filterColumn++;
    }
    return lines.get(0);
  }

  private String calculateCo2ScrubberRating(List<String> input) {
    ArrayList<String> lines = new ArrayList<>(input);
    int filterColumn = 0;
    while (lines.size() > 1) {
      List<Character> mcb = countMostCommonBits(lines).map(n -> n >= 0 ? '0' : '1').toJavaList();
      for (Iterator<String> iterator = lines.iterator(); iterator.hasNext(); ) {
        if (iterator.next().charAt(filterColumn) != mcb.get(filterColumn)) {
          iterator.remove();
        }
      }
      filterColumn++;
    }
    return lines.get(0);
  }
}
