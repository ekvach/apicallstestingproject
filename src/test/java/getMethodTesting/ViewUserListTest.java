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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.GlobalConstants.*;

public class ViewUserListTest {

    private final User expectedUser = new User(1L, "george.bluth@reqres.in", "George",
            "Bluth", "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg");

    private HttpResponse httpResponseForValidEndPoint;
    private HttpResponse responseFromSecondPage;

    private List<User> actualUsers;

    @BeforeClass
    public void createResponsesForVerifications() throws IOException {

        httpResponseForValidEndPoint = ResponseCreator.createGetResponseForUri(USER_LIST_FIRST_PAGE_URI);
        responseFromSecondPage = ResponseCreator.createGetResponseForUri(USER_LIST_SECOND_PAGE_URI);

        JSONObject returnedJsonObjectForValidRequest = JsonObjectFromResponseEntityCreator
                .getJsonObjectFromResponse(httpResponseForValidEndPoint);

        String actualUserListInJson = returnedJsonObjectForValidRequest
                .getJSONArray(EXPECTED_USER_CONTAINER_NAME).toString();

        actualUsers = UserCreatorHelper.createUserListFromJsonString(actualUserListInJson);
    }

    @Test
    public void successStatusIsReturned() {

        assertThat(httpResponseForValidEndPoint.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));

    }

    @Test
    public void returnedUserListSizeIsExpected() {

        assertThat(actualUsers.size(), equalTo(6));

    }

    @Test
    public void returnedUserListContainsExpectedUser() {

        assertThat(actualUsers.get(0), samePropertyValuesAs(expectedUser));

    }

    @Test
    public void returnedContentTypeIsJson() {

        Header[] header = httpResponseForValidEndPoint.getHeaders("Content-Type");

        assertThat(header[0].getValue(), containsString("application/json"));

    }

    @Test
    public void contentDiffersThroughThePages()  {

        assertThat(responseFromSecondPage.getEntity().toString(),
                not(httpResponseForValidEndPoint.getEntity().toString()));

    }

}
