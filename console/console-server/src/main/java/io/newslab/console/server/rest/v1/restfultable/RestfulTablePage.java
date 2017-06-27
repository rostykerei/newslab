package io.newslab.console.server.rest.v1.restfultable;

import java.util.ArrayList;
import java.util.List;

public class RestfulTablePage<T> {

    private int pageSize;

    private int pageNumber;

    private long totalElements;

    private List<RestfulTableSort> sort = new ArrayList<>();

    private List<T> data;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<RestfulTableSort> getSort() {
        return sort;
    }
}