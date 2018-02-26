package com.gateway.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

/**
 * Customers rest api.
 * <p>
 * Note: It use the ribbon for client load balancer as added in yml.
 */

@FeignClient(
        name = "customers"
)
public interface SampleClient {


    @GetMapping(
            value = "/test",
            produces = TEXT_PLAIN_VALUE
    )
    String test();
}
