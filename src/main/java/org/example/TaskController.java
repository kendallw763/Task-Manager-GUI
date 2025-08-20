package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskController {

    private final List<Task> tasks = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    // ~~~~~~~~~~~~~~MENU~~~~~~~~~~~~~~
    private void menu() {
        System.out.println("\nTask Manager Options");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. View all tasks");
        System.out.println("2. Add a task");
        System.out.println("3. Update a task");
        System.out.println("4. Delete a task");
        System.out.println("5. Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Enter a command: ");
    }

    // ~~~~~~~~~~~~~~RUN FUNCTIONALITY~~~~~~~~~~~~~~
    public void run() {
        while (true) {
            menu();
            String command = scanner.nextLine();

            switch (command) {
                case "1" -> viewTasks();
                case "2" -> addTask();
                case "3" -> updateTask();
                case "4" -> deleteTask();
                case "5" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid command! Try again.");
            }
        }
    }

    // ~~~~~~~~~~~~~~VIEW TASKS~~~~~~~~~~~~~~
    private void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("No tasks found");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("All tasks:");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~");
            tasks.forEach(System.out::println);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    // ~~~~~~~~~~~~~~ADD TASK~~~~~~~~~~~~~~
    private void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter a description: ");
        String description = scanner.nextLine();

        System.out.print("Enter due date (yyyy-MM-dd): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        Task task = new Task(title, description, dueDate, false);
        tasks.add(task);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Task created! ID: " + task.getId());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }

    // ~~~~~~~~~~~~~~UPDATE TASK~~~~~~~~~~~~~~
    private void updateTask() {
        System.out.print("Enter the task ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Task taskToUpdate = findTaskById(id);
        if (taskToUpdate == null) {
            System.out.println("Task not found!");
            return;
        }

        System.out.print("Enter new title (leave blank to keep current): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) taskToUpdate.setTitle(newTitle);

        System.out.print("Enter new description (leave blank to keep current): ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isEmpty()) taskToUpdate.setDescription(newDescription);

        System.out.print("Enter new due date (leave blank to keep current): ");
        String newDueDate = scanner.nextLine();
        if (!newDueDate.isEmpty()) taskToUpdate.setDueDate(LocalDate.parse(newDueDate));

        System.out.print("Mark as completed? (yes/no, leave blank to skip): ");
        String completedInput = scanner.nextLine();
        if (completedInput.equalsIgnoreCase("yes")) taskToUpdate.setCompleted(true);
        else if (completedInput.equalsIgnoreCase("no")) taskToUpdate.setCompleted(false);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Task updated!\n" + taskToUpdate);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }

    // ~~~~~~~~~~~~~~DELETE TASK~~~~~~~~~~~~~~
    private void deleteTask() {
        System.out.print("Enter the task ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        Task taskToDelete = findTaskById(id);
        if (taskToDelete == null) {
            System.out.println("Task not found!");
            return;
        }

        tasks.remove(taskToDelete);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Task deleted!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }

    // ~~~~~~~~~~~~~~HELPER FUNCTION~~~~~~~~~~~~~~
    private Task findTaskById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }
}
