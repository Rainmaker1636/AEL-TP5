#!/bin/sh

mkdir classes
java -jar jflex-1.6.1.jar src/jadelex/jade.lex
javac -cp src/ -d classes/ src/jadelex/Test.java
echo ""
java -cp classes/ jadelex.Test testJade.txt
echo ""
java -jar DrawMachine.jar testJade.txt
