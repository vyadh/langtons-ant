/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * The objective of this class is to provide a interface showing input and
 * outputs, and to get trainees to provide an implementation using streams and
 * lambdas to pass the supplied tests.
 * <p/>
 * No imperative loops allowed!
 */
public interface Transforms {

  /**
   * Create and return 'count' ant objects, using the supplied factory.
   */
  List<Ant> createAnts(int count, Supplier<Ant> factory);

  /**
   * Create a stream of cells representing a two dimensional grid. Each cell
   * requires an x and y coordinate, but the stream can return cell in any order.
   */
  Stream<Cell> createCells(int dimension);

  /**
   * Convert the supplied grid into a stream of cells in any order.
   */
  Stream<Cell> cells(Cell[][] grid);

}
