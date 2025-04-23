module lk.ijse.hibernate.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires fontawesomefx;
    requires lombok;
    requires com.jfoenix;
    requires net.sf.jasperreports.core;
    requires org.controlsfx.controls;
    requires org.slf4j;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires jbcrypt;

    opens lk.ijse.hibernate.serenitymentalhealththerapycenter.config to jakarta.persistence;
    opens lk.ijse.hibernate.serenitymentalhealththerapycenter.view.tdm to javafx.base;
    opens lk.ijse.hibernate.serenitymentalhealththerapycenter.controller to javafx.fxml;
    opens lk.ijse.hibernate.serenitymentalhealththerapycenter.entity to org.hibernate.orm.core;
    exports lk.ijse.hibernate.serenitymentalhealththerapycenter;
}
