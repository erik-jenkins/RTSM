package com.retweet.rtsm.app;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class SearchController {
    @FXML
    private TitledPane advSearchPane;

    @FXML
    private ChoiceBox qualityExactly, qualityGreaterThan, qualityLessThan;

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
        try {
            System.out.println(XenoCantoService.makeRequest(this.search.getText()));
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleKeyPress() {
        this.handleSearchButtonClick();
    }

    @FXML
    private void closeWindow() {
        this.stage.close();
    }
}
