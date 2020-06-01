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

package uc.seng301.asg3.order;

import uc.seng301.asg3.egg.ChocolateType;
import uc.seng301.asg3.packaging.Packaging;
import uc.seng301.asg3.packaging.PackagingType;

/**
 * An order contains everything a Chocolatier needs to know to prepare some chocolate eggs for a
 * Client.
 *
 * An order typically defines:
 * <ul>
 *   <li>the type of packaging that will used to contain the eggs</li>
 *   <li>the type of chocolate if a regular box/containing egg is to be produced</li>
 *   <li>the quantity of eggs to be produced</li>
 *   <li>if the eggs can be stuffed and/or contains alcohol</li>
 * </ul>
 *
 * @see Counter
 * @see Chocolatier
 * @see PackagingType
 */
public abstract class Order {

  protected Packaging packaging;
  protected PackagingType packagingType;
  protected ChocolateType chocolateType;
  protected boolean stuffed;
  protected boolean containsAlcohol;
  protected int quantity;

  /**
   * Creates an order object with the parameters it receives.
   *
   * @param packagingType the type of packaging for the order
   * @param chocolate the type of chocolate for this egg order. Will be ignored if packaging type
   *       is a mixed type one (i.e. {@link PackagingType#MIXED_BOX} and
   *       {@link PackagingType#MIXED_HOLLOW_EGG}.
   * @param stuffed true if the order is made of a mix of stuffed and hollow eggs,
   *       false if only hollow eggs are to be added in this order.
   * @param containsAlcohol true if the order may contain stuffed eggs with alcohol, false if no
   *       eggs can contain alcohol. Must be false if eggs to produce are not stuffed.
   * @param quantity the number of eggs to be produced for this order
   */
  public void createOrder(PackagingType packagingType, ChocolateType chocolate, boolean stuffed,
      boolean containsAlcohol, int quantity) {
    if (!stuffed && containsAlcohol) {
      throw new IllegalArgumentException("Hollow eggs cannot contain alcohol, but order was created"
          + " with 'stuffed = false' and 'containsAlcohol = true'.");
    }
    if (null == packagingType) {
      throw new IllegalArgumentException("Need a packaging type to prepare the order later.");
    }
    this.packagingType = packagingType;
    packaging = new Packaging(this.packagingType);
    this.chocolateType = chocolate;
    this.stuffed = stuffed;
    this.containsAlcohol = containsAlcohol;
    this.quantity = quantity;
  }

  /**
   * Abstract method to prepare the order with this order's attributes.
   * <b>Must be implemented by subtype.</b>
   */
  public abstract void prepare();

  /**
   * Abstract method to cancel the order.
   * <b>Must be implemented by subtype.</b>
   */
  public abstract void cancel();

}
