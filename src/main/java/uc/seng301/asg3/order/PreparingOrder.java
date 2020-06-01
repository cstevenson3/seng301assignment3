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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uc.seng301.asg3.egg.ChocolateEgg;
import uc.seng301.asg3.egg.ChocolateEggFactory;
import uc.seng301.asg3.egg.ChocolateType;
import uc.seng301.asg3.egg.HollowEggFactory;
import uc.seng301.asg3.egg.StuffedEggFactory;
import uc.seng301.asg3.packaging.Packaging;
import uc.seng301.asg3.packaging.PackagingType;

/**
 * PreparingOrder defines how an order is managed by the Chocolatier in practice. The Chocolatier
 * relies on the factories to create the appropriate eggs to fulfil the order and fill in the
 * required packaging.
 *
 * @see ChocolateEggFactory
 * @see uc.seng301.asg3.packaging.Packaging
 */
public class PreparingOrder extends Order {

  private final HollowEggFactory hollowEggFactory;
  private final StuffedEggFactory stuffedEggFactory;
  private ExecutorService executor;
  private final Logger logger = LogManager.getLogger(PreparingOrder.class.getName());

  /**
   * Default constructor. Create the concrete order that knows how to prepare the order.
   *
   * @param hollowEggFactory the hollow egg factory
   * @param stuffedEggFactory the stuffed egg factory
   */
  public PreparingOrder(HollowEggFactory hollowEggFactory, StuffedEggFactory stuffedEggFactory) {
    this.hollowEggFactory = hollowEggFactory;
    this.stuffedEggFactory = stuffedEggFactory;
  }

  /**
   * Prepare an order by creating all expected eggs using the attributes defined in
   * {@link Order#createOrder} and corresponding factories.<br>
   * Eggs are asynchronously created in separate threads (except the containing hollow egg if
   * the type of packaging is an hollow egg) and placed into the packaging or the containing
   * hollow egg when produced.
   *
   * @see PackagingType
   */
  @Override
  public void prepare() {
    executor = Executors.newFixedThreadPool(3);
    if (PackagingType.isHollowEggPackaging(packagingType)) {
      packaging.addChocolateEgg(produceEgg(hollowEggFactory, randomChocolateType(), false));
    }

    for (int i = 0; i < quantity; i++) {
      // CompletableFutures are sorts of threads that can be easily created on the fly to process
      // long running tasks, as the produceEgg method. We also pass the executor where we created
      // a pool of threads with a given size of 3
      CompletableFuture.supplyAsync(() ->
          produceEgg(randomFactory(stuffed), randomChocolateType(), withAlcohol(containsAlcohol)), executor)
          // and we can be called-back when the process ran inside a CompletableFuture finishes and
          // return some result when want to process, like here the produced eggs
          .thenAcceptAsync(egg -> {
            logger.debug("add egg to package");
            boolean eggAdded;
            if (PackagingType.isHollowEggPackaging(packagingType)) {
              eggAdded = packaging.getEggs().get(0).addChocolateEgg(egg);
            } else {
              eggAdded = packaging.addChocolateEgg(egg);
            }
            logger.info("{} egg has{}been produced.",
                egg.getChocolateType(), eggAdded ? " " : " not ");
          }, executor)
        .exceptionally(e -> {
          // we need to check here if no exception has been raised from the execution the
          // the future. Exception raised inside the body of supplyAsync and thenAcceptAsync
          // won't show up anywhere unless we catch them with this "exceptionally" method
          logger.error("Something bad happen", e);
          return null;
        });
    }

    // we need to wait to ensure all tasks submitted to the executor (pool of threads)
    // have been completed in any way (either with success or with an exception)
    // if we do not wait, then tasks may take longer to complete and the method may return
    // before they finished, so before the eggs are produced and packed.
    try {
      executor.awaitTermination(30, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      logger.error("Interrupted while waiting completion of all submitted tasks to executor", e);
    }
    executor.shutdown();
    logger.debug("exiting");
  }

  /**
   * Stop the production of all eggs in this order. Some eggs may have been produced and added into
   * the packaging linked to this order.
   */
  @Override
  public void cancel() {
    executor.shutdownNow();
  }

  /**
   * Check whether this order is fully done by checking if all expected eggs have been produced
   * (i.e. this order quantity, potentially + 1 if it is a hollow_egg box).
   *
   * @return all expected eggs have been produced and added into the packaging
   */
  public boolean isFinished() {
    logger.debug("packaging contains {} eggs", packaging.getEggs().size());
    return PackagingType.isHollowEggPackaging(packagingType) ?
        !packaging.getEggs().isEmpty() && packaging.getEggs().get(0).getContent().size() == quantity
        : packaging.getEggs().size() == quantity;
  }

  /**
   * Return the packaging when all eggs of this order have been produced.
   *
   * @return the packaging, or null if this order is not yet finished
   */
  public Packaging getPackaging() {
    return isFinished() ? packaging : null;
  }

  /**
   * Helper method to produce an egg.
   *
   * @param factory the factory needed to create an egg
   * @param type the chocolate type
   * @param containsAlcohol if this chocolate may contain alcohol or not
   * @return the produced egg by given factory
   */
  private ChocolateEgg produceEgg(ChocolateEggFactory factory, ChocolateType type,
      boolean containsAlcohol) {
    logger.debug("produce egg with factory {} of type {} with{} alcohol",
        factory.getClass().getSimpleName(), type.name(), containsAlcohol ? "" : "out");
    try {
      // add a sleep to simulate some preparation time and therefore longer tasks
      Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
    } catch (InterruptedException e) {
      logger.error("Interrupted while producing an egg", e);
    }
    return factory.createChocolateEgg(type, containsAlcohol);
  }

  /**
   * Get a random egg factory
   *
   * @return an egg factory
   */
  private ChocolateEggFactory randomFactory(boolean stuffed) {
    return ThreadLocalRandom.current().nextInt() % 2 == 0 && stuffed ?
        stuffedEggFactory : hollowEggFactory;
  }

  /**
   * Get a random chocolate type if packaging allows it
   *
   * @return a random chocolate type, or this order chocolate type if packaging type requires it
   * @see PackagingType
   */
  private ChocolateType randomChocolateType() {
    return packagingType.equals(PackagingType.MIXED_BOX)
        || packagingType.equals(PackagingType.MIXED_HOLLOW_EGG) ?
        ChocolateType.values()[ThreadLocalRandom.current().nextInt(ChocolateType.values().length)]
        : chocolateType;
  }

  /**
   * Randomize the chance to have an alcoholic egg when the order allows to have some
   *
   * @param containsAlcohol some eggs may contain alcohol
   * @return always false if containsAlcohol is false, randomly true otherwise
   */
  private boolean withAlcohol(boolean containsAlcohol) {
    return ThreadLocalRandom.current().nextInt() % 2 == 0 && containsAlcohol;
  }
}
