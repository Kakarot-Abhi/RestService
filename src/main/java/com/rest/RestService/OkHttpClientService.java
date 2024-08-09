//package com.rest.RestService;
//
//import okhttp3.*;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//public class OkHttpClientService {
//
//    private final OkHttpClient client;
//
//    public OkHttpClientService() {
//        this.client = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .addNetworkInterceptor(chain -> {
//                    long start = System.currentTimeMillis();
//                    Response response = chain.proceed(chain.request());
//                    long end = System.currentTimeMillis();
//
//                    // Capture metrics
//                    System.out.println("Request URL: " + response.request().url());
//                    System.out.println("Response time: " + (end - start) + " ms");
//                    System.out.println("DNS lookup time: " + chain.connection().route().dns());
//                    System.out.println("Socket initialization time: " + chain.connection().socket());
//                    // and more...
//
//                    return response;
//                })
//                .build();
//    }
//
//    public void sendRequest(String url) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            System.out.println("Response code: " + response.code());
//            System.out.println("Response body: " + response.body().string());
//        }
//    }
//}
