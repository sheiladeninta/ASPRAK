import java.util.ArrayList;
import java.util.List;

public class Member {
    private String id;
    private String name;
    private List<Task> assignedTasks;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.assignedTasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void assignTask(Task task) {
        if (!assignedTasks.contains(task)) {
            assignedTasks.add(task);
        }
    }

    public String getAssignedTasksInfo(int columnWidth) {
        StringBuilder tasksInfo = new StringBuilder();
        for (Task task : assignedTasks) {
            tasksInfo.append("Task ID: ").append(task.getId())
                     .append(", Title: ").append(task.getTitle())
                     .append("\n").append(" ".repeat(columnWidth - 15));
        }
        return tasksInfo.toString();
    }
}
