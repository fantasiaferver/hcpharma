package com.doan.hcpharma.controller;

import com.doan.hcpharma.dao.KhachHangDAO;
import com.doan.hcpharma.model.KhachHangEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {



    @FXML
    private ChoiceBox<String> sexChoiceBox;
    @FXML
    private TextField tfMaKH,tfTenKH,tfSdtKH;
    @FXML
    private DatePicker datePickerKH;
    @FXML
    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexChoiceBox.getItems().addAll("Nam", "Nữ");
    }

    KhachHangDAO khachHangDAO=null;
    public void addKH(ActionEvent event){
       String maKH= tfMaKH.getText();
       String tenKH= tfTenKH.getText();
       String gioiTinh= sexChoiceBox.getValue();
       String sdtKH= tfSdtKH.getText();
       Date ngaySinh = Date.valueOf(datePickerKH.getValue());


       khachHangDAO = new KhachHangDAO();
       KhachHangEntity KH = new KhachHangEntity(maKH, tenKH, gioiTinh, ngaySinh, sdtKH);
       khachHangDAO.addData(KH);

        // Đóng scene thêm
        Node source = (Node) event.getSource();
        source.getScene().getWindow().hide();

        if (mainController != null) {
            mainController.showCustomerTab();
        }

    }



    //     Setter để thiết lập MainController
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
