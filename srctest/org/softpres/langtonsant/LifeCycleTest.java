/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import javafx.scene.paint.Color;
import org.assertj.core.data.Offset;
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

  @Test
  public void shadeForLangtonIsBetweenWhiteAndBlack() {
    LifeCycle lc = LifeCycle.of("LR");

    assertThat(lc.colour(0)).isEqualTo(Color.gray(1.0));
    assertThat(lc.colour(1)).isEqualTo(Color.gray(0.0));
  }

  @Test
  public void shadeIsProportionalToLength() {
    LifeCycle lc = LifeCycle.of("LRLR");

    assertThat(lc.colour(0).getBrightness()).isCloseTo(Color.gray(1.0 ).getBrightness(), Offset.offset(0.01));
    assertThat(lc.colour(1).getBrightness()).isCloseTo(Color.gray(0.66).getBrightness(), Offset.offset(0.01));
    assertThat(lc.colour(2).getBrightness()).isCloseTo(Color.gray(0.33).getBrightness(), Offset.offset(0.01));
    assertThat(lc.colour(3).getBrightness()).isCloseTo(Color.gray(0.0 ).getBrightness(), Offset.offset(0.01));
  }

  @Test
  public void createLifeCycleFromString() {
    LifeCycle lc = LifeCycle.of("RLLR");

    assertThat(lc.direction(0, north)).isEqualTo(east);
    assertThat(lc.direction(1, north)).isEqualTo(west);
    assertThat(lc.direction(2, north)).isEqualTo(west);
    assertThat(lc.direction(3, north)).isEqualTo(east);
  }

}
