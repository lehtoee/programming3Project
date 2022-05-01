/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fi.tuni.prog3.sisu_fxml;

import static fi.tuni.prog3.sisu_fxml.App.studyList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import java.util.prefs.Preferences;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author eemil
 */
public class HomePageController implements Initializable {

    @FXML
    private TreeView treeView;

    @FXML
    private FlowPane coursesFlowPane;

    @FXML
    private ChoiceBox<String> studyProgram;

    @FXML
    private ChoiceBox fieldOfStudy;

    private List<String> courses;

    private final Preferences prefs = Preferences.userRoot();

    String selectedModule = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> items = new ArrayList<>();

        for (Study s : studyList.values()) {
            //System.out.println(s);
            for (StudyGroupModule stg : s.GroupModules.values()) {
                //System.out.println(stg);
            }
            items.add(s.getname());
        }
        Collections.sort(items);
        studyProgram.getItems().addAll(items);
        studyProgram.setOnAction(this::setCourseModule);

//        studyProgram.setOnAction(eh -> {
//            int val = studyProgram.getSelectionModel().getSelectedIndex();
//            prefs.putInt("studyProgram", val);
//            try {
//                prefs.exportNode(new FileOutputStream("test.xml"));
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            } catch (BackingStoreException ex) {
//                ex.printStackTrace();
//            }
//        });

        /*
    branchItem1.getChildren().addAll(leafItem1);
    branchItem2.getChildren().addAll(leafItem2);
    branchItem3.getChildren().addAll(leafItem3);

    rootItem.getChildren().addAll(branchItem1, branchItem2, branchItem3);

    treeView.setRoot(rootItem);
    courses = new ArrayList<>();
    courses.add("ohjelmointi");
    courses.add("tietokannat");
    courses.add("tilastotiede");
    courses.add("matikka");
    courses.add("viestintä");
    for (int i = 0; i < courses.size(); i++) {
      CheckBox course = new CheckBox(courses.get(i));
      coursesFlowPane.getChildren().add(course);
    }
    coursesFlowPane.setOrientation(Orientation.VERTICAL);
    coursesFlowPane.setVgap(10);
    fieldOfStudy.getItems().addAll((Object[]) fieldofstudies);
        fieldOfStudy.setOnAction(eh -> {
            int val = fieldOfStudy.getSelectionModel().getSelectedIndex();
            prefs.putInt("fieldOfStudy", val);
            try {
                prefs.exportNode(new FileOutputStream("test.xml"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (BackingStoreException ex) {
                ex.printStackTrace();
            }
        });
    
         */
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Tietojen lataaminen");
        alert.setContentText("Ladataanko aiemmin tehdyt muutokset?");
        ButtonType yesButton = new ButtonType("Kyllä", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Ei", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == yesButton) {
                importPreferencesFromXml();
            } else if (type == noButton) {
                try {
                    prefs.clear();
                } catch (BackingStoreException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void importPreferencesFromXml() {
        try {
            Preferences.importPreferences(new FileInputStream("test.xml"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InvalidPreferencesFormatException ex) {
            ex.printStackTrace();
        }

        loadStudyProgram();
//        loadFieldOfStudy();

    }

    public void loadStudyProgram() {
        int progIdx = prefs.getInt("studyProgram", -1);
        if (progIdx != -1) {
            System.out.println("Selecting study programme with ID: " + progIdx);
            studyProgram.getSelectionModel().select(progIdx);
        }
    }

//    public void loadFieldOfStudy() {
//        int fieldIdx = prefs.getInt("fieldOfStudy", -1);
//        if (fieldIdx != -1) {
//            System.out.println("Selecting study field with ID: " + fieldIdx);
//            fieldOfStudy.getSelectionModel().select(fieldIdx);
//        }
//    }
    public void setCourseModule(ActionEvent event) {

        {
            // Tallentaa valinnan tutkinto-ohjelmasta
            int idx = studyProgram.getSelectionModel().getSelectedIndex();
            prefs.putInt("studyProgram", idx);
            try {
                prefs.exportNode(new FileOutputStream("test.xml"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (BackingStoreException ex) {
                ex.printStackTrace();
            }
        }

        String study = studyProgram.getValue();
        System.out.println(study);
        TreeItem<String> newRoot = new TreeItem<>(study);
        /*
    for (Study s : studyList.values()) {
        //System.out.println(s);
      if(s.getname() == study){
        selectedCourseModules =  s.getStudyGroupHashMap();
        System.out.println(selectedCourseModules + "selectedmodules");
        selectedCourseModules.forEach((key, value) -> studyGroupModules.add(value.getName()));
        for(int i=0; i<studyGroupModules.size(); i++){
          
          //String branchName = getBranchText(i);
          TreeItem<String> branch = new TreeItem<>(studyGroupModules.get(i));
          for(StudyGroupModule stg : s.GroupModules.values()){
            courseModules = stg.getCourseModuleHashMap();
            System.out.println(courseModules + "coursemodules");
            courseModules.forEach((key, value) -> courseModuleList.add(value.getName()));
            for(int j=0; i<courseModuleList.size(); i++){
              TreeItem<String> leaf = new TreeItem<>(courseModuleList.get(j));
              branch.getChildren().add(leaf);
            }
            
          }
          newRoot.getChildren().add(branch);
        }
        treeView.setRoot(newRoot);
      }
    }*/

        for (Study s : studyList.values()) {
            //System.out.println(s);
            if (s.getname().equals(study)) {
                for (StudyGroupModule stg : s.GroupModules.values()) {
                    System.out.println(stg);
                    TreeItem<String> branch = new TreeItem<>(stg.getName());
                    for (CourseModule cours : stg.courseModules.values()) {
                        System.out.println(cours);
                        TreeItem<String> leaf = new TreeItem<>(cours.getName());
                        branch.getChildren().add(leaf);
                    }
                    newRoot.getChildren().add(branch);
                }
                treeView.setRoot(newRoot);
            }

        }


        /*List<TreeItem> branches = new ArrayList<TreeItem>();
    for(int i=0; i<courseModules.size(); i++){
        System.out.println(courseModules.get(i));
        //String branchName = getBranchText(i);
        TreeItem<String> branch = new TreeItem<>(courseModules.get(i));
        TreeItem<String> leaf = new TreeItem<>("juu");
        branch.getChildren().add(leaf);
        newRoot.getChildren().add(branch);
    }
   
    
    treeView.setRoot(newRoot);
    HashMap<String, StudyGroupModule> selectedCourseModule = 
            new HashMap<String, StudyGroupModule>();
    for (Study s : studyList.values()) {
      System.out.println(s);
      selectedCourseModule.put(s.getStudyGroupHashMap());
    }*/
    }

    public void selectItem() {
        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        if (!item.getValue().equals(selectedModule)) {
            selectedModule = item.getValue();
            coursesFlowPane.getChildren().clear();
            for (Study s : studyList.values()) {
                //System.out.println(s);
                for (StudyGroupModule stg : s.GroupModules.values()) {
                    System.out.println(stg);
                    if (stg.getName().equals(item.getValue())) {
                        for (CourseModule cours : stg.courseModules.values()) {
                            CheckBox course = new CheckBox(cours.getName());
                            coursesFlowPane.getChildren().add(course);
                        }
                    }
                }
                coursesFlowPane.setOrientation(Orientation.VERTICAL);
                coursesFlowPane.setVgap(10);
            }
        }

    }

}
