import java.util.List;
import java.util.Scanner;

public class Main {

    private static TaskManager taskManager = new TaskManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTaskPage();
                    break;
                case 2:
                    addMemberPage();
                    break;
                case 3:
                    assignTaskToMemberPage();
                    break;
                case 4:
                    displayTasksPage();
                    break;
                case 5:
                    markTaskAsCompletedPage();
                    break;
                case 6:
                    viewAllMembersPage();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Pilihlah dengan mengetikkan angkanya!");
            }
        }

        System.out.println("Program selesai.");
    }

    private static void printMainMenu() {
        clearScreen();
        printHeader("MENU UTAMA");
        System.out.println("| 1. Tambah Task                                         |");
        System.out.println("| 2. Tambah Member                                       |");
        System.out.println("| 3. Tugaskan ke Member Lain                             |");
        System.out.println("| 4. Lihat Semua Task                                    |");
        System.out.println("| 5. Tandai Task Selesai                                 |");
        System.out.println("| 6. Lihat Semua Member                                  |");
        System.out.println("| 7. Keluar                                              |");
        printLine();
        System.out.print("Masukkan pilihan menu: ");
    }

    private static void addTaskPage() {
        clearScreen();
        printHeader("TAMBAH TUGAS BARU");
        
        while (true) {
            System.out.print("Masukkan ID Task          : ");
            String id = scanner.nextLine();
            System.out.print("Masukkan Judul Task       : ");
            String title = scanner.nextLine();
            System.out.print("Masukkan Deskripsi Task   : ");
            String description = scanner.nextLine();
            System.out.print("Masukkan Due Date Task (YYYY-MM-DD): ");
            String dueDate = scanner.nextLine();
            System.out.print("Masukkan Prioritas Task (High, Medium, Low): ");
            String priority = scanner.nextLine();
    
            if (taskManager.addTask(id, title, description, dueDate, priority)) {
                pause();
                break; 
            }
        }
    }
    
    private static void addMemberPage() {
        clearScreen();
        printHeader("TAMBAH MEMBER BARU");
    
        while (true) {
            System.out.print("Masukkan ID Member   : ");
            String id = scanner.nextLine();
            System.out.print("Masukkan Nama Member : ");
            String name = scanner.nextLine();
    
            if (taskManager.addMember(id, name)) {
                pause();
                break;
            }
        }
    }    
    
    private static void assignTaskToMemberPage() {
        clearScreen();
        printHeader("TUGASKAN KE MEMBER LAIN");
        System.out.print("Masukkan ID Task : ");
        String taskId = scanner.nextLine();
        System.out.print("Masukkan ID Member: ");
        String memberId = scanner.nextLine();

        if (taskManager.assignTaskToMember(taskId, memberId)) {
            System.out.println("\nTelah Berhasil Ditugaskan ke Member!");
        }
        pause();
    }

    private static void displayTasksPage() {
        clearScreen();
        printHeader("LIHAT SEMUA TASK");
        
        System.out.println("| Sort by:                                               |");
        System.out.println("| 1. Due Date                                            |");
        System.out.println("| 2. Priority                                            |");
        printLine();
        System.out.print("Masukkan pilihanmu: ");
        int sortChoice = scanner.nextInt();
        scanner.nextLine();
        
        List<Task> tasksToDisplay;
        
        switch (sortChoice) {
            case 1:
                tasksToDisplay = taskManager.getAllTasksSortedByDate();
                break;
            case 2:
                tasksToDisplay = taskManager.getAllTasksSortedByPriority();
                break;
            default:
                System.out.println("Pilihlah dengan mengetikkan angkanya!");
                tasksToDisplay = taskManager.getAllTasks();
        }
    
        for (Task task : tasksToDisplay) {
            printLine();  // Menambahkan garis di atas Title
            String format = "| %-18s : %-33s |\n"; // Format kolom untuk menyelaraskan teks
            
            System.out.printf(format, "Title", task.getTitle());
            System.out.printf(format, "Description", task.getDescription());
            System.out.printf(format, "Due Date", task.getDueDate().toString());
            System.out.printf(format, "Priority", task.getPriority());
            System.out.printf(format, "Completed", task.isCompleted() ? "Yes" : "No");
    
            if (!task.getAssignedMembers().isEmpty()) {
                System.out.println("| Assigned Members   :                                   |");
                for (Member member : task.getAssignedMembers()) {
                    System.out.printf("|- ID                : %-33s |\n", member.getId());
                    System.out.printf("|- Name              : %-33s |\n", member.getName());
                }
            } else {
                System.out.printf("| Assigned Members   : %-33s |\n", "None");
            }
            
            printLine();
        }
        pause();
    }    
    
    private static void markTaskAsCompletedPage() {
        clearScreen();
        printHeader("TANDAI TUGAS SELESAI");
        System.out.print("Masukkan ID Task: ");
        String taskId = scanner.nextLine();

        if (taskManager.markTaskAsCompleted(taskId)) {
            System.out.println("\nTask Berhasil Ditandai Selesai!");
        }
        pause();
    }

    private static void viewAllMembersPage() {
        clearScreen();
        printHeader("LIHAT SEMUA MEMBERS");
    
        for (Member member : taskManager.getAllMembers()) {
            System.out.printf("| ID        : %-42s |\n", member.getId());
            System.out.printf("| Name      : %-42s |\n", member.getName());
    
            if (!member.getAssignedTasks().isEmpty()) {
                System.out.println("| Assigned Members   :                                   |");
                for (Task task : member.getAssignedTasks()) {
                    System.out.printf("|- ID                : %-33s |\n", task.getId());
                    System.out.printf("|- Title             : %-33s |\n", task.getTitle());
                }
            } else {
                System.out.printf("| Assigned Tasks: %-38s |\n", "None");
            }
            
            printLine();
        }
        pause();
    }

    private static void printHeader(String title) {
        printLine();
        System.out.printf("| %-54s |\n", title);
        printLine();
    }

    private static void printLine() {
        System.out.println("+--------------------------------------------------------+");
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pause() {
        System.out.println("\nTekan Enter untuk kembali...");
        scanner.nextLine();
    }
}
