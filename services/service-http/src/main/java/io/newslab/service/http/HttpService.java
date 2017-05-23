package io.newslab.service.http;

import java.io.IOException;

public interface HttpService {

    HttpResponse execute(HttpRequest httpRequest) throws IOException;

    void setMaxContentLength(long bytes);

    long getMaxContentLength();
}
