import api.Authorization;
import api.JiraApiActions;
import api.JiraApiJsonFixture;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DesompositionTest {

    static String issueID;
    static final Logger logger = Logger.getLogger(SampleTest.class);

    @BeforeTest
    public void setup(){
        Authorization.loginToJira(JiraApiJsonFixture.authorizeCredentials());
    }

    @Test
    public void createIssueTest(){

        String summary = "The Issue was created via API test";
        String assigneeName = "Webinar5";
        String projectKey="QAAUT6";

        ValidatableResponse response = JiraApiActions.createIssue(
                JiraApiJsonFixture.generateIssueJSON(summary,assigneeName,projectKey));

        Assert.assertEquals(response.extract().statusCode(),201);
        Assert.assertTrue(response.extract().contentType().contains(ContentType.JSON.toString()));

        issueID = response.extract().path("id");
        logger.info("IssueID: " + issueID);

        Assert.assertNotNull(issueID);
    }

    @Test(dependsOnMethods = {"createIssueTest"}, priority = 1)
    public void addCommentTest(){

        String comment = "Sample comment added with API methods";

        ValidatableResponse response = JiraApiActions.addComment(issueID,comment);

        Assert.assertEquals(response.extract().statusCode(),201);
        Assert.assertTrue(response.extract().contentType().contains(ContentType.JSON.toString()));

    }

    @Test(dependsOnMethods = {"createIssueTest"}, priority = 10)
    public void deleteCreatedIssue(){

        ValidatableResponse response = JiraApiActions.deleteIssue(issueID);

        Assert.assertEquals(response.extract().statusCode(),204);
        Assert.assertTrue(response.extract().contentType().contains(ContentType.JSON.toString()));

    }
}
