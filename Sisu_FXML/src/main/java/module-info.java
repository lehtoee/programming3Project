module fi.tuni.prog3.sisu_fxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.prefs;
    
    opens fi.tuni.prog3.sisu_fxml to javafx.fxml;
    exports fi.tuni.prog3.sisu_fxml;
}
