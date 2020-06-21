#!/bin/sh
app=durak-card-game.jar
mvn clean package
cd target
mv durak-card-game-0.0.1-SNAPSHOT.jar $app
java -jar $app