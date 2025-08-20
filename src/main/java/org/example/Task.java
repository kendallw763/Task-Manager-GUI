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
        this.dueDate = dueDate;
        this.completed = completed;
        System.out.println(this);
    }

    public String toString() {
        return "Id: " + id + "\n"
                + "Title: " + title + "\n"
                + "Description: " + description + "\n"
                + "Due date: " + dueDate + "\n"
                + "Complete: " + completed
                + "\n";
    }

    //GETTERS/SETTERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public int getId(){return id;}

    public void setId(Integer id){this.id = id;}

    public void setTitle(String title){this.title = title;}

    public void setDescription(String description){this.description = description;}

    public void setCompleted(boolean completed){this.completed = completed;}

    //Checks if the due date is in the past and throws an exception if it is~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void setDueDate(LocalDate dueDate){

        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Dates must be present or in the future. Select a valid date.");
        }else{
            this.dueDate = dueDate;
            }
        }
    }


