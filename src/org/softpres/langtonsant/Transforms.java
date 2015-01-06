/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.Arrays;
import java.util.List;
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
   */
  public List<Ant> createAnts(int count, Supplier<Ant> factory) {
    return Stream.generate(factory).limit(count).collect(Collectors.toList());
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
  }

  /**
   * Convert the supplied grid into a stream of cells in any order.
   */
  public Stream<Cell> cells(Cell[][] grid) {
    return Stream.of(grid).flatMap(Arrays::stream);
  }

}
