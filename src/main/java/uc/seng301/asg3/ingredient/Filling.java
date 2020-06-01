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

import java.util.List;

/**
 * A filling is what is used to stuff StuffedChocolateEggs with. A filling determines the visible pattern shape
 * of an egg, contains ingredients that may themselves contains alcohol.
 *
 * @see uc.seng301.asg3.egg.StuffedChocolateEgg
 */
public class Filling {

  private final char shape;
  private final List<Ingredient> ingredients;
  private final boolean containsAlcohol;


  /**
   * Default constructor. Create a filling with given shape and made of given ingredients.
   *
   * @param shape visual shape pattern to be applied to the egg containing this filling
   * @param ingredients a non empty and non null list of ingredients used to make this filling
   */
  public Filling(char shape, List<Ingredient> ingredients) {
    if (null == ingredients || ingredients.isEmpty()) {
      throw new IllegalArgumentException("Ingredients can't be empty or null");
    }
    this.shape = shape;
    this.ingredients = ingredients;
    containsAlcohol = ingredients.stream().anyMatch(Ingredient::isAlcoholic);
  }

  /**
   * Retrieve this filling's shape
   *
   * @return the shape that will be used to decorate the stuffed chocolate egg
   */
  public char getShape() {
    return shape;
  }

  /**
   * Retrieve the list of all ingredients used to make this filling
   *
   * @return a (never empty or null) list of ingredients
   */
  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  /**
   * Retrieve whether this filling contains alcohol or not, i.e. any ingredient used to mix this filling contains
   * alcohol
   *
   * @return true if this filling contains alcohol, false otherwise
   */
  public boolean containsAlcohol() {
    return containsAlcohol;
  }

  /**
   * Prints this filling visual shape pattern for eggs stuffed with this filling
   *
   * @return the visual shape pattern.
   */
  @Override
  public String toString() {
    return String.valueOf(shape);
  }
}
