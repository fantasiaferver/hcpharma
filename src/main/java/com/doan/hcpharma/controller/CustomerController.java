package com.doan.hcpharma.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {



    @FXML
    private ChoiceBox<String> sexChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexChoiceBox.getItems().addAll("Nam", "Nữ");
    }
}
