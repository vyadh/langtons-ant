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

  private final Transforms transforms = new DefaultTransforms();

  @Test
  public void createThreeAntsFromFactory() {
    List<Ant> ants = Arrays.asList(mock(Ant.class), mock(Ant.class), mock(Ant.class));
    List<Ant> consumable = new ArrayList<>(ants);
    Supplier<Ant> factory = () -> consumable.remove(0);

    assertThat(ants).isEqualTo(transforms.createAnts(3, factory));
  }

  @Test
  public void createGridOfCellsWithDimension3() {
    Set<Cell> cells = transforms.createCells(3).collect(toSet());

    assertThat(cells).containsOnly(
          new Cell(0, 0), new Cell(0, 1), new Cell(0, 2),
          new Cell(1, 0), new Cell(1, 1), new Cell(1, 2),
          new Cell(2, 0), new Cell(2, 1), new Cell(2, 2)
    );
  }

}
