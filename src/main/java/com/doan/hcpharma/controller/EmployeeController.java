package com.doan.hcpharma.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;


import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {



    @FXML
    private ChoiceBox<String> sexChoiceBox;

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexChoiceBox.getItems().addAll("Nam", "Nữ");
        roleChoiceBox.getItems().addAll("Nhân viên", "Quản lý");
    }
}
