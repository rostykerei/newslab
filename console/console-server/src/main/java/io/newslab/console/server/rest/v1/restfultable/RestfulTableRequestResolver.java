package io.newslab.console.server.rest.v1.restfultable;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RestfulTableRequestResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(RestfulTableRequest.class);
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {

        String pageSize = nativeWebRequest.getParameter("pageSize");
        String pageNumber = nativeWebRequest.getParameter("pageNumber");
        String searchQuery = nativeWebRequest.getParameter("searchQuery");

        String sort[] = nativeWebRequest.getParameterValues("sort");

        RestfulTableRequest result = new RestfulTableRequest();

        result.setPageSize(parseInt(pageSize, 25));
        result.setPageNumber(parseInt(pageNumber, 1));
        result.setSearchQuery(searchQuery);

        if (sort != null) {
            for (String s : sort) {
                result.getSort().add(parseSort(s));
            }
        }

        return result;
    }

    private RestfulTableSort parseSort(String input) {
        String[] parsed = input.split(",");

        return new RestfulTableSort(parsed[0],
                "DESC".equalsIgnoreCase(parsed[1]) ? RestfulTableSort.Direction.DESC : RestfulTableSort.Direction.ASC
        );
    }

    private int parseInt(String input, int defaultValue) {
        if (input != null && input.length() > 0) {
            return Integer.parseInt(input);
        }
        else {
            return defaultValue;
        }
    }
}
