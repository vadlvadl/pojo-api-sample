package api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

public class JiraApiActions {

    public static ValidatableResponse createIssue(String issueContent){
        return HttpRequestSender.post(JiraApiParameters.createIssuePath,issueContent);
    }

    public static ValidatableResponse addComment(String issueID, String comment){
        return HttpRequestSender.post(String.format(JiraApiParameters.addCommentPath,issueID),comment);
    }

    public static ValidatableResponse changePriority(String issueID, String priority){
        return HttpRequestSender.put(String.format(JiraApiParameters.editIssuePath,issueID),priority);
    }

    public static ValidatableResponse deleteIssue(String issueID){
        return HttpRequestSender.delete(String.format(JiraApiParameters.deleteIssuePath,issueID));
    }

}
