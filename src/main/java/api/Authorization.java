package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class Authorization {

    public static String JSESSIONID = "";

    static final Logger logger = Logger.getLogger(Authorization.class);

    public static void loginToJira(String credentialsJSON){
        RestAssured.baseURI = JiraApiParameters.BASE_URI;

        JSESSIONID =
                given()
                        .contentType(ContentType.JSON)
                        .body(credentialsJSON)
                        .when()
                        .post(JiraApiParameters.loginPath)
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract()
                        .path("session.value");

        logger.info("Authorization token: " + JSESSIONID);
    }

}
