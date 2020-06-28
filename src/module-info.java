module reservation.manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.graphics;

    opens sample;
    opens sample.controllers;
    opens sample.fxml_resources;
    exports sample;

//    For packages that do not belong to THIS Module reservation.manager.sample, any exportation and opening
//    requirements must my declared as statements in the VM options. Check the link below for more details.
//    https://github.com/jfoenixadmin/JFoenix/issues/862

}