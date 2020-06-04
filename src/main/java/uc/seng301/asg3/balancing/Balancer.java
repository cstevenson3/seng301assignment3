package uc.seng301.asg3.balancing;

import uc.seng301.asg3.balancing.rearranging.Rearranger;
import uc.seng301.asg3.order.PreparingOrder;

/**
 * Provides a facade for packages related to balancing a packaging
 * @author Cameron Stevenson
 *
 */
public class Balancer {
  /**
   * Take an existing packaging of eggs, and move around the eggs to suit the requirements
   * Requirements are:
   * Separate identical (same chocolate type, same filling) from each other.
   */
  public void rearrangePackaging(PreparingOrder order) {
    Rearranger rearranger = new Rearranger();
    rearranger.rearrangePackaging(order);
  }
}
