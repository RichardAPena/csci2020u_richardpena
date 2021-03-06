package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML private TableView<StudentRecord> marks;
    @FXML private TableColumn<StudentRecord, String> sid;
    @FXML private TableColumn<StudentRecord, Double> assignments;
    @FXML private TableColumn<StudentRecord, Double> midterm;
    @FXML private TableColumn<StudentRecord, Double> final_exam;
    @FXML private TableColumn<StudentRecord, Double> final_mark;
    @FXML private TableColumn<StudentRecord, String> letter_grade;

    @FXML
    public void initialize() {
        sid.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("sid"));
        assignments.setCellValueFactory(new PropertyValueFactory<StudentRecord, Double>("assignments"));
        midterm.setCellValueFactory(new PropertyValueFactory<StudentRecord, Double>("midterm"));
        final_exam.setCellValueFactory(new PropertyValueFactory<StudentRecord, Double>("final_exam"));
        final_mark.setCellValueFactory(new PropertyValueFactory<StudentRecord, Double>("final_mark"));
        letter_grade.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("letter_grade"));
        marks.setItems(DataSource.getAllMarks());
    }
}
