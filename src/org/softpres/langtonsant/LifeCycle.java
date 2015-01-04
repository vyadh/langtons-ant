/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * In Langton's Ant, this is just two states, (0) left and (1) right.
 */
public class LifeCycle {

  public static final LifeCycle LANGTON = new LifeCycle(Arrays.asList(
        Direction::turnLeft,
        Direction::turnRight
  ));

  public static LifeCycle of(String tape) {
    return new LifeCycle(tape
          .chars()
          .mapToObj(c -> Turn.of((char)c))
          .collect(Collectors.toList())
    );
  }

  private final List<Function<Direction, Direction>> tape;

  private LifeCycle(List<Function<Direction, Direction>> tape) {
    this.tape = tape;
  }

  public Direction direction(int state, Direction direction) {
    return tape.get(state).apply(direction);
  }

  public Color colour(int state) {
    int size = Math.max(1, tape.size() - 1);
    double inc = 1.0 / size;
    double change = inc * state;
    return Color.BLACK.interpolate(Color.WHITE, change);
  }

  public int next(int state) {
    return (state + 1) % tape.size();
  }

}
