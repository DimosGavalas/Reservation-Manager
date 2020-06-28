package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import sample.customUtil.FXMLUtil;
import sample.model.Reservation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Main_Controller implements Initializable {

    @FXML
    private StackPane stage_background;
    @FXML
    private Button dashboard_tab, reservations_tab, new_property_tab, settings_tab; // Menu buttons - tabs
    private Button activeTab; // Represents one of the above menu buttons - tabs

    private ArrayList<Reservation> reservations = new ArrayList<>();


    // Main Controller start up method
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        defaultView("/sample/fxml_resources/reservations_tab.fxml");

        reservations_tab.setOnAction(event -> {
            setMenuActiveTab(reservations_tab);
            FXMLLoader loader = setView("/sample/fxml_resources/reservations_tab.fxml");
            if(loader != null) {
                TabReservations_Controller controller = loader.getController();
                controller.loadView();
            }
        });

        dashboard_tab.setOnAction(event -> {
            setMenuActiveTab(dashboard_tab);
            setView(null);
        });

        new_property_tab.setOnAction(event -> {
            setMenuActiveTab(new_property_tab);
            setView(null);
        });

        settings_tab.setOnAction(event -> {
            setMenuActiveTab(settings_tab);
            setView(null);
        });
    }


    // Method used to create and set the view regarding the selected tab from the Main Menu side bar.
    // The previous view will be cleared and the view for the active tab will be loaded and set on the root container.
    // This method returns the FXMLLoader of the specified-passed fxml file. This loader can be used to get the controller
    // of this fxml file and setup a communication with the Main Controller (this class), making possible to pass any data between them
    // and any other view's controller.
    private FXMLLoader setView(String fxmlFile){
        GridPane root;
        // if statement for views that have not been built yet and do not have an FXML file.
        if(fxmlFile == null || fxmlFile.equals("")){
            stage_background.getChildren().clear();
            return null;
        } else {
            try {
                FXMLLoader loader = FXMLUtil.loadFxmlFile(fxmlFile); // This loads the fxml file provided the location
                root = loader.load(); // This loads the root element of the loaded fxml file (or view).
                root.setStyle("-fx-background-color: transparent;");
                stage_background.getChildren().clear();
                stage_background.getChildren().add(root);
                return loader;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    // Method to set the default view when application starts
    private void defaultView(String fxmlFile){
        setView(fxmlFile);
    }

    // Method to set the background color (style) of the active (selected) Tab of the Main Menu side bar
    private void setMenuActiveTab(Button tab){
        if(activeTab == null) {
            // if no active menu Tab yet, set active the clicked one
            tab.setStyle("-fx-background-color: #535151;"); // Dark gray - Active
            activeTab = tab;
        } else {
            // if an active menu Tab exists, the existing active Tab style will be set to normal, next the clicked tab's style
            // will be set as active and the active tab will be represented by the last clicked Tab.
            activeTab.setStyle("-fx-background-color: #5E5D5D88;"); // Medium gray - Normal
            tab.setStyle("-fx-background-color: #535151;"); // Dark gray - Active
            activeTab = tab;
        }

    }
}