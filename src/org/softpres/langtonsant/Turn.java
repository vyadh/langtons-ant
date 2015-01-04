/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.function.Function;

/**
 * Represents a change in direction.
 */
public class Turn {

  public static Function<Direction, Direction> of(char c) {
    switch (c) {
      case 'L': return Direction::turnLeft;
      case 'R': return Direction::turnRight;
      default: throw new IllegalArgumentException("Unsupported turn: " + c);
    }
  }

}
