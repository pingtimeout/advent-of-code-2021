package fr.pingtimeout.adventofcode2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Day02 {
    public enum Direction {
        forward, up, down;
    }

    public record Command(Direction direction, int units) {
    }

    public Command parseCommand(String direction) {
        String[] tokens = direction.split(" ");
        return new Command(Direction.valueOf(tokens[0]), Integer.parseInt(tokens[1]));
    }

    public int calculateMultipleAfter(List<Command> commands) {
        int position = 0, depth = 0;
        for (Command command : commands) {
            switch (command.direction) {
                case forward -> position += command.units;
                case down -> depth += command.units;
                case up -> depth -= command.units;
            }
        }
        return position * depth;
    }

    public int calculateAimedMultipleAfter(List<Command> commands) {
        int position = 0, depth = 0, aim = 0;
        for (Command command : commands) {
            switch (command.direction) {
                case forward -> {
                    position += command.units;
                    depth += command.units * aim;
                }
                case down -> aim += command.units;
                case up -> aim -= command.units;
            }
        }
        return position * depth;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Day02 day02 = new Day02();
        List<Command> commands = new Parser().readFileAs("/day02/input.txt", day02::parseCommand);
        System.out.println(day02.calculateMultipleAfter(commands));
        System.out.println(day02.calculateAimedMultipleAfter(commands));
    }
}
