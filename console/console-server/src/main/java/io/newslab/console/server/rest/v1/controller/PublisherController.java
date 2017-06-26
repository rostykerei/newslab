package io.newslab.console.server.rest.v1.controller;

import io.newslab.persistence.model.Publisher;
import io.newslab.persistence.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepository;

    @RequestMapping(method = RequestMethod.GET)
    public RestfulTablePage<Publisher> list(Pageable pageable) {
        Page<Publisher> publisherPage = publisherRepository.findAll(pageable);

        RestfulTablePage<Publisher> result = new RestfulTablePage<>();

        result.setPageSize(publisherPage.getSize());
        result.setPageNumber(publisherPage.getNumber() + 1);
        result.setTotalPages(publisherPage.getTotalPages());
        result.setTotalElements(publisherPage.getTotalElements());
        result.setData(publisherPage.getContent());

        for (Sort.Order order : publisherPage.getSort()) {
            result.getSort().add(new RestfulTableSort(order.getProperty(), order.getDirection().toString()));
        }

        return result;
    }

    class RestfulTablePage<T> {

        private int pageSize;

        private int pageNumber;

        private int totalPages;

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

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
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

    class RestfulTableSort {
        private String fieldName;
        private String direction;

        public RestfulTableSort(String fieldName, String direction) {
            this.fieldName = fieldName;
            this.direction = direction;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getDirection() {
            return direction;
        }
    }
}
