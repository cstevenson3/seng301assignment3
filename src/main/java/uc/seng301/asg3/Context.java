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

package uc.seng301.asg3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uc.seng301.asg3.ingredient.Filling;
import uc.seng301.asg3.ingredient.Ingredient;

/**
 * This class contains hardcoded examples to load the application with sample data.<br>
 * It has been implemented in this (nasty) way to keep the App class basic since the objective
 * of this assignment is not focused on this aspect. Please do not follow this example to
 * initialise data in a real-world application. It is oversimplified and actually bad practice.
 */
public class Context {

  final Map<String, Ingredient> allIngredients;
  final Map<Character, Filling> allFillings;

  /**
   * Default constructor. Bunch of hardcoded ingredients and filling
   */
  public Context() {
    allIngredients = new HashMap<>();
    allFillings = new HashMap<>();

    allIngredients.put("Sugar", new Ingredient("Sugar", false));
    allIngredients.put("Butter", new Ingredient("Butter", false));
    allIngredients.put("Caramel", new Ingredient("Caramel", false));
    allIngredients.put("Strawberry", new Ingredient("Strawberry", false));
    allIngredients.put("Passion fruit", new Ingredient("Passion fruit", false));
    allIngredients.put("Advocaat cream", new Ingredient("Advocaat cream", true));
    allIngredients.put("Cointreau", new Ingredient("Cointreau", true));
    allIngredients.put("Coconut", new Ingredient("Coconut", false));
    allIngredients.put("Feijoa", new Ingredient("Feijoa", false));


    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(allIngredients.get("Strawberry"));
    ingredients.add(allIngredients.get("Advocaat cream"));
    ingredients.add(allIngredients.get("Sugar"));
    Filling strawberryMousse = new Filling('s', ingredients);
    allFillings.put('s', strawberryMousse);

    ingredients.clear();
    ingredients.add(allIngredients.get("Coconut"));
    ingredients.add(allIngredients.get("Sugar"));
    ingredients.add(allIngredients.get("Butter"));
    Filling coconutCream = new Filling('c', ingredients);
    allFillings.put('c', coconutCream);

    ingredients.clear();
    ingredients.add(allIngredients.get("Passion fruit"));
    ingredients.add(allIngredients.get("Sugar"));
    ingredients.add(allIngredients.get("Cointreau"));
    Filling passionFruitMousse = new Filling('p', ingredients);
    allFillings.put('p', passionFruitMousse);

    ingredients.clear();
    ingredients.add(allIngredients.get("Feijoa"));
    ingredients.add(allIngredients.get("Caramel"));
    ingredients.add(allIngredients.get("Sugar"));
    Filling feijoaMouse = new Filling('f', ingredients);
    allFillings.put('f', feijoaMouse);
  }
}
