/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.function.Function;

/**
 * Represents a change in direction.
 */
public enum Turn implements Function<Direction, Direction> {

  LEFT(Direction::turnLeft),
  RIGHT(Direction::turnRight);

  private final Function<Direction, Direction> turn;

  Turn(Function<Direction, Direction> turn) {
    this.turn = turn;
  }

  @Override
  public Direction apply(Direction direction) {
    return turn.apply(direction);
  }

  public static Turn of(char c) {
    switch (c) {
      case 'L': return LEFT;
      case 'R': return RIGHT;
      default: throw new IllegalArgumentException("Unsupported turn: " + c);
    }
  }

}
