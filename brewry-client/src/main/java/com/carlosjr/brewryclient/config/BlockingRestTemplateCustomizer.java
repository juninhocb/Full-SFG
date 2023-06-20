package com.carlosjr.brewryclient.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    private final Integer maxTotalConnections;
    private final Integer maxConnectionsPerRoute;
    private final Integer connectionRequestTimeout;
    private final Integer socketRequestTimeout;

    public BlockingRestTemplateCustomizer(@Value("${carlosjr.max.connection.total}") Integer maxTotalConnections,
                                          @Value("${carlosjr.max.connection.per-route}") Integer maxConnectionsPerRoute,
                                          @Value("${carlosjr.request.timeout}") Integer connectionRequestTimeout,
                                          @Value("${carlosjr.request.socket.timeout}") Integer socketRequestTimeout) {
        this.maxTotalConnections = maxTotalConnections;
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.socketRequestTimeout = socketRequestTimeout;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(maxConnectionsPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketRequestTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
