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

import uc.seng301.asg3.ingredient.Filling;

/**
 * Stuffed chocolate eggs are eggs that are stuffed with some (potentially alcoholic) filling.
 * Stuffed eggs cannot contain other eggs.
 *
 * @see Filling
 */
public class StuffedChocolateEgg extends ChocolateEgg {

  private final Filling filling;

  /**
   * Package-private constructor.
   *
   * @param type a chocolate type for this egg, can't be null
   * @param filling filling to stuff this egg with, can't be null
   */
  StuffedChocolateEgg(ChocolateType type, Filling filling) {
    super(type);
    if (null == filling) {
      throw new IllegalArgumentException("Egg filling can't be null.");
    }
    this.filling = filling;
  }

  /**
   * Retrieve this chocolate egg's filling
   *
   * @return the chocolate egg's filling
   */
  public Filling getFilling() {
    return filling;
  }

  /**
   * Prints this egg with its filling pattern.
   *
   * @return a String representation of this egg with its filling.
   */
  @Override
  public String toString() {
    return "  " + (filling.containsAlcohol() ? "^" : "_") + "\n" +
        "/ " + filling.toString() + " \\\n" +
        "|" + filling.toString() + filling.toString() + filling.toString() + "|\n" +
        "\\ " + filling.toString() + " /\n" +
        " '" + getChocolateType().name().substring(0, 1).toLowerCase() + "'";
  }
}
