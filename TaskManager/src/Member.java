import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private List<Task> assignedTasks;

    public Member(String name) {
        this.name = name;
        this.assignedTasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void assignTask(Task task) {
        assignedTasks.add(task);
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }
}
