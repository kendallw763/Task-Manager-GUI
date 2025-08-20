package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Objects;

public class TaskApp extends Application {

    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // ---------- Title ----------
        Label title = new Label("Task Manager");
        title.getStyleClass().add("title");

        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20, 0, 20, 0));
        titleBox.setStyle("-fx-background-color: #0B3D91; -fx-background-radius: 8");

        // ---------- Table ----------
        TableView<Task> tableView = new TableView<>();
        tableView.setItems(tasks);

        TableColumn<Task, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(50);

        TableColumn<Task, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setPrefWidth(200);

        TableColumn<Task, String> descColumn = new TableColumn<>("Description");
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descColumn.setPrefWidth(300);

        TableColumn<Task, LocalDate> dueColumn = new TableColumn<>("Due Date");
        dueColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        dueColumn.setPrefWidth(150);

        // ---------- Completed Column with Radio Buttons ----------
        TableColumn<Task, Boolean> completeColumn = new TableColumn<>("Completed");
        completeColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
        completeColumn.setPrefWidth(150);

        completeColumn.setCellFactory(col -> new TableCell<Task, Boolean>() {
            private final RadioButton yesButton = new RadioButton("Yes");
            private final RadioButton noButton = new RadioButton("No");
            private final ToggleGroup toggleGroup = new ToggleGroup();

            {
                yesButton.setToggleGroup(toggleGroup);
                noButton.setToggleGroup(toggleGroup);

                toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
                    if (newToggle != null && getTableRow() != null && getTableRow().getItem() != null) {
                        Task task = getTableRow().getItem();
                        task.setCompleted(newToggle == yesButton);
                    }
                });
            }

            @Override
            protected void updateItem(Boolean completed, boolean empty) {
                super.updateItem(completed, empty);

                if (empty || getTableRow().getItem() == null) {
                    setGraphic(null);
                } else {
                    if (completed != null && completed) {
                        yesButton.setSelected(true);
                    } else {
                        noButton.setSelected(true);
                    }
                    HBox box = new HBox(5, yesButton, noButton);
                    box.setAlignment(Pos.CENTER);
                    setGraphic(box);
                }
            }
        });

        // let table expand
        HBox.setHgrow(tableView, Priority.ALWAYS);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(idColumn, titleColumn, descColumn, dueColumn, completeColumn);

        // ---------- Input Fields ----------
        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");

        TextField descInput = new TextField();
        descInput.setPromptText("Description");

        DatePicker dueDateInput = new DatePicker();
        dueDateInput.setPromptText("Due Date");

        HBox inputBox = new HBox(10, titleInput, descInput, dueDateInput);
        inputBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setPadding(new Insets(10, 0, 10, 0));

        // ---------- Buttons ----------
        Button addButton = new Button("Add");
        addButton.getStyleClass().add("add-button");
        addButton.setOnAction(e -> {
            try {
                String taskTitle = titleInput.getText();
                String taskDesc = descInput.getText();
                LocalDate taskDue = dueDateInput.getValue();

                if (taskTitle.isEmpty() || taskDesc.isEmpty() || taskDue == null) {
                    showAlert("Error", "All fields must be filled.");
                    return;
                }

                Task newTask = new Task(taskTitle, taskDesc, taskDue, false);
                tasks.add(newTask);

                titleInput.clear();
                descInput.clear();
                dueDateInput.setValue(null);

            } catch (IllegalArgumentException ex) {
                showAlert("Invalid Date", ex.getMessage());
            }
        });

        Button updateButton = new Button("Update");
        updateButton.getStyleClass().add("update-button");
        updateButton.setOnAction(e -> {
            Task selectedTask = tableView.getSelectionModel().getSelectedItem();
            if (selectedTask == null) {
                showAlert("Error", "No task selected to update.");
                return;
            }

            try {
                String newTitle = titleInput.getText();
                String newDesc = descInput.getText();
                LocalDate newDue = dueDateInput.getValue();

                if (!newTitle.isEmpty()) selectedTask.setTitle(newTitle);
                if (!newDesc.isEmpty()) selectedTask.setDescription(newDesc);
                if (newDue != null) selectedTask.setDueDate(newDue);

                // refresh table view to reflect changes
                tableView.refresh();

                titleInput.clear();
                descInput.clear();
                dueDateInput.setValue(null);

            } catch (IllegalArgumentException ex) {
                showAlert("Invalid Date", ex.getMessage());
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setOnAction(e -> {
            Task selectedTask = tableView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                tasks.remove(selectedTask);
            } else {
                showAlert("Error", "No task selected to delete.");
            }
        });

        HBox buttonBox = new HBox(15, addButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        // ---------- Root Layout ----------
        VBox root = new VBox(20, titleBox, inputBox, buttonBox, tableView);
        root.setPadding(new Insets(20));

        // ---------- Scene ----------
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/GUIStyle.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Task App");
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
