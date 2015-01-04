/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

  @Test
  public void rotateRight() {
    Direction east = Direction.NORTH.turnRight();
    Direction south = east.turnRight();
    Direction west = south.turnRight();
    Direction north = west.turnRight();

    assertDirections(east, south, west, north);
  }

  @Test
  public void rotateLeft() {
    Direction west = Direction.NORTH.turnLeft();
    Direction south = west.turnLeft();
    Direction east = south.turnLeft();
    Direction north = east.turnLeft();

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
