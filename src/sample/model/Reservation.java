package sample.model;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

/**
 * In this class we are going to save the details for each reservation.
 * The class will be assigned only to the first cell of the reservation
 * that corresponds to the arrival date (and room).
 *
 * Used in combination with CalendarCell interface.
 */
public class Reservation {

    private String reservationID;
    private LocalDate localDateCheckIn;
    private LocalDate localDateCheckOut;
    private Period accommodationPeriod;
    private int roomNumber;
    private String roomType;
    private HashMap<String,String> guestDetails;

    private final int RESERVATION_ITEMS_MARGIN = 5;

    /**
     * Constructors
     **/
    public Reservation(){
    }

    public Reservation(LocalDate localDateCheckIn, LocalDate localDateCheckOut){
        this.localDateCheckIn = localDateCheckIn;
        this.localDateCheckOut = localDateCheckOut;
        this.accommodationPeriod = Period.between(localDateCheckIn, localDateCheckOut);
    }

    /**
     * Methods
     **/

    public Pane createReservationNode(){
        Pane reservationPane = new Pane();
        GridPane.setMargin(reservationPane, new Insets(RESERVATION_ITEMS_MARGIN));
        reservationPane.setStyle(
                "-fx-background-color: #fab061;" +
                "-fx-background-radius: 10"
        );
        // Testing animations
        reservationNodeSetup(reservationPane);
        //
        return reservationPane;
    }

    // Setting up reservation tiles features
    private void reservationNodeSetup(Node node){
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(300));
        scaleTransition.setNode(node);

        node.setOnMouseEntered(event -> {
            reservationTileMouseEntered(node, scaleTransition);
        });
        node.setOnMouseExited(event -> {
            reservationTileMouseExited(node, scaleTransition);
        });
    }

    private void reservationTileMouseEntered(Node node, ScaleTransition scaleTransition){
        node.toFront();
//        node.setScaleY(1.2);
//        node.setEffect(new DropShadow());
        node.setEffect(new Glow());

        scaleTransition.stop();
        scaleTransition.setToY(1.2);
        scaleTransition.play();
        System.out.println("Mouse Entered " + node.getScaleY());
    }

    private void reservationTileMouseExited(Node node, ScaleTransition scaleTransition){
//        node.setScaleY(1);
        node.setEffect(null);

        scaleTransition.stop();
        scaleTransition.setToY(1);
        scaleTransition.play();
        System.out.println("Mouse Exited " + node.getScaleY());
    }


    /**
     * Setters Getters
     */

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public LocalDate getLocalDateCheckIn() {
        return localDateCheckIn;
    }

    public void setLocalDateCheckIn(LocalDate localDateCheckIn) {
        this.localDateCheckIn = localDateCheckIn;
    }

    public LocalDate getLocalDateCheckOut() {
        return localDateCheckOut;
    }

    public void setLocalDateCheckOut(LocalDate localDateCheckOut) {
        this.localDateCheckOut = localDateCheckOut;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public HashMap<String, String> getGuestDetails() {
        return guestDetails;
    }

    public void setGuestDetails(HashMap<String, String> guestDetails) {
        this.guestDetails = guestDetails;
    }

    public int getAccommodationPeriodDays() {
        return accommodationPeriod.getDays();
    }

    public void setAccommodationPeriodDays(int noOfDays) {
        this.accommodationPeriod = Period.ofDays(noOfDays);
    }
}
