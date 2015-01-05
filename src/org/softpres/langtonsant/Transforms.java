/*
 * Copyright (c) 2015, Kieron Wilkinson
 */
package org.softpres.langtonsant;

import java.util.List;
import java.util.function.Supplier;

/**
 * The objective of this class is to provide a interface showing input and
 * outputs, and to get trainees to provide an implementation using streams and
 * lambdas to pass the supplied tests.
 * <p/>
 * No imperative loops allowed!
 */
public interface Transforms {

  /**
   * Create and return 'antCount' ant objects, using the supplied factory.
   */
  List<Ant> createAnts(int antCount, Supplier<Ant> factory);

}
