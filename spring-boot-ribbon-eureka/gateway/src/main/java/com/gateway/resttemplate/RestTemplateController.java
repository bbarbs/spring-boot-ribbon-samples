package com.gateway.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/resttemplate")
@RibbonClient(name = "customer-service", configuration = ServiceConfiguration.class)
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(
            value = "/test",
            produces = TEXT_PLAIN_VALUE
    )
    public String test() {
        String str = this.restTemplate.getForObject("http://customer-service/test", String.class);
        return str;
    }
}
