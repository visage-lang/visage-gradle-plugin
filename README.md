visage-gradle-plugin
====================

What is Visage?
===============
Visage is a domain specific language (DSL) designed for the express purpose of writing user interfaces.

What is Gradle?
===============
Gradle is a general purpose Java build tool. Built on Groovy language but its also used for Java projects. 
You write build using Domain-Specific Language (DSL).

What is visage-gradle-plugin?
=============================
This project is a Gradle plugin for Visage language. Using this plugin you can build and run Visage projects.

Usage
=====
This project is currently under development. 

Add following code snippet in your `build.gradle` and you are all set!

     buildscript {
          repositories {
               mavenLocal();
               repositories {	mavenRepo url: "http://visage-lang.github.com/visage-gradle-plugin/repository/" } 
          }
          dependencies {	
               classpath 'visage:visage-gradle-plugin:0.5' 
          }
     }

     apply plugin: 'visage-template'  

Put all your visage files at `src/main/visage` folder

visage-template - In future this command will be placed in Project Gradle Template

Plugins
=======
`visage` Core Visage plugin 
`visage-template` Plugin to create visage gradle project with sample application.
`visage-javafx` Visage JavaFX plugin. Contains default current release of Visge compiler/runner and Visagefx dependency 

Tasks
============
`createVisgeProject` - Creates a new visge project by asking details of project, group name and version.
`compileVisage` - Compile all Visage files.
`runVisage` - runs the calss assigned to `visageMainClass` variable.

Prerequisite
============
* JDK 1.7+
* Gradle

