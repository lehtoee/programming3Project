package fi.tuni.prog3.sisu_fxml;

public class Credits {

    private Integer min;
    private Integer max;

    public Credits(String min, String max) {
      if (min == null || min.equals("null")) {
        this.min = null;
      } else {
        this.min = Integer.parseInt(min);
      }
      if (max == null || max.equals("null")) {
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

    @Override
    public String toString() {
      return String.format("{\n   min: %d\n   max: %d\n}", this.min, this.max);
    }
  }
