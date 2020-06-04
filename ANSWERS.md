# SENG301 Assignment 1 (2020) - student answers

## Task 1 - Identify the patterns

### Pattern 1 -  Factory Method

FactoryMethod.png

The factory method pattern is used to give a standard method for instantiating a product. In this case we want to always log creation of a HollowChocolateEgg, so we make a class HollowEggFactory (implementing ChocolateEggFactory) with a method createChocolateEgg which logs the creation and then returns a new HollowChocolateEgg (extending ChocolateEgg). This makes it easier to add more complicated construction procedures later.

| GoF element           | Code element          |
|-----------------------|-----------------------|
| FactoryBase           | ChocolateEggFactory   |
| ProductBase           | ChocolateEgg          |
| ConcreteFactory       | HollowEggFactory      |
| ConcreteProduct       | HollowChocolateEgg    |

### Pattern 2 -  Abstract Factory

AbstractFactory.png

The abstract factory pattern is used in the creation of various chocolate eggs. The abstract factory, ChocolateEggFactory, standardizes what methods each chocolate egg creating factory (HollowEggFactory, StuffedEggFactory) should have. In this case the only method is the previously mentioned createChocolateEgg which takes in a type enum to determine what type of egg to create. But equally the abstract factory could specify a method for each different egg type if they were less related.

| GoF element           | Code element          |
|-----------------------|-----------------------|
| AbstractFactory       | ChocolateEggFactory   |
| AbstractProduct       | ChocolateEgg          |
| ConcreteFactory1      | HollowEggFactory      |
| ConcreteFactory2      | StuffedEggFactory     |
| ConcreteProductA1     | HollowChocolateEgg    |
| ConcreteProductA2     | StuffedChocolateEgg   |

### Pattern 3 - Command

Command.png

The command pattern is used to define a command as an object. In this case the Order class is a command object, which only contains data sufficient to carry out the command "prepare", and method signatures for acting out the command. PreparingOrder implements these methods to carry out the command (using the relevant implementation of ChocolateEggFactory to do so), so acts as the concrete command. Counter and Chocolatier serve to arrange and invoke these commands. App, the client, uses the neater interface of Counter to place orders and receive results.

| GoF element           | Code element                        |
|-----------------------|-------------------------------------|
| CommandBase           | Order                               |
| ConcreteCommand       | PreparingOrder                      |
| Invoker               | Counter/Chocolatier                 |
| Receiver              | ChocolateEggFactory implementations |
| Client                | App                                 |

## Task 2 - Full UML Class diagram



## Task 3 - Implement new features

### Task 3.1 - balanced packaging 

Builder.png

To get an idea of the problem, I started by putting all of the balancing logic in PreparingOrder.prepare, where I would generate quantity-length lists of chocolate types and fillings to be used, and these would be used in generating the calls to PreparingOrder.produceEgg. I found the biggest challenge was dealing with complicated overlapping requirements and behaviours when generating these lists, and sought a way to separate out the steps of (order -> requirements -> list generation). To start off with I defined a class Selection which would be the product of this process, storing these lists and representing a selection of chocolate eggs to be produced. So then the goal became to build up one of these depending on the requirements. I selected the builder pattern for this, so that I could use a director SelectionDirector to interpret the order, and call methods on the builder SelectionBuilder to build up the requirements to be used. Then SelectionBuilder.create would generate the Selection and return it.

This implementation of the builder pattern doesn't have an abstract builder, since only one builder type was necessary for now. If another builder was necessary later, the overlap between the two could be considered and they could both implement an abstract builder with these features. Also SelectionDirector.construct does not accept a SelectionBuilder as an argument. I'm assuming the default builder pattern includes this so that callers of the director can add their own requests to the builder before the director takes over, but in this case all selection logic lies within the "selection" package so I omitted this for now. An overloaded method which accepts a SelectionBuilder could be added later if necessary.

| GoF element           | Code element          |
|-----------------------|-----------------------|
| Director              | SelectionDirector     |
| Builder               | SelectionBuilder      |
| Product               | Selection             |

### Task 3.2 - fill in the packages with eggs

Facade.png

If you consider selecting and rearranging eggs compared to producing eggs, these tasks are distinct, and so selecting and rearranging can be put into their own package, "balancing". However selecting is distinct enough from rearranging for them to be in their own subpackages. I want a facade for balancing that presents the useful balancing methods from across these two processes, so I used the facade pattern. In the package balancing the class Balancer is the facade for the subpackages involved with balancing. The subpackage "selecting" contains the selection classes in task 3.1, whilst the subpackage "rearranging" contains Rearranger, which handles rearranging the eggs in the package to keep identical ones away from each other. The facade accepts calls from outside the package, and calls methods in the appropriate subpackages to complete the request. Should the complicated subpackages change, the facade can updated to fit them whilst staying constant on the outside.

| GoF element           | Code element          |
|-----------------------|-----------------------|
| Facade                | Balancer              |
| PackageA              | balancing.selecting   |
| ClassA1               | SelectionDirector     |
| PackageB              | balancing.rearranging |
| ClassB1               | Rearranger            |
