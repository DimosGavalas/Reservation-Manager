package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ReservationManager extends Application {

    private Parent root;

    @Override
    public void start(Stage primaryStage) {

//        Font font = Font.loadFont(getClass().getResourceAsStream("/sample/assets/fonts/Exo_2/Exo2-Bold.ttf"), 16);
//        System.out.println(font);

        try {
            root = FXMLLoader.load(getClass().getResource("fxml_resources/main_menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
//        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
