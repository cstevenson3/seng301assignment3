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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Counter is where the orders are added and passed to the Chocolatier.
 */
public class Counter implements Runnable {

  private final Chocolatier chocolatier;
  private final BlockingQueue<Order> orders;
  private static boolean stop;
  private final Logger logger = LogManager.getLogger(Counter.class.getName());

  /**
   * Default constructor.
   *
   * @param chocolatier the Chocolatier dealing with orders registered to this counter.
   */
  public Counter(Chocolatier chocolatier) {
    this.chocolatier = chocolatier;
    orders = new LinkedBlockingQueue<>();
    stop = false;
  }

  /**
   * Add the client's order to produce some eggs
   *
   * @param order the order to be taken care of by the Chocolatier
   */
  public synchronized void placeOrder(Order order) {
    logger.debug("new order placed");
    orders.add(order);
    notifyAll();
  }

  /**
   * Gently stop this Counter process
   */
  public synchronized void stop() {
    stop = true;
  }

  @Override
  public synchronized void run() {
    try {
      while (orders.isEmpty() && !stop) {
        wait();
      }
      logger.debug("picked order");

      Order order = orders.poll();
      String orderType = order.getClass().getSimpleName();

      // suboptimal idiomatic pattern matching to route the correct Order
      // implementation to the right handler.
      switch (orderType) {
        case "PreparingOrder":
          chocolatier.executeOrder((PreparingOrder) order);
          break;
        default:
          logger.warn("Unhandled type of order: {}", orderType);
      }

    } catch (InterruptedException e) {
      logger.error("Interrupted while waiting", e);
    }
  }
}
