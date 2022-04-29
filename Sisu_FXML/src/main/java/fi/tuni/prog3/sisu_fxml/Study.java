package fi.tuni.prog3.sisu_fxml;

import java.util.HashMap;
import java.util.List;

public class Study {

  HashMap<String, StudyGroupModule> GroupModules = new HashMap<String, StudyGroupModule>();
  private String id;
  private String groupId;
  private String code;
  private String name;
  private String[] periodIds;
  private Credits credits;

  public Study(
    String id,
    String groupId,
    String code,
    String name,
    String[] periodIds,
    String min,
    String max,
    List<StudyGroupModule> groupList
  ) {
    this.id = id;
    this.groupId = groupId;
    this.code = code;
    this.name = name;
    this.periodIds = periodIds;
    this.credits = new Credits(min, max);
    for(StudyGroupModule m : groupList) {
      GroupModules.put(m.getModuleId(), m);
    }
  }

  public String getId() {
    return this.id;
  }

  public String getGroupId() {
    return this.groupId;
  }

  public String getCode() {
    return this.code;
  }

  public String getname() {
    return this.name;
  }

  public String[] getPeriodIds() {
    return this.periodIds;
  }

  public Credits getCredits() {
    return this.credits;
  }

  @Override
  public String toString() {
    return String.format(
      "%s\n id: %s\n groupId: %s\n code: %s\n curriculumPeriodIds: %s\n credits: %s\n",
      this.name,
      this.id,
      this.groupId,
      this.code,
      this.periodIds,
      this.credits.toString()
    );
  }
}
