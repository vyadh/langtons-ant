/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * In Langton's Ant, this is just two states, (0) left and (1) right.
 */
public class LifeCycle {

  public static final LifeCycle LANGTON = new LifeCycle();

  private LifeCycle() {
  }

  private final List<Function<Direction, Direction>> tape = Arrays.asList(
        Direction::turnLeft,
        Direction::turnRight
  );

  public Direction next(int state, Direction direction) {
    return tape.get(state).apply(direction);
  }

  public int next(int state) {
    return (state + 1) % tape.size();
  }

}
