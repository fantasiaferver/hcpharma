package com.doan.hcpharma.controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label tabLable;
    private String currentTabName;

    @FXML
    private AnchorPane overviewTab;

    @FXML
    private AnchorPane medicineTab;

    @FXML
    private AnchorPane storageTab;

    @FXML
    private AnchorPane customerTab;

    @FXML
    private AnchorPane employeeTab;

    @FXML
    private AnchorPane supplierTab;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnEditCustomer;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnAddMedicine;

    @FXML
    private Button btnEditMedicine;

    @FXML
    private Button btnDeleteMedicine;

    @FXML
    private Button btnDeltailMedicine;


    @FXML
    private Label dateTimeLabel;




    //Tổng hóa đơn trang tổng quan
    @FXML
    private Label toatalOrder;

    //Tổng khách hàng trang tổng quan
    @FXML
    private Label totalCustomer;

    //Tổng số lượng thuốc trang tổng quan
    @FXML
    private Label totalMedicine;

    //Tổng doánh số trang tổng quan
    @FXML
    private Label totoalCash;

    //Cập nhật Label Tab
    private void updateTabLabel() {
        tabLable.setText(currentTabName);
    }

    //Chuyển qua lại giữa các tab
    private void showTab(AnchorPane tab, String tabName) {
        overviewTab.setVisible(tab == overviewTab);
        medicineTab.setVisible(tab == medicineTab);
        storageTab.setVisible(tab == storageTab);
        customerTab.setVisible(tab == customerTab);
        employeeTab.setVisible(tab == employeeTab);
        supplierTab.setVisible(tab == supplierTab);
        currentTabName = tabName;
        updateTabLabel();
    }

    @FXML
    private void showOverviewTab() {
        showTab(overviewTab, "TỔNG QUAN");
    }

    @FXML
    private void showMedicineTab() {
        showTab(medicineTab, "THUỐC");
    }

    @FXML
    private void showStorageTab() {
        showTab(storageTab, "KHU VỰC LƯU TRỮ");
    }

    @FXML
    private void showCustomerTab() {
        showTab(customerTab, "KHÁCH HÀNG");
    }

    @FXML
    private void showEmployeeTab() {
        showTab(employeeTab, "NHÂN VIÊN");
    }

    @FXML
    private void showSupplierTab() {
        showTab(supplierTab, "NHÀ CUNG CẤP");
    }

    @FXML
    private void openAddCustomerModel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doan/hcpharma/view/add-customer-modal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("THÊM NHÂN VIÊN");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    @FXML
    private void openEditCustomerModel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doan/hcpharma/view/edit-customer-modal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("CẬP NHẬT NHÂN VIÊN");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openDeleteCustomerPopup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doan/hcpharma/view/delete-customer-popup.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("XÓA NHÂN VIÊN");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openAddMedicineModal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doan/hcpharma/view/add-medicine-modal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("THÊM THUỐC MỚI");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    private void openDetailMedicineModal(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doan/hcpharma/view/detail-medicine-modal.fxml"));
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("THÔNG TIN CHI TIẾT THUỐC");
//            stage.setResizable(false);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //    @FXML
//    private void openEditMedicineModal(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doan/hcpharma/view/edit-medicine-modal.fxml"));
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("SỬA THÔNG TIN THUỐC");
//            stage.setResizable(false);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateTime()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDateTime = now.format(formatter);
        dateTimeLabel.setText(formattedDateTime);
    }
}

