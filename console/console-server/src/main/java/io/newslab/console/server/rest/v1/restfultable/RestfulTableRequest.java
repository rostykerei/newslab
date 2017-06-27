package io.newslab.console.server.rest.v1.restfultable;

import java.util.ArrayList;
import java.util.List;

public class RestfulTableRequest {

    private int pageSize;

    private int pageNumber;

    private List<RestfulTableSort> sort = new ArrayList<>();

    private String searchQuery;

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

    public List<RestfulTableSort> getSort() {
        return sort;
    }

    public void setSort(List<RestfulTableSort> sort) {
        this.sort = sort;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
