package api;

public class JiraApiParameters {

    public static String BASE_URI = "http://jira.hillel.it:8080";

    public static String loginPath = "/rest/auth/1/session";
    public static String createIssuePath = "/rest/api/2/issue";
    public static String editIssuePath = "/rest/api/2/issue/%s"; //PUT /rest/api/2/issue/{issueIdOrKey}
    public static String addCommentPath = "/rest/api/2/issue/%s/comment"; //POST /rest/api/2/issue/{issueIdOrKey}/comment
    public static String deleteIssuePath = "/rest/api/2/issue/%s"; // DELETE /rest/api/2/issue/{issueIdOrKey}

}
