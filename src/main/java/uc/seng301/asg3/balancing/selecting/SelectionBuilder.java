package uc.seng301.asg3.balancing.selecting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uc.seng301.asg3.egg.ChocolateType;
import uc.seng301.asg3.ingredient.Filling;
import uc.seng301.asg3.order.PreparingOrder;

public class SelectionBuilder {
  // quantity of eggs
  private int quantity;
  
  // is the selection allowed to contain alcohol
  private boolean containsAlcohol;
  
  // some eggs are stuffed
  private boolean stuffed;
  
  // fillings available
  private List<Filling> fillings;
  
  // limit the amount of crunchy chocolate type eggs
  private boolean limitCrunchy = false;
  private float maxProportionCrunchy;
  
  // limit chocolate types to just one
  private boolean oneChocolateType = false;
  private ChocolateType chocolateType = null;
  
  //should there be an outer egg
  private boolean outerEgg = false;
  
  // make the outer egg not crunchy
  private boolean outerEggNotCrunchy = false;
  
  // if regular chocolateType is crunchy, make it white
  private boolean outerEggWhiteIfCrunchy = false;
  
  // limit the amount of hollow chocolate eggs
  private boolean limitProportionHollow = false;
  private float minProportionHollow;
  private float maxProportionHollow;
  
  public SelectionBuilder(PreparingOrder order, List<Filling> fillings) {
    quantity = order.getQuantity();
    stuffed = order.isStuffed();
    containsAlcohol = order.containsAlcohol();
    this.fillings = fillings;
  }
  
  /**
   * Limit the amount of crunchy chocolate eggs in this selection?
   * @param limitCrunchy true or false
   * @param maxProportionCrunchy At most how many chocolate eggs by proportion should be crunchy?
   * @return this
   */
  public SelectionBuilder limitCrunchy(boolean limitCrunchy, float maxProportionCrunchy) {
    this.limitCrunchy = limitCrunchy;
    this.maxProportionCrunchy = maxProportionCrunchy;
    return this;
  }
  
  /**
   * Should there only be one type of chocolate in this selection?
   * @param oneChocolateType true or false
   * @param chocolateType The one type to use
   * @return this
   */
  public SelectionBuilder setOneChocolateType(boolean oneChocolateType, ChocolateType chocolateType) {
    this.oneChocolateType = oneChocolateType;
    this.chocolateType = chocolateType;
    return this;
  }
  
  /**
   * Should this selection have an outer egg?
   * @param outerEgg true or false
   * @return this
   */
  public SelectionBuilder setOuterEgg(boolean outerEgg) {
    this.outerEgg = outerEgg;
    return this;
  }
  
  /**
   * Should the outer egg not be crunchy?
   * @param outerEggNotCrunchy true or false
   * @return
   */
  public SelectionBuilder setOuterEggNotCrunchy(boolean outerEggNotCrunchy) {
    this.outerEggNotCrunchy = outerEggNotCrunchy;
    return this;
  }
  
  /**
   * If the outer egg is crunchy, should it be made white?
   * @param outerEggWhiteIfCrunchy
   * @return
   */
  public SelectionBuilder setOuterEggWhiteIfCrunchy(boolean outerEggWhiteIfCrunchy) {
    this.outerEggWhiteIfCrunchy = outerEggWhiteIfCrunchy;
    return this;
  }
  
  /**
   * Limit the proportion of eggs not hollow
   * @param limitProportionHollow true or false
   * @param minProportionHollow minimum proportion
   * @param maxProportionHollow maximum proportion
   * @return
   */
  public SelectionBuilder limitProportionHollow(boolean limitProportionHollow, float minProportionHollow, float maxProportionHollow) {
    this.limitProportionHollow = limitProportionHollow;
    this.minProportionHollow = minProportionHollow;
    this.maxProportionHollow = maxProportionHollow;
    return this;
  }
  
  /**
   * build the Selection
   * @return the Selection with the given build parameters
   */
  public Selection create() {
    Selection selection = new Selection();
    
    if (outerEgg) {
      ChocolateType outerEggType = null;
      if(oneChocolateType) {
        if(outerEggWhiteIfCrunchy && chocolateType == ChocolateType.CRUNCHY) {
          outerEggType = ChocolateType.WHITE;
        } else {
          outerEggType = chocolateType;
        }
      } else if (outerEggNotCrunchy) {
        outerEggType = ChocolateType.MILK;
      }
      selection.setOuterEgg(true);
      selection.setOuterEggType(outerEggType);
    }
    
    // generate balancing requirements
    ArrayList<ChocolateType> chocolateTypesToUse = new ArrayList<ChocolateType>();
    if(oneChocolateType) {
      for(int i = 0; i < quantity; i++) {
        chocolateTypesToUse.add(chocolateType);
      }
    } else {
      ChocolateType[] availableChocolateTypesUnfiltered = ChocolateType.values();
      ArrayList<ChocolateType> availableChocolateTypes = new ArrayList<ChocolateType>();
      
      // put crunchy first
      availableChocolateTypes.add(ChocolateType.CRUNCHY);
      for(int ctu = 0; ctu < availableChocolateTypesUnfiltered.length; ctu++) {
        if(availableChocolateTypesUnfiltered[ctu] != ChocolateType.CRUNCHY) {
          availableChocolateTypes.add(availableChocolateTypesUnfiltered[ctu]);
        }
      }
      int quantityRemaining = quantity;
      int numChocTypesRemaining = availableChocolateTypes.size();
      for(int c = 0; c < availableChocolateTypes.size(); c++) {
        float approxChocTypeQuantity;
        int chocTypeQuantity;
        if(limitCrunchy && availableChocolateTypes.get(c) == ChocolateType.CRUNCHY) {
          approxChocTypeQuantity = ((float)quantityRemaining) * maxProportionCrunchy;
          chocTypeQuantity = (int) Math.floor(approxChocTypeQuantity);
        } else {
          approxChocTypeQuantity = ((float)quantityRemaining) / ((float)numChocTypesRemaining);
          chocTypeQuantity = Math.round(approxChocTypeQuantity);
        }
        
        // add this many to chocolateTypesToUse
        for(int ct = 0; ct < chocTypeQuantity; ct++) {
          chocolateTypesToUse.add(availableChocolateTypes.get(c));
        }
        quantityRemaining -= chocTypeQuantity;
        numChocTypesRemaining -= 1;
      }
    }
    
    ArrayList<Filling> fillingsToUse = new ArrayList<Filling>();
    if(!stuffed) {
      for(int i = 0; i < quantity; i++) {
        fillingsToUse.add(null);
      }
    } else {
      List<Filling> unfilteredAvailableFillings = fillings;
      List<Filling> availableFillings = new ArrayList<Filling>();
      availableFillings.add(null); // null will represent hollow eggs
      for(Filling fill : unfilteredAvailableFillings) {
        if(!(fill.containsAlcohol() && !containsAlcohol)) {
          availableFillings.add(fill);
        }
      }
      int quantityRemaining = quantity;
      int numFillingsRemaining = availableFillings.size();
      for(int f = 0; f < availableFillings.size(); f++) {
        float approxFillingQuantity;
        int fillingQuantity;
        if(limitProportionHollow && availableFillings.get(f) == null) {
          approxFillingQuantity = ((float)quantityRemaining + 1) * maxProportionHollow - 1;
          fillingQuantity = (int)Math.floor(approxFillingQuantity);
        } else {
          approxFillingQuantity = ((float)(quantityRemaining)) / ((float)numFillingsRemaining);
          fillingQuantity = Math.round(approxFillingQuantity);
        }
        // add this many to fillingsToUse
        for(int ct = 0; ct < fillingQuantity; ct++) {
          fillingsToUse.add(availableFillings.get(f));
        }
        quantityRemaining -= fillingQuantity;
        numFillingsRemaining -= 1;
      }
    }
    
    // shuffle so different choc types end up with different fillings
    Collections.shuffle(chocolateTypesToUse);
    Collections.shuffle(fillingsToUse);
    
    selection.setChocolateTypes(chocolateTypesToUse);
    selection.setFillings(fillingsToUse);
    selection.setOuterEgg(outerEgg);
    
    return selection;
  }
}
