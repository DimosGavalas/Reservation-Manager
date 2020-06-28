package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Reservation;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PopupNewReservation_Controller {

    @FXML
    private Button cancel_button, confirm_button;
    @FXML
    private TextField checkIn_input, checkOut_input, roomType_input;

    Reservation newReservation;


    // Method to show pop up window and wait for the input. It is important to pass the controller's loader
    public Reservation loadView(FXMLLoader loader) {
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Popup Window");

        // Setting the stage components
        confirm_button.setOnAction(e -> {
            createReservation();
            stage.close();
        });
        cancel_button.setOnAction(event -> {
            stage.close();
        });

        // Show and Wait creates a blocking situation, so the program waits here for events before it returns anything.
        stage.showAndWait();

        return newReservation;
    }


    private void createReservation(){
        newReservation = new Reservation();
//        newReservation.setLocalDateCheckIn(LocalDate.parse(checkIn_input.getText()));
//        newReservation.setLocalDateCheckOut(LocalDate.parse(checkOut_input.getText()));
        newReservation.setRoomType(roomType_input.getText());
    }

    public String getSomething(){
        return "RRRRR";
    }
}
