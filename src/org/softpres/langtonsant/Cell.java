/*
 * Copyright (c) 2015, Kieron Wilkinson
 */

package org.softpres.langtonsant;

import javafx.css.PseudoClass;
import javafx.scene.shape.Rectangle;

/**
 * Represent the a cell on the grid.
 */
public class Cell extends Rectangle {

  private static final int CELL_SIZE = 4;

  private static final PseudoClass PSEUDO_CLASS_VISITED = PseudoClass.getPseudoClass("visited");
  private static final PseudoClass PSEUDO_CLASS_OCCUPIED = PseudoClass.getPseudoClass("occupied");

  private final int x;
  private final int y;
  private boolean visited = false;

  public Cell(int x, int y) {
    super(x + (x * CELL_SIZE), y, CELL_SIZE, CELL_SIZE);
    this.x = x;
    this.y = y;
    getStyleClass().add("cell");
    setWidth(CELL_SIZE);
    setHeight(CELL_SIZE);
    setX(x + (x * CELL_SIZE));
    setY(y + (y * CELL_SIZE));
    pseudoClassStateChanged(PSEUDO_CLASS_OCCUPIED, false);
    pseudoClassStateChanged(PSEUDO_CLASS_VISITED, false);
    update();
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }

  public void update() {
    pseudoClassStateChanged(PSEUDO_CLASS_VISITED, visited);
  }

  public void flip() {
    visited = !visited;
    pseudoClassStateChanged(PSEUDO_CLASS_OCCUPIED, false);
    update();
  }

  public boolean isVisited() {
    return visited;
  }

  public void occupy() {
    pseudoClassStateChanged(PSEUDO_CLASS_OCCUPIED, true);
  }

}
