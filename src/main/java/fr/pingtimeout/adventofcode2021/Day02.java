package fr.pingtimeout.adventofcode2021;

import io.vavr.collection.Stream;

import java.util.List;

public class Day02 implements AdventDay {
    @Override
    public int partOne(List<String> input) {
        SimpleLocation finalLocation = Stream.ofAll(input)
                .map(this::parseCommand)
                .foldLeft(SimpleLocation.ZERO, SimpleLocation::applyCommand);
        return finalLocation.position * finalLocation.depth;
    }

    @Override
    public int partTwo(List<String> input) {
        AimedLocation finalLocation = Stream.ofAll(input)
                .map(this::parseCommand)
                .foldLeft(AimedLocation.ZERO, AimedLocation::applyCommand);
        return finalLocation.position * finalLocation.depth;
    }

    public Command parseCommand(String direction) {
        String[] tokens = direction.split(" ");
        return new Command(Direction.valueOf(tokens[0]), Integer.parseInt(tokens[1]));
    }

    public record Command(Direction direction, int units) {
    }

    public enum Direction {
        forward, up, down;
    }

    public record SimpleLocation(int position, int depth) {
        public static SimpleLocation ZERO = new SimpleLocation(0, 0);

        public static SimpleLocation applyCommand(SimpleLocation l, Command c) {
            return switch (c.direction) {
                case forward -> new SimpleLocation(l.position + c.units, l.depth);
                case up -> new SimpleLocation(l.position, l.depth - c.units);
                case down -> new SimpleLocation(l.position, l.depth + c.units);
            };
        }
    }

    public record AimedLocation(int position, int depth, int aim) {
        public static AimedLocation ZERO = new AimedLocation(0, 0, 0);

        public static AimedLocation applyCommand(AimedLocation l, Command c) {
            return switch (c.direction) {
                case forward -> new AimedLocation(l.position + c.units, l.depth + c.units * l.aim, l.aim);
                case up -> new AimedLocation(l.position, l.depth, l.aim - c.units);
                case down -> new AimedLocation(l.position, l.depth, l.aim + c.units);
            };
        }
    }
}
