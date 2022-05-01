package fi.tuni.prog3.sisu_fxml;

import java.util.HashMap;
import java.util.List;

public class StudyGroupModule {

    HashMap<String, CourseModule> courseModules = new HashMap<String, CourseModule>();
    private String name;
    private String moduleId;
    private String code;
    private Credits credits;
    private boolean allMandatory;

    public StudyGroupModule(String name, String moduleId, String code, String min, String max, boolean allMandatory, List<CourseModule> courses) {
      this.name = name;
      this.moduleId = moduleId;
      this.code = code;
      this.credits = new Credits(min, max);
      this.allMandatory = allMandatory;
      for(CourseModule m : courses) {
        courseModules.put(m.getCourseId(), m);
      }
    }
    public String getName() {
      return this.name;
    }
    public String getModuleId() {
      return this.moduleId;
    }
    public String getCode() {
      return this.code;
    }
    public boolean getAllMandatory() {
      return this.allMandatory;
    }
    public Credits getCredits() {
        return this.credits;
    }
    @Override
  public String toString() {
    String mand = "No";
    if(this.allMandatory) {
      mand = "Yes";
    }
    return String.format(
      "%s\n id: %s\n code: %s\n credits: %s\n mandatory: %s",
      this.name,
      this.moduleId,
      this.code,
      this.credits.toString(),
      mand
    );
  }
  }
