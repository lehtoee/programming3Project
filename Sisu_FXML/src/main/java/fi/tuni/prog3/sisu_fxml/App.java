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
  static Hashtable<String, Study> studyList = new Hashtable<String, Study>();

  public CourseModule getCourseModuleById(String id) throws IOException {
    StringBuilder result = new StringBuilder();
    URL url;
    url = new URL(
        ("https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId=" + id
            + "&universityId=tuni-university-root-id"));
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      conn.setRequestMethod("GET");
    } catch (ProtocolException e) {
      e.printStackTrace();
    }
    try (
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream()))) {
      for (String line; (line = reader.readLine()) != null;) {
        result.append(line);
      }
    }
    String str = result.toString();
    String[] t1;
    t1 = str.split("\"id\":\"");
    t1 = t1[1].split("\"");
    String courseId = t1[0];

    String name;
    t1 = str.split("\"name\":");
    name = t1[0];
    if (t1[1].contains("\"fi\":")) {
      t1 = t1[1].split("\"fi\":\"");
      t1 = t1[1].split("\"");
      name = t1[0];
    } else {
      t1 = t1[1].split("\"en\":\"");
      t1 = t1[1].split("\"");
      name = t1[0];
    }

    t1 = str.split("\"code\":\"");
    t1 = t1[1].split("\"");
    String code = t1[0];

    t1 = str.split("\"min\":");
    t1 = t1[1].replace(",", "").split("\"max\":");

    String min = t1[0];
    String max = t1[1].split("}")[0];
    CourseModule course = new CourseModule(name, code, courseId, min, max);
    // System.out.println(course.toString());

    return course;
  }

  public StudyGroupModule getGroupModuleById(String id) throws IOException {
    StringBuilder result = new StringBuilder();
    URL url;
    url = new URL(
        ("https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=" +
            id +
            "&universityId=tuni-university-root-id"));
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      conn.setRequestMethod("GET");
    } catch (ProtocolException e) {
      e.printStackTrace();
    }
    try (
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream()))) {
      for (String line; (line = reader.readLine()) != null;) {
        result.append(line);
      }
    }
    String str = result.toString();
    String[] t1;
    if (str.contains("\"id\":\"")) {
      t1 = str.split("\"id\":\"");
      t1 = t1[1].split("\"");
      String moduleId = t1[0];

      String code;
      if (!str.contains("\"code\":\"")) {
        code = null;
      } else {
        t1 = str.split("\"code\":\"");
        t1 = t1[1].split("\"");
        code = t1[0];
      }

      String name;
      t1 = str.split("\"name\":");
      if (t1[1].contains("\"fi\":")) {
        t1 = t1[1].split("\"fi\":\"");
        t1 = t1[1].split("\"");
        name = t1[0];
      } else {
        t1 = t1[1].split("\"en\":\"");
        t1 = t1[1].split("\"");
        name = t1[0];
      }

      String min;
      String max;
      if (!str.contains("\"min\":")) {
        min = null;
        max = null;
      } else {
        t1 = str.split("\"min\":");
        t1 = t1[1].replace(",", "").split("\"max\":");
        min = t1[0];
        max = t1[1].split("}")[0];
      }

      t1 = str.split("\"allMandatory\":");
      t1 = t1[1].split("}");
      boolean mandatory;
      if (t1[0].equals("true")) {
        mandatory = true;
      } else {
        mandatory = false;
      }
      String[] splitted = str.split("\"courseUnitGroupId\":\"");
      List<String> idList = new ArrayList<String>();
      for (int i = 1; i < splitted.length; i++) {
        String splt = splitted[i].split("}")[0];
        splt = splt.replace("\"", "");
        idList.add(splt);
      }
      List<CourseModule> courseList = new ArrayList<CourseModule>();
      for (String s : idList) {
        courseList.add(getCourseModuleById(s));
      }
      return new StudyGroupModule(name, moduleId, code, min, max, mandatory, courseList);
    } else {
      return null;
    }
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
            new InputStreamReader(conn.getInputStream()))) {
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
      StudyGroupModule sgm = getGroupModuleById(s);
      if (sgm != null) {
        moduleList.add(sgm);
      }
    }

    return moduleList;
  }

  public void getData() throws IOException {
    StringBuilder result = new StringBuilder();
    URL url;
    url = new URL(
        "https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=1000");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      conn.setRequestMethod("GET");
    } catch (ProtocolException e) {
      e.printStackTrace();
    }
    try (
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream()))) {
      for (String line; (line = reader.readLine()) != null;) {
        result.append(line);
      }
    }
    String line = result.toString();
    String[] splitted = line.split("},");
    for (String str : splitted) {
      String[] t1;
      t1 = str.split("\"id\":\"");
      if (t1.length == 1) {
        break;
      }

      t1 = t1[1].split("\"");
      String id = t1[0];

      t1 = str.split("\"code\":\"");
      t1 = t1[1].split("\"");
      String code = t1[0];

      t1 = str.split("\"groupId\":\"");
      t1 = t1[1].split("\"");
      String groupId = t1[0];

      t1 = str.split("\"name\":");
      t1 = t1[1].split("\"");
      String name = t1[1];

      t1 = str.split("\"curriculumPeriodIds\":");
      t1[1] = t1[1].replace("[", "");
      t1 = t1[1].split("],");
      String[] currP = t1[0].replace("\"", "").split(",");

      t1 = str.split("\"min\":");
      t1 = t1[1].replace(",", "").split("\"max\":");
      String min = t1[0];
      String max = t1[1].split("}")[0];
      Study stu = new Study(
          id,
          groupId,
          code,
          name,
          currP,
          min,
          max,
          getGroupModules(id));

      studyList.put(id, stu);
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
        App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    launch();
  }
}
