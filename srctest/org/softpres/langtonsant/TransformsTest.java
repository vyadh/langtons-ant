/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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

}
