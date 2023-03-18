# Spring Boot

- [Spring Boot](#spring-boot)
  - [Spring Boot Create](#spring-boot-create)
    - [Create new project](#create-new-project)

## Spring Boot Create
### Create new project
Create a basic application using the Spring Initializr using curl:
```
curl https://start.spring.io/starter.zip -d dependencies=web -d type=gradle-project -d bootVersion=3.0.4 -o demo.zip
```

Unpack the archive file:
```
tar -xf demo.zip
```

Run the application using Gradle:
```
.\gradlew bootRun
```

View the Application
```
curl http://localhost:8080
```
>output:
```
{"timestamp":"2023-03-18T00:14:54.510+00:00","status":404,"error":"Not Found","path":"/"}
```