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

package uc.seng301.asg3.packaging;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import uc.seng301.asg3.egg.ChocolateEgg;

/**
 * A Packaging contains eggs produced by orders. The way eggs are placed in a packaging depends on
 * the packaging type, i.e.
 * <ol>
 *   <li>if the package is a containing hollow egg, then the package contains one hollow egg with
 *   all other eggs inside.</li>
 *   <li>if the package is a box, then all eggs are directly inside the packaging</li>
 * </ol>
 *
 * @see uc.seng301.asg3.order.Order
 * @see uc.seng301.asg3.order.PreparingOrder
 * @see PackagingType
 */
public class Packaging {

  private final PackagingType packagingType;
  private final List<ChocolateEgg> eggs;

  /**
   * Default constructor.
   *
   * @param packagingType packaging type to be used when packing the eggs, can't be null
   */
  public Packaging(PackagingType packagingType) {
    if (null == packagingType) {
      throw new IllegalArgumentException("Packaging type cannot be null");
    }
    this.packagingType = packagingType;
    // content of packaging must be thread safe
    eggs = new CopyOnWriteArrayList<>();
  }

  /**
   * Retrieve this packaging type. Will define how eggs are placed in the packaging and what sorts
   * of chocolate type may be used.
   *
   * @return the packaging type
   */
  public PackagingType getPackagingType() {
    return packagingType;
  }

  /**
   * Wraps/packs the received chocolate egg. If the packaging type does allow only one egg (i.e.
   * {@link PackagingType#MIXED_HOLLOW_EGG} or {@link PackagingType#REGULAR_HOLLOW_EGG}), then
   * only one egg can be added.
   *
   * @param egg chocolate egg being packed, can't be null.
   * @return true if the packing process succeeds, false otherwise.
   */
  public boolean addChocolateEgg(ChocolateEgg egg) {
    if (null == egg) {
      throw new IllegalArgumentException("Cannot add a null egg in package");
    }
    if (eggs.size() == 1 && PackagingType.isHollowEggPackaging(packagingType)) {
      throw new UnsupportedOperationException("Cannot add more than one egg when packaging is a"
          + " hollow egg.");
    }
    return eggs.add(egg);
  }

  /**
   * Retrieve the list of eggs packed in this package. Will be only one if this packaging is
   * a hollow egg type.
   *
   * @return the list of chocolate eggs already packed
   */
  public List<ChocolateEgg> getEggs() {
    return eggs;
  }

  /**
   * Build a description of this Packaging including a description of its content.
   *
   * @return built description of the this Hollow Chocolate egg
   */
  @Override
  public String toString() {
    return "Package of " + getPackagingType() + " filled with\n" +
        eggs.stream().map(ChocolateEgg::toString).collect(Collectors.joining("\n"));
  }
}
