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

import java.util.ArrayList;
import java.util.List;

/**
 * Hollow chocolate eggs are eggs without any filling but that can contain other eggs inside.
 */
public class HollowChocolateEgg extends ChocolateEgg {

  private final List<ChocolateEgg> content;

  /**
   * Package-private constructor.
   * @param type a chocolate type for this egg, can't be null
   */
  HollowChocolateEgg(ChocolateType type) {
    super(type);
    content = new ArrayList<>();
  }

  /**
   * Retrieves the content of this chocolate egg.
   *
   * @return the inner chocolate eggs. Can be empty, but never null.
   */
  @Override
  public List<ChocolateEgg> getContent() {
    return content;
  }

  /**
   * Add given chocolate egg into this chocolate egg.
   *
   * @param egg the egg to add into this chocolate egg (can't be null)
   * @return true if the addition succeeded, false otherwise.
   */
  @Override
  public boolean addChocolateEgg(ChocolateEgg egg) {
    if (null == egg) {
      throw new IllegalArgumentException("Given egg to add can't be null.");
    }
    return content.add(egg);
  }

  /**
   * Remove given egg from the content of this chocolate egg
   *
   * @param egg an egg to remove from the content (can't be null)
   * @return true if given egg could be removed, false otherwise.
   */
  @Override
  public boolean removeChocolateEgg(ChocolateEgg egg) {
    if (null == egg) {
      throw new IllegalArgumentException("Given egg to remove can't be null");
    }
    return content.remove(egg);
  }

  /**
   * Prints this egg. If it has some content, will print all eggs contained in this hollow egg.
   *
   * @return a String representation of this egg.
   */
  @Override
  public String toString() {
    if (content.isEmpty()) {
      return
          "  _\n" +
          " / \\\n" +
          "|   |\n" +
          "\\   /\n" +
          " '" + getChocolateType().name().substring(0, 1).toLowerCase() + "'";
    } else {
      StringBuilder hollowContent = new StringBuilder("/ HOLLOW \\\n");
      content.forEach(egg -> hollowContent.append(egg.toString()).append("\n"));
      hollowContent.append("\n\\__ ")
          .append(getChocolateType().name().substring(0, 1).toLowerCase())
          .append("  __/\n");
      return hollowContent.toString();
    }
  }
}
