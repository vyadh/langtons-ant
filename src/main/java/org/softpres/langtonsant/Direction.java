/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * The direction to move.
 */
public enum Direction {

  NORTH( 0, -1),
  EAST ( 1,  0),
  SOUTH( 0,  1),
  WEST (-1,  0);

  private static final int directions = values().length;

  private static final List<Direction> cache =
        IntStream.rangeClosed(0, 3)
              .mapToObj(i -> Direction.values()[i])
              .collect(toList());

  private final int x;
  private final int y;

  private Direction(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Direction turnLeft() {
    return cache.get((ordinal() + cache.size() - 1) % directions);
  }

  public Direction turnRight() {
    return cache.get((ordinal() + 1) % directions);
  }

  public int velX() {
    return x;
  }

  public int velY() {
    return y;
  }

  @Override
  public String toString() {
    return x + "," + y;
  }

}
