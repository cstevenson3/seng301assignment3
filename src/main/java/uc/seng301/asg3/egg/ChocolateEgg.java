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

import java.util.List;
import uc.seng301.asg3.ingredient.Ingredient;

/**
 * This abstract class deals with common behavioural features of all chocolate eggs. <br>
 * It provides a common way of manipulating eggs regardless their concrete types.
 */
public abstract class ChocolateEgg {

  private final ChocolateType chocolateType;

  /**
   * Package-private constructor.
   *
   * @param type a chocolate type for this egg, can't be null
   */
  ChocolateEgg(ChocolateType type) {
    if (null == type) {
      throw new IllegalArgumentException("Egg chocolate type cannot be null.");
    }
    chocolateType = type;
  }

  /**
   * Retrieve this chocolate egg's type
   *
   * @return the chocolate type
   */
  public ChocolateType getChocolateType() {
    return chocolateType;
  }

  /**
   * Retrieve the list of chocolate eggs contained in this egg
   *
   * @return the (possibly empty) list of inner chocolate eggs
   */
  public List<ChocolateEgg> getContent() {
    throw new UnsupportedOperationException("This chocolate egg cannot have any content.");
  }

  /**
   * Add given chocolate egg inside this egg
   *
   * @return true if the addition succeeds, false otherwise.
   */
  public boolean addChocolateEgg(ChocolateEgg egg){
    throw new UnsupportedOperationException("No egg can be added to this chocolate egg.");
  }

  /**
   * Remove given egg from the content of this chocolate egg
   *
   * @return true if the egg could be found in this egg's content and removed, false otherwise.
   */
  public boolean removeChocolateEgg(ChocolateEgg egg){
    throw new UnsupportedOperationException("No egg can be removed from this chocolate egg.");
  }
}
