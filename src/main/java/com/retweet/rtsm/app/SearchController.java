package com.retweet.rtsm.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchController {
    @FXML
    private TitledPane advSearchPane;

    @FXML
    private Label searchLabel;

    @FXML
    private TextField search;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;

        this.setupListeners();
    }

    private void setupListeners() {
        this.advSearchPane.heightProperty().addListener((obs, oldHeight, newHeight) -> this.stage.sizeToScene());
    }

    private void openResultsWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/results.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("reTweet Song Manager");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        this.advSearchPane.setExpanded(true);
    }

    @FXML
    private void handleSearchButtonClick() {
        searchLabel.setVisible(true);
        try {
            XenoCantoService.makeRequest(this.search.getText())
                    .asStringAsync(new Callback<String>() {
                        @Override
                        public void completed(HttpResponse<String> httpResponse) {
                            searchLabel.setVisible(false);
                            System.out.println(httpResponse.getBody());
                            openResultsWindow();
                        }

                        @Override
                        public void failed(UnirestException e) {
                            searchLabel.setVisible(false);
                            e.printStackTrace();
                        }

                        @Override
                        public void cancelled() {
                            searchLabel.setVisible(false);
                        }
                    });
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.handleSearchButtonClick();
        }
    }

    @FXML
    private void closeWindow() {
        this.stage.close();
    }
}
