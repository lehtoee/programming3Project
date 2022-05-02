package fi.tuni.prog3.sisu_fxml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

  private static Scene scene;
  static Hashtable<String, Study> studyList = new Hashtable<>();

  public CourseModule getCourseModuleById(String id) throws IOException {
    StringBuilder result = new StringBuilder();
    URL url;
    url =
      new URL(
        (
          "https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId="+id+"&universityId=tuni-university-root-id"
        )
      );
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      conn.setRequestMethod("GET");
    } catch (ProtocolException e) {
      e.printStackTrace();
    }
    try (
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(conn.getInputStream())
      )
    ) {
      for (String line; (line = reader.readLine()) != null;) {
        result.append(line);
      }
    }
    String line = result.toString().replace("\"", "");
    line = line.replace("[", "");
    line = line.replace("]", "");
    String[] t1;
    t1 = line.split("id:");
    t1 = t1[1].split(",");
    String courseId = t1[0];

    t1 = line.split("name:");
    t1 = t1[1].split(",");
    String name;
    if(t1[0].split("en:").length == 1) {
      name = t1[0].split("fi:")[1];
    }
    else {
      name = t1[0].split("en:")[1];
    }

    t1 = line.split("code:");
    t1 = t1[2].split(",");
    String code = t1[0];

    t1 = line.split("min:");
    t1 = t1[1].split(",");
    String min = t1[0];

    t1 = line.split("max:");
    t1 = t1[1].split("\\}");
    String max = t1[0];

    return new CourseModule(name, code, courseId, min, max);
  }
  public StudyGroupModule getGroupModuleById(String id) throws IOException {
    StringBuilder result = new StringBuilder();
    URL url;
    url =
      new URL(
        (
          "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=" +
          id +
          "&universityId=tuni-university-root-id"
        )
      );
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      conn.setRequestMethod("GET");
    } catch (ProtocolException e) {
      e.printStackTrace();
    }
    try (
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(conn.getInputStream())
      )
    ) {
      for (String line; (line = reader.readLine()) != null;) {
        result.append(line);
      }
    }
    String line = result.toString().replace("\"", "");
    line = line.replace("[", "");
    line = line.replace("]", "");
    String[] t1;
    t1 = line.split("id:");
    t1 = t1[1].split(",");
    String moduleId = t1[0];

    t1 = line.split("code:");
    if (t1[1].equals("study-right-selection-type:minor-study-right},")) {
      t1 = t1[2].split(",");
    } else {
      t1 = t1[1].split(",");
    }
    String code = t1[0];

    t1 = line.split("name:");
    t1 = t1[1].split(",");
    String name;
    if(t1[0].split("en:").length == 1) {
      name = t1[0].split("fi:")[1];
    }
    else {
      name = t1[0].split("en:")[1];
    }

    t1 = line.split("min:");
    t1 = t1[1].split(",");
    String min = t1[0];

    t1 = line.split("max:");
    t1 = t1[1].split("\\}");
    String max = t1[0];

    t1 = line.split("allMandatory:");
    t1 = t1[1].split("\\}");
    boolean mandatory;
    if (t1[0].equals("true")) {
      mandatory = true;
    } else {
      mandatory = false;
    }
    String[] splitted = line.split("courseUnitGroupId:");
    List<String> idList = new ArrayList<String>();
    for (int i = 1; i < splitted.length; i++) {
      String splt = splitted[i].split(",")[0].replace("}", "");
      idList.add(splt);
    }
    List<CourseModule> courseList = new ArrayList<CourseModule>();
    for (String s : idList) {
      courseList.add(getCourseModuleById(s));
    }
    return new StudyGroupModule(name, moduleId, code, min, max, mandatory, courseList);
  }

  public List<StudyGroupModule> getGroupModules(String id) throws IOException {
    StringBuilder result = new StringBuilder();
    URL url;
    url = new URL(("https://sis-tuni.funidata.fi/kori/api/modules/" + id));
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      conn.setRequestMethod("GET");
    } catch (ProtocolException e) {
      e.printStackTrace();
    }
    try (
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(conn.getInputStream())
      )
    ) {
      for (String line; (line = reader.readLine()) != null;) {
        result.append(line);
      }
    }
    String line = result.toString().replace("}", "");
    line = line.replace("\"", "");
    line = line.replace("]", "");
    String[] splitted = line.split("moduleGroupId:");
    List<String> idList = new ArrayList<String>();
    for (int i = 1; i < splitted.length; i++) {
      idList.add(splitted[i].split(",")[0]);
    }
    List<StudyGroupModule> moduleList = new ArrayList<StudyGroupModule>();
    for (String s : idList) {
      moduleList.add(getGroupModuleById(s));
    }
    
    
    return moduleList;
  }

  public void getData() throws IOException {
    StringBuilder result = new StringBuilder();
    URL url;
    url =
      new URL(
        "https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=1000"
      );
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      conn.setRequestMethod("GET");
    } catch (ProtocolException e) {
      e.printStackTrace();
    }
    try (
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(conn.getInputStream())
      )
    ) {
      for (String line; (line = reader.readLine()) != null;) {
        result.append(line);
      }
    }
    String line = result.toString();
    line = line.replace("\"", "");
    line = line.replace("[", "");
    line = line.replace("]", "");
    String[] splitted = line.split("\\},");
    for (String str : splitted) {
      String[] t1;
      t1 = str.split("lang:");
      if (t1.length == 1) {
        break;
      }
      t1 = t1[1].split(",");
      if (t1[0].equals("en")) {
        t1 = str.split("id:");
        t1 = t1[1].split(",");
        String id = t1[0];

        t1 = str.split("code:");
        t1 = t1[1].split(",");
        String code = t1[0];

        t1 = str.split("groupId:");
        t1 = t1[1].split(",");
        String groupId = t1[0];

        t1 = str.split("name:");
        t1 = t1[1].split(",");
        String name = t1[0];

        t1 = str.split("curriculumPeriodIds:");
        t1 = t1[1].split(",credits");
        String[] currP = t1[0].split(",");

        t1 = str.split("min:");
        t1 = t1[1].split(",");
        String min = t1[0];

        t1 = str.split("max:");
        t1 = t1[1].split("\\}");
        String max = t1[0];
        studyList.put(
          id,
          new Study(
            id,
            groupId,
            code,
            name,
            currP,
            min,
            max,
            getGroupModules(id)
          )
        );
      }
    }
  }

  @Override
  public void start(Stage stage) throws IOException {
    getData();
    scene = new Scene(loadFXML("HomePage"), 675, 750);
    stage.setScene(scene);
    stage.show();
  }

  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
      App.class.getResource(fxml + ".fxml")
    );
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    launch();
  }
}
