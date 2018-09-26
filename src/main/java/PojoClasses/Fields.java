package PojoClasses;

public class Fields implements Pojo {
    private String summary;
    private Project project;
    private IssueType issueType;
    private Assignee assignee;

    public String getSummary() {
        return summary;
    }

    public Fields setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Fields setProject(Project project) {
        this.project = project;
        return this;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public Fields setIssueType(IssueType issueType) {
        this.issueType = issueType;
        return this;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public Fields setAssignee(Assignee assignee) {
        this.assignee = assignee;
        return this;
    }
}
