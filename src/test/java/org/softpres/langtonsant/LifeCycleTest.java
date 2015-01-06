/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LifeCycleTest {

  private final LifeCycle lifeCycle = LifeCycle.LANGTON;
  private final Direction north = Direction.NORTH;
  private final Direction west = north.turnLeft();
  private final Direction east = north.turnRight();

  @Test
  public void nextLangtonStateFrom0Is1() {
    assertThat(lifeCycle.next(0)).isEqualTo(1);
  }

  @Test
  public void nextLangtonStateFrom1Is0() {
    assertThat(lifeCycle.next(1)).isEqualTo(0);
  }

  @Test
  public void turnLangtonFrom0IsLeft() {
    assertThat(lifeCycle.direction(0, north)).isEqualTo(west);
  }

  @Test
  public void turnLangtonFrom0IsRight() {
    assertThat(lifeCycle.direction(1, north)).isEqualTo(east);
  }

}
