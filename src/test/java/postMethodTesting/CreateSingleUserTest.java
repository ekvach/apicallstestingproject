package postMethodTesting;

import entities.User;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RequestBodyWithUserFiller;
import utils.ResponseCreator;
import utils.UserCreatorHelper;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.GlobalConstants.SINGLE_USER_ACCESS_URI;
import static utils.GlobalConstants.SINGLE_USER_NOT_FOUND_URI;

public class CreateSingleUserTest {

    private User userForCreation;
    private User userWithoutId;

    @BeforeClass
    public void initializer() {

        userForCreation = new User(1000L, "myUser.in", "userName",
                "userSurname", "userAvatar");
        userWithoutId = new User("myUser.in", "userName",
                "userSurname", "userAvatar");
    }

    @Test
    public void successStatusIsReturned() throws IOException {

        HttpResponse response = ResponseCreator.createPostResponseForUri(SINGLE_USER_NOT_FOUND_URI);

        assertThat(response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_CREATED));

    }

    @Test
    public void expectedUserWithIdIsCreated() throws IOException {

        HttpPost request = new HttpPost(SINGLE_USER_NOT_FOUND_URI);

        RequestBodyWithUserFiller.fillRequestBodyWithUser(request, userForCreation);

        User actuallyCreatedUser = UserCreatorHelper.createSingleUserFromResponseTo(request);

        assertThat(actuallyCreatedUser, samePropertyValuesAs(userForCreation));

    }


    @Test
    public void idIsGeneratedAutomaticallyForUserWithoutOne() throws IOException {

        HttpPost request = new HttpPost(SINGLE_USER_NOT_FOUND_URI);

        RequestBodyWithUserFiller.fillRequestBodyWithUser(request, userWithoutId);

        User actuallyCreatedUser = UserCreatorHelper.createSingleUserFromResponseTo(request);

        assertThat(actuallyCreatedUser.getId(), notNullValue());

    }

    @Test
    public void returnedContentTypeIsJson() throws IOException {

        HttpResponse response = ResponseCreator.createPostResponseForUri(SINGLE_USER_NOT_FOUND_URI);

        Header[] header = response.getHeaders("Content-Type");

        assertThat(header[0].getValue(), containsString("application/json"));

    }

    @Test
    public void userIsUpdatesIfIdExists() throws IOException {

        HttpPost request = new HttpPost(SINGLE_USER_ACCESS_URI);

        RequestBodyWithUserFiller.fillRequestBodyWithUser(request, userForCreation);

        User actuallyCreatedUser = UserCreatorHelper.createSingleUserFromResponseTo(request);

        assertThat(actuallyCreatedUser, samePropertyValuesAs(userForCreation));

    }

}
