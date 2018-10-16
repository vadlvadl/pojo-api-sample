import PojoClasses.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SampleTest {

    private String sessionID;

    static final Logger logger = Logger.getLogger(SampleTest.class);

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "http://jira.hillel.it";
        RestAssured.port = 8080;
    }

    @Test
    public void loginToJiraTest(){

        JSONObject credentials = new JSONObject();
        credentials.put("username", "webinar5");
        credentials.put("password", "webinar5");

        sessionID = given().
                header("Content-Type", "application/json").
                body(credentials.toString()).
        when().
                post("/rest/auth/1/session").
        then().
                extract().
                path("session.value");

        Assert.assertNotNull(sessionID);
    }

    @Test(dependsOnMethods = "loginToJiraTest")
    public void createIssueTest(){
        String summary = "Sample test QAAUT6_0918";
        String assignee = "webinar5";
        String projectId = "11400";
        String issueTypeId = "10105";

        Issue issue = new Issue(new Fields()
                .setSummary(summary)
                .setAssignee(new Assignee(assignee))
                .setIssueType(new IssueType(issueTypeId))
                .setProject(new Project(projectId))
                );

        ObjectMapper mapper = new ObjectMapper();
        String jsonIssue = "";

        try {
            jsonIssue = mapper.writeValueAsString(issue);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ValidatableResponse response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + sessionID)
                .body(jsonIssue)
                .when()
                .post("/rest/api/3/issue")
                .then();

        Assert.assertEquals(response.extract().statusCode(), 201);
        logger.info("Issue Key: " + response.extract().path("key"));
    }
}
