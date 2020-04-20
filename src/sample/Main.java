package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Aplikacja JavaFX pokazujaca jak rysowac rozmaite figury
 * 
 * @author: Dariusz Ceglarek
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Rysowanie");
        primaryStage.setScene(new Scene(root, 790, 660));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
