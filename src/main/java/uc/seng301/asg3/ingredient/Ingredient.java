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

package uc.seng301.asg3.ingredient;

/**
 * An ingredient is a food used to create chocolate eggs and their filling. Ingredients have names and may be
 * alcoholic or not.
 *
 * @see Filling
 * @see uc.seng301.asg3.egg.ChocolateEgg
 */
public class Ingredient {

  private final String name;
  private final boolean isAlcoholic;

  /**
   * Default constructor. Create an ingredient with given name and specify its alcoholic nature.
   *
   * @param name ingredient name, can't be null
   * @param isAlcoholic true if this ingredient is alcoholic, false otherwise
   */
  public Ingredient(String name, boolean isAlcoholic) {
    if (null == name) {
      throw new IllegalArgumentException("Ingredient name can't be null.");
    }
    this.name = name;
    this.isAlcoholic = isAlcoholic;
  }

  /**
   * Retrieve this ingredient's name
   *
   * @return the ingredient name
   */
  public String getName() {
    return name;
  }

  /**
   * Check whether this ingredient contains alcohol or not
   *
   * @return true if this ingredient contains alcohol, false otherwise
   */
  public boolean isAlcoholic() {
    return isAlcoholic;
  }
}
