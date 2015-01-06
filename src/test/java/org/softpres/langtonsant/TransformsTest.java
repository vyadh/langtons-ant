/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import javafx.scene.paint.Color;
import org.assertj.core.data.Offset;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.softpres.langtonsant.Direction.NORTH;

/**
 * Tests a provided implementation of {@link Transforms}.
 */
public class TransformsTest {

  private final Transforms transforms = new Transforms();

  @Test
  public void createThreeAntsFromFactory() {
    List<Ant> expected = Arrays.asList(mock(Ant.class), mock(Ant.class), mock(Ant.class));
    List<Ant> consumable = new ArrayList<>(expected);
    Supplier<Ant> factory = () -> consumable.remove(0);

    List<Ant> actual = transforms.createAnts(3, factory);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void createGridOfCellsWithDimension3() {
    Cell[] expected = new Cell[] {
          new Cell(0, 0), new Cell(0, 1), new Cell(0, 2),
          new Cell(1, 0), new Cell(1, 1), new Cell(1, 2),
          new Cell(2, 0), new Cell(2, 1), new Cell(2, 2)
    };

    Set<Cell> actual = transforms.createCells(3).collect(toSet());

    assertThat(actual).containsOnly(expected);
  }

  @Test
  public void createLifeCycleFromSpecifiedLangtonTape() {
    LifeCycle lifeCycle = transforms.createLifeCycle("LR");

    assertThat(lifeCycle.direction(0, NORTH)).isEqualTo(NORTH.turnLeft());
    assertThat(lifeCycle.direction(1, NORTH)).isEqualTo(NORTH.turnRight());
  }

  @Test
  public void createLifeCycleFromSpecifiedArbitaryTape() {
    LifeCycle lifeCycle = transforms.createLifeCycle("RLLR");

    assertThat(lifeCycle.direction(0, NORTH)).isEqualTo(NORTH.turnRight());
    assertThat(lifeCycle.direction(1, NORTH)).isEqualTo(NORTH.turnLeft());
    assertThat(lifeCycle.direction(2, NORTH)).isEqualTo(NORTH.turnLeft());
    assertThat(lifeCycle.direction(3, NORTH)).isEqualTo(NORTH.turnRight());
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void createdLifeCycleShouldBeOfAppropriateSize() {
    transforms.createLifeCycle("LRLRLR").direction(6, NORTH);
  }

  @Test
  public void shadeForLangtonIsBetweenWhiteAndBlack() {
    LifeCycle lc = transforms.createLifeCycle("LR");

    assertThat(lc.colour(0)).isEqualTo(Color.gray(1.0));
    assertThat(lc.colour(1)).isEqualTo(Color.gray(0.0));
  }

  @Test
  public void shadeIsProportionalToLength() {
    LifeCycle lc = transforms.createLifeCycle("LRLR");

    assertThat(lc.colour(0).getBrightness()).isCloseTo(Color.gray(1.0).getBrightness(), Offset.offset(0.01));
    assertThat(lc.colour(1).getBrightness()).isCloseTo(Color.gray(0.66).getBrightness(), Offset.offset(0.01));
    assertThat(lc.colour(2).getBrightness()).isCloseTo(Color.gray(0.33).getBrightness(), Offset.offset(0.01));
    assertThat(lc.colour(3).getBrightness()).isCloseTo(Color.gray(0.0).getBrightness(), Offset.offset(0.01));
  }

  @Test
  public void convertGridOfCellsIntoStream() {
    Cell[][] grid = {
          { new Cell(0, 0), new Cell(0, 1), new Cell(0, 2) },
          { new Cell(1, 0), new Cell(1, 1), new Cell(1, 2) },
          { new Cell(2, 0), new Cell(2, 1), new Cell(2, 2) }
    };

    Set<Cell> expected = new HashSet<>();
    for (Cell[] row : grid) {
      expected.addAll(Arrays.asList(row));
    }

    Set<Cell> actual = transforms.cells(grid).collect(toSet());

    assertThat(actual).isEqualTo(expected);
  }

}
