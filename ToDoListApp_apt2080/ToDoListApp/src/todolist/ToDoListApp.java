/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ToDoListApp extends JFrame {
    private TaskManager taskManager = new TaskManager();
    private DefaultListModel<Task> taskListModel = new DefaultListModel<>();
    private JList<Task> taskList;

    // 🔹 Filter Components
    private JComboBox<String> categoryFilter;
    private JComboBox<String> priorityFilter;
    private JComboBox<String> sortFilter;

    public ToDoListApp() {
        setTitle("✨ Planner ✨");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // 🎨 Theme Colors
        Color bgColor = new Color(253, 246, 255);
        Color cardColor = Color.WHITE;
        Color btnPink = new Color(255, 182, 193);
        Color btnMint = new Color(152, 251, 152);
        Color btnCoral = new Color(255, 127, 127);
        Color btnBlue = new Color(135, 206, 250);
        Color accent = new Color(230, 220, 255);

        // 📝 Fonts
        Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Font headerFont = new Font("Segoe UI", Font.BOLD, 22);

        // Header
        JLabel header = new JLabel("To-Do List :)", JLabel.CENTER);
        header.setFont(headerFont);
        header.setForeground(new Color(100, 70, 150));
        header.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(header, BorderLayout.NORTH);

        // 🔹 Input Fields
        JTextField titleField = new JTextField(12);
        JTextField descriptionField = new JTextField(12);
        JTextField dueDateField = new JTextField(10);
        String[] priorities = {"High", "Medium", "Low"};
        JComboBox<String> priorityBox = new JComboBox<>(priorities);
        JTextField categoryField = new JTextField(10);

        // 🔹 Buttons
        JButton addButton = new JButton("➕ Add Task");
        JButton deleteButton = new JButton("🗑️ Delete Task");
        JButton completeButton = new JButton("✔ Mark Complete");
        JButton updateButton = new JButton("✏️ Update Task");

        styleButton(addButton, btnPink, buttonFont);
        styleButton(deleteButton, btnCoral, buttonFont);
        styleButton(completeButton, btnMint, buttonFont);
        styleButton(updateButton, btnBlue, buttonFont);

        // 🔹 Task List Panel
        taskList = new JList<>(taskListModel);
        taskList.setFont(mainFont);
        taskList.setBackground(cardColor);
        taskList.setFixedCellHeight(40);
        taskList.setBorder(BorderFactory.createLineBorder(accent, 1));
        JScrollPane scrollPane = new JScrollPane(taskList);

        taskList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.toString());
            label.setOpaque(true);
            label.setFont(mainFont);
            label.setForeground(((Task) value).isCompleted() ? new Color(0, 128, 0) : Color.BLACK);
            label.setBackground(isSelected ? new Color(230, 220, 255) : Color.WHITE);
            return label;
        });

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(bgColor);
        listPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        scrollPane.setPreferredSize(new Dimension(350, 350));
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // 🔹 Filters Panel
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

        // 🔹 Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(bgColor);
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.3;

        addField(inputPanel, gbc, 0, "Title:", titleField);
        addField(inputPanel, gbc, 1, "Due Date (YYYY-MM-DD):", dueDateField);
        addField(inputPanel, gbc, 2, "Description:", descriptionField);
        addField(inputPanel, gbc, 3, "Priority:", priorityBox);
        addField(inputPanel, gbc, 4, "Category:", categoryField);

        // Buttons row
        gbc.gridx = 0; gbc.gridy = 5;
        inputPanel.add(addButton, gbc);
        gbc.gridx = 1;
        inputPanel.add(deleteButton, gbc);
        gbc.gridx = 2;
        inputPanel.add(updateButton, gbc);

        // 🔹 Main Layout
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        centerPanel.setBackground(bgColor);
        centerPanel.add(inputPanel);
        centerPanel.add(listPanel);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(bgColor);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 20, 10));
        bottomPanel.add(completeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // 🔹 Button Actions
        addButton.addActionListener(e -> {
            Task task = new Task(
                titleField.getText(),
                descriptionField.getText(),
                dueDateField.getText(),
                (String) priorityBox.getSelectedItem(),
                categoryField.getText()
            );
            taskManager.addTask(task);
            taskListModel.addElement(task);
            clearFields(titleField, descriptionField, dueDateField, categoryField);

            updateCategoryFilter();
            applyFilters();
        });

        deleteButton.addActionListener(e -> {
            Task selected = taskList.getSelectedValue();
            if (selected != null) {
                taskManager.removeTask(selected);
                taskListModel.removeElement(selected);
                updateCategoryFilter();
                applyFilters();
            }
        });

        completeButton.addActionListener(e -> {
            Task selected = taskList.getSelectedValue();
            if (selected != null) {
                selected.setCompleted(true);
                taskList.repaint();
                updateCategoryFilter();
                applyFilters();
                JOptionPane.showMessageDialog(this,
                    "🎉 Task marked as completed!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            Task selected = taskList.getSelectedValue();
            int selectedIndex = taskList.getSelectedIndex();

            if (selected != null && selectedIndex != -1) {
                selected.setTitle(titleField.getText());
                selected.setDescription(descriptionField.getText());
                selected.setDueDate(dueDateField.getText());
                selected.setPriority((String) priorityBox.getSelectedItem());
                selected.setCategory(categoryField.getText());

                taskListModel.set(selectedIndex, selected);
                JOptionPane.showMessageDialog(this,
                    "✏️ Task updated successfully!",
                    "Update",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields(titleField, descriptionField, dueDateField, categoryField);
                updateCategoryFilter();
                applyFilters();
            }
        });

        // 🔹 Filter actions
        categoryFilter.addActionListener(e -> applyFilters());
        priorityFilter.addActionListener(e -> applyFilters());
        sortFilter.addActionListener(e -> applyFilters());

        // ✅ Ensure categories are loaded at start
        updateCategoryFilter();
        getContentPane().setBackground(bgColor);
    }

    private void updateCategoryFilter() {
        Set<String> categories = new HashSet<>();
        categories.add("All Categories");
        for (Task task : taskManager.getTasks()) {
            if (!task.getCategory().isEmpty()) {
                categories.add(task.getCategory());
            }
        }
        categoryFilter.setModel(new DefaultComboBoxModel<>(categories.toArray(new String[0])));
    }

    private void applyFilters() {
        taskListModel.clear();
        String selectedCategory = (String) categoryFilter.getSelectedItem();
        String selectedPriority = (String) priorityFilter.getSelectedItem();
        String selectedSort = (String) sortFilter.getSelectedItem();

        List<Task> filteredTasks = new ArrayList<>(taskManager.getTasks());

        if (!"All Categories".equals(selectedCategory)) {
            filteredTasks.removeIf(task -> !task.getCategory().equals(selectedCategory));
        }

        if (!"All Priorities".equals(selectedPriority)) {
            filteredTasks.removeIf(task -> !task.getPriority().equals(selectedPriority));
        }

        if ("Sort by Deadline".equals(selectedSort)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            filteredTasks.sort((t1, t2) -> {
                try {
                    Date d1 = sdf.parse(t1.getDueDate());
                    Date d2 = sdf.parse(t2.getDueDate());
                    return d1.compareTo(d2);
                } catch (ParseException e) {
                    return 0;
                }
            });
        } else if ("Sort by Priority".equals(selectedSort)) {
            List<String> priorityOrder = Arrays.asList("High", "Medium", "Low");
            filteredTasks.sort(Comparator.comparingInt(t -> priorityOrder.indexOf(t.getPriority())));
        } else if ("Sort by Category".equals(selectedSort)) {
            filteredTasks.sort(Comparator.comparing(Task::getCategory));
        }

        for (Task task : filteredTasks) {
            taskListModel.addElement(task);
        }
    }

    private void styleButton(JButton button, Color color, Font font) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        button.setPreferredSize(new Dimension(140, 40));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 1),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int y, String label, Component field) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(field, gbc);
        gbc.gridwidth = 1;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListApp().setVisible(true));
    }
}
