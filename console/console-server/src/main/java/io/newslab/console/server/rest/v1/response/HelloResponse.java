package io.newslab.console.server.rest.v1.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Hello response type model")
public class HelloResponse {

    private int number;

    private String name;

    public HelloResponse(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @ApiModelProperty(allowableValues = "1,2,3", example = "5")
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
