package fi.tuni.prog3.sisu_fxml;

public class Study {

  public class Credits {

    private Integer min;
    private Integer max;

    public Credits(String min, String max) {
      if (min.equals("null")) {
        this.min = null;
      } else {
        this.min = Integer.parseInt(min);
      }
      if (max.equals("null")) {
        this.min = null;
      } else {
        this.max = Integer.parseInt(min);
      }
    }

    public Integer getMin() {
      return this.min;
    }

    public Integer getMax() {
      return this.max;
    }
  }

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
    String max
  ) {
    this.id = id;
    this.groupId = groupId;
    this.code = code;
    this.name = name;
    this.periodIds = periodIds;
    this.credits = new Credits(min, max);
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
    return String.format("%s\n id: %s\n groupId: %s\n code: %s\n curriculumPeriodIds: %s\n credits: %s\n", this.name, this.id, this.groupId, this.code, this.periodIds, this.credits);
  }

}
