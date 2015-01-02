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
public class Direction {

  private static final List<Direction> dirs =
        IntStream.rangeClosed(0, 3).mapToObj(Direction::new).collect(toList());

  public static final Direction NORTH = dirs.get(3);

  // East, South, West, North
  private static final int[] xs = new int[] { 1, 0,-1, 0 };
  private static final int[] ys = new int[] { 0, 1, 0,-1 };

  private final int i;

  private Direction(int i) {
    this.i = i;
  }

  public Direction left() {
    return dirs.get((i + dirs.size() - 1) % 4);
  }

  public Direction right() {
    return dirs.get((i + 1) % 4);
  }

  public int velX() {
    return xs[i];
  }

  public int velY() {
    return ys[i];
  }

  @Override
  public String toString() {
    return xs[i] + "," + ys[i];
  }

}
