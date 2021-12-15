package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;

public class CoordinatesTest {
  @Test
  public void should_be_ordered_by_x_coordinates() {
    Coordinates a = new Coordinates(4, 9);
    Coordinates b = new Coordinates(5, 0);
    assertThat(a).isLessThan(b);
  }

  @Test
  public void should_be_ordered_by_y_coordinates_if_x_equal() {
    Coordinates a = new Coordinates(4, 9);
    Coordinates b = new Coordinates(4, 0);
    assertThat(a).isGreaterThan(b);
  }

  @Test
  public void should_compute_all_neighbours_of_central_coordinates() {
    Coordinates c = new Coordinates(1, 1);
    List<Coordinates> neighbours = c.allNeighbours(new Grid(3, 3));
    assertThat(neighbours)
        .containsOnly(
            new Coordinates(0, 0),
            new Coordinates(0, 1),
            new Coordinates(0, 2),
            new Coordinates(1, 0),
            new Coordinates(1, 2),
            new Coordinates(2, 0),
            new Coordinates(2, 1),
            new Coordinates(2, 2));
  }

  @Test
  public void should_skip_neighbours_of_top_left_corner_coordinates() {
    Coordinates c = new Coordinates(0, 0);
    List<Coordinates> neighbours = c.allNeighbours(new Grid(3, 3));
    assertThat(neighbours)
        .containsOnly(new Coordinates(0, 1), new Coordinates(1, 0), new Coordinates(1, 1));
  }

  @Test
  public void should_skip_neighbours_of_bottom_right_corner_coordinates() {
    Coordinates c = new Coordinates(2, 2);
    List<Coordinates> neighbours = c.allNeighbours(new Grid(3, 3));
    assertThat(neighbours)
        .containsOnly(new Coordinates(1, 1), new Coordinates(1, 2), new Coordinates(2, 1));
  }
}
