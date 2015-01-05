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
 * Instructor-provided implementations of the transforms.
 */
public class DefaultTransforms implements Transforms {

  @Override
  public List<Ant> createAnts(int count, Supplier<Ant> factory) {
    return Stream.generate(factory).limit(3).collect(Collectors.toList());
  }

  @Override
  public Stream<Cell> createCells(int dimension) {
    // I'm forcing myself to use a stream here, but without for combinations or a zip method
    // it's not very nice, and the imperative for loop would probably have been nicer
    return IntStream.range(0, dimension).boxed().flatMap(
          x -> IntStream.range(0, dimension).mapToObj(y -> new Cell(x, y)));
  }

  @Override
  public Stream<Cell> cells(Cell[][] grid) {
    return Stream.of(grid).flatMap(Arrays::stream);
  }

}
