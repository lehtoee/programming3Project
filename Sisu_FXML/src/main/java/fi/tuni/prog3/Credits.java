package fi.tuni.prog3;

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
