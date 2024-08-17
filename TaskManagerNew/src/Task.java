import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    private String id;
    private String title;
    private String description;
    private Date dueDate;
    private String priority;
    private boolean isCompleted;
    private String submissionInfo;
    private List<Member> assignedMembers;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Task(String id, String title, String description, Date dueDate, String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
        this.submissionInfo = "";
        this.assignedMembers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getSubmissionInfo() {
        return submissionInfo;
    }

    public List<Member> getAssignedMembers() {
        return assignedMembers;
    }

    public void assignMember(Member member) {
        if (!assignedMembers.contains(member)) {
            assignedMembers.add(member);
        }
    }

    public void markAsCompleted() {
        Date now = new Date();
        if (now.before(dueDate)) {
            this.submissionInfo = "Telah ditandai pada " + dateFormat.format(now);
        } else {
            this.submissionInfo = "Penandaan terlambat pada " + dateFormat.format(now);
        }
        this.isCompleted = true;
    }
}
