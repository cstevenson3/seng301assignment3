package uc.seng301.asg3.balancing;

import java.util.List;

import uc.seng301.asg3.balancing.rearranging.Rearranger;
import uc.seng301.asg3.balancing.selecting.Selection;
import uc.seng301.asg3.balancing.selecting.SelectionDirector;
import uc.seng301.asg3.ingredient.Filling;
import uc.seng301.asg3.order.PreparingOrder;

/**
 * Provides a facade for packages related to balancing a packaging
 * @author Cameron Stevenson
 *
 */
public class Balancer {
  /**
   * Get a balanced selection of eggs to produce
   * @param order The order to generate a balanced selection for
   * @param fillings All available fillings
   * @return The balanced selection
   */
  public Selection getSelection(PreparingOrder order, List<Filling> fillings) {
    SelectionDirector selectionDirector = new SelectionDirector(order, fillings);
    Selection selection = selectionDirector.construct();
    return selection;
  }
  
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
