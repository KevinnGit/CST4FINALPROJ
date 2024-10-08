module com.example.cst {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.cst to javafx.fxml;
    exports com.example.cst;
}