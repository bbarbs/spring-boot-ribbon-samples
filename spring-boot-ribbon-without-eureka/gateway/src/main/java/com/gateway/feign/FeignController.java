package com.gateway.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/feign")
public class FeignController {

    @Autowired
    SampleClient sampleClient;

    @GetMapping(
            value = "/test",
            produces = TEXT_PLAIN_VALUE
    )
    public String test() {
        return this.sampleClient.test();
    }
}
