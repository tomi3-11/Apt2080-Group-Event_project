/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ToDoListApp extends JFrame {
    private final TaskManager taskManager = new TaskManager();
    private final DefaultListModel<Task> taskListModel = new DefaultListModel<>();
    private final JList<Task> taskList = new JList<>(taskListModel);

    private JComboBox<String> categoryFilter;
    private JComboBox<String> priorityFilter;
    private JComboBox<String> sortFilter;
    private JComboBox<String> memberBox;
    private JSlider progressSlider;

    public ToDoListApp() {
        setTitle("✨ Planner ✨");
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // Colors
        Color bgColor = new Color(253, 246, 255);
        Color cardColor = Color.WHITE;
        Color btnPink = new Color(255, 182, 193);
        Color btnMint = new Color(152, 251, 152);
        Color btnCoral = new Color(255, 127, 127);
        Color btnBlue = new Color(135, 206, 250);
        Color btnGold = new Color(255, 215, 0);
        Color accent = new Color(230, 220, 255);

        // Fonts
        Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Font headerFont = new Font("Segoe UI", Font.BOLD, 22);

        // Header
        JLabel header = new JLabel("To-Do List :)", JLabel.CENTER);
        header.setFont(headerFont);
        header.setForeground(new Color(100, 70, 150));
        header.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(header, BorderLayout.NORTH);

        // Input fields
        JTextField titleField = new JTextField(12);
        JTextField descriptionField = new JTextField(12);
        JTextField dueDateField = new JTextField(10);
        String[] priorities = {"High", "Medium", "Low"};
        JComboBox<String> priorityBox = new JComboBox<>(priorities);
        JTextField categoryField = new JTextField(10);

        // Members combo box (editable)
        memberBox = new JComboBox<>();
        memberBox.setEditable(true);

        // Progress slider
        progressSlider = new JSlider(0, 100, 0);
        progressSlider.setMajorTickSpacing(20);
        progressSlider.setMinorTickSpacing(5);
        progressSlider.setPaintTicks(true);
        progressSlider.setPaintLabels(true);

        // Buttons
        JButton addButton = new JButton("➕ Add Task");
        JButton deleteButton = new JButton("🗑️ Delete Task");
        JButton completeButton = new JButton("✔ Mark Complete");
        JButton updateButton = new JButton("✏️ Update Task");
        JButton workloadButton = new JButton("📊 View Workload");

        styleButton(addButton, btnPink, buttonFont);
        styleButton(deleteButton, btnCoral, buttonFont);
        styleButton(completeButton, btnMint, buttonFont);
        styleButton(updateButton, btnBlue, buttonFont);
        styleButton(workloadButton, btnGold, buttonFont);

        // Task list
        taskList.setFont(mainFont);
        taskList.setBackground(cardColor);
        taskList.setFixedCellHeight(40);
        taskList.setBorder(BorderFactory.createLineBorder(accent, 1));

        taskList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.toString());
            label.setOpaque(true);
            label.setFont(mainFont);
            label.setForeground(value.isCompleted() ? new Color(0, 128, 0) : Color.BLACK);
            label.setBackground(isSelected ? accent : Color.WHITE);
            return label;
        });

        JScrollPane scrollPane = new JScrollPane(taskList);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(bgColor);
        listPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setPreferredSize(new Dimension(350, 350));
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // Filters
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        filterPanel.setBackground(bgColor);

        categoryFilter = new JComboBox<>(new String[]{"All Categories"});
        priorityFilter = new JComboBox<>(new String[]{"All Priorities", "High", "Medium", "Low"});
        sortFilter = new JComboBox<>(new String[]{"No Sorting", "Sort by Deadline", "Sort by Priority", "Sort by Category"});

        filterPanel.add(new JLabel("Filter by Category:"));
        filterPanel.add(categoryFilter);
        filterPanel.add(new JLabel("Filter by Priority:"));
        filterPanel.add(priorityFilter);
        filterPanel.add(new JLabel("Sort:"));
        filterPanel.add(sortFilter);
        listPanel.add(filterPanel, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(bgColor);
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addField(inputPanel, gbc, 0, "Title:", titleField);
        addField(inputPanel, gbc, 1, "Due Date (YYYY-MM-DD):", dueDateField);
        addField(inputPanel, gbc, 2, "Description:", descriptionField);
        addField(inputPanel, gbc, 3, "Priority:", priorityBox);
        addField(inputPanel, gbc, 4, "Category:", categoryField);
        addField(inputPanel, gbc, 5, "Assigned To:", memberBox);
        addField(inputPanel, gbc, 6, "Progress:", progressSlider);

        // Button row
        gbc.gridx = 0; gbc.gridy = 7;
        inputPanel.add(addButton, gbc);
        gbc.gridx = 1; inputPanel.add(deleteButton, gbc);
        gbc.gridx = 2; inputPanel.add(updateButton, gbc);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        centerPanel.setBackground(bgColor);
        centerPanel.add(inputPanel);
        centerPanel.add(listPanel);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(bgColor);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 20, 10));
        bottomPanel.add(completeButton);
        bottomPanel.add(workloadButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addTask(titleField, descriptionField, dueDateField, priorityBox, categoryField));
        deleteButton.addActionListener(e -> deleteTask());
        completeButton.addActionListener(e -> markTaskComplete());
        updateButton.addActionListener(e -> updateTask(titleField, descriptionField, dueDateField, priorityBox, categoryField));
        workloadButton.addActionListener(e -> showWorkload());

        categoryFilter.addActionListener(e -> applyFilters());
        priorityFilter.addActionListener(e -> applyFilters());
        sortFilter.addActionListener(e -> applyFilters());

        updateCategoryFilter();
        getContentPane().setBackground(bgColor);
    }

    // --- Core Methods ---
    private void addTask(JTextField title, JTextField desc, JTextField date, JComboBox<String> priority, JTextField category) {
        String titleText = title.getText().trim();
        String dateText = date.getText().trim();

        if (titleText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task title cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidDate(dateText)) {
            JOptionPane.showMessageDialog(this, "Invalid date format! Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String memberName = (String) memberBox.getSelectedItem();
        if (memberName != null && !memberName.trim().isEmpty() && ((DefaultComboBoxModel<String>) memberBox.getModel()).getIndexOf(memberName) == -1) {
            memberBox.addItem(memberName.trim());
        }

        Task task = new Task(titleText, desc.getText(), dateText,
                (String) priority.getSelectedItem(), category.getText(), memberName);
        task.setProgress(progressSlider.getValue());

        taskManager.addTask(task);
        taskListModel.addElement(task);
        clearFields(title, desc, date, category);
        progressSlider.setValue(0);
        updateCategoryFilter();
        applyFilters();
    }

    private void deleteTask() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            taskManager.removeTask(selected);
            taskListModel.removeElement(selected);
            updateCategoryFilter();
            applyFilters();
        }
    }

    private void markTaskComplete() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            selected.setCompleted(true);
            selected.setProgress(100);
            taskList.repaint();
            JOptionPane.showMessageDialog(this, "🎉 Task marked as completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateTask(JTextField title, JTextField desc, JTextField date, JComboBox<String> priority, JTextField category) {
        Task selected = taskList.getSelectedValue();
        int index = taskList.getSelectedIndex();

        if (selected != null && index != -1) {
            if (!isValidDate(date.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Invalid date format! Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            selected.setTitle(title.getText());
            selected.setDescription(desc.getText());
            selected.setDueDate(date.getText());
            selected.setPriority((String) priority.getSelectedItem());
            selected.setCategory(category.getText());
            selected.setAssignedTo((String) memberBox.getSelectedItem());
            selected.setProgress(progressSlider.getValue());

            taskListModel.set(index, selected);
            JOptionPane.showMessageDialog(this, "✏️ Task updated successfully!", "Update", JOptionPane.INFORMATION_MESSAGE);
            clearFields(title, desc, date, category);
            progressSlider.setValue(0);
            applyFilters();
        }
    }

    private void applyFilters() {
        taskListModel.clear();
        String cat = (String) categoryFilter.getSelectedItem();
        String pr = (String) priorityFilter.getSelectedItem();
        String sort = (String) sortFilter.getSelectedItem();

        List<Task> filtered = new ArrayList<>(taskManager.getTasks());
        if (!"All Categories".equals(cat)) filtered.removeIf(t -> !t.getCategory().equals(cat));
        if (!"All Priorities".equals(pr)) filtered.removeIf(t -> !t.getPriority().equals(pr));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if ("Sort by Deadline".equals(sort)) {
            filtered.sort((a, b) -> {
                try {
                    return sdf.parse(a.getDueDate()).compareTo(sdf.parse(b.getDueDate()));
                } catch (ParseException e) {
                    return 0;
                }
            });
        } else if ("Sort by Priority".equals(sort)) {
            List<String> order = Arrays.asList("High", "Medium", "Low");
            filtered.sort(Comparator.comparingInt(t -> order.indexOf(t.getPriority())));
        } else if ("Sort by Category".equals(sort)) {
            filtered.sort(Comparator.comparing(Task::getCategory));
        }

        filtered.forEach(taskListModel::addElement);
    }

    private void updateCategoryFilter() {
        Set<String> categories = new HashSet<>();
        categories.add("All Categories");
        for (Task t : taskManager.getTasks()) {
            if (!t.getCategory().isEmpty()) categories.add(t.getCategory());
        }
        categoryFilter.setModel(new DefaultComboBoxModel<>(categories.toArray(new String[0])));
    }

    private void showWorkload() {
        Map<String, Integer> workload = new TreeMap<>();
        for (Task t : taskManager.getTasks()) {
            String member = t.getAssignedTo();
            if (member != null && !member.isEmpty()) {
                workload.put(member, workload.getOrDefault(member, 0) + 1);
            }
        }

        StringBuilder sb = new StringBuilder("📊 Workload Distribution:\n\n");
        workload.forEach((m, count) -> sb.append(m).append(": ").append(count).append(" tasks\n"));
        JOptionPane.showMessageDialog(this, sb.toString(), "Workload", JOptionPane.INFORMATION_MESSAGE);
    }

    // --- Utility Methods ---
    private void styleButton(JButton b, Color c, Font f) {
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFont(f);
        b.setPreferredSize(new Dimension(160, 40));
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(c.darker(), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addField(JPanel p, GridBagConstraints gbc, int y, String label, Component field) {
        gbc.gridx = 0;
        gbc.gridy = y;
        p.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        p.add(field, gbc);
        gbc.gridwidth = 1;
    }

    private void clearFields(JTextField... f) {
        for (JTextField field : f) field.setText("");
    }

    private boolean isValidDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListApp().setVisible(true));
    }
}
