package com.doan.hcpharma.controller;
import com.doan.hcpharma.dao.KhachHangDAO;
import com.doan.hcpharma.dao.NhanVienDAO;
import com.doan.hcpharma.model.KhachHangEntity;
import com.doan.hcpharma.model.NhanVienEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {



    @FXML
    private ChoiceBox<String> sexChoiceBox;

    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexChoiceBox.getItems().addAll("Nam", "Nữ");
        roleChoiceBox.getItems().addAll("Nhân viên", "Quản lý");
    }

    @FXML
    private TextField tfMaNV, tfTenNV,tfMaTK,tfEmailNV,tfCCCD,tfDiaChi,tfSdtNV;
    @FXML
    private DatePicker datePickerNV;
    NhanVienDAO nhanVienDAO=null;
    public void addNV(ActionEvent event){
        String maNV= tfMaNV.getText();
        String tenNV= tfTenNV.getText();
        String gioiTinh= sexChoiceBox.getValue();
        String chucVu= roleChoiceBox.getValue();
        String maTK= tfMaTK.getText();

        String emailNV= tfEmailNV.getText();
        String cccd= tfCCCD.getText();
        String diachi= tfDiaChi.getText();
        Date ngaySinh = Date.valueOf(datePickerNV.getValue());
        String sdtNV = tfSdtNV.getText();


        nhanVienDAO = new NhanVienDAO();
        NhanVienEntity NV = new NhanVienEntity(maNV, tenNV, gioiTinh, cccd, chucVu,sdtNV,emailNV,diachi,ngaySinh,maTK);
        nhanVienDAO.addData(NV);

        // Đóng scene thêm
        Node source = (Node) event.getSource();
        source.getScene().getWindow().hide();

        if (mainController != null) {
            mainController.showNV();
        }

    }



    //     Setter để thiết lập MainController
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
