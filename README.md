# SENG301 Assignment 3 (2020)

## Context

This material is part of the SENG301 assignment 3. It contains a series of Java 
classes meant to set the framework for students to analyse the presence of 
design patterns and weave new patterns to fulfil new features. Please take some 
to familiarise yourself with the code and its structure.

This assignment is meant to simulate the baking of Easter Egg Chocolate:

- Clients can pass orders of chocolate eggs (using a Command Line Interface, CLI)
- Eggs are of various types and sizes
- A (Belgian) Chocolatier prepares the eggs and put them in a packaging

## Authors

Initial contribution by SENG301 teaching team.

## Run the project

This project relies on gradle (version 5 or later). See `build.gradle` file for 
full list of dependencies. You can use the built-in scripts to bootstrap the 
project (`gradlew` on Linux/Mac or `gradlew.bat` on Windows).

To build the project, place yourself at the root folder of this project, then, in 
a command line:

- On Windows: type `gradlew.bat build` 
- On Linux/Mac: type `./gradlew build` 

To run the Common Line Interface application, from the root folder:

- On Windows: type `gradlew.bat --console=PLAIN run` 
- On Linux/Mac: type `./gradlew --console=PLAIN run`

The option `--console=PLAIN` is passed to suppress part of the coloured output 
of gradle that may interfere with the CLI. More details about gradle, see 
[Gradle Website](https://gradle.org/).

## Copyright notice

Copyright (c) 2020. University of Canterbury

This file is part of SENG301 lab material. 

The lab material is free software: you can redistribute it and/or modify it under 
the terms of the GNU Lesser  General Public License as published by the Free 
Software Foundation, either version 3 of the License, or (at your  option) any 
later version.

The lab material is distributed in the hope that it will be useful, but WITHOUT 
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
FITNESS FOR A PARTICULAR PURPOSE.  

See the GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License along 
with this material.  If not, see https://www.gnu.org/licenses/. 