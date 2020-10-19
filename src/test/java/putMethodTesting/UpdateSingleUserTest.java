package putMethodTesting;

import entities.User;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPut;
import org.testng.annotations.Test;
import utils.RequestBodyWithUserFiller;
import utils.ResponseCreator;
import utils.UserCreatorHelper;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.GlobalConstants.SINGLE_USER_ACCESS_URI;

public class UpdateSingleUserTest {

    private final User userWithNewData = new User(1000L, "myUser.in", "userName",
            "userSurname", "userAvatar");

    @Test
    public void successStatusIsReturned() throws IOException {

        HttpResponse response = ResponseCreator.createPutResponseForUri(SINGLE_USER_ACCESS_URI);

        assertThat(response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));

    }

    @Test
    public void userIsUpdatedWithNewValues() throws IOException {

        HttpPut request = new HttpPut(SINGLE_USER_ACCESS_URI);

        RequestBodyWithUserFiller.fillRequestBodyWithUser(request, userWithNewData);

        User updatedUser = UserCreatorHelper.createSingleUserFromResponseTo(request);

        assertThat(updatedUser,
                samePropertyValuesAs(userWithNewData));

    }

    @Test
    public void returnedContentTypeIsJson() throws IOException {

        HttpResponse response = ResponseCreator.createPutResponseForUri(SINGLE_USER_ACCESS_URI);

        Header[] header = response.getHeaders("Content-Type");

        assertThat(header[0].getValue(), containsString("application/json"));

    }

    @Test
    public void userIsNotUpdatedIfNotAllValuesProvided() {

        // currently this test cannot be implemented due to reqres.in always returns status OK for the PUT request

    }

    @Test
    public void userIsCreatedIfIdIsNotFound() {

        // currently this test cannot be implemented due to reqres.in always returns status OK for the PUT request

    }


}
