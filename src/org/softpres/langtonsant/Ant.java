/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

/**
 * Represents an Langton's Ant.
 */
public class Ant {

  private final Grid grid;
  private LifeCycle lifeCycle;
  private Direction direction;
  private int x;
  private int y;

  public Ant(Grid grid, LifeCycle lifeCycle) {
    this.grid = grid;
    this.lifeCycle = lifeCycle;

    direction = Direction.NORTH;
    placeInCentre(grid);
  }

  private void placeInCentre(Grid grid) {
    x = y = grid.size() / 2;
    occupy();
  }

  private void occupy() {
    grid.cell(x, y).occupy();
  }

  public void tick() {
    Cell cell = grid.cell(x, y);

    makeNextTurn(cell);
    transitionCell(cell);
    updatePosition();
    occupy();
  }

  private void makeNextTurn(Cell cell) {
    direction = lifeCycle.direction(cell.state(), direction);
  }

  private void transitionCell(Cell cell) {
    int state = cell.state();
    cell.transition(lifeCycle.colour(state), lifeCycle.next(state));
  }

  private void updatePosition() {
    x += direction.velX();
    y += direction.velY();

    wrapScreenIfNecessary();
  }

  private void wrapScreenIfNecessary() {
    if (x < 0) x = grid.size() - 1;
    if (y < 0) y = grid.size() - 1;
    if (x >= grid.size()) x = 0;
    if (y >= grid.size()) y = 0;
  }

}
