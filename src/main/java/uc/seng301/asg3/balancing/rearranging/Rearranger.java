package uc.seng301.asg3.balancing.rearranging;

import java.util.Collections;
import java.util.List;

import uc.seng301.asg3.egg.ChocolateEgg;
import uc.seng301.asg3.egg.StuffedChocolateEgg;
import uc.seng301.asg3.ingredient.Filling;
import uc.seng301.asg3.order.PreparingOrder;
import uc.seng301.asg3.packaging.Packaging;
import uc.seng301.asg3.packaging.PackagingType;

/**
 * This is a part of the facade pattern. Through Balancer it provides methods 
 * related to rearranging the eggs in a packaging
 * @author Cameron Stevenson
 *
 */
public class Rearranger {
  
  /**
   * Rearrange packaging to meet requirements of spacing of identical eggs
   * @param order The order containing the packaging to be balanced
   */
  public void rearrangePackaging(PreparingOrder order) {
    while(!isPackageDistributedSuitably(order)) {
      if(PackagingType.isHollowEggPackaging(order.getPackaging().getPackagingType())) {
        Collections.shuffle(order.getPackaging().getEggs().get(0).getContent());
      } else {
        Collections.shuffle(order.getPackaging().getEggs());
      }
    }
  }
  
  /**
   * Are identical eggs spread away from each other?
   * @param packaging the packaging to check
   * @return true if identical eggs are not next to each other
   */
  private boolean isPackageDistributedSuitably(PreparingOrder order) {
    Packaging packaging = order.getPackaging();
    if(!order.isStuffed() && !PackagingType.isMixedPackaging(packaging.getPackagingType())) {
      // then distributing is impossible
      return true;
    }
    List<ChocolateEgg> eggs;
    if(PackagingType.isHollowEggPackaging(packaging.getPackagingType())) {
      eggs = packaging.getEggs().get(0).getContent();
    } else {
      eggs = packaging.getEggs();
    }
    
    for(int i = 0; i < eggs.size() - 1; i++) {
      ChocolateEgg egg1 = eggs.get(i);
      ChocolateEgg egg2 = eggs.get(i + 1);
      boolean sameChocolateType = egg1.getChocolateType() == egg2.getChocolateType();
      Filling filling1;
      if(egg1 instanceof StuffedChocolateEgg) {
        filling1 = ((StuffedChocolateEgg) egg1).getFilling();
      } else {
        filling1 = null;
      }
      Filling filling2;
      if(egg2 instanceof StuffedChocolateEgg) {
        filling2 = ((StuffedChocolateEgg) egg2).getFilling();
      } else {
        filling2 = null;
      }
      boolean sameFilling = filling1 == filling2;
      if(sameChocolateType && sameFilling) {
        return false;
      }
    }
    return true;
  }
}
