package io.newslab.service.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public interface HttpResponse {

    int STATUS_OK = 200;
    int STATUS_NOT_MODIFIED = 304;
    int STATUS_NOT_FOUND = 404;
    int STATUS_INTERNAL_SERVER_ERROR = 500;

    InputStream getStream() throws IOException;

    int getHttpStatus();

    void abort();

    void releaseConnection();

    Date getLastModified();

    String getEtag();

    Date getExpires();

    String getContentType();
}
