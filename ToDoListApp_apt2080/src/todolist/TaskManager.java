/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all Task objects in the To-Do List application.
 */
public class TaskManager {
    private final List<Task> tasks;

    /**
     * Initializes an empty task list.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     * @param task the Task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a specific task from the list.
     * @param task the Task to remove
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Returns all tasks currently stored.
     * @return list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Removes all tasks from the manager.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Finds a task by its title (case-insensitive).
     * @param title the title to search for
     * @return the matching Task, or null if not found
     */
    public Task getTaskByTitle(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return task;
            }
        }
        return null;
    }
}


