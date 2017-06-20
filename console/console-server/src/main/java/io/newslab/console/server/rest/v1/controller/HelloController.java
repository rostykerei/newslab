package io.newslab.console.server.rest.v1.controller;

import io.newslab.console.server.rest.v1.response.HelloResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public HelloResponse message() {
        return new HelloResponse(123, "Hello World");
    }
}
