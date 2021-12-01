package fr.pingtimeout.adventofcode2021;

import java.io.IOException;
import java.net.URISyntaxException;

public class Day01 {
    public int countIncreases(int[] nums) {
        int increases = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                increases++;
            }
        }
        return increases;
    }

    public int countThreeMeasurementsIncreases(int[] nums) {
        int increases = 0;
        for (int i = 3; i < nums.length; i++) {
            int prevSum = nums[i - 3] + nums[i - 2] + nums[i - 1];
            int nextSum = nums[i - 2] + nums[i - 1] + nums[i];
            if (nextSum > prevSum) {
                increases++;
            }
        }
        return increases;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Day01 day01 = new Day01();
        int[] nums = new Parser().readFileAsInts("/day01/input.txt");
        System.out.println(day01.countIncreases(nums));
        System.out.println(day01.countThreeMeasurementsIncreases(nums));
    }
}
