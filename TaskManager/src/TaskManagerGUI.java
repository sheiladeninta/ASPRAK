import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManagerGUI extends JFrame {

    private TaskManager taskManager = new TaskManager();

    private DefaultListModel<String> taskListModel = new DefaultListModel<>();
    private DefaultListModel<String> memberListModel = new DefaultListModel<>();

    private JList<String> taskList = new JList<>(taskListModel);
    private JList<String> memberList = new JList<>(memberListModel);
    private JTextArea taskDetailsArea = new JTextArea(10, 30);

    private JTextField taskTitleField = new JTextField(15);
    private JTextField taskDescriptionField = new JTextField(15);
    private JTextField taskDueDateField = new JTextField(15);
    private JComboBox<String> taskPriorityBox = new JComboBox<>(new String[]{"High", "Medium", "Low"});

    private JTextField memberNameField = new JTextField(15);

    public TaskManagerGUI() {
        setTitle("Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        JPanel listPanel = createListPanel();

        add(inputPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));

        JPanel taskInputPanel = new JPanel();
        taskInputPanel.setBorder(BorderFactory.createTitledBorder("Add New Task"));
        taskInputPanel.add(new JLabel("Title:"));
        taskInputPanel.add(taskTitleField);
        taskInputPanel.add(new JLabel("Description:"));
        taskInputPanel.add(taskDescriptionField);
        taskInputPanel.add(new JLabel("Due Date:"));
        taskInputPanel.add(taskDueDateField);
        taskInputPanel.add(new JLabel("Priority:"));
        taskInputPanel.add(taskPriorityBox);
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        taskInputPanel.add(addTaskButton);

        JPanel memberInputPanel = new JPanel();
        memberInputPanel.setBorder(BorderFactory.createTitledBorder("Add New Member"));
        memberInputPanel.add(new JLabel("Name:"));
        memberInputPanel.add(memberNameField);
        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMember();
            }
        });
        memberInputPanel.add(addMemberButton);

        inputPanel.add(taskInputPanel);
        inputPanel.add(memberInputPanel);

        return inputPanel;
    }

    private JPanel createListPanel() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(1, 2));

        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setBorder(BorderFactory.createTitledBorder("Tasks"));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.addListSelectionListener(e -> showTaskDetails());
        taskPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        JButton markCompletedButton = new JButton("Mark as Completed");
        markCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markTaskAsCompleted();
            }
        });
        taskPanel.add(markCompletedButton, BorderLayout.SOUTH);

        JPanel memberPanel = new JPanel(new BorderLayout());
        memberPanel.setBorder(BorderFactory.createTitledBorder("Members"));
        memberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        memberPanel.add(new JScrollPane(memberList), BorderLayout.CENTER);
        JButton assignTaskButton = new JButton("Assign Task to Member");
        assignTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTaskToMember();
            }
        });
        memberPanel.add(assignTaskButton, BorderLayout.SOUTH);

        listPanel.add(taskPanel);
        listPanel.add(memberPanel);

        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Task Details"));
        taskDetailsArea.setEditable(false);
        detailsPanel.add(new JScrollPane(taskDetailsArea), BorderLayout.CENTER);

        listPanel.add(detailsPanel);

        return listPanel;
    }

    private void addTask() {
        String title = taskTitleField.getText();
        String description = taskDescriptionField.getText();
        String dueDate = taskDueDateField.getText();
        String priority = (String) taskPriorityBox.getSelectedItem();

        if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty() || priority == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all task fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        taskManager.addTask(title, description, dueDate, priority);
        taskListModel.addElement(title);
        clearTaskFields();
    }

    private void addMember() {
        String name = memberNameField.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the member name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        taskManager.addMember(name);
        memberListModel.addElement(name);
        memberNameField.setText("");
    }

    private void showTaskDetails() {
        String selectedTaskTitle = taskList.getSelectedValue();
        if (selectedTaskTitle != null) {
            Task task = taskManager.findTask(selectedTaskTitle);
            if (task != null) {
                taskDetailsArea.setText(task.toString());
            }
        }
    }

    private void assignTaskToMember() {
        String selectedTaskTitle = taskList.getSelectedValue();
        String selectedMemberName = memberList.getSelectedValue();

        if (selectedTaskTitle == null || selectedMemberName == null) {
            JOptionPane.showMessageDialog(this, "Please select a task and a member to assign.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        taskManager.assignTaskToMember(selectedTaskTitle, selectedMemberName);
        showTaskDetails();
    }

    private void markTaskAsCompleted() {
        String selectedTaskTitle = taskList.getSelectedValue();

        if (selectedTaskTitle == null) {
            JOptionPane.showMessageDialog(this, "Please select a task to mark as completed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        taskManager.markTaskAsCompleted(selectedTaskTitle);
        showTaskDetails();
    }

    private void clearTaskFields() {
        taskTitleField.setText("");
        taskDescriptionField.setText("");
        taskDueDateField.setText("");
        taskPriorityBox.setSelectedIndex(-1);
    }
}
