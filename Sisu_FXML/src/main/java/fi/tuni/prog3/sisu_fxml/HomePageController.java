/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fi.tuni.prog3.sisu_fxml;

import static fi.tuni.prog3.sisu_fxml.App.studyList;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
  private ChoiceBox studyProgram;

  @FXML
  private ChoiceBox fieldOfStudy;

  private List<String> courses;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    List<String> items = new ArrayList<String>();

    for (Study s : studyList.values()) {
      //System.out.println(s);
      items.add(s.getname());
    }
    Collections.sort(items);
    studyProgram.getItems().addAll(items);

    String[] fieldofstudies = { "tietoteknikka", "sähkötekniikka" };
    String[] studies = {
      "tietojenkäsittelytiede",
      "perusopinnot",
      "aineopinnot",
      "vapaa-valintaiset opinnot",
      "ohjelmointi1",
      "matikka",
      "espanja",
    };

    fieldOfStudy.getItems().addAll(fieldofstudies);

    TreeItem<String> rootItem = new TreeItem<>(studies[0]);

    TreeItem<String> branchItem1 = new TreeItem<>(items.get(1));
    TreeItem<String> branchItem2 = new TreeItem<>(studies[2]);
    TreeItem<String> branchItem3 = new TreeItem<>(studies[3]);

    TreeItem<String> leafItem1 = new TreeItem<>(studies[4]);
    TreeItem<String> leafItem2 = new TreeItem<>(studies[5]);
    TreeItem<String> leafItem3 = new TreeItem<>(studies[6]);

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
  }

  public void selectItem() {}
}
