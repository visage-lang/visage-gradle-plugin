visage-gradle-plugin
====================

What is Gradle?
===============
Gradle is a general purpose Java build tool. Built on Groovy language but its also used for Java projects. You write build using Domain-Specific Language (DSL)

What is Visage?
===============
Visage is a domain specific language (DSL) designed for the express purpose of writing user interfaces.

What is visage-gradle-plugin?
=============================
This project is a Gradle plugin for Visage language. Using this plugin you can build and run Visage projects.

Usage
=====
This project is currently under development. 

Add following code snippet in your 'build.gradle' and you are all set!


      buildscript {
          	repositories {
                		repositories {	mavenRepo url: "http://jugchennai.github.com/visage-gradle-plugin/repository" } 
         	}
     	dependencies {	classpath 'visage:visage-gradle-plugin:0.1-SNAPSHOT' }
    }

    apply plugin: 'visage'




