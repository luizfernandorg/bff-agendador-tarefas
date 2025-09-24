FROM openjdk:21-jdk
LABEL authors="fernando"
WORKDIR /app
COPY target/bff-agendador-tarefas-0.0.1-SNAPSHOT.jar /app/bff-agendador-tarefas.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/bff-agendador-tarefas.jar"]