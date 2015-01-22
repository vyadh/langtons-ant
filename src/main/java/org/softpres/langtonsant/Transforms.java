/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
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
    List<Ant> ants = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      ants.add(factory.get());
    }
    return ants;
  }

  /**
   * Create a stream of cells representing a two dimensional grid. Each cell
   * requires an x and y coordinate, but the stream can return cell in any order.
   */
  public Stream<Cell> createCells(int dimension) {
    List<Cell> cells = new ArrayList<>(dimension * dimension);
    for (int x = 0; x < dimension; x++) {
      for (int y = 0; y < dimension; y++) {
        cells.add(new Cell(x, y));
      }
    }
    return cells.stream();
  }

  /**
   * A "tape" of ant turn instructions specified by the user is used to create
   * a lifecycle that instructs an ant on what to do next based on the state of
   * the cell it is sitting. For example, given a tape of "LR" an ant would turn
   * left if it is sitting on a fresh cell (state number 0), or right if on a
   * cell in state number 0.
   */
  public LifeCycle createLifeCycle(String tape) {
    List<Function<Direction, Direction>> result = new ArrayList<>();
    for (char c : tape.toCharArray()) {
      result.add(turn(c));
    }
    return new LifeCycle(result);
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
    List<Cell> result = new ArrayList<>(grid.length * grid.length);
    for (Cell[] row : grid) {
      Collections.addAll(result, row);
    }
    return result.stream();
  }

}
