package io.newslab.service.http.impl;

import io.newslab.service.http.HttpRequest;

import java.util.Date;

public class HttpRequestImpl implements HttpRequest {

    private String url;

    private String lastEtag;

    private Date lastModified;

    private String accept;

    public HttpRequestImpl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastEtag() {
        return lastEtag;
    }

    public void setLastEtag(String lastEtag) {
        this.lastEtag = lastEtag;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String getAccept() {
        return accept;
    }

    @Override
    public void setAccept(String accept) {
        this.accept = accept;
    }
}
