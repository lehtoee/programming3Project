package fi.tuni.prog3.sisu_fxml;

/**
 *
 * @author Visa
 */
public class Credits {

  private Integer min;
  private Integer max;

  /**
   * @param min a string where (min >= 0 || min == "null")
   * @param max a string where (min >= 0 || min == "null")
   */
  public Credits(String min, String max) {
    if (min == null || min.equals("null")) {
      this.min = null;
    } else {
      this.min = Integer.parseInt(min);
    }
    if (max == null || max.equals("null")) {
      this.min = null;
    } else {
      this.max = Integer.parseInt(max);
    }
  }

  /**
   *
   * @return
   */
  public Integer getMin() {
    return this.min;
  }

  /**
   *
   * @return
   */
  public Integer getMax() {
    return this.max;
  }

  /**
   *
   * @return
   */
  @Override
  public String toString() {
    return String.format("{\n   min: %d\n   max: %d\n}", this.min, this.max);
  }
}
