# Empezamos con una imagen base de Maven para construir nuestro proyecto
FROM maven:3.8-openjdk-17 AS build

# Definimos el directorio de trabajo en el contenedor
WORKDIR /Medicalert

# Copiamos el pom.xml por separado para aprovechar la capa de caché de Docker
# Esto es útil si solo se modifican los archivos de código fuente y no el archivo pom.xml
COPY pom.xml .

# Descargamos todas las dependencias según lo que hay en pom.xml
RUN mvn dependency:go-offline -B

# Copiamos el resto de la aplicación
COPY src ./src

# Instalamos la aplicación
RUN mvn clean install

# Construimos la aplicación
RUN mvn clean package

# Ahora, en una imagen base de Java 17, ejecutamos nuestra aplicación
FROM openjdk:17.0.1-jdk-slim

# Copiamos el JAR del build anterior
COPY --from=build /Medicalert/target/*.jar /Medicalert/Medicalert-0.0.1-SNAPSHOT.jar

# Exponemos el puerto en el que se ejecutará la aplicación (por ejemplo, 8080)
EXPOSE 9090

# Corremos el JAR
ENTRYPOINT ["java","-jar","/Medicalert/Medicalert-0.0.1-SNAPSHOT.jar"]

