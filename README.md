visage-gradle-plugin
====================

What is Gradle?
===============
Gradle is a general purpose Java build tool. Built on Groovy language but its also used for Java projects. 
You write build using Domain-Specific Language (DSL).

What is Visage?
===============
Visage is a domain specific language (DSL) designed for the express purpose of writing user interfaces.

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
               classpath 'visage:visage-gradle-plugin:0.1' }
          }

     apply plugin: 'visage'
	apply plugin: 'visage-template'  

Put all your visage files at `src/main/visage` folder

visage-template - In future this command will be placed in Project Gradle Template

Tasks
============
createVisgeProject - Creates a new visge project by asking details of project, group name and version.
compileVisage - Compile all Visage files.
runVisage - runs the calss assigned to `visageMainClass` variable.

Prerequisite
============
Before you use this plugin you need following in your syste:
* JDK 1.6+
* Gradle - If you dont have just run `gradlew`
* Visage Compiler - 
* VisageFX.jar - You need to build from source to create the jar
* JavaFX 2

Before you start using the plugin make sure you have installed Visage Compiler to your system and set the VISAGE_HOME.

