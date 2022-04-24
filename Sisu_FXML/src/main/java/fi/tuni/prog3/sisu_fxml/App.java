package fi.tuni.prog3.sisu_fxml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Hashtable;
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
    String[] splitted = line.split("},");
    for (String str : splitted) {
      String t1;
      String[] t2;
      String[] t3;

      t1 = str.replace("\"", "");
      t2 = t1.split("id:");
      t3 = t2[1].split(",");
      String id = t3[0];

      t1 = str.replace("\"", "");
      t2 = t1.split("code:");
      t3 = t2[1].split(",");
      String code = t3[0];

      t1 = str.replace("\"", "");
      t2 = t1.split("groupId:");
      t3 = t2[1].split(",");
      String groupId = t3[0];

      t1 = str.replace("\"", "");
      t2 = t1.split("name:");
      t3 = t2[1].split(",");
      String name = t3[0];

      t1 = str.replace("\"", "");
      t2 = t1.split("curriculumPeriodIds:");
      t3 = t2[1].split(",credits");
      String t4 = t3[0].replace("[", "");
      t4 = t4.replace("]", "");
      String[] currP = t4.split(",");

      t1 = str.replace("\"", "");
      t2 = t1.split("min:");
      t3 = t2[1].split(",");
      String min = t3[0];

      t1 = str.replace("\"", "");
      t2 = t1.split("max:");
      t3 = t2[1].split("}");
      String max = t3[0];

      studyList.put(id, new Study(id, groupId, code, name, currP, min, max));
    }
  }

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("primary"), 640, 480);
    stage.setScene(scene);
    stage.show();
    getData();
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
