package uc.seng301.asg3.balancing.selecting;

import java.util.List;

import uc.seng301.asg3.ingredient.Filling;
import uc.seng301.asg3.order.PreparingOrder;
import uc.seng301.asg3.packaging.PackagingType;

/**
 * This is a part of the builder pattern. It directs the building by interpreting an order,
 * and converting it into build requirements (enacted by calling methods on SelectionBuilder).
 * Is also used in the facade pattern, as its methods are only ever used by the Builder facade
 * @author Cameron Stevenson
 *
 */
public class SelectionDirector {
  
  // order to balance for
  private PreparingOrder order;
  // available fillings
  private List<Filling> fillings;
  
  /**
   * Construct a SelectionDirector for this context
   * @param order order to balance for
   * @param fillings all available fillings
   */
  public SelectionDirector(PreparingOrder order, List<Filling> fillings) {
    this.order = order;
    this.fillings = fillings;
  }
  
  /**
   * Construct a balanced selection of eggs based on the order provided
   * @return A balanced selection of eggs
   */
  public Selection construct() {
    SelectionBuilder builder = new SelectionBuilder(order, fillings);
    
    PackagingType packagingType = order.getPackagingType();
    
    if(!PackagingType.isMixedPackaging(packagingType)) {
      builder.setOneChocolateType(true, order.getChocolateType());
    }
    
    if(PackagingType.isHollowEggPackaging(packagingType)) {
      builder.setOuterEgg(true);
    }
    
    if(packagingType == PackagingType.MIXED_BOX) {
      builder.limitCrunchy(true, 0.1F);
    }
    if(packagingType == PackagingType.REGULAR_BOX) {

    }
    if(packagingType == PackagingType.MIXED_HOLLOW_EGG) {
      builder.setOuterEggNotCrunchy(true);
    }
    if(packagingType == PackagingType.REGULAR_HOLLOW_EGG) {
      builder.setOuterEggWhiteIfCrunchy(true)
             .limitProportionHollow(true, 0.2F, 0.3F);
    }
    
    return builder.create();
  }
}
