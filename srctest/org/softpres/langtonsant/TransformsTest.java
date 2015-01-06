/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

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
