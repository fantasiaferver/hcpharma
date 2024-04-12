//package com.doan.hcpharma.controller;
//
//import com.doan.hcpharma.dao.KhachHangDAO;
//import com.doan.hcpharma.model.KhachHangEntity;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//
//import java.net.URL;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class CustomerController {
//
//
//    /*----------------------------------------------------  KHÁCH HÀNG ---------------------------------------------------------------*/
//    //Show KH lên bảng
//
//    @FXML
//    private TableView<KhachHangEntity> tvKhachHang;
//    @FXML
//    private TableColumn<KhachHangEntity, String> maKH, tenKH, gioiTinhKH, sdtKH;
//
//    @FXML
//    private TableColumn<KhachHangEntity, java.util.Date> ngaySinhKH;
//
//    // Định nghĩa GridPane để Thêm, Sửa
////    @FXML
////    private GridPane gridPaneKH;
//
//    @FXML
//    private TextField txtMaKH, txtTenKH, txtSdtKH;
//    @FXML
//    private DatePicker dateNgaySinhKH;
//    @FXML
//    private RadioButton rdBtnNam, rdBtnNu;
//    KhachHangDAO khachHangDAO = new KhachHangDAO();
//
//    //Hiện khách hàng lên table
//    @FXML
//    public void showKH() {
//        if (tvKhachHang == null) {
//            // Xử lý trường hợp tvThuoc là null
//            System.err.println("TableView tvKH is null. Initialization failed.");
//            return; // Không thực hiện các thao tác khác nếu tvKH là null
//        }
//        // Thiết lập giá trị của từng cột
//        try {
//            maKH.setCellValueFactory(new PropertyValueFactory<>("maKh"));
//            tenKH.setCellValueFactory(new PropertyValueFactory<>("tenKh"));
//            gioiTinhKH.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
//            sdtKH.setCellValueFactory(new PropertyValueFactory<>("sdtKh"));
//            ngaySinhKH.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
//
//
//            List<KhachHangEntity> li = khachHangDAO.getAll();
//
//            if (li != null && !li.isEmpty()) {
//                ObservableList<KhachHangEntity> allKH = FXCollections.observableList(li);
//                tvKhachHang.setItems(allKH);
//
//
//                tvKhachHang.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        KhachHangEntity selectedKH = tvKhachHang.getSelectionModel().getSelectedItem();
//                        if (selectedKH != null) {
//                            txtMaKH.setText(selectedKH.getMaKh());
//                            txtMaKH.setEditable(false);
//                            if (selectedKH.getGioiTinh().equals("Nam")) {
//                                rdBtnNam.setSelected(true);
//                                rdBtnNu.setSelected(false);
//                            } else {
//                                rdBtnNu.setSelected(true);
//                                rdBtnNam.setSelected(false);
//                            }
//                            txtTenKH.setText(selectedKH.getTenKh());
//                            txtSdtKH.setText(selectedKH.getSdtKh());
//                            //DatePicker
//                            Date ngaySinh = selectedKH.getNgaySinh();
//                            // Chuyển đổi từ java.sql.Date sang java.util.Date
//                            java.util.Date utilDate = new java.util.Date(ngaySinh.getTime());
//                            LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                            dateNgaySinhKH.setValue(localDate);
//                        }
//                    }
//                });
//
//
//            } else {
//                // Xử lý trường hợp danh sách rỗng
//                System.err.println("List of Thuốc is null or empty. No data to display.");
//
//            }
//        } catch (Exception e) {
//            // Xử lý ngoại lệ nếu có bất kỳ lỗi nào xảy ra trong quá trình khởi tạo
//            System.err.println("An error occurred during initialization: " + e.getMessage());
//            e.printStackTrace(); // In stack trace để debug
//        }
//
//    }
//}
