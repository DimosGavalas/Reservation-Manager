package sample.customUtil;

import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class FXMLUtil {
    // loads an FXML file from a location relative to this class.
    /*public static <T> T loadFxmlFile(String loc) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLUtil.class.getResource(loc));
        return loader.loadFxmlFile();
    }*/

    public static FXMLLoader loadFxmlFile(String loc) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLUtil.class.getResource(loc));
        return loader;
    }
}
