package fr.pingtimeout.adventofcode2021;

import static java.util.Collections.emptyList;

import io.vavr.collection.HashSet;
import io.vavr.collection.Vector;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 implements AdventDay {

  record Node(String name) {
    public boolean isEnd() {
      return name.equals("end");
    }

    public boolean isSmall() {
      return name.equals(name.toLowerCase());
    }

    @Override
    public String toString() {
      return name;
    }
  }

  record Edge(Node from, Node to) {}

  record Path(Vector<Node> edges) {
    Path prependWith(Node node) {
      return new Path(edges.prepend(node));
    }
  }

  @Override
  public int partOne(List<String> input) {
    Node start = new Node("start");
    return (int) pathsToEnd(start, parsePaths(input), HashSet.of(start), false).count();
  }

  Stream<Path> pathsToEnd(
      Node current,
      Map<Node, List<Edge>> paths,
      HashSet<Node> forbidden,
      boolean canVisitSmallCaveTwice) {
    if (current.isEnd()) {
      return Stream.of(new Path(Vector.of(current)));
    } else {
      return paths.getOrDefault(current, emptyList()).stream()
          // List of edges from current to something
          .filter(edge -> !forbidden.contains(edge.to))
          // List of edges not already visited or multi-visitable
          .flatMap(
              edge -> {
                HashSet<Node> newForbidden = edge.to.isSmall() ? forbidden.add(edge.to) : forbidden;
                Stream<Path> p1 =
                    pathsToEnd(edge.to, paths, newForbidden, canVisitSmallCaveTwice)
                        .map(path -> path.prependWith(edge.from));
                Stream<Path> p2;
                if (canVisitSmallCaveTwice && edge.to.isSmall()) {
                  // Handle case where we visit this node twice
                  p2 =
                      pathsToEnd(edge.to, paths, forbidden, false)
                          .map(path -> path.prependWith(edge.from));
                } else {
                  p2 = Stream.empty();
                }
                return Stream.concat(p1, p2);
              });
    }
  }

  Map<Node, List<Edge>> parsePaths(List<String> input) {
    return input.stream()
        .flatMap(edgeStr -> bidirectionalPathsFrom(edgeStr).stream())
        .collect(Collectors.groupingBy(Edge::from));
  }

  List<Edge> bidirectionalPathsFrom(String s) {
    String[] splits = s.split("-");
    Node from = new Node(splits[0]);
    Node to = new Node(splits[1]);
    return List.of(new Edge(from, to), new Edge(to, from));
  }

  @Override
  public long partTwo(List<String> input) {
    Node start = new Node("start");
    return pathsToEnd(start, parsePaths(input), HashSet.of(start), true)
        .collect(Collectors.toSet())
        .size();
  }
}
