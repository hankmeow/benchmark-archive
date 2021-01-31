package com.hankmew.benchmark.spring.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {
    @Bean
    public CloseableHttpClient defaultHttpClient() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(6000);
        connManager.setDefaultMaxPerRoute(500);
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(500)
                .setConnectTimeout(500)
                .setSocketTimeout(1000)
                .build();
        return HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(config)
                .build();
    }
}
