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

package uc.seng301.asg3;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import uc.seng301.asg3.egg.ChocolateType;
import uc.seng301.asg3.egg.HollowEggFactory;
import uc.seng301.asg3.egg.StuffedEggFactory;
import uc.seng301.asg3.order.Chocolatier;
import uc.seng301.asg3.order.Counter;
import uc.seng301.asg3.order.PreparingOrder;
import uc.seng301.asg3.packaging.PackagingType;

/**
 * This class is the main entry point of the whole system. This application emulates a Chocolatier
 * where clients (you through a Command Line Interface - CLI - ), will be able to order some
 * chocolate boxes of various types and content.<br><br>
 *
 * Please take some time to play around and look into the extensive documentation to understand the
 * structure of the application, as it is the central point for this assignment.
 */
public class App {

  // the scanner for the CLI
  private final Scanner cli;

  // a logger is useful to print messages in a configurable and flexible way
  // see resources/log4j2.xml configuration file
  private static final Logger logger = LogManager.getLogger(App.class.getName());

  private App() {
    cli = new Scanner(System.in);
  }

  /**
   * This main method is the entry point to the overall application. It starts the CLI after
   * instantiating the hardcoded/nasty Context resources (where ingredients and fillings are
   * defined).
   *
   * @param args none are expected
   */
  public static void main(String[] args) {

    App app = new App();
    Context context = new Context();
    Counter counter = new Counter(new Chocolatier());
    new Thread(counter).start();

    PackagingType packagingType;
    ChocolateType chocolateType = null;
    boolean stuffed;
    boolean isAlcoholic = false;
    int quantity;

    System.out.println(app.greetings());
    System.out.println(packagingType = app.selectPackagingType());
    if (PackagingType.REGULAR_BOX.equals(packagingType)
        || PackagingType.REGULAR_HOLLOW_EGG.equals(packagingType)) {
      System.out.println(chocolateType = app.selectChocolateType());
    }

    System.out.println(stuffed = app.selectStuffed());
    if (stuffed) {
      System.out.println(isAlcoholic = app.selectAlcoholic());
    }
    System.out.println(quantity = app.selectQuantity());

    PreparingOrder order = new PreparingOrder(new HollowEggFactory(),
        new StuffedEggFactory(new ArrayList<>(context.allFillings.values())));

    order.createOrder(packagingType, chocolateType, stuffed, isAlcoholic, quantity);
    counter.placeOrder(order);

    try {
      while (null == order.getPackaging()) {
        Thread.sleep(2000);
      }
    } catch (InterruptedException e) {
      logger.error("Interrupted while busy waiting...", e);
    }

    System.out.println(order.getPackaging());
    counter.stop();
  }


  /*
   * PRIVATE METHODS TO DEAL WITH CLI MENU
   */

  private String greetings() {
    return "#########################################\n\n" +
        "  Welcome to 301 Easter Egg Chocolatier \n\n" +
        "#########################################\n\n";
  }

  private PackagingType selectPackagingType() {
    PackagingType packagingType = null;
    while (null == packagingType) {
      System.out.println("Which packaging would you like?\n" +
          "\t 1. Box with one type of chocolate (regular box)\n" +
          "\t 2. Box with mixed types of chocolates (mixed box)\n" +
          "\t 3. Large hollow egg with similar eggs inside (regular hollow egg)\n" +
          "\t 4. Large hollow egg with mixed eggs inside (mixed hollow egg)\n\n" +
          "Your choice: ");

      String input = cli.nextLine();
      int choice;
      try {
        choice = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        choice = -1;
      }
      packagingType = PackagingType.value(choice);
      if (null == packagingType) {
        System.out.println("Unknown value entered, please select a value between 1 and 4.\n");
      }
    }
    return packagingType;
  }

  private ChocolateType selectChocolateType() {
    ChocolateType chocolateType = null;
    while (null == chocolateType) {
      System.out.println("Which type of chocolate would you like?\n" +
          "\t 1. Milk Chocolate\n" +
          "\t 2. Dark Chocolate\n" +
          "\t 3. White Chocolate\n" +
          "\t 4. Crunchy Chocolate\n\n" +
          "Your choice: ");

      String input = cli.nextLine();
      int choice;
      try {
        choice = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        choice = -1;
      }
      chocolateType = ChocolateType.value(choice);
      if (null == chocolateType) {
        System.out.println("Unknown value entered, please select a value between 1 and 4.\n");
      }
    }
    return chocolateType;
  }

  private boolean selectStuffed() {
    System.out.println("Would you like a mix of hollow and stuffed eggs? "
        + "Type true for yes, anything else for no: ");
    return Boolean.parseBoolean(cli.nextLine());
  }

  private boolean selectAlcoholic() {
    System.out.println("Would you like some eggs with alcoholic filling? "
        + "Type true for yes, anything else for no: ");
    return Boolean.parseBoolean(cli.nextLine());
  }

  private int selectQuantity() {
    int quantity = -1;
    while (quantity == -1) {
      System.out.println("How many chocolate eggs do you want?");
      String input = cli.nextLine();
      try {
        quantity = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        quantity = -1;
      }
      if (quantity == -1) {
        System.out.println("Invalid integer value entered.\n");
      }
    }
    return quantity;
  }
}
