package io.newslab.service.http.impl;

import java.io.IOException;
import java.net.URLEncoder;

public class HttpServiceUtils {

    public static String urlEncode(String input) throws IOException {
        return URLEncoder.encode(input, "UTF-8");
    }
}
