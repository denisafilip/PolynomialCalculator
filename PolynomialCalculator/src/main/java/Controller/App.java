package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX Controller.App
 */
public class App extends Application {

    /*@Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML("PolynomialCalculator"));
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/PolynomialCalculator.fxml"));
        Parent root = myLoader.load();
        primaryStage.setTitle("Polynomial Calculator");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch();
    }

}