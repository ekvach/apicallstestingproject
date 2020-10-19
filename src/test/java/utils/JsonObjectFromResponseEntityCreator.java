package utils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class JsonObjectFromResponseEntityCreator {

    public static JSONObject getJsonObjectFromResponse(HttpResponse response) throws IOException {

        return new JSONObject(EntityUtils
                .toString(response.getEntity()));
    }

}
