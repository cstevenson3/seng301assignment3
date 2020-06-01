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

/**
 * This interface specifies how chocolate eggs can be produced regardless of their concrete types.
 *
 * @see ChocolateEgg
 */
public interface ChocolateEggFactory {

  /**
   * Create a chocolate egg based on its chocolate type (i.e. colour) and if it may contain alcohol.
   *
   * @param type the chocolate type, can't be null
   * @param containsAlcohol true if the chocolate egg may contain alcohol, false otherwise
   * @return produced (non null) chocolate egg
   */
  ChocolateEgg createChocolateEgg(ChocolateType type, boolean containsAlcohol);
}
