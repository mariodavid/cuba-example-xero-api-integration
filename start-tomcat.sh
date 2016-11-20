#!/bin/bash
./gradlew deploy start
cp  ./privatekey.pem ./build/tomcat/conf/app-core/privatekey.pem
