package deleteMethodTesting;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.ResponseCreator;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.GlobalConstants.SINGLE_USER_ACCESS_URI;

public class DeleteSingleUserTest {

    @Test
    public void successStatusIsReturned() throws IOException {

        HttpResponse response = ResponseCreator.createDeleteResponseForUri(SINGLE_USER_ACCESS_URI);

        assertThat(response.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NO_CONTENT));

    }
}
