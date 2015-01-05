/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Instructor-provided implementations of the transforms.
 */
public class DefaultTransforms implements Transforms {

  @Override
  public List<Ant> createAnts(int antCount, Supplier<Ant> factory) {
    return Stream.generate(factory).limit(3).collect(Collectors.toList());
  }

}
