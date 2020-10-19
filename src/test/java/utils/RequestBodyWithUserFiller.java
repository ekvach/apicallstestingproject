package utils;

import com.google.gson.Gson;
import entities.User;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class RequestBodyWithUserFiller {

    private static final Gson gson = new Gson();

    public static void fillRequestBodyWithUser(HttpEntityEnclosingRequestBase request, User u) throws UnsupportedEncodingException {

        request.setEntity(new StringEntity(gson.toJson(u)));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json; charset=UTF-8");

    }
}
