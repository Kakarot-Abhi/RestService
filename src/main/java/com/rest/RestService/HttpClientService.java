package com.rest.RestService;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpClientService {

    private CloseableHttpClient httpClient;

    public HttpClientService() throws Exception {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    builder.build());

            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
            connManager.setMaxTotal(100);
            connManager.setDefaultMaxPerRoute(10);
            connManager.setValidateAfterInactivity(1000);

            this.httpClient = HttpClients.custom()
                    .setConnectionManager(connManager)
                    .setSSLSocketFactory(sslsf)
                    .setRetryHandler(new StandardHttpRequestRetryHandler())
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendRequest(String url) throws IOException {
        HttpGet httpget = new HttpGet(url);
        HttpClientContext context = HttpClientContext.create();

        System.out.println("Executing request " + httpget.getRequestLine());

        long start = System.currentTimeMillis();
        try (CloseableHttpResponse response = httpClient.execute(httpget, context)) {
            long end = System.currentTimeMillis();
            System.out.println("Response status: " + response.getStatusLine());
            System.out.println("Response time: " + (end - start) + " ms");

            // Capture additional metrics
            System.out.println("DNS lookup time: " + context.getAttribute(HttpClientContext.HTTP_ROUTE));
            System.out.println("Socket initialization time: " + context.getAttribute(HttpClientContext.HTTP_TARGET_HOST));
            // and more...

            EntityUtils.consume(response.getEntity());
        }
    }
}
