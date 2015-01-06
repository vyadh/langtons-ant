/*
 * Copyright (c) 2015, Kieron Wilkinson
 */

package org.softpres.langtonsant;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represent the a cell on the grid.
 */
public class Cell extends Rectangle {

  private static final int CELL_SIZE = 4;

  private final int x;
  private final int y;
  private int state;

  public Cell(int x, int y) {
    super(x + (x * CELL_SIZE), y, CELL_SIZE, CELL_SIZE);
    this.x = x;
    this.y = y;
    setWidth(CELL_SIZE);
    setHeight(CELL_SIZE);
    setX(x + (x * CELL_SIZE));
    setY(y + (y * CELL_SIZE));
    reset();
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }

  private void update(Color colour) {
    getStyleClass().clear();
    setFill(colour);
  }

  public void transition(Color colour, int state) {
    this.state = state;
    update(colour);
  }

  public int state() {
    return state;
  }

  public void occupy() {
    getStyleClass().setAll("cell", "occupied");
  }

  public void reset() {
    state = 0;
    getStyleClass().setAll("cell", "unvisited");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cell cell = (Cell) o;
    return state == cell.state && x == cell.x && y == cell.y;

  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    result = 31 * result + state;
    return result;
  }

}
