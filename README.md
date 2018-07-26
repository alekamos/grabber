# Grabber

Spring Boot application that expose rest webService for grabbing html page

## Getting Started

Build the application with maven. The run the jar 
```
mvn install
java -jar target/grabber-1.0-SNAPSHOT.jar
```

On windows, double click on scriptRun.bat

You could also change the default port where you call services (default 8080)
```
mvn install
java -jar -Dserver.port = <port> target/grabber-1.0-SNAPSHOT.jar
```
## Usage

Once application is up and running you could call this service for grabbing element of a page
```
localhost:8080/grabHtmlData
```

These parameter allow you to navigate in html page
```
url=<url>
instruction=<arrayofInstruction>
```

Example:
```
grabHtmlData?url=http://www.bbc.com/&instruction=htmlTag:div,Sport,2
```

Grabber goes to bbc site and do this stuff:
1) search and list all html element "div"
2) search and list all element containing "Sport"
3) Go into element with assigned index 2



## Built With


* [Maven](https://maven.apache.org/) - Dependency Management



