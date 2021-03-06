package api;

import org.json.JSONArray;
import org.json.JSONObject;

public class JiraApiJsonFixture {

    private static String AUTH_USERNAME = "webinar5";
    private static String AUTH_PASSWORD = "webinar5";

    public static String authorizeCredentials(){
        JSONObject login = new JSONObject();
        login.put("username", JiraApiJsonFixture.AUTH_USERNAME);
        login.put("password", JiraApiJsonFixture.AUTH_PASSWORD);

        return login.toString();
    }


    public static String createIssueJSON(String summary, String assigneeName, String projectKey){
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

    public static String addCommentJSON(String commentText){
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

    public static String changePriorityJSON(String priorityID){
        JSONObject issue = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject priority = new JSONObject();

        priority.put("id",priorityID);
        fields.put("priority",priority);
        issue.put("fields",fields);

        return issue.toString();
    }
}
