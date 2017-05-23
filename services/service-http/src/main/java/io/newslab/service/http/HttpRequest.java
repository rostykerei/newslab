package io.newslab.service.http;

import java.util.Date;

public interface HttpRequest {

    String getUrl();

    void setUrl(String url);

    String getLastEtag();

    void setLastEtag(String lastEtag);

    Date getLastModified();

    void setLastModified(Date lastModified);

    String getAccept();

    void setAccept(String accept);
}
