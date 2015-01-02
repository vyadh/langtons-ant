/*
 * Copyright (c) 2015, Kieron Wilkinson
 */

package org.softpres.langtonsant;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * Represent the a cell on the grid.
 */
public class Cell extends Rectangle {

  private static final int CELL_SIZE = 4;

  private final int x;
  private final int y;
  private boolean white = false;

  public Cell(int x, int y) {
    super(x + (x * CELL_SIZE), y, CELL_SIZE, CELL_SIZE);
    this.x = x;
    this.y = y;
    setWidth(CELL_SIZE);
    setHeight(CELL_SIZE);
    setX(x + (x * CELL_SIZE));
    setY(y + (y * CELL_SIZE));
    update();
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }

  public void update() {
    setFill(white ? Color.BLACK : Color.WHITE);
  }

  public void flip() {
    white = !white;
    update();
  }

  public boolean isWhite() {
    return white;
  }

}
