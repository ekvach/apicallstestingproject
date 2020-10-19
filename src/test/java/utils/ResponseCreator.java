package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;


public class ResponseCreator {

    public static HttpResponse createGetResponseForUri(String uri) throws IOException {
        HttpGet request = new HttpGet(uri);

        @SuppressWarnings("is not inlined into 'return' line for better visibility")
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        return response;

    }
    public static HttpResponse createPostResponseForUri(String uri) throws IOException {
        HttpPost request = new HttpPost(uri);

        @SuppressWarnings("is not inlined into 'return' line for better visibility")
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        return response;

    }

    public static HttpResponse createPutResponseForUri(String uri) throws IOException {
        HttpPut request = new HttpPut(uri);

        @SuppressWarnings("is not inlined into 'return' line for better visibility")
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        return response;

    }

    public static HttpResponse createDeleteResponseForUri(String uri) throws IOException {
        HttpDelete request = new HttpDelete(uri);

        @SuppressWarnings("is not inlined into 'return' line for better visibility")
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        return response;

    }
}
