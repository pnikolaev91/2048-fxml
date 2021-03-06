package ru.mypackage.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        ClassLoader cl = Main.class.getClassLoader();
        loader.setLocation(cl.getResource("sample.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("2048");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(cl.getResourceAsStream("icon.png")));
        Controller controller = loader.getController();
        controller.init();
        primaryStage.setOnCloseRequest(we -> {
            controller.shutdown();
        });
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    controller.shutdown();
                    Platform.exit();
                    System.exit(0);
                    break;
                case UP:
                case DOWN:
                case LEFT:
                case RIGHT:
                    controller.keyPressed(e);
                    break;
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
