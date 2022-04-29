package fi.tuni.prog3;

import java.util.HashMap;

public class StudyGroupModule {

    HashMap<String, CourseModule> courseModules = new HashMap<String, CourseModule>();
    private String name;
    private String moduleId;
    private Credits credits;
    private boolean allMandatory;

    public StudyGroupModule(String name, String moduleId, String min, String max, boolean allMandatory) {
      this.name = name;
      this.moduleId = moduleId;
      this.credits = new Credits(min, max);
      this.allMandatory = allMandatory;
    }
    public String getName() {
      return this.name;
    }
    public String getModuleId() {
      return this.moduleId;
    }
    public boolean getAllMandatory() {
      return this.allMandatory;
    }
    public Credits getCredits() {
        return this.credits;
    }
  }
