package utils;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class UserCreatorHelper {

    private static final GsonBuilder gsonBuilder = new GsonBuilder();

    public static User createSingleUserFromResponseTo(HttpEntityEnclosingRequestBase request) throws IOException {

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        JSONObject returnedJsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));

        String stringRepresentationalOfReturnedJsonObject = returnedJsonObject.toString();

        return gsonBuilder.create().fromJson(stringRepresentationalOfReturnedJsonObject, User.class);

    }

    public static User createUserFromJsonString(String jsonString) {

        return gsonBuilder.create().fromJson(jsonString, User.class);

    }

    public static List<User> createUserListFromJsonString(String jsonString) {

        Type listType = new TypeToken<List<User>>() {
        }.getType();

        return gsonBuilder.create().fromJson(jsonString, listType);

    }
}
