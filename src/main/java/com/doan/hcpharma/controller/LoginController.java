package com.doan.hcpharma.controller;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
public class LoginController {
    @FXML
    public void switchToAdminView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doan/hcpharma/view/admin-main-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("HCPHARMA");
        stage.show();

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
