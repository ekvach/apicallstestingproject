package getMethodTesting;

import entities.User;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonObjectFromResponseEntityCreator;
import utils.ResponseCreator;
import utils.UserCreatorHelper;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.GlobalConstants.*;

public class ViewSingleUserTest {

    private HttpResponse httpResponseForValidEndPoint;
    private HttpResponse httpResponseForInvalidEndPoint;
    private HttpResponse httpResponseForUserDoesNotExistEndPoint;

    private User actualUser;
    private final User expectedUser = new User(2L, "janet.weaver@reqres.in", "Janet",
            "Weaver", "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");

    @BeforeClass
    public void createResponsesForVerifications() throws IOException {

        httpResponseForValidEndPoint = ResponseCreator.createGetResponseForUri(SINGLE_USER_ACCESS_URI);
        httpResponseForInvalidEndPoint = ResponseCreator.createGetResponseForUri(SINGLE_USER_INVALID_URI);
        httpResponseForUserDoesNotExistEndPoint = ResponseCreator.createGetResponseForUri(SINGLE_USER_NOT_FOUND_URI);

        JSONObject returnedJsonObjectForValidRequest = JsonObjectFromResponseEntityCreator
                .getJsonObjectFromResponse(httpResponseForValidEndPoint);

        String stringRepresentationalOfActualUserInJson = returnedJsonObjectForValidRequest
                .getJSONObject(EXPECTED_USER_CONTAINER_NAME).toString();

        actualUser = UserCreatorHelper.createUserFromJsonString(stringRepresentationalOfActualUserInJson);
    }

    @Test
    public void successStatusIsReturned()   {

        assertThat(httpResponseForValidEndPoint.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));

    }

    @Test
    public void expectedUserIsReturned()   {

        assertThat(actualUser, samePropertyValuesAs(expectedUser));

    }

    @Test
    public void returnedContentTypeIsJson() {

        Header[] header = httpResponseForValidEndPoint.getHeaders("Content-Type");

        assertThat(header[0].getValue(), containsString("application/json"));

    }

    @Test
    public void notFoundStatusIsReturnedForInvalidEndPoint()   {

        assertThat(
                httpResponseForInvalidEndPoint.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void notFoundStatusIsReturnedIfUserDoesNotExist()   {

        assertThat(
                httpResponseForUserDoesNotExistEndPoint.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND));
    }



}
