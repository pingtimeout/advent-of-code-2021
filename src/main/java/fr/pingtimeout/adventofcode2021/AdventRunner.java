package fr.pingtimeout.adventofcode2021;

import java.util.List;

public class AdventRunner {
  public static void main(String[] args) throws Exception {
    Class<?> dayClass = Class.forName("fr.pingtimeout.adventofcode2021." + args[0]);
    AdventDay day = (AdventDay) dayClass.newInstance();
    String inputFileRelativePath = "/" + args[0].toLowerCase() + "/input.txt";
    List<String> input = new Parser().readFileAsStrings(inputFileRelativePath);
    System.out.println(day.partOne(input));
    System.out.println(day.partTwo(input));
  }
}
