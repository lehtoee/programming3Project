package fi.tuni.prog3.sisu_fxml;

public class CourseModule {

  private String name;
  private String code;
  private String courseId;
  private Credits credits;

  public CourseModule(
    String name,
    String code,
    String courseId,
    String min,
    String max
  ) {
    this.name = name;
    this.code = code;
    this.courseId = courseId;
    this.credits = new Credits(min, max);
  }

  public String getName() {
    return this.name;
  }

  public String getCode() {
    return this.code;
  }

  public String getCourseId() {
    return this.courseId;
  }

  public Credits getCredits() {
    return this.credits;
  }

  @Override
  public String toString() {
    return String.format(
      "%s\n course id: %s\n code: %s\n credits: %s\n",
      this.name,
      this.courseId,
      this.code,
      this.credits.toString()
    );
  }
}
