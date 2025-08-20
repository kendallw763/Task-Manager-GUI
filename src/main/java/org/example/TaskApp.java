package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Objects;

public class TaskApp extends Application {//extends the fx class

    private final ObservableList<Task> tasks = FXCollections.observableArrayList();//pulls data from the list of tasks

    @Override//override current functionality
    public void start(Stage primaryStage) {//method for implementing the GUI

        // ---------- Title ----------
        Label title = new Label("Task Manager");//gives the GUI a title
        //title.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");
        title.getStyleClass().add("title");//external style sheet (GUIStyle.css)

        HBox titleBox = new HBox(title);
        HBox.setHgrow(titleBox, Priority.ALWAYS);
        HBox.setMargin(title, new Insets(30, 0, 30, 0));

        titleBox.setAlignment(Pos.CENTER);
        titleBox.setMaxWidth(Double.MAX_VALUE);
        titleBox.setStyle("-fx-background-color: #0B3D91; -fx-background-radius: 8");

        // ---------- Table ----------
        TableView<Task> tableView = new TableView<>();
        tableView.setItems(tasks);


        TableColumn<Task, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMaxWidth(50);
        idColumn.setStyle("-fx-background-color: #F4F4F4;");


        TableColumn<Task, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setMaxWidth(200);
        titleColumn.setStyle("-fx-background-color: #F4F4F4;");

        TableColumn<Task, String> descColumn = new TableColumn<>("Description");
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descColumn.setMaxWidth(300);
        descColumn.setStyle("-fx-background-color: #F4F4F4;");

        TableColumn<Task, String> dueColumn = new TableColumn<>("Due Date");
        dueColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        dueColumn.setMaxWidth(300);
        dueColumn.setStyle("-fx-background-color:#F4F4F4;");

        TableColumn<Task, Boolean> completeColumn = new TableColumn<>("Completed");
        descColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
        completeColumn.setMaxWidth(300);
        completeColumn.setStyle("-fx-background-color: #F4F4F4;");


        HBox.setHgrow(tableView, Priority.ALWAYS);

        //noinspection deprecation
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setFixedCellSize(25);

        //noinspection unchecked
        tableView.getColumns().addAll(idColumn, titleColumn, descColumn, dueColumn, completeColumn);

        // ---------- Buttons ----------
        Button addButton = new Button("Add");
        addButton.getStyleClass().add("add-button");//external style sheet (GUIStyle.css)


        Button updateButton = new Button("Update");
        updateButton.getStyleClass().add("update-button");//external style sheet (GUIStyle.css)


        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("delete-button");//external style sheet (GUIStyle.css)


        VBox buttonBox = new VBox(15, addButton,  updateButton, deleteButton);
        buttonBox.setPadding(new Insets(30, 0, 0,0));

        // ---------- Table + Buttons HBox ----------
        HBox tableWithButtons = new HBox(20, tableView, buttonBox);
        tableWithButtons.setAlignment(Pos.CENTER);

        // ---------- Root VBox ----------
        VBox root = new VBox(20, titleBox, tableWithButtons);
        root.setPadding(new Insets(20));

        // ---------- Scene ----------
        Scene scene = new Scene(root, 800, 600);

       scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/GUIStyle.css")).toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.setTitle("Task App");
        primaryStage.show();

        // ---------- Example data ----------


    }

    public static void main(String[] args) {
        launch(args);
    }
}
