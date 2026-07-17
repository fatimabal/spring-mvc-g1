# ---------- Étape 1 : Build du projet avec Maven ----------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# On copie d'abord le pom.xml pour profiter du cache Docker sur les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# On copie le reste du code source
COPY src ./src

# Build du .war (les tests sont sautés pour accélérer le build CI)
RUN mvn clean package -DskipTests

# ---------- Étape 2 : Image finale avec Tomcat ----------
FROM tomcat:10.1-jdk17-temurin

# On vide le webapps par défaut (supprime la doc Tomcat, etc.)
RUN rm -rf /usr/local/tomcat/webapps/*

# On copie le .war généré à l'étape précédente et on le renomme en ROOT.war
# pour qu'il soit accessible directement à la racine (http://localhost:8080/)
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
