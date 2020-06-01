/*
 * Copyright (c) 2020. University of Canterbury
 *
 * This file is part of SENG301 lab material.
 *
 *  The lab material is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published
 *  by the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The lab material is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this material.  If not, see <https://www.gnu.org/licenses/>.
 */

package uc.seng301.asg3.egg;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This enumeration defines the range of types of chocolate available to create chocolate eggs with.
 */
public enum ChocolateType {
  /**
   * A milk chocolate type
   */
  MILK(1),
  /**
   * A dark chocolate type
   */
  DARK(2),
  /**
   * A white chocolate type
   */
  WHITE(3),
  /**
   * A crunchy chocolate type
   */
  CRUNCHY(4);

  private final int id;
  private static final Map<Integer, ChocolateType> map = new LinkedHashMap<>();

  static {
    for (ChocolateType type : ChocolateType.values()) {
      map.put(type.id, type);
    }
  }

  /**
   * Constructor
   *
   * @param id an int representing a chocolate type
   */
  ChocolateType(int id) {
    this.id = id;
  }

  /**
   * Get the ChocolateType enum value for a given id
   *
   * @param id an int representing a chocolate type
   * @return the ChocolateType enum value corresponding to the given id, null otherwise.
   */
  public static ChocolateType value(int id) {
    return map.get(id);
  }

  /**
   * Get this id
   *
   * @return an int representation of this chocolate type
   */
  public int id() {
    return id;
  }
}

