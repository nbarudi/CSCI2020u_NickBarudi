package csci2020u.lab05;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Lab05 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Lab05.class.getResource("lab05.fxml"));

        TableView tableView = new TableView();
        TableColumn<StudentRecord, String> StudentIDColumn = new TableColumn<>("Student ID");
        StudentIDColumn.setCellValueFactory(new PropertyValueFactory<>("StudentID"));

        TableColumn<StudentRecord, Float> MidtermColumn = new TableColumn<>("Midterm");
        MidtermColumn.setCellValueFactory(new PropertyValueFactory<>("Midterm"));

        TableColumn<StudentRecord, Float> AssignmentsColumn = new TableColumn<>("Assignments");
        AssignmentsColumn.setCellValueFactory(new PropertyValueFactory<>("Assignments"));

        TableColumn<StudentRecord, Float> FinalExamColumn = new TableColumn<>("Final Exam");
        FinalExamColumn.setCellValueFactory(new PropertyValueFactory<>("FinalExam"));

        TableColumn<StudentRecord, Float> FinalMarkColumn = new TableColumn<>("Final Mark");
        FinalMarkColumn.setCellValueFactory(new PropertyValueFactory<>("FinalMark"));

        TableColumn<StudentRecord, String> LetterGradeColumn = new TableColumn<>("Letter Grade");
        LetterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("Grade"));

        tableView.getColumns().add(StudentIDColumn);
        tableView.getColumns().add(MidtermColumn);
        tableView.getColumns().add(AssignmentsColumn);
        tableView.getColumns().add(FinalExamColumn);
        tableView.getColumns().add(FinalMarkColumn);
        tableView.getColumns().add(LetterGradeColumn);

        tableView.getItems().addAll(DataSource.getAllMarks());

        tableView.setPlaceholder(new Label("No rows to display!"));
        VBox vBox = new VBox(tableView);
        Scene scene = new Scene(vBox, 470, 400);
        stage.setTitle("Student Grades");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}