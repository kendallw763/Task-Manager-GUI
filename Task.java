package org.example;

import java.time.LocalDate;

public class Task {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public Task(String title, String description, LocalDate dueDate, boolean completed){
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        setDueDate(dueDate); // use validation
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n"
                + "Title: " + title + "\n"
                + "Description: " + description + "\n"
                + "Due date: " + dueDate + "\n"
                + "Complete: " + completed
                + "\n";
    }

    // GETTERS
    public int getId(){return id;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public LocalDate getDueDate(){return dueDate;}
    public boolean isCompleted(){return completed;}

    // SETTERS
    public void setId(Integer id){this.id = id;}
    public void setTitle(String title){this.title = title;}
    public void setDescription(String description){this.description = description;}
    public void setCompleted(boolean completed){this.completed = completed;}

    // Validation for due date
    public void setDueDate(LocalDate dueDate){
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Dates must be today or in the future. Select a valid date.");
        } else {
            this.dueDate = dueDate;
        }
    }
}
