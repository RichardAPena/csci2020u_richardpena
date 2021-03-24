package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class Controller {

    @FXML private TableView<StudentRecord> marks;
    @FXML private TableColumn<StudentRecord, String> sid;
    @FXML private TableColumn<StudentRecord, Double> assignments;
    @FXML private TableColumn<StudentRecord, Double> midterm;
    @FXML private TableColumn<StudentRecord, Double> final_exam;
    @FXML private TableColumn<StudentRecord, Double> final_mark;
    @FXML private TableColumn<StudentRecord, String> letter_grade;
    @FXML private MenuBar menuBar;
    private String currentFilename;
    private File textFile;
    private ObservableList<StudentRecord> list;
    private String writeName;

    // New: Clear items
    // Open: Use file chooser, and read everything
    // Save: Save the data as a file
    // Save As:

    @FXML
    public void initialize() {
        sid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        assignments.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        final_exam.setCellValueFactory(new PropertyValueFactory<>("final_exam"));
        final_mark.setCellValueFactory(new PropertyValueFactory<>("final_mark"));
        letter_grade.setCellValueFactory(new PropertyValueFactory<>("letter_grade"));
        list = DataSource.getAllMarks();
        marks.setItems(list);
    }

    private void load() throws FileNotFoundException {
        Scanner scan = new Scanner(textFile);
        String token = scan.nextLine();
        if (token.contains("sid,assignments,midterm,final_exam")) {
            while (scan.hasNext()) {
                token = scan.nextLine();
                String[] tokenArr = token.split(",");
                list.add(new StudentRecord(tokenArr[0], Double.parseDouble(tokenArr[1]), Double.parseDouble(tokenArr[2]), Double.parseDouble(tokenArr[3])));
            }
        }
        scan.close();
    }

    private void save() throws IOException {
        String content = "sid,assignments,midterm,final_exam\n";
        for (int i=0; i<list.size(); i++) {
            content += list.get(i).getSid() + "," + list.get(i).getAssignments() + "," + list.get(i).getMidterm() + "," + list.get(i).getFinal_exam() + "\n";
        }

        File newFile = new File("student.csv");
        newFile.createNewFile();
        FileWriter fileWriter = new FileWriter(writeName);
        fileWriter.write(content);
        fileWriter.close();
    }

    @FXML
    public void handleNew(ActionEvent actionEvent) {
        System.out.println("Clearing table...");
        list.clear();
        marks.setItems(list);
    }

    @FXML
    public void handleOpen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.csv"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        textFile = fileChooser.showOpenDialog(null);

        if (textFile != null) {
            try { load(); } catch (FileNotFoundException e) { e.printStackTrace(); }
        }
        System.out.println("Opening File...");
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        System.out.println("Saving File...");
        writeName = "student.csv";
        try { save(); } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    public void handleSaveAs(ActionEvent actionEvent) {
        System.out.println("Saving File As...");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("student.csv");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        textFile = fileChooser.showSaveDialog(null);
        
        //textFile = fileChooser.getInitialDirectory();
        System.out.println(textFile.getAbsolutePath());
        writeName = textFile.getAbsolutePath();
        //writeName = fileChooser.getInitialFileName();
        //writeName += fileChooser.getInitialFileName();
        //textFile.getAbsolutePath();
        try { save(); } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
