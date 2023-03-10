/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fi.tuni.prog3.sisu_fxml;

import static fi.tuni.prog3.sisu_fxml.App.studyList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import java.util.prefs.Preferences;
import javafx.beans.value.ObservableValue;
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

  /**
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    List<String> items = new ArrayList<>();

    for (Study s : studyList.values()) {
      items.add(s.getname());
    }
    Collections.sort(items);
    studyProgram.getItems().addAll(items);
    studyProgram.setOnAction(this::setCourseModule);

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Tietojen lataaminen");
    alert.setContentText("Ladataanko aiemmin tehdyt muutokset?");
    ButtonType yesButton = new ButtonType("Kyll??", ButtonBar.ButtonData.YES);
    ButtonType noButton = new ButtonType("Ei", ButtonBar.ButtonData.NO);
    alert.getButtonTypes().setAll(yesButton, noButton);
    alert.showAndWait().ifPresent(type -> {
      if (type == yesButton) {
        importPreferencesFromXml();
      } else if (type == noButton) {
        try {
          prefs.clear();
        } catch (BackingStoreException ex) {
        }
      }
    });
  }

  /**
   * Loads the earlier saved preferences from the preferences.xml file
   */
  private void importPreferencesFromXml() {
    try {
      Preferences.importPreferences(new FileInputStream("preferences.xml"));
    } catch (IOException | InvalidPreferencesFormatException ex) {
    }

    loadStudyProgram();
    // loadFieldOfStudy();

  }

  /**
   * Loads the saved value of the study program to the UI
   */
  public void loadStudyProgram() {
    int progIdx = prefs.getInt("studyProgram", -1);
    if (progIdx != -1) {
      studyProgram.getSelectionModel().select(progIdx);
    }
  }

  /**
   * Selects the clicked Study Program from the dropdown menu and saves preferences
   *
   * @param event Study Program was selected
   */
  public void setCourseModule(ActionEvent event) {

    {
      // Tallentaa valinnan tutkinto-ohjelmasta
      int idx = studyProgram.getSelectionModel().getSelectedIndex();
      prefs.putInt("studyProgram", idx);
      try {
        prefs.exportNode(new FileOutputStream("preferences.xml"));
        prefs.flush();
      } catch (IOException | BackingStoreException ex) {
      }

      String study = studyProgram.getValue();
      TreeItem<String> newRoot = new TreeItem<>(study);

      for (Study s : studyList.values()) {
        if (s.getname().equals(study)) {
          for (StudyGroupModule stg : s.GroupModules.values()) {
            TreeItem<String> branch = new TreeItem<>(stg.getName());
            for (CourseModule cours : stg.courseModules.values()) {
              TreeItem<String> leaf = new TreeItem<>(cours.getName());
              branch.getChildren().add(leaf);
            }
            newRoot.getChildren().add(branch);
          }
          treeView.setRoot(newRoot);
        }
      }
    }
  }

  /**
   * When selecting a module from, shows the selectable courses related to the module on the FloatPane on the right
   */
  public void selectItem() {
    TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
    if (!item.getValue().equals(selectedModule)) {
      selectedModule = item.getValue();
      coursesFlowPane.getChildren().clear();
      for (Study s : studyList.values()) {
        for (StudyGroupModule stg : s.GroupModules.values()) {
          if (stg.getName().equals(item.getValue())) {
            for (CourseModule cours : stg.courseModules.values()) {
              CheckBox course = new CheckBox(cours.getName());
              course.selectedProperty().addListener((ObservableValue<? extends Boolean> metadata, Boolean oldVal, Boolean newVal) -> {
                // Tallentaa valinnan kurssin suoritustiedon
                if (newVal) {
                  prefs.putBoolean(course.getText(), newVal);
                } else {
                  prefs.remove(course.getText());
                }
                try {
                  prefs.exportNode(new FileOutputStream("preferences.xml"));
                  prefs.flush();
                } catch (IOException | BackingStoreException ex) {
                }
              });
              if (prefs.getBoolean(course.getText(), false)) {
                course.setSelected(true);
              }
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
