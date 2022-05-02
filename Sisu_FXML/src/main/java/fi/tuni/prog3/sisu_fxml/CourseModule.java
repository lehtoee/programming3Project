package fi.tuni.prog3.sisu_fxml;

/**
 *
 * @author Visa
 */
public class CourseModule implements Comparable<CourseModule> {

  private String name;
  private String code;
  private String courseId;
  private Credits credits;

  /**
   *
   * @param name
   * @param code
   * @param courseId
   * @param min
   * @param max
   */
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

  /**
   *
   * @return
   */
  public String getName() {
    return this.name;
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
  public String getCourseId() {
    return this.courseId;
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
            "%s\n course id: %s\n code: %s\n credits: %s\n",
            this.name,
            this.courseId,
            this.code,
            this.credits.toString()
    );
  }

  /**
   *
   * @param o
   * @return
   */
  @Override
  public int compareTo(CourseModule o) {
    int names = this.name.compareTo(o.getName());
    int codes = this.code.compareTo(o.getCode());
    if (names == 0) {
      return codes;
    } else {
      return names;
    }
  }
}
