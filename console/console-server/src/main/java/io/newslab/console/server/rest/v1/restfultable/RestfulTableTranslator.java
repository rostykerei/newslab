package io.newslab.console.server.rest.v1.restfultable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class RestfulTableTranslator {

    public static <T> RestfulTablePage<T> translate(Page<T> page) {
        RestfulTablePage<T> result = new RestfulTablePage<>();

        result.setPageSize(page.getSize());
        result.setPageNumber(page.getNumber() + 1);
        result.setTotalElements(page.getTotalElements());
        result.setData(page.getContent());

        result.getSort().addAll(translate(page.getSort()));

        return result;
    }

    public static Pageable translate(RestfulTableRequest request) {
        return PageRequest.of(
                request.getPageNumber() - 1,
                request.getPageSize(),
                translate(request.getSort())
        );
    }

    public static Sort translate(List<RestfulTableSort> sort) {
        List<Sort.Order> orders = new ArrayList<>();

        for (RestfulTableSort s : sort) {
            orders.add(translate(s));
        }

        return Sort.by(orders);
    }

    public static List<RestfulTableSort> translate(Sort sort) {
        List<RestfulTableSort> result = new ArrayList<>();

        for (Sort.Order order : sort) {
            result.add(translate(order));
        }

        return result;
    }

    public static Sort.Order translate(RestfulTableSort sort) {
        if (sort.getDirection() == RestfulTableSort.Direction.DESC) {
            return Sort.Order.desc(sort.getColumn());
        }

        return Sort.Order.asc(sort.getColumn());
    }

    public static RestfulTableSort translate(Sort.Order order) {
        return new RestfulTableSort(order.getProperty(), translate(order.getDirection()));
    }

    public static RestfulTableSort.Direction translate(Sort.Direction direction) {
        if (direction.isDescending()) {
            return RestfulTableSort.Direction.DESC;
        }
        else {
            return RestfulTableSort.Direction.ASC;
        }
    }

}
