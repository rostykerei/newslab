package io.newslab.console.server.rest.v1.restfultable;

public class RestfulTableSort {

    enum Direction {
        ASC,
        DESC
    }

    private String column;
    private Direction direction;

    public RestfulTableSort(String column, Direction direction) {
        this.column = column;
        this.direction = direction;
    }

    public String getColumn() {
        return column;
    }

    public Direction getDirection() {
        return direction;
    }
}
