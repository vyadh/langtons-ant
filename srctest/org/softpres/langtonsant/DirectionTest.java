/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

  @Test
  public void rotateRight() {
    Direction east = Direction.NORTH.right();
    Direction south = east.right();
    Direction west = south.right();
    Direction north = west.right();

    assertDirections(east, south, west, north);
  }

  @Test
  public void rotateLeft() {
    Direction west = Direction.NORTH.left();
    Direction south = west.left();
    Direction east = south.left();
    Direction north = east.left();

    assertDirections(east, south, west, north);
  }

  private void assertDirections(Direction east, Direction south, Direction west, Direction north) {
    assertThat(east.velX()).isEqualTo(1);
    assertThat(east.velY()).isEqualTo(0);
    assertThat(south.velX()).isEqualTo(0);
    assertThat(south.velY()).isEqualTo(1);
    assertThat(west.velX()).isEqualTo(-1);
    assertThat(west.velY()).isEqualTo(0);
    assertThat(north.velX()).isEqualTo(0);
    assertThat(north.velY()).isEqualTo(-1);
  }

}
