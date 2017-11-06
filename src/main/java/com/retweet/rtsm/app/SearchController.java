package com.retweet.rtsm.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SearchController {
    @FXML
    private TitledPane advSearchPane;

    @FXML
    private ChoiceBox qualityExactly, qualityGreaterThan, qualityLessThan;

    @FXML
    private Label searchLabel;

    @FXML
    private TextField search, genus, recordist, country, loc, remarks, box, lat, lon, also,
            type, catNumber, area, year;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;

        this.setupListeners();
    }

    private void setupListeners() {
        this.advSearchPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            this.stage.sizeToScene();
        });
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
