package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("2048");
        Controller controller = loader.getController();
        controller.init();
        primaryStage.setOnCloseRequest(we -> controller.shutdown());
        scene.setOnKeyPressed(controller::keyPressed);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
