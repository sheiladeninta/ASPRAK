import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public void addTask(String title, String description, String dueDate, String priority) {
        Task task = new Task(title, description, dueDate, priority);
        tasks.add(task);
    }

    public void addMember(String name) {
        members.add(new Member(name));
    }

    public Task findTask(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                return task;
            }
        }
        return null;
    }

    public Member findMember(String name) {
        for (Member member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    public void assignTaskToMember(String taskTitle, String memberName) {
        Task task = findTask(taskTitle);
        Member member = findMember(memberName);

        if (task != null && member != null) {
            member.assignTask(task);
        }
    }

    public void markTaskAsCompleted(String taskTitle) {
        Task task = findTask(taskTitle);
        if (task != null) {
            task.setCompleted(true);
        }
    }
}
