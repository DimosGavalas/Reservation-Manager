package sample.model;

import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Calendar extends GridPane {

    final String[] ROOM_NAMES =  {"Room 2", "Room 3", "Room 5", "Room 6", "Room 7", "Room 8"};
    private final HashMap<String, ArrayList<Integer>> ROOMS = new HashMap<>();
    //------------------------------------------------------------------------------------------
    private final HashMap<String,String> WEEK_DAYS_MAP = new HashMap<>();


    private LocalDate currentDate;
    private int lengthOfMonth;
    private int RESERVATION_SPAN = 4;
    private final int width = 40;
    private final int height = 40;
    private final int numofCols = 29;
    private final int numofRows = 10;

    // Constructor
    public Calendar(){
        this.currentDate = LocalDate.now();
        this.lengthOfMonth = currentDate.lengthOfMonth();

        WEEK_DAYS_MAP.put("SUNDAY","SUN");
        WEEK_DAYS_MAP.put("MONDAY","MON");
        WEEK_DAYS_MAP.put("TUESDAY","TUE");
        WEEK_DAYS_MAP.put("WEDNESDAY","WEN");
        WEEK_DAYS_MAP.put("THURSDAY","THU");
        WEEK_DAYS_MAP.put("FRIDAY","FRI");
        WEEK_DAYS_MAP.put("SATURDAY","SAT");
    }

    private GridPane createCommonGrid(){
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        grid.setHgap(2);
        grid.setVgap(2);
        return grid;
    }


    public GridPane createMonthDaysHeaderV2(){
        GridPane grid = createCommonGrid();
        grid.setVgap(0);

        // Setting up the Grid dimensions with constrains
        for(int col = 0; col < lengthOfMonth; col++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(width);
            grid.getColumnConstraints().add(colConst);
        }
        RowConstraints rowConst = new RowConstraints();
        rowConst.setPrefHeight(height);
        grid.getRowConstraints().add(rowConst);

        // Insert labeled boxes to each cell of the grid (Header)
        for(int dayOfMonth = 1; dayOfMonth <= lengthOfMonth; dayOfMonth++){
            VBox dayBox = new VBox();
            Label dayNameLabel = new Label(WEEK_DAYS_MAP.get(LocalDate.of(currentDate.getYear(), currentDate.getMonth(), dayOfMonth).getDayOfWeek().toString()));
//            Label dayNameLabel = new Label("MON");
            Label dayNumberLabel = new Label("" + dayOfMonth);

            dayNameLabel.setMaxSize(width,height);
            dayNumberLabel.setMaxSize(width, height);
            dayNameLabel.setAlignment(Pos.CENTER);
            dayNumberLabel.setAlignment(Pos.CENTER);
            dayNameLabel.setStyle("-fx-font-weight: normal;" + "-fx-font-size: 9;" + "-fx-text-fill: black;");
            dayNumberLabel.setStyle("-fx-font-weight: bold;" +
                    "-fx-underline: false;" + "-fx-border-width: 0 0 5 0;" + "-fx-font-size: 11;" + "-fx-text-fill: black;");

            dayBox.getChildren().addAll(dayNameLabel,dayNumberLabel);
            dayBox.setPrefSize(width,height);
            dayBox.setAlignment(Pos.CENTER);

            if(dayOfMonth == currentDate.getDayOfMonth()){
                dayBox.setStyle("-fx-background-color: #01DD80;" +
                        "-fx-background-radius: 5px;" +
                        "-fx-border-width: 0px 0px 0px 0px;" +
                        "-fx-border-color: gray;");
                dayNameLabel.setStyle("-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" + "-fx-font-size: 9;");
                dayNumberLabel.setStyle("-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" + "-fx-underline: false;" + "-fx-font-size: 11;");
            } else {
                dayBox.setStyle("-fx-background-color: white;" +
                        "-fx-border-width: 0px 0px 0px 0px;" +
                        "-fx-border-color: gray;" + "-fx-background-radius: 1px;");
            }

            grid.add(dayBox, dayOfMonth-1 , 0);
        }

        return grid;
    }


    public GridPane createMainCalendarAreaV2(){
       GridPane grid = createCommonGrid();

        for(int col = 0; col < lengthOfMonth; col++) {
            // 1st) Way
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(width);
            grid.getColumnConstraints().add(colConst);
        }
        for(int row = 0; row < numofRows; row++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(height);
            grid.getRowConstraints().add(rowConst);
        }
        for(int col = 0; col < lengthOfMonth; col++){
            for(int row = 0; row < numofRows; row++){
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: white;" + "-fx-background-radius: 2px");
                pane.setPrefSize(width, height);
                grid.add(pane, col, row);
            }
        }

        // #### Creating some reservation tiles for testing ####
        // ########################################################################################################

//        Reservation reservation = new Reservation(LocalDate.parse("2020-02-16"), LocalDate.parse("2020-02-24"));
        Reservation reservation1 = new Reservation();
        grid.add(reservation1.createReservationNode(), 15, 2, 7, 1);

        Reservation reservation2 = new Reservation();
        grid.add(reservation2.createReservationNode(), 13, 0, 10, 1);

        Reservation reservation5 = new Reservation();
        grid.add(reservation5.createReservationNode(), 6, 2, 9, 1);

        Reservation reservation3 = new Reservation();
        grid.add(reservation3.createReservationNode(), 3, 3, 18, 1);

        Reservation reservation4 = new Reservation();
        grid.add(reservation4.createReservationNode(), 7, 1, 7, 1);

        // ########################################################################################################

       return grid;
    }


    public GridPane createRoomCellsHeader() {
        GridPane grid = createCommonGrid();

        // Setting up the Grid dimensions with constrains
        for(int i = 0; i < numofRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(height);
            grid.getRowConstraints().add(rowConst);
        }
        for(int i = 0; i < 2; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            if(i == 1) {
                // Making the second column smaller width
                colConst.setMaxWidth(50);
                colConst.setMinWidth(50);
                grid.getColumnConstraints().add(colConst);
            } else {
                colConst.setMaxWidth(100);
                colConst.setMinWidth(100);
                grid.getColumnConstraints().add(colConst);
            }
        }

        for(int col = 0; col < 2; col++){
            for(int row = 0; row < numofRows; row++){
                VBox roomBox = new VBox();
                roomBox.setStyle("-fx-background-color: white;" + "-fx-background-radius: 2px");

                if(col == 0) {
                    Label label = new Label("Apartment/Studio");
                    label.setStyle("-fx-font-size: 10;" + "-fx-text-fill: black;");
                    roomBox.getChildren().add(label);
                    roomBox.setAlignment(Pos.CENTER_LEFT);
                    roomBox.setPadding(new Insets(0, 0, 0, 8));
                } else {
                    Label label = new Label("No.");
                    label.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 10;" + "-fx-text-fill: black;");
                    roomBox.getChildren().add(label);
                    roomBox.setAlignment(Pos.CENTER);
                }
                grid.add(roomBox, col, row);
            }
        }

        return grid;
    }

}
