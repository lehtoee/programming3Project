package fi.tuni.prog3.sisu_fxml;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * 
 * @author Visa
 */
public class Study {

  public TreeMap<String, StudyGroupModule> GroupModules = new TreeMap<>();
  private String id;
  private String groupId;
  private String code;
  private String name;
  private String[] periodIds;
  private Credits credits;

  /**
     * @param id  
     * @param groupId  
     * @param code  
     * @param name  
     * @param periodIds  
     * @param min  
     * @param max  
     * @param groupList  
  */
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

  /**
   * 
   * @return 
   */
  public String getId() {
    return this.id;
  }
  
  /**
   * 
   * @return 
   */
  public String getGroupId() {
    return this.groupId;
  }

  /**
   * 
   * @return 
   */
  public String getCode() {
    return this.code;
  }
  /**
   * 
   * @return 
   */
  public String getname() {
    return this.name;
  }

  public String[] getPeriodIds() {
    return this.periodIds;
  }
  /**
   * 
   * @return 
   */
  public Credits getCredits() {
    return this.credits;
  }
  /**
   * 
   * @return 
   */
  @Override
  public String toString() {
    return String.format(
      "%s\n id: %s\n groupId: %s\n code: %s\n curriculumPeriodIds: %s\n credits: %s\n GroupModules:\n%s",
      this.name,
      this.id,
      this.groupId,
      this.code,
      Arrays.toString(this.periodIds),
      this.credits.toString(),
      this.GroupModules.toString()
    );
  }
}
