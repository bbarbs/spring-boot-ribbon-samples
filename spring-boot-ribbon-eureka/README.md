# Getting Started
This sample project uses ribbon with eureka server.

## Feature
* Feign
* Rest Template

## Eureka Server Configuration
We disable register of eureka since it will be the server.
```
eureka:
  client:
    fetch-registry: false
    register-with-eureka: false
```
We enable the eureka server in this spring application.
```
@SpringBootApplication
@EnableEurekaServer
public class ServerApplication {
```

## Gateway Configuration
Since we are using eureka we don't need to specify list of servers. Added config is the refresh interval so we could notice the change of 
application port being accessed.
```
ribbon:
  ServerListRefreshInterval: 15000
```
We can directly use the name of service for ribbon. This sample for FeignClient and RestTemplate.
```
@FeignClient(
        value = "customer-service"
)

@RibbonClient(name = "customer-service", configuration = ServiceConfiguration.class)
```
Then we enable eureka client discovery.
```
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class GatewayApplication {
```

## Customer Configuration
Enable the eureka client discovery.
```
@SpringBootApplication
@EnableEurekaClient
public class CustomerApplication {
```

## Testing
First run the eureka server which is server project. After running the server check the http://localhost:8761/ to determine if is good and up.
<br/>
Then run the gateway project.
<br/>
Then in customer project run **gradle build** to generate jar, and run the jar in different ports **9090, 9091, 9092**.
> ex: java -Dserver.port=9090 -jar customer-0.0.1-SNAPSHOT.jar


When everything is running check again the http://localhost:8761/ and you can see the instance of projects running.
![](https://github.com/bbarbs/spring-boot-ribbon-samples/blob/master/spring-boot-ribbon-eureka/test-results/eureka%20server.PNG)
<br/>
<br/>

Then check http://localhost:8080/feign/test or http://localhost:8080/resttemplate/test to check the load balancing when you refresh the browser the port will change like the ff:
<br/>
<br/>
![](https://github.com/bbarbs/spring-boot-ribbon-samples/blob/master/spring-boot-ribbon-eureka/test-results/port%209090.PNG)
<br/>
<br/>

![](https://github.com/bbarbs/spring-boot-ribbon-samples/blob/master/spring-boot-ribbon-eureka/test-results/port%209091.PNG)
<br/>
<br/>

![](https://github.com/bbarbs/spring-boot-ribbon-samples/blob/master/spring-boot-ribbon-eureka/test-results/port%209092.PNG)
<br/>
<br/>


