# SENG301 Assignment 1 (2020) - student answers


## Task 1 - Identify the patterns

### Template

- What pattern is it? 
- What is its goal in the code?
- Name of UML Class diagram attached:
- Mapping to GoF pattern elements:

| GoF element           | Code element          |
|-----------------------|-----------------------|
|                       |                       |

### Pattern 1 -  Factory Method

The factory method pattern is used to give a standard method for instantiating a product. In this case we want to always log creation of a HollowChocolateEgg, so we make a class HollowEggFactory (implementing ChocolateEggFactory) with a method createChocolateEgg which logs the creation and then returns a new HollowChocolateEgg (extending ChocolateEgg). This makes it easier to add more complicated common construction procedures later.

| GoF element           | Code element          |
|-----------------------|-----------------------|
| FactoryBase           | ChocolateEggFactory   |
| ProductBase           | ChocolateEgg          |
| ConcreteFactory       | HollowEggFactory      |
| ConcreteProduct       | HollowChocolateEgg    |

### Pattern 2 -  Abstract Factory

The abstract factory pattern is used in the creation of various chocolate eggs. The abstract factory, ChocolateEggFactory, standardizes what methods each chocolate egg creating factory (HollowEggFactory, StuffedEggFactory) should have. In this case the only method is the previously mentioned createChocolateEgg which takes in a type enum to determine what type of egg to create. But equally the abstract factory could specify a factory must have one method for each type if types were less related.

| GoF element           | Code element          |
|-----------------------|-----------------------|
| AbstractFactory       | ChocolateEggFactory   |
| AbstractProduct       | ChocolateEgg          |
| ConcreteFactory1      | HollowEggFactory      |
| ConcreteFactory2      | StuffedEggFactory     |
| ConcreteProductA1     | HollowChocolateEgg    |
| ConcreteProductA2     | StuffedChocolateEgg   |

### Pattern 3 - Command

The command pattern is used to define a command as an object. In this case the Order class is a command object, which only contains data sufficient to carry out the command "prepare", and method signatures for acting out the command. PreparingOrder implements these methods to carry out the command (using the relevant implementation of ChocolateEggFactory to do so), so acts as the concrete command. Counter and Chocolatier are both involved in arranging/invoking these commands.

| GoF element           | Code element                        |
|-----------------------|-------------------------------------|
| CommandBase           | Order                               |
| ConcreteCommand       | PreparingOrder                      |
| Invoker               | Counter/Chocolatier                 |
| Receiver              | ChocolateEggFactory implementations |
| Client                | App                                 |

## Task 2 - Full UML Class diagram

- Name of file of full UML Class diagram attached:
- More explanation (if needed):

## Task 3 - Implement new features

### Task 3.1 - balanced packaging 

- What pattern fulfils the need for the feature?
- What is its goal and why is it needed here?
- Name of UML Class diagram attached: 
- Mapping to GoF pattern elements:

| GoF element           | Code element          |
|-----------------------|-----------------------|
|                       |                       |

### Task 3.2 - fill in the packages with eggs

- What pattern fulfils the need for the feature?
- What is its goal and why is it needed here?
- Name of UML Class diagram attached: 
- Mapping to GoF pattern elements:

| GoF element           | Code element          |
|-----------------------|-----------------------|
|                       |                       |
