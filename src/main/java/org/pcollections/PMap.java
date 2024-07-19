/*
 * Copyright (c) 2008 Harold Cooper. All rights reserved.
 * Licensed under the MIT License.
 * See LICENSE file in the project root for full license information.
 */

package org.pcollections;

import qual.Immutable;
import qual.Readonly;

import java.util.Collection;
import java.util.Map;

/**
 * An immutable, persistent map from keys of type K to values of type V.
 *
 * <p>Some implementations may not support null keys and/or values, in which case they will throw
 * {@link NullPointerException} for attempts to add such keys/values.
 *
 * @author harold
 * @param <K>
 * @param <V>
 */
@Immutable
public interface PMap<K extends @Immutable Object, V> extends Map<@Immutable K, V> {
  /**
   * @param key
   * @param value
   * @return a map with the mappings of this but with key mapped to value
   */
  public PMap<K, V> plus(K key, V value);

  /**
   * @param map
   * @return this combined with map, with map's mappings used for any keys in both map and this
   */
  public PMap<K, V> plusAll(Map<? extends K, ? extends V> map);

  /**
   * @param key
   * @return a map with the mappings of this but with no value for key
   */
  public PMap<K, V> minus(@Readonly Object key);

  /**
   * @param keys
   * @return a map with the mappings of this but with no value for any element of keys
   */
  public PMap<K, V> minusAll(@Readonly PMap<K, V> this, @Readonly Collection<?> keys);

  @Deprecated
  V put(K k, V v);

  @Deprecated
  V remove(@Readonly Object k);

  @Deprecated
  void putAll(@Readonly Map<? extends K, ? extends V> m);

  @Deprecated
  void clear();
}
