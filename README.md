# Stack

Built a simple shortURL web application using:
* Spring Boot
* Thymeleaf template engine
* MongoDB

# Features
* Create a "short" URL from another, perhaps longer, URL, and persist it in db.
* Receive a request for the short URL and redirect to the long URL.

# Requirements
* Java 11
* MongoDB

# How to
* Build: `mvn install`
* Start MongoDB: `docker run -p 27017:27017 --name shorturl-mongo mongo:latest`
* Run packaged application: `java -jar target/shortURL-0.0.1-SNAPSHOT.jar`
* Or run using foreman: `foreman run web`
* Access application: http://localhost:8080/
