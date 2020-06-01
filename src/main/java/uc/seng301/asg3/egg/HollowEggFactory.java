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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A HollowChocolateEggFactory allows to produce hollow eggs.
 *
 * @see HollowChocolateEgg
 */
public class HollowEggFactory implements ChocolateEggFactory {

  private final Logger logger = LogManager.getLogger(HollowEggFactory.class.getName());

  /**
   * Create a hollow chocolate egg based on its chocolate type (i.e. colour).
   *
   * @param type the chocolate type, can't be null
   * @param containsAlcohol ignored since hollow eggs have no filling, hence no alcohol
   * @return produced (non null) hollow chocolate egg
   */
  @Override
  public ChocolateEgg createChocolateEgg(ChocolateType type, boolean containsAlcohol) {
    logger.debug("create a {} hollow egg without alcohol", type.name());
    return new HollowChocolateEgg(type);
  }
}
