package api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class HttpRequestSender {

    static final Logger logger = Logger.getLogger(HttpRequestSender.class);

    public static ValidatableResponse get(String path){
        ValidatableResponse response = given()
                  .header("Content-Type", ContentType.JSON)
                  .header("Cookie", "JSESSIONID=" + Authorization.JSESSIONID)
                .when()
                  .get(path)
                .then();

        logger.info("Method: GET");
        logger.info("URL: " + JiraApiParameters.BASE_URI + path);
        logger.info("RESPONSE STATUS_CODE: " + response.extract().statusCode());

        return response;
    }

    public static ValidatableResponse post(String path, String json){
        ValidatableResponse response = given()
                  .header("Content-Type", ContentType.JSON)
                  .header("Cookie", "JSESSIONID=" + Authorization.JSESSIONID)
                  .body(json)
                .when()
                  .post(path)
                .then();

        logger.info("Method: POST");
        logger.info("URL: " + JiraApiParameters.BASE_URI + path);
        logger.info("REQUEST String: " + json);
        logger.info("RESPONSE STATUS_CODE: " + response.extract().statusCode());

        return response;

    }

    public static ValidatableResponse delete(String path){
        ValidatableResponse response = given()
                  .header("Content-Type", ContentType.JSON)
                  .header("Cookie", "JSESSIONID=" + Authorization.JSESSIONID)
                .when()
                  .delete(path)
                .then();

        logger.info("Method: DELETE");
        logger.info("URL: " + JiraApiParameters.BASE_URI + path);
        logger.info("RESPONSE STATUS_CODE: " + response.extract().statusCode());

        return response;
    }

}
