/*
 * Copyright (c) 2008 Harold Cooper. All rights reserved.
 * Licensed under the MIT License.
 * See LICENSE file in the project root for full license information.
 */

package org.pcollections;

import qual.Immutable;

import java.util.Collection;

/**
 * A static convenience class for creating efficient persistent bags.
 *
 * <p>This class simply creates MapPBags backed by HashTreePMaps.
 *
 * @author harold
 */
public final class HashTreePBag {
  // not instantiable (or subclassable):
  private HashTreePBag() {}

  private static final MapPBag<@Immutable Object> EMPTY = MapPBag.empty(HashTreePMap.<@Immutable Object, Integer>empty());

  /**
   * @param <E>
   * @return an empty bag
   */
  @SuppressWarnings("unchecked")
  public static <E  extends @Immutable Object> MapPBag<E> empty() {
    return (MapPBag<E>) EMPTY;
  }

  /**
   * @param <E>
   * @param e
   * @return empty().plus(e)
   */
  public static <E  extends @Immutable Object> MapPBag<E> singleton(final E e) {
    return HashTreePBag.<E>empty().plus(e);
  }

  /**
   * @param <E>
   * @param list
   * @return empty().plusAll(map)
   */
  public static <E  extends @Immutable Object> MapPBag<E> from(final Collection<? extends E> list) {
    return HashTreePBag.<E>empty().plusAll(list);
  }
}
