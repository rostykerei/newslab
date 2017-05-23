package io.newslab.service.http;

import io.newslab.service.http.apache.HttpServiceApache;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties(prefix = "newslab.http")
public class HttpServiceConfiguration {

    private int timeout;

    private int maxContentLength;

    private int maxConnections;

    private String userAgent;

    @Bean
    public HttpService httpService() {
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(getTimeout())
                .setConnectTimeout(getTimeout())
                .setCircularRedirectsAllowed(false)
                .setExpectContinueEnabled(false)
                .setMaxRedirects(2)
                .setAuthenticationEnabled(false)
                .setRelativeRedirectsAllowed(true)
                .setConnectTimeout(getTimeout())
                .setRedirectsEnabled(true)
                .build();

        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(getTimeout(), TimeUnit.MILLISECONDS);
        connectionManager.setMaxTotal(getMaxConnections());
        connectionManager.setDefaultMaxPerRoute(getMaxConnections());

        HttpService httpService = new HttpServiceApache(
                HttpClientBuilder
                        .create()
                        .setDefaultRequestConfig(requestConfig)
                        .setConnectionReuseStrategy(NoConnectionReuseStrategy.INSTANCE)
                        .setUserAgent(getUserAgent())
                        .setMaxConnTotal(getMaxConnections())
                        .setMaxConnPerRoute(getMaxConnections())
                        .setConnectionManager(connectionManager)
                        .build()
        );

        httpService.setMaxContentLength(getMaxContentLength());

        return httpService;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxContentLength() {
        return maxContentLength;
    }

    public void setMaxContentLength(int maxContentLength) {
        this.maxContentLength = maxContentLength;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}