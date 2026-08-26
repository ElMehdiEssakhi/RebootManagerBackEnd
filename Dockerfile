# Utiliser une image de base Java
FROM openjdk:17-jdk-alpine

# Créer un répertoire pour l'application
WORKDIR /app

# Copier le JAR construit dans l'image
COPY target/*.jar app.jar

# Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
