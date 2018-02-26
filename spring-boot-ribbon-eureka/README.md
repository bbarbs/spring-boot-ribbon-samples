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
