package io.newslab.console.server.rest.v1.controller;

import io.newslab.console.server.rest.v1.response.HelloResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/hello", description = "Test API")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation(value = "Returns test Hello response")
    @RequestMapping(method = RequestMethod.GET)
    public HelloResponse message() {
        return new HelloResponse(123, "Hello World");
    }
}
