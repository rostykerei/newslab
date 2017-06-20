package io.newslab.console.server.rest.v1.response;

/**
 * Created by rosty on 20/06/2017.
 */
public class HelloResponse {

    private int number;

    private String name;

    public HelloResponse(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
