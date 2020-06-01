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
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uc.seng301.asg3.ingredient.Filling;

/**
 * A StuffedChocolateFactory allows to produce stuffed eggs.
 *
 * @see StuffedChocolateEgg
 */
public class StuffedEggFactory implements ChocolateEggFactory {

  private final List<Filling> fillings;
  private final Random nextFillingPicker;
  private final Logger logger = LogManager.getLogger(StuffedEggFactory.class.getName());

  public StuffedEggFactory(List<Filling> fillings) {
    this.fillings = fillings;
    nextFillingPicker = new Random();
  }

  /**
   * Create a stuffed chocolate egg based on its chocolate type.<br><br>
   *
   * Filling will be randomly decided according to the containsAlcohol value
   *
   * @param type the chocolate type, can't be null
   * @param containsAlcohol true if the chocolate egg may contain alcohol, false otherwise
   * @return produced (non null) stuffed chocolate egg
   */
  @Override
  public ChocolateEgg createChocolateEgg(ChocolateType type, boolean containsAlcohol) {
    logger.debug("create a {} stuffed egg with{} alcohol",
        type.name(), containsAlcohol ? "" : "out");
    return new StuffedChocolateEgg(type, getSomeFilling(containsAlcohol));
  }

  /**
   * Get a filling from the list of known fillings to be used to fill a stuffed egg.<br><br>
   *
   * If containsAlcohol is false, then returned filling will not contain alcohol. Otherwise returned filling
   * may or may not contain alcohol.
   *
   * @param containsAlcohol true if the filling to return may contain alcohol, false if unspecified
   * @return a randomly chosen filling
   */
  private Filling getSomeFilling(boolean containsAlcohol) {
    Filling filling = fillings.get(nextFillingPicker.nextInt(fillings.size()));
    while (filling.containsAlcohol() != containsAlcohol) {
      filling = fillings.get(nextFillingPicker.nextInt(fillings.size()));
    }
    return filling;
  }
}
