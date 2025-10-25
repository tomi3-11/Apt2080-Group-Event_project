/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

/**
 * Represents a single task in the To-Do List application.
 */
public class Task {
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private String category;
    private boolean completed;
    private String assignedTo;
    private int progress; // 0–100%

    /**
     * Creates a new Task.
     */
    public Task(String title, String description, String dueDate,
                String priority, String category, String assignedTo) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
        this.assignedTo = assignedTo;
        this.completed = false;
        this.progress = 0;
    }

    // ===== Getters =====
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDueDate() { return dueDate; }
    public String getPriority() { return priority; }
    public String getCategory() { return category; }
    public boolean isCompleted() { return completed; }
    public String getAssignedTo() { return assignedTo; }
    public int getProgress() { return progress; }

    // ===== Setters =====
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setCategory(String category) { this.category = category; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    /**
     * Sets task progress (clamped between 0–100%).
     */
    public void setProgress(int progress) {
        this.progress = Math.max(0, Math.min(100, progress));
    }

    /**
     * String representation of the task, used in the JList display.
     */
    @Override
    public String toString() {
        String icon = completed ? "✅" : "🗓️";
        return String.format("%s %s | %s | Due: %s | Priority: %s | Category: %s | Assigned: %s | Progress: %d%%",
                icon, title, description, dueDate, priority, category,
                (assignedTo == null || assignedTo.isBlank() ? "None" : assignedTo),
                progress);
    }
}
