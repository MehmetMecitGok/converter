# Link Converter

* Link Converter Webservice, Spring Boot, Gradle, Java Restful Web API Project.
* Application will start at port 8080

## Requirements

* Java 8
* Spring Boot 2.4.4
* PostgreSql latest version
* JUNIT5

## Run with Docker
```bash
docker-compose up --build
```

## Usage Rest APIs
```java
POST /api/converter/webUrlToDeeplink         # Converts web url to deeplink
POST /api/converter/deeplinkToWebUrl         # Converts deeplink to web url