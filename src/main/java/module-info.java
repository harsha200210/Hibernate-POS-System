module Hibernate.POS.System {
    requires jakarta.persistence;
    requires java.sql;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires java.naming;

    exports lk.ijse;

    opens lk.ijse to org.hibernate.orm.core, javafx.base;
    opens lk.ijse.controller to javafx.fxml;
    opens lk.ijse.entity to org.hibernate.orm.core;
    opens lk.ijse.tdm to javafx.base;
}