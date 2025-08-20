package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    //~~~~~~~~~~~~~~~~~master task list~~~~~~~~~~~~~~~~~
    private static final List<Task> tasks = new ArrayList<>();
    private static int idCounter = 1; // ensures unique IDs

    //~~~~~~~~~~~~~~~~~Create~~~~~~~~~~~~~~~~~
    public static void addTask(Task task){
        task.setId(idCounter++);
        tasks.add(task);
    }

    //~~~~~~~~~~~~~~~~~Read~~~~~~~~~~~~~~~~~
    static List <Task> getAllTasks(){
        return tasks;
   }

    //~~~~~~~~~~~~~~~~~Update~~~~~~~~~~~~~~~~~
    public static void updateTask(Task updatedTask){
        for(int t = 0; t < tasks.size(); t++){
            if(tasks.get(t).getId() == updatedTask.getId()){
                tasks.set(t, updatedTask);
                return;
            }
        }
        throw new IllegalArgumentException("The task does not exist" + updatedTask);
    }

    //~~~~~~~~~~~~~~~~~Delete~~~~~~~~~~~~~~~~~
    public static void deleteTask(int id){
        tasks.removeIf(task -> task.getId() == id);
    }

    //~~~~~~~~~~~~~~~~~Helper function~~~~~~~~~~~~~~~~~
    public static Task getTaskById(int id){
        return tasks.stream()
                .filter(t -> t.getId() == id)
                    .findFirst()
                        .orElse(null);
    }
}
