/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream processing functions intended to drive the simulation.
 * <p/>
 * The objective of this class is to provide a interface showing input and
 * outputs, and to get trainees to provide an implementation using streams and
 * lambdas to pass the supplied tests.
 * <p/>
 * No imperative loops allowed! :)
 */
public class Transforms {

  /**
   * Create and return 'count' ant objects, using the supplied factory.
   * This is used to create the number of simulated ants specified by the user.
   */
  public List<Ant> createAnts(int count, Supplier<Ant> factory) {
    return Stream.generate(factory).limit(count).collect(Collectors.toList());

//    List<Ant> ants = new ArrayList<>(count);
//    for (int i = 0; i < count; i++) {
//      ants.add(factory.get());
//    }
//    return ants;
  }

  /**
   * Create a stream of cells representing a two dimensional grid. Each cell
   * requires an x and y coordinate, but the stream can return cell in any order.
   */
  public Stream<Cell> createCells(int dimension) {
    // I'm forcing myself to use a stream here, but without for combinations or a zip method
    // it's not very nice, and the imperative for loop would probably have been nicer
    return IntStream.range(0, dimension).boxed().flatMap(
          x -> IntStream.range(0, dimension).mapToObj(y -> new Cell(x, y)));

//    List<Cell> cells = new ArrayList<>(dimension * dimension);
//    for (int x = 0; x < dimension; x++) {
//      for (int y = 0; y < dimension; y++) {
//        cells.add(new Cell(x, y));
//      }
//    }
//    return cells.stream();
  }

  /**
   * A "tape" of ant turn instructions specified by the user is used to create
   * a lifecycle that instructs an ant on what to do next based on the state of
   * the cell it is sitting. For example, given a tape of "LR" an ant would turn
   * left if it is sitting on a fresh cell (state number 0), or right if on a
   * cell in state number 0.
   */
  public LifeCycle createLifeCycle(String tape) {
    // Scala:
    // new LifeCycle(tape.map(_ match {
    //   case 'L' => (d: Direction) => d.turnLeft
    //   case 'R' => (d: Direction) => d.turnRight
    // })

    return new LifeCycle(tape.chars()
          .mapToObj(c -> (char)c)
          .map(this::turn)
          .collect(Collectors.toList())
    );

//    List<Function<Direction, Direction>> result = new ArrayList<>();
//    for (char c : tape.toCharArray()) {
//      result.add(turn(c));
//    }
//    new LifeCycle(result);
  }

  private Function<Direction, Direction> turn(char c) {
    switch (c) {
      case 'L': return Direction::turnLeft;
      case 'R': return Direction::turnRight;
      default: throw new IllegalArgumentException("Unsupported turn: " + c);
    }
  }

  /**
   * Convert the supplied grid into a stream of cells in any order.
   * This is provided for when we need to iterate over all cells, currently
   * this is just when the reset button is pressed to reset cell state.
   */
  public Stream<Cell> cells(Cell[][] grid) {
    return Stream.of(grid).flatMap(Arrays::stream);

//    List<Cell> result = new ArrayList<>(grid.length * grid.length);
//    for (Cell[] row : grid) {
//      Collections.addAll(result, row);
//    }
//    return result.stream();
  }

}
