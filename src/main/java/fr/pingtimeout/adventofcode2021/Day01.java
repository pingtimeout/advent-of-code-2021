package fr.pingtimeout.adventofcode2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day01 {
    public int countIncreases(List<Integer> nums) {
        int increases = 0;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) > nums.get(i-1)) {
                increases++;
            }
        }
        return increases;
    }

    public int countThreeMeasurementsIncreases(List<Integer> nums) {
        int increases = 0;
        for (int i = 3; i < nums.size(); i++) {
            int prevSum = nums.get(i-3) + nums.get(i-2) + nums.get(i-1);
            int nextSum = nums.get(i-2) + nums.get(i-1) + nums.get(i);
            if (nextSum > prevSum) {
                increases++;
            }
        }
        return increases;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Day01 day01 = new Day01();
        List<Integer> nums = new Parser().readFileAsInts("/day01/input.txt");
        System.out.println(day01.countIncreases(nums));
        System.out.println(day01.countThreeMeasurementsIncreases(nums));
    }
}
