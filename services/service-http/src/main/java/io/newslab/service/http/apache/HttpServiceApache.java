package io.newslab.service.http.apache;

import io.newslab.service.http.HttpRequest;
import io.newslab.service.http.HttpResponse;
import io.newslab.service.http.HttpService;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.DateUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;

public class HttpServiceApache implements HttpService {

    private HttpClient httpClient;

    private long maxContentLength = 256 * 1024;

    public HttpServiceApache(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public HttpResponse execute(HttpRequest httpRequest) throws IOException {
        final HttpGet httpGet = new HttpGet(httpRequest.getUrl());

        String accept = httpRequest.getAccept();
        String etag = httpRequest.getLastEtag();
        Date lastModified = httpRequest.getLastModified();

        if (!StringUtils.isEmpty(accept)) {
            httpGet.setHeader(HttpHeaders.ACCEPT, accept);
        }

        if (!StringUtils.isEmpty(etag)) {
            httpGet.setHeader(HttpHeaders.IF_NONE_MATCH, etag);
        }

        if (!StringUtils.isEmpty(lastModified)) {
            String ifModifiedSinceString = DateUtils.formatDate(lastModified, "EEE, d MMM yyyy HH:mm:ss 'GMT'");
            httpGet.setHeader(HttpHeaders.IF_MODIFIED_SINCE, ifModifiedSinceString );
        }

        httpGet.setHeader(HttpHeaders.CONNECTION, "close");

        final org.apache.http.HttpResponse httpResponse = httpClient.execute(httpGet);

        return new HttpResponseApache(httpResponse, httpGet, getMaxContentLength());
    }

    @Override
    public void setMaxContentLength(long bytes) {
        this.maxContentLength = bytes;
    }

    @Override
    public long getMaxContentLength() {
        return maxContentLength;
    }
}
