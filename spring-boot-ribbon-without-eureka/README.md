# Getting Started
This sample project uses ribbon without eureka.

## Feature
* Feign
* Rest Template

## Gateway Configuration
Added are the list of servers where the customer application will be running. We disable eureka since we don't use it.
```
customers:
  ribbon:
    eureka:
      enabled: false
    listOfServers: http://localhost:8090, http://localhost:8091, http://localhost:8092
    ServerListRefreshInterval: 15000
```

## Customer Project Setup
* In customer controller we added a path where ribbon will check if application is alive.
```
@GetMapping(
            value = "/",
            produces = TEXT_PLAIN_VALUE
    )
    public String ping() {
        return "Access";
    }
```
Without this we will encounter a error: **java.lang.IllegalStateException: No instances available for customers**.

## Testing
To test ribbon run the customer jar in different ports **8090, 8091, 8092**.
> java -Dserver.port=8090 -jar customer-0.0.1-SNAPSHOT.jar

Then the gateway project in port **8080**. After running those project check the http://localhost:8080/resttemplate/test. When you refresh the page port will change. See test results [here](https://github.com/bbarbs/spring-boot-ribbon-samples/tree/master/spring-boot-ribbon-without-eureka/test-results)
<br/>
<br/>
![](https://github.com/bbarbs/spring-boot-ribbon-samples/blob/master/spring-boot-ribbon-without-eureka/test-results/port%208090.PNG)
<br/>
<br/>
![](https://github.com/bbarbs/spring-boot-ribbon-samples/blob/master/spring-boot-ribbon-without-eureka/test-results/port%208091.PNG)
<br/>
<br/>
![](https://github.com/bbarbs/spring-boot-ribbon-samples/blob/master/spring-boot-ribbon-without-eureka/test-results/port%208092.PNG)





