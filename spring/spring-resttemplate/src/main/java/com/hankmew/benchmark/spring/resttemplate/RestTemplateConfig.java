package com.hankmew.benchmark.spring.resttemplate;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(6000);
        connManager.setDefaultMaxPerRoute(500);
        RequestConfig.Builder custom = RequestConfig.custom();
        custom.setConnectTimeout(800);
        custom.setConnectionRequestTimeout(500);
        custom.setSocketTimeout(1000);
        RequestConfig config = custom.build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(config).build();
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }
}
