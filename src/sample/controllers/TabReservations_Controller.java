package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import sample.customUtil.FXMLUtil;
import sample.model.Calendar;
import sample.model.Reservation;

import java.io.IOException;

public class TabReservations_Controller {

    @FXML
    private AnchorPane calendar_head_container, calendar_rooms_container, calendar_body_container;
    @FXML
    private ScrollPane scrollPane_calendar_head, scrollPane_calendar_rooms, scrollPane_calendar_body;
    @FXML
    private Button new_reservation_button;

    private Calendar calendar;


    public void loadView(){
        calendar = new Calendar();
        calendar.setId("calendar");

        calendar_body_container.getChildren().add(calendar.createMainCalendarAreaV2());
        calendar_head_container.getChildren().add(calendar.createMonthDaysHeaderV2());
        calendar_rooms_container.getChildren().add(calendar.createRoomCellsHeader());

        // Binding horizontal and vertical movement of scroll panes
        scrollPane_calendar_body.setPannable(true);
        scrollPane_calendar_body.hvalueProperty().bindBidirectional(scrollPane_calendar_head.hvalueProperty());
        scrollPane_calendar_body.vvalueProperty().bindBidirectional(scrollPane_calendar_rooms.vvalueProperty());

        new_reservation_button.setOnAction(event -> {
            try {
                // Loading dialog window Scene
                FXMLLoader loader = FXMLUtil.loadFxmlFile("/sample/fxml_resources/popup_new_reservation.fxml");
                loader.load();
                // Get dialog window Scene controller
                PopupNewReservation_Controller controller = loader.getController();
                // make calls using the controller
                System.out.println(controller.getSomething());
                Reservation reservation = controller.loadView(loader);
                if(reservation != null){
                    System.out.println("New reservation: " + reservation.getRoomType());
                } else {
                    System.out.println("Reservation Canceled");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
