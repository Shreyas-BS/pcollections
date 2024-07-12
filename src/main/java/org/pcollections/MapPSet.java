/*
 * Copyright (c) 2008 Harold Cooper. All rights reserved.
 * Licensed under the MIT License.
 * See LICENSE file in the project root for full license information.
 */

package org.pcollections;

import qual.Immutable;
import qual.Readonly;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * A map-backed persistent set.
 *
 * <p>If the backing map is thread-safe, then this implementation is thread-safe (assuming Java's
 * AbstractSet is thread-safe), although its iterators may not be.
 *
 * @author harold
 * @param <E>
 */
public final class MapPSet< E extends @Immutable Object> extends AbstractUnmodifiableSet< E> implements PSet< E>, Serializable {
  private static final long serialVersionUID = 1L;

  //// STATIC FACTORY METHODS ////
  /**
   * @param <E>
   * @param map
   * @return a PSet with the elements of map.keySet(), backed by map
   */
  @SuppressWarnings("unchecked")
  public static <E  extends @Immutable Object> MapPSet<E> from(final PMap< E, ?> map) {
    return new MapPSet<E>((PMap< E, @Immutable Object>) map);
  }

  /**
   * @param <E>
   * @param map
   * @param e
   * @return from(map).plus(e)
   */
  public static <E  extends @Immutable Object> MapPSet<E> from(final PMap< E, ?> map, E e) {
    return from(map).plus(e);
  }

  /**
   * @param <E>
   * @param map
   * @param list
   * @return from(map).plusAll(list)
   */
  public static <E  extends @Immutable Object> MapPSet<E> from(final PMap< E, ?> map, final Collection<? extends E> list) {
    return from(map).plusAll(list);
  }

  //// PRIVATE CONSTRUCTORS ////
  private final PMap<E, @Immutable Object> map;

  // not instantiable (or subclassable):
  private MapPSet(final PMap<E, @Immutable Object> map) {
    this.map = map;
  }

  //// REQUIRED METHODS FROM AbstractSet ////
  @Override
  public @Readonly Iterator<E> iterator() {
    return map.keySet().iterator();
  }

  @Override
  public int size() {
    return map.size();
  }

  //// OVERRIDDEN METHODS OF AbstractSet ////
  @Override
  public boolean contains(final @Readonly Object e) {
    return map.containsKey(e);
  }

  //// IMPLEMENTED METHODS OF PSet ////
  private @Immutable static enum In {
    @Immutable IN
  }

  public MapPSet<E> plus(final E e) {
    if (contains(e)) return this;
    return new MapPSet<E>(map.plus(e, In.IN));
  }

  public MapPSet<E> minus(final Object e) {
    if (!contains(e)) return this;
    return new MapPSet<E>(map.minus(e));
  }

  public MapPSet<E> plusAll(final Collection<? extends E> list) {
    PMap<E, @Immutable Object> map = this.map;
    for (E e : list) map = map.plus(e, In.IN);
    return from(map);
  }

  public MapPSet<E> minusAll(final Collection<?> list) {
    PMap<E, @Immutable Object> map = this.map.minusAll(list);
    return from(map);
  }

  @Override
  public MapPSet<E> intersect(Collection<? extends E> list) {
    return this.minusAll(this.minusAll(list));
  }
}
