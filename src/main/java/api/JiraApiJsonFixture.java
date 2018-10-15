package api;

import org.json.JSONArray;
import org.json.JSONObject;

public class JiraApiJsonFixture {

    public static String authorizeCredentials(){
        JSONObject login = new JSONObject();
        login.put("username", "webinar5");
        login.put("password", "webinar5");

        return login.toString();
    }


    public static String generateIssueJSON(String summary, String assigneeName, String projectKey){
        String issueTypeId = "10105";

        JSONObject issueCreate = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject issueType = new JSONObject();
        JSONObject assignee = new JSONObject();

        project.put("key", projectKey);
        issueType.put("id", issueTypeId);
        assignee.put("name", assigneeName);
        fields.put("project", project);
        fields.put("issuetype", issueType);
        fields.put("assignee", assignee);
        fields.put("summary", summary);
        issueCreate.put("fields", fields);

        return issueCreate.toString();
    }

    public static String generateCommentJSON(String commentText){
        JSONObject comment = new JSONObject();
        JSONObject body = new JSONObject();
        JSONArray  bodyContent = new JSONArray();
        JSONObject bodyVisibility = new JSONObject();
        JSONObject textObject = new JSONObject();
        JSONArray  textContent = new JSONArray();
        JSONObject contentData = new JSONObject();

        contentData.put("type", "text");
        contentData.put("text", commentText);

        textContent.put(contentData);

        textObject.put("type","paragraph");
        textObject.put("content", textContent);

        bodyContent.put(textObject);

        bodyVisibility.put("type","role");
        bodyVisibility.put("value","Administrators");

        body.put("type", "doc");
        body.put("version", 1);
        body.put("content",bodyContent);

        comment.put("body",body);
        comment.put("visibility",bodyVisibility);

        return comment.toString();
    }

}
