package uc.seng301.asg3.balancing.selecting;

import java.util.ArrayList;

import uc.seng301.asg3.egg.ChocolateType;
import uc.seng301.asg3.ingredient.Filling;

/**
 * This is a part of the builder pattern. Represents a selection of chocolate types 
 * and fillings to use in a packaging. Is built by SelectionBuilder
 * @author Cameron Stevenson
 *
 */
public class Selection {
  // quantity-length lists of chocolate types and fillings, to be paired into eggs
  private ArrayList<ChocolateType> chocolateTypes;
  private ArrayList<Filling> fillings;
  
  // is there an outer egg
  private boolean outerEgg = false;
  private ChocolateType outerEggType;
  
  public Selection() {
    setChocolateTypes(new ArrayList<ChocolateType>());
    setFillings(new ArrayList<Filling>());
  }

  public ArrayList<ChocolateType> getChocolateTypes() {
    return chocolateTypes;
  }

  public void setChocolateTypes(ArrayList<ChocolateType> chocolateTypes) {
    this.chocolateTypes = chocolateTypes;
  }

  public ArrayList<Filling> getFillings() {
    return fillings;
  }

  public void setFillings(ArrayList<Filling> fillings) {
    this.fillings = fillings;
  }

  public boolean isOuterEgg() {
    return outerEgg;
  }

  public void setOuterEgg(boolean outerEgg) {
    this.outerEgg = outerEgg;
  }

  public ChocolateType getOuterEggType() {
    return outerEggType;
  }

  public void setOuterEggType(ChocolateType outerEggType) {
    this.outerEggType = outerEggType;
  }
  
}
