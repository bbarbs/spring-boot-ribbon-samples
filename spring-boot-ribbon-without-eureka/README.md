# Getting Started
This sample project implements feign and hystrix. It uses feignclient to call the customer rest api and propagate also the error to the end user when exception occur in customer service using feign error decoder.

## Feature
* Feign
* Hystrix
* Swagger

## Gateway Configuration
* Hystrix timeout
```
hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 4000
```
* Enabling hystrix in feign
```
feign:
  hystrix:
    enabled: true
    
@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
@EnableFeignClients
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
```
* For sample usage of loadbalancer i enable ribbon and disable eureka, since we don't use it.
```
@FeignClient(
        name = "customers",
        fallbackFactory = CustomerFallbackFactory.class
)
=========================================================
customers:
  ribbon:
    eureka:
      enabled: false
    listOfServers: http://localhost:8080
```

## Server ports
```
Customer
server:
  port: 8080
  
Gateway
server:
  port: 8090
```

## Gateway Feignclient Custom Handling
It uses [ErrorDecoder](https://github.com/OpenFeign/feign/wiki/Custom-error-handling) to manage exception and to propagate it in controller in Gateway.
<br/>
Then you can provide the builder of feign client.
```
public CustomerController() {
        // Create builder to add the error decoder.
        this.customerClient = HystrixFeign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .errorDecoder(new GatewayErrorDecoder())
                .target(CustomerClient.class, "http://localhost:8080", new CustomerFallbackFactory());
    }
```

## Swagger
* Check the http://localhost:8090/swagger-ui.html to view the api.

## Hystrix Dashboard
* You can access the **http://localhost:8090/hystrix** and the hostname **http://localhost:8090/hystrix.stream**

## Running Test Case
* Test case in gateway project is using [Wiremock](http://wiremock.org/).
* To test hystrix fallback, stop the customer project and run test case in gateway project and it will log the fallback data. **Note:** When the customer project is running and test case is run wiremock will perform the operation.

