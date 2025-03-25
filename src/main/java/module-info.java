module lk.ijse.hibernate.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires fontawesomefx;


    opens lk.ijse.hibernate.serenitymentalhealththerapycenter.controller to javafx.fxml;
    exports lk.ijse.hibernate.serenitymentalhealththerapycenter;
}