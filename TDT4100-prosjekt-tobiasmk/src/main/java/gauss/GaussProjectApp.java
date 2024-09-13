package gauss;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GaussProjectApp extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Gauss App");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("GaussApp.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
