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
  public void nextStateFrom0Is1() {
    assertThat(lifeCycle.next(0)).isEqualTo(1);
  }

  @Test
  public void nextStateFrom1Is0() {
    assertThat(lifeCycle.next(1)).isEqualTo(0);
  }

  @Test
  public void nextTurnFrom0TurnsLeft() {
    assertThat(lifeCycle.next(0, north)).isEqualTo(west);
  }

  @Test
  public void nextTurnFrom0TurnsRight() {
    assertThat(lifeCycle.next(1, north)).isEqualTo(east);
  }


  @Test
  public void createLifeCycleFromString() {
    LifeCycle lc = LifeCycle.of("RLLR");

    assertThat(lc.next(0, north)).isEqualTo(east);
    assertThat(lc.next(1, north)).isEqualTo(west);
    assertThat(lc.next(2, north)).isEqualTo(west);
    assertThat(lc.next(3, north)).isEqualTo(east);
  }

}
