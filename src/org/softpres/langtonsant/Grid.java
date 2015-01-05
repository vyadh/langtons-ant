/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import javafx.scene.Group;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Represents the cells on a grid.
 */
public class Grid {

  private static final int DIMENSION = 100;
  private final Transforms transforms;
  private Cell[][] grid = new Cell[DIMENSION][DIMENSION];

  public Grid(Group group, Transforms transforms) {
    this.transforms = transforms;
    List<Cell> cells = transforms.createCells(DIMENSION).collect(toList());
    cells.forEach(cell -> grid[cell.x()][cell.y()] = cell);
    group.getChildren().addAll(cells);
  }

  public int size() {
    return DIMENSION;
  }

  public Cell cell(int x, int y) {
    return grid[x][y];
  }

  public void reset() {
    transforms.cells(grid).forEach(Cell::reset);
  }

}
