package fr.pingtimeout.adventofcode2021;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class Day01Test {
    @Test
    public void should_find_increases() throws URISyntaxException, IOException {
        int[] ints = new Parser().readFileAsInts("/day01/input.txt");
        int increases = new Day01().countIncreases(ints);
        assertThat(increases).isEqualTo(7);
    }

    @Test
    public void should_find_three_measurements_increases() throws URISyntaxException, IOException {
        int[] ints = new Parser().readFileAsInts("/day01/input.txt");
        int increases = new Day01().countThreeMeasurementsIncreases(ints);
        assertThat(increases).isEqualTo(5);
    }
}