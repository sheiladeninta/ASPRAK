public class Task {
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private boolean isCompleted;

    public Task(String title, String description, String dueDate, String priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\nDue Date: " + dueDate + "\nPriority: " + priority + "\nCompleted: " + isCompleted;
    }
}
