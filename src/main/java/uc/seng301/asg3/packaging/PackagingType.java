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

package uc.seng301.asg3.packaging;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class defines the different types of packaging to be filled with chocolate eggs.
 * <ol>
 *   <li>BOXes are simple cardboard boxes containing eggs directly, so that the packaging
 *   will potentially contain many eggs.</li>
 *   <li>HOLLOW_EGGs are larger hollow eggs containing eggs inside, so that the packaging will
 *   contain only one egg directly (the larger hollow egg) that itself will contain other eggs.</li>
 * </ol>
 */
public enum PackagingType {
  /**
   * This packaging type is a box filled with eggs made of one type of chocolate only.
   */
  REGULAR_BOX(1),
  /**
   * This packaging type is a box filled with eggs made of all available types of chocolate.
   */
  MIXED_BOX(2),
  /**
   * This packaging type is a larger hollow egg that will be filled with eggs made of one type of
   * chocolate only.
   *
   * @see uc.seng301.asg3.egg.HollowChocolateEgg
   */
  REGULAR_HOLLOW_EGG(3),
  /**
   * This packaging type is a larger hollow egg that will be filled with eggs made of all available
   * types of chocolate.
   *
   * @see uc.seng301.asg3.egg.HollowChocolateEgg
   */
  MIXED_HOLLOW_EGG(4);

  private final int id;
  private static final Map<Integer, PackagingType> map = new LinkedHashMap<>();

  static {
    for (PackagingType type : PackagingType.values()) {
      map.put(type.id, type);
    }
  }

  /**
   * Constructor
   *
   * @param id an int representing a packaging type
   */
  PackagingType(int id) {
    this.id = id;
  }

  /**
   * Get the PackagingType enum value for a given id
   *
   * @param id an int representing a packaging type
   * @return the PackagingType enum value corresponding to the given id, null otherwise.
   */
  public static PackagingType value(int id) {
    return map.get(id);
  }

  /**
   * Get this id
   *
   * @return an int representation of this packaging type
   */
  public int id() {
    return id;
  }


  /**
   * Helper method Check whether given packaging type is a hollow egg that will contain other eggs
   * or a simple box.
   * @param packagingType a packaging type
   * @return true if given packaging type is a hollow egg, false if it is a box.
   */
  public static boolean isHollowEggPackaging(PackagingType packagingType) {
    return packagingType.equals(PackagingType.REGULAR_HOLLOW_EGG)
        || packagingType.equals(PackagingType.MIXED_HOLLOW_EGG);
  }


}
