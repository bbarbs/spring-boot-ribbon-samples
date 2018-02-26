package com.customer.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping
public class WebController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${server.port}")
    int port;

    /**
     * This is will used by ribbon to check if application is alive.
     * Without this an error will throw.
     * <p>
     * java.lang.IllegalStateException: No instances available for customers
     *
     * @return
     */
    @GetMapping(
            value = "/",
            produces = TEXT_PLAIN_VALUE
    )
    public String ping() {
        this.logger.info("Request ping");
        return "Access";
    }

    /**
     * Get request to return what port used by the application.
     *
     * @return
     */
    @GetMapping(
            value = "/test",
            produces = TEXT_PLAIN_VALUE
    )
    public String test() {
        return "Eureka Implementation \n " +
                "Request to customer service running on port: " + port;
    }
}
