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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uc.seng301.asg3.packaging.Packaging;

/**
 * A Chocolatier is responsible for receiving and preparing chocolate egg orders.
 *
 * @see PreparingOrder
 */
public class Chocolatier {

  private final Logger logger = LogManager.getLogger(Chocolatier.class.getName());

  /**
   * Execute the received order, i.e. produce and pack the chocolate eggs corresponding to the
   * Client order.
   *
   * @param order a non null order to be prepared
   * @return a packaging containing the eggs as requested in by the order
   */
  public Packaging executeOrder(PreparingOrder order) {
    if (null == order) {
      throw new IllegalArgumentException("Cannot execute null order.");
    }

    logger.info("Starting to execute order");
    order.prepare();

    // Chocolatier is now busy preparing the chocolate, so we make the process being "busy waiting"
    try {
      while (!order.isFinished()) {
        Thread.sleep(5000);
      }
    } catch (InterruptedException e) {
      logger.error("Interrupted while being busy waiting", e);
    }
    return order.getPackaging();
  }

}
