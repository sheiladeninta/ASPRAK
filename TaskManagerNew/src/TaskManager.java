import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public boolean addTask(String id, String title, String description, String dueDateString, String priority) {
        Date dueDate;
        try {
            dueDate = dateFormat.parse(dueDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return false;
        }

        if (findTaskById(id) != null) {
            System.out.println("Task with this ID already exists.");
            return false;
        }
        Task task = new Task(id, title, description, dueDate, priority); // Perhatikan bahwa dueDate sekarang bertipe Date
        tasks.add(task);
        System.out.println("Task berhasil ditambahkan!\n");
        return true;
    }

    public boolean addMember(String id, String name) {
        if (findMemberById(id) != null) {
            System.out.println("Member with this ID already exists.");
            return false; // Indikator bahwa member gagal ditambahkan
        }

        members.add(new Member(id, name));
        System.out.println("Member berhasil ditambahkan!\n");
        return true; // Indikator bahwa member berhasil ditambahkan
    }

    public Task findTaskById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public Member findMemberById(String id) {
        for (Member member : members) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
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

    public boolean assignTaskToMember(String taskId, String memberId) {
        Task task = findTaskById(taskId);
        Member member = findMemberById(memberId);

        if (task != null && member != null) {
            task.assignMember(member);
            member.assignTask(task);
            return true;
        } else {
            if (task == null) {
                System.out.println("Task dengan ID ini belum ditambahkan.");
            }
            if (member == null) {
                System.out.println("Member dengan ID ini belum ditambahkan.");
            }
            return false;
        }
    }

    public boolean markTaskAsCompleted(String taskId) {
        Task task = findTaskById(taskId);
        if (task != null) {
            task.markAsCompleted();
            // Hanya tampilkan pesan dalam satu tempat
            return true;
        } else {
            // Hanya tampilkan pesan dalam satu tempat
            return false;
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Member> getAllMembers() {
        return members;
    }
    
    public List<Task> getAllTasksSortedByDate() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparing(Task::getDueDate));
        return sortedTasks;
    }

    public List<Task> getAllTasksSortedByPriority() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparing(Task::getPriority)); // Asumsi priority adalah string. Jika menggunakan enum, ubah metode ini
        return sortedTasks;
    }
}
