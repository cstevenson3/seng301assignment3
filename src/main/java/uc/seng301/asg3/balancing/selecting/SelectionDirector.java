package uc.seng301.asg3.balancing.selecting;

import java.util.List;

import uc.seng301.asg3.ingredient.Filling;
import uc.seng301.asg3.order.PreparingOrder;
import uc.seng301.asg3.packaging.PackagingType;

public class SelectionDirector {
  
  private PreparingOrder order;
  private List<Filling> fillings;
  
  public SelectionDirector(PreparingOrder order, List<Filling> fillings) {
    this.order = order;
    this.fillings = fillings;
  }
  
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
