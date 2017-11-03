package com.retweet.rtsm.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/search.fxml"));
        Parent root = (Parent)loader.load();
        SearchController controller = (SearchController) loader.getController();
        controller.setStage(primaryStage);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("reTweet Song Manager");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
