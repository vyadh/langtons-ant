/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import javafx.scene.Group;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Represents the cells on a grid.
 */
public class Grid {

  private static final int SIZE = 100;
  private Cell[][] grid = new Cell[SIZE][SIZE];

  public Grid(Group group) {
    List<Cell> cells = createCells().collect(toList());
    cells.forEach(cell -> grid[cell.x()][cell.y()] = cell);
    group.getChildren().addAll(cells);
  }

  private Stream<Cell> createCells() {
    // I'm forcing myself to use a stream here, but without for combinations or a zip method
    // it's not very nice, and the imperative for loop would probably have been nicer
    return IntStream.range(0, SIZE).boxed().flatMap(
          x -> IntStream.range(0, SIZE).mapToObj(y -> new Cell(x, y)));
  }

  public Stream<Cell> cells() {
    return Stream.of(grid).flatMap(Arrays::stream);
  }

  public int size() {
    return SIZE;
  }

  public Cell cell(int x, int y) {
    return grid[x][y];
  }

}
