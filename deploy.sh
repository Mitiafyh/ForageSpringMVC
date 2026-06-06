#!/bin/bash

APP_NAME="ForageMVC"
TOMCAT_WEBAPPS="$HOME/Documents/tomcat/tomcat/webapps"

set -e

echo "Build Maven (WAR)..."
mvn -DskipTests clean package

echo "Déploiement Tomcat..."
cp -f target/$APP_NAME.war $TOMCAT_WEBAPPS/

echo "Redémarrage Tomcat..."
$HOME/Documents/tomcat/tomcat/bin/shutdown.sh
sleep 2
$HOME/Documents/tomcat/tomcat/bin/startup.sh

echo "OK - Déploiement terminé"