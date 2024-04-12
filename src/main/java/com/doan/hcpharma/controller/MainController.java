package com.doan.hcpharma.controller;
import com.doan.hcpharma.dao.*;
import com.doan.hcpharma.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private Label tabLable;
    private String currentTabName;

    @FXML
    private AnchorPane overviewTab,medicineTab,storageTab,customerTab,employeeTab,supplierTab, sellDrugsTab, sellNonPrescriptionDrugsTab, sellPrescriptionDrugsTab, prescriptionDrugsTab, accountTab, personalInformationTab, importMedicineTab;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private ChoiceBox<String> kindOfMedicineChoiceBox;




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
        sellDrugsTab.setVisible(tab == sellDrugsTab);
        accountTab.setVisible(tab == accountTab);
        personalInformationTab.setVisible(tab == personalInformationTab);
        importMedicineTab.setVisible(tab == importMedicineTab);
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
        kindOfMedicineChoiceBox.getItems().addAll("KDU"
                , "KV"
                , "NT"
                , "CL"
                , "DL"
                , "GC"
                , "MTM"
                , "TH"
                , "GDHS"
                , "TCN"
                , "TCN"
                , "TTK"
                , "TXK"
                , "VVKC");
        showThuoc();
    }

    @FXML
    private void showStorageTab() {
        showTab(storageTab, "KHU VỰC LƯU TRỮ");
        showKV();
    }

    @FXML
    public void showCustomerTab() {
        showTab(customerTab, "KHÁCH HÀNG");
        showKH();
    }

    @FXML
    private void showEmployeeTab() {
        showTab(employeeTab, "NHÂN VIÊN");
        showNV();
    }

    @FXML
    private void showSupplierTab()
    {
        showTab(supplierTab, "NHÀ CUNG CẤP");
        showNCC();
    }

    private void openModal(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showSellDrugsTab() {
        showTab(sellDrugsTab, "BÁN THUỐC");
    }

    @FXML
    private void showAccountTab() {
        showTab(accountTab, "QUẢN LÝ TÀI KHOẢN");
    }
    @FXML
    private void showPersonalInformationTab() {
        showTab(personalInformationTab, "THÔNG TIN TÀI KHOẢN");
    }
    @FXML
    private void showimportMedicineTab() {
        showTab(importMedicineTab, "NHẬP THUỐC");
    }


    //Chuyển qua lại giữa các tab trong tab mua thuốc
    @FXML
    private void showSellNonPrescriptionDrugsTab() {
        sellNonPrescriptionDrugsTab.setVisible(true);
        sellPrescriptionDrugsTab.setVisible(false);
        prescriptionDrugsTab.setVisible(false);
    }

    @FXML
    private void showSellPrescriptionDrugsTab() {
        sellNonPrescriptionDrugsTab.setVisible(false);
        sellPrescriptionDrugsTab.setVisible(true);
        prescriptionDrugsTab.setVisible(false);
    }

    @FXML
    private void showNonPrescriptionDrugsTab() {
        sellNonPrescriptionDrugsTab.setVisible(false);
        sellPrescriptionDrugsTab.setVisible(false);
        prescriptionDrugsTab.setVisible(true);
    }

    @FXML
    private void openAddEmployeeModel(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/add-employee-modal.fxml", "THÊM NHÂN VIÊN");
    }

    @FXML
    private void openEditEmployeeModel(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/edit-employee-modal.fxml", "CẬP NHẬT NHÂN VIÊN");
    }

    @FXML
    private void openDeleteEmployeePopup(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/delete-employee-popup.fxml", "XÓA NHÂN VIÊN");
    }


    @FXML
    private ChoiceBox<String> sexChoiceBox;
    @FXML
    private TextField tfMaKH, tfTenKH, tfSdtKH;
    @FXML
    private DatePicker datePickerKH;

    @FXML
    private void openAddMedicineModal(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/add-medicine-modal.fxml", "THÊM THUỐC MỚI");

    }

    @FXML
    private void openDetailMedicineModal(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/detail-medicine-modal.fxml", "THÔNG TIN CHI TIẾT THUỐC");
    }

    @FXML
    private void openEditMedicineModal(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/edit-medicine-modal.fxml", "SỬA THÔNG TIN THUỐC");
    }

    @FXML
    private void openDeleteMedicinePopup(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/delete-medicine-popup.fxml", "XÓA THUỐC");
    }

    @FXML
    private void openAddStorageModel(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/add-storage-modal.fxml", "THÊM KHU VỰC LƯU TRỮ");
    }

    @FXML
    private void openEditStorageModel(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/edit-storage-modal.fxml", "SỬA KHU VỰC LƯU TRỮ");
    }

    @FXML
    private void openDeleteStoragePopup(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/delete-storage-popup.fxml", "XÓA KHU VỰC LƯU TRỮ");
    }

    @FXML
    private void openAddCustomerModel(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/add-customer-modal.fxml", "THÊM KHÁCH HÀNG");
    }

    @FXML
    private void openEditCustomerModel(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/edit-customer-modal.fxml", "SỬA KHÁCH HÀNG");
    }

    @FXML
    private void openDeleteCustomerPopup(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/delete-customer-popup.fxml", "XÓA KHÁCH HÀNG");
    }

    @FXML
    private void openAddSupplierModel(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/add-customer-modal.fxml", "THÊM NHÀ CUNG CẤP");
    }

    @FXML
    private void openEditSupplierModel(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/edit-customer-modal.fxml", "SỬA NHÀ CUNG CẤP");
    }

    @FXML
    private void openDeleteSupplierPopup(ActionEvent event) {
        openModal("/com/doan/hcpharma/view/delete-customer-popup.fxml", "XÓA NHÀ CUNG CẤP");
    }





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

    // Phương thức hiển thị hộp thoại thông báo
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    /*----------------------------------------------------  THUỐC  ---------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------------------*/

    @FXML
    private TextField txtGiaBan;

    @FXML
    private TextField txtGiaNhap;

    @FXML
    private ChoiceBox cbKVLT, cbDVT;

    @FXML
    private TextField txtMaThuoc;

    @FXML
    private TextArea txtTacDung;

    @FXML
    private TextField txtTenThuoc;

    @FXML
    private TextField txtXuatXu;

    @FXML
    private TextField txtDoiTuongSD;


    @FXML
    private TableView<ThuocEntity> tvThuoc;
    @FXML
    private TableColumn<ThuocEntity, String> maT, tenT, loaiT, donVi, xuatXu, kvlt, moTa;
    @FXML
    private TableColumn<ThuocEntity, Double> gia;

    @FXML
    private TableColumn<ThuocEntity, Date> ngaySX, ngayHH;

    @FXML
    private TableColumn<ThuocEntity, Boolean> keDon;
    @FXML
    private CheckBox checkBoxKeDon;
    @FXML
    private ImageView hinhAnhThuoc;

    @FXML
    private DatePicker dateNSX,dateNHH;

    // Định nghĩa GridPane và các Label
    @FXML
    private GridPane gridPane;

    ThuocDAO thuocDAO = new ThuocDAO();


    //Show Thuốc lên bảng

    @FXML
    public void showThuoc() {

        List<String> khuVucList = khuVucLuuTruDAO.getAllKhuVuc(); // Lấy danh sách khu vực
        cbKVLT.setItems(FXCollections.observableList(khuVucList));

        if (tvThuoc == null) {
            System.err.println("TableView tvThuoc is null. Initialization failed.");
            return;
        }

        try {
            maT.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
            tenT.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
            loaiT.setCellValueFactory(new PropertyValueFactory<>("maLoaiThuoc"));
            donVi.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
            gia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
            xuatXu.setCellValueFactory(new PropertyValueFactory<>("xuatXu"));
            ngaySX.setCellValueFactory(new PropertyValueFactory<>("ngaySx"));
            ngayHH.setCellValueFactory(new PropertyValueFactory<>("ngayHh"));
            kvlt.setCellValueFactory(new PropertyValueFactory<>("maKhuVuc"));
            keDon.setCellValueFactory(new PropertyValueFactory<>("keDon"));

            thuocDAO = new ThuocDAO();
            List<ThuocEntity> li = thuocDAO.getAll();



            if (li != null && !li.isEmpty()) {
                ObservableList<ThuocEntity> allThuoc = FXCollections.observableList(li);
                tvThuoc.setItems(allThuoc);

                tvThuoc.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ThuocEntity selectedThuoc = tvThuoc.getSelectionModel().getSelectedItem();
                        if (selectedThuoc != null) {
                            txtMaThuoc.setText(String.valueOf(selectedThuoc.getMaThuoc()));
                            txtTenThuoc.setText(selectedThuoc.getTenThuoc());
                            kindOfMedicineChoiceBox.setValue(selectedThuoc.getMaLoaiThuoc());

                            cbDVT.setValue(selectedThuoc.getDonViTinh());
                            txtTacDung.setText(selectedThuoc.getMoTa());
                            txtGiaNhap.setText(String.valueOf(selectedThuoc.getDonGiaNhap()));
                            txtGiaBan.setText(String.valueOf(selectedThuoc.getDonGia()));
                            checkBoxKeDon.setSelected(selectedThuoc.getKeDon());
                            cbKVLT.setValue(selectedThuoc.getMaKhuVuc());
                            txtXuatXu.setText(selectedThuoc.getXuatXu());
                            txtDoiTuongSD.setText(selectedThuoc.getDoiTuongSD());

                            if (selectedThuoc.getNgaySx() != null) {
                                //DatePicker ngaySX
                                Date ngaySX = selectedThuoc.getNgaySx();
                                java.util.Date utilDate = new java.util.Date(ngaySX.getTime());
                                LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                dateNSX.setValue(localDate);
                            }

                            if (selectedThuoc.getNgayHh() != null) {
                                //DatePicker ngayHH
                                Date ngayHH = selectedThuoc.getNgayHh();
                                java.util.Date utilDateHH = new java.util.Date(ngayHH.getTime());
                                LocalDate localDateHH = utilDateHH.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                dateNHH.setValue(localDateHH);
                            }
//
//                            String imageUrl = selectedThuoc.getHinhAnh();
//                            if (imageUrl != null && !imageUrl.isEmpty()) {
//                                Image image = new Image(imageUrl);
//                                hinhAnhThuoc.setImage(image);
//                            }
                        }
                    }
                });
            } else {
                System.err.println("List of Thuốc is null or empty. No data to display.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred during initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
//    //Thêm thuốc
    @FXML
    public void addThuoc() {
        try {
            // Retrieve information from the input fields on the form
            int maThuoc = Integer.parseInt(txtMaThuoc.getText());
            String tenThuoc = txtTenThuoc.getText();
            String loaiThuoc = kindOfMedicineChoiceBox.getValue();
            boolean keDon = checkBoxKeDon.isSelected();
            String donViTinh = String.valueOf(cbDVT.getValue());
            Double giaNhap = Double.parseDouble(txtGiaNhap.getText());
            Double giaBan = Double.parseDouble(txtGiaBan.getText());
            String tacDung = txtTacDung.getText();
            String xuatXu = txtXuatXu.getText();
            String doiTuongSD = txtDoiTuongSD.getText();
            String kvlt = String.valueOf(cbKVLT.getValue());
            java.sql.Date ngaySX = java.sql.Date.valueOf(dateNSX.getValue()); // Get production date from DatePicker
            java.sql.Date ngayHH = java.sql.Date.valueOf(dateNHH.getValue()); // Get expiry date from DatePicker

            if (tenThuoc.isEmpty() || loaiThuoc == null || donViTinh.isEmpty() || tacDung.isEmpty() || xuatXu.isEmpty() || doiTuongSD.isEmpty() || kvlt.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ thông tin.");
                return;
            }



            // Create a new medicine entity with soLuong property
            ThuocEntity newThuoc = new ThuocEntity(maThuoc, tenThuoc, xuatXu, giaNhap, giaBan, donViTinh, tacDung, ngayHH, ngaySX, kvlt, loaiThuoc, keDon, doiTuongSD);

            // Add the medicine to the database
            if (thuocDAO.addData(newThuoc)) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm thuốc thành công.");
                clearTextFieldMedicine();
                showThuoc();
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm thuốc thành công.");
                clearTextFieldMedicine();
                showThuoc();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đúng định dạng cho giá nhập và giá bán.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Đã xảy ra lỗi khi thêm thuốc: " + e.getMessage());
        }
    }
        //Xóa thuốc

    public void deleteThuoc() {
        // Lấy thuốc được chọn từ TableView
        ThuocEntity selected = tvThuoc.getSelectionModel().getSelectedItem();

        // Kiểm tra xem người dùng đã chọn thuốc để xóa chưa
        if (selected == null) {
            // Hiển thị thông báo lỗi nếu không có khách hàng nào được chọn
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một thuốc để xóa.");
            alert.showAndWait();
            return;
        }

        // Hiển thị hộp thoại xác nhận xóa
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận xóa");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Bạn có chắc chắn muốn xóa thuốc này?");

        // Kiểm tra xem người dùng đã chọn đồng ý hoặc hủy bỏ
        ButtonType result = confirmAlert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            // Nếu người dùng đồng ý, tiến hành xóa khách hàng
            thuocDAO.removeData(selected);

            // Cập nhật TableView sau khi xóa
            showThuoc();

        }
    }

    //Sửa thuốc
    @FXML
    public void updateThuoc() {
        // Lấy thông tin thuốc đã chọn từ bảng (nếu có)
        ThuocEntity selectedThuoc = tvThuoc.getSelectionModel().getSelectedItem();

        // Kiểm tra xem đã chọn thuốc để sửa chưa
        if (selectedThuoc == null) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng chọn thuốc để sửa.");
            return;
        }

        // Hiển thị hộp thoại xác nhận trước khi cập nhật
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận sửa thông tin");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Bạn có chắc chắn muốn sửa thông tin của thuốc này?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Lấy thông tin đã chỉnh sửa
            String tenThuoc = txtTenThuoc.getText();
            String loaiThuoc = kindOfMedicineChoiceBox.getValue();
            boolean keDon = checkBoxKeDon.isSelected();
            String donViTinh = String.valueOf(cbDVT.getValue());
            Double giaNhap = Double.parseDouble(txtGiaNhap.getText());
            Double giaBan = Double.parseDouble(txtGiaBan.getText());
            String tacDung = txtTacDung.getText();
            String xuatXu = txtXuatXu.getText();
            String doiTuongSD = txtDoiTuongSD.getText();
            String kvlt = String.valueOf(cbKVLT.getValue());
            java.sql.Date ngaySX = java.sql.Date.valueOf(dateNSX.getValue());
            java.sql.Date ngayHH = java.sql.Date.valueOf(dateNHH.getValue());

            // Kiểm tra xem người dùng đã nhập đủ thông tin hay chưa
            if ( tenThuoc.isEmpty() || donViTinh.isEmpty() || ngaySX == null
                    || ngayHH == null || giaNhap == 0.0 || giaBan == 0.0 || kvlt.isEmpty()
                    || tacDung.isEmpty() || xuatXu.isEmpty() || doiTuongSD.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ thông tin.");
                return;
            }

            // Cập nhật thông tin cho đối tượng selectedKH
            selectedThuoc.setTenThuoc(tenThuoc);
            selectedThuoc.setMaLoaiThuoc(loaiThuoc);
            selectedThuoc.setKeDon(keDon);
            selectedThuoc.setDonViTinh(donViTinh);
            selectedThuoc.setDonGiaNhap(giaNhap);
            selectedThuoc.setDonGia(giaBan);
            selectedThuoc.setMoTa(tacDung);
            selectedThuoc.setXuatXu(xuatXu);
            selectedThuoc.setDoiTuongSD(doiTuongSD);
            selectedThuoc.setMaKhuVuc(kvlt);
            selectedThuoc.setNgayHh(ngayHH);
            selectedThuoc.setNgaySx(ngaySX);

            // Cập nhật thông tin thuốc trong cơ sở dữ liệu
            if (thuocDAO.updateData(selectedThuoc)) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Sửa thông tin thuốc thành công.");
                clearTextFieldMedicine();
                showThuoc();
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Sửa thông tin thuốc thành công.");
            }
        }
    }
    // Xử lý button xóa rỗng

    @FXML
    private void clearTextFieldMedicine() {
        txtMaThuoc.setText("");
        txtXuatXu.setText("");
        txtTenThuoc.setText("");
        txtTacDung.setText("");
        txtMaThuoc.setText("");
        txtGiaNhap.setText("");
        cbDVT.setValue(null);
        cbKVLT.setValue(null);
        txtGiaBan.setText("");
        txtDoiTuongSD.setText("");
        dateNSX.setValue(null);
        dateNHH.setValue(null);
    }
//    /*----------------------------------------------------  KHU VỰC LƯU TRỮ ---------------------------------------------------------------*/
    //Show Khu vực lên bảng

    @FXML
    private TableColumn<KhuVucLuuTruEntity, String> maKV, tenKV, soLuongThuoc, moTaKV;

    @FXML
    private TableView<KhuVucLuuTruEntity> tvKhuVucLuuTru;
    @FXML
    private TextField txtMaKV, txtTenKV;

    KhuVucLuuTruDAO khuVucLuuTruDAO = new KhuVucLuuTruDAO();

    @FXML
    public void showKV() {
        if (tvKhuVucLuuTru == null) {
            // Xử lý trường hợp tvThuoc là null
            System.err.println("TableView tvKVLT is null. Initialization failed.");
            return; // Không thực hiện các thao tác khác nếu tvKH là null
        }
        // Thiết lập giá trị của từng cột
        try {
            maKV.setCellValueFactory(new PropertyValueFactory<>("maKv"));
            tenKV.setCellValueFactory(new PropertyValueFactory<>("tenKv"));
//            soLuongThuoc.setCellValueFactory(new PropertyValueFactory<>("soLuongThuoc"));
//            moTaKV.setCellValueFactory(new PropertyValueFactory<>("moTa"));



            List<KhuVucLuuTruEntity> li = khuVucLuuTruDAO.getAll();

            if (li != null && !li.isEmpty()) {
                ObservableList<KhuVucLuuTruEntity> allKV = FXCollections.observableList(li);
                tvKhuVucLuuTru.setItems(allKV);


                tvKhuVucLuuTru.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        KhuVucLuuTruEntity selectedKVLT = tvKhuVucLuuTru.getSelectionModel().getSelectedItem();
                        if (selectedKVLT != null) {
                            txtMaKV.setText(selectedKVLT.getMaKv());
                            txtMaKV.setEditable(false);
                            txtTenKV.setText(selectedKVLT.getTenKv());
                        }
                    }
                });


            } else {
                // Xử lý trường hợp danh sách rỗng
                System.err.println("List of Thuốc is null or empty. No data to display.");

            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có bất kỳ lỗi nào xảy ra trong quá trình khởi tạo
            System.err.println("An error occurred during initialization: " + e.getMessage());
            e.printStackTrace(); // In stack trace để debug
        }

    }

    // Them KVLT
    public void addKVLT(){
        // Lấy thông tin từ các trường nhập liệu trên form
        String maKV = txtMaKV.getText();
        String tenKV = txtTenKV.getText();


        KhuVucLuuTruEntity newKVLT = new KhuVucLuuTruEntity(maKV,tenKV);

        // Thêm NCC mới vào cơ sở dữ liệu
        if (khuVucLuuTruDAO.addData(newKVLT)) {
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm khu vực lưu trữ  thành công.");
            clearFieldsKVLT();
            showKV();
        } else {
            // Hiển thị thông báo lỗi nếu thêm không thành công
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thêm khu vực lưu trữ thất bại.");
        }
    }
    //BT Xóa KV
    public void deleteKVLT() {
        // Lấy KV được chọn từ TableView
        KhuVucLuuTruEntity selected = tvKhuVucLuuTru.getSelectionModel().getSelectedItem();

        // Kiểm tra xem người dùng đã chọn KV để xóa chưa
        if (selected == null) {
            // Hiển thị thông báo lỗi nếu không có khách hàng nào được chọn
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một khu vực để xóa.");
            alert.showAndWait();
            return;
        }

        // Hiển thị hộp thoại xác nhận xóa
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận xóa");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Bạn có chắc chắn muốn xóa khu vực này?");

        // Kiểm tra xem người dùng đã chọn đồng ý hoặc hủy bỏ
        ButtonType result = confirmAlert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            // Nếu người dùng đồng ý, tiến hành xóa khách hàng
            khuVucLuuTruDAO.removeData(selected);

            // Cập nhật TableView sau khi xóa
            showKV();
            clearFieldsKVLT();

        }
    }
    //BT Sửa KV
    // Button Sửa NCC
    public void updateKVLT() {
        // Lấy thông tin khu vực đã chọn từ bảng (nếu có)
        KhuVucLuuTruEntity selectedKVLT = tvKhuVucLuuTru.getSelectionModel().getSelectedItem();

        // Kiểm tra xem đã chọn khách hàng để sửa chưa
        if (selectedKVLT == null) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng chọn khu vực để sửa.");
            return;
        }
        // Lấy thông tin đã chỉnh sửa
        String tenKV = txtTenNCC.getText();

        // Kiểm tra xem người dùng đã nhập đủ thông tin hay chưa
        if (tenKV.isEmpty() ) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        // Cập nhật thông tin cho đối tượng selectedKH
        selectedKVLT.setTenKv(tenKV);

        // Cập nhật thông tin khách hàng trong cơ sở dữ liệu
        if (khuVucLuuTruDAO.updateData(selectedKVLT)) {
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Sửa thông tin khu vực lưu trữ thành công.");
            showKV();
            clearFieldsKVLT();
        } else {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Sửa thông tin khu vực lưu trữ thất bại.");
        }
    }
    //BT Xoa trăng KV
    public void clearFieldsKVLT(){
        txtMaKV.setText("");
        txtMaKV.setEditable(true);
        txtTenKV.setText("");
    }
    /*----------------------------------------------------  NHÀ CUNG CẤP  ---------------------------------------------------------------*/
    //Show NCC lên bảng
    @FXML
    private TableView<NhaCungCapEntity> tvNCC;
    @FXML
    private TableColumn<NhaCungCapEntity,String> maNCC,tenNCC,diaChiNCC,sdtNCC;
    @FXML
    private TextField txtMaNCC,txtTenNCC,txtDiaChiNCC,txtSdtNCC;

    NhaCungCapDAO nhaCungCapDAO=new NhaCungCapDAO();
    @FXML
    public void showNCC() {
        if (tvNCC == null) {
            // Xử lý trường hợp tvThuoc là null
            System.err.println("TableView tvNCC is null. Initialization failed.");
            return; // Không thực hiện các thao tác khác nếu tvKH là null
        }
        // Thiết lập giá trị của từng cột
        try {
            maNCC.setCellValueFactory(new PropertyValueFactory<>("maNcc"));
            tenNCC.setCellValueFactory(new PropertyValueFactory<>("tenNcc"));
            diaChiNCC.setCellValueFactory(new PropertyValueFactory<>("diaChiNcc"));
            sdtNCC.setCellValueFactory(new PropertyValueFactory<>("soDtNcc"));


            List<NhaCungCapEntity> li = nhaCungCapDAO.getAll();

            if (li != null && !li.isEmpty()) {
                ObservableList<NhaCungCapEntity> allNCC = FXCollections.observableList(li);
                tvNCC.setItems(allNCC);


                tvNCC.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        NhaCungCapEntity selectedNCC = tvNCC.getSelectionModel().getSelectedItem();
                        if (selectedNCC != null) {
                            txtMaNCC.setText(selectedNCC.getMaNcc());
                            txtMaNCC.setEditable(false);
                            txtTenNCC.setText(selectedNCC.getTenNcc());
                            txtSdtNCC.setText(selectedNCC.getSoDtNcc());
                            txtDiaChiNCC.setText(selectedNCC.getDiaChiNcc());
                        }
                    }
                });


            } else {
                // Xử lý trường hợp danh sách rỗng
                System.err.println("List of NCC is null or empty. No data to display.");

            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có bất kỳ lỗi nào xảy ra trong quá trình khởi tạo
            System.err.println("An error occurred during initialization: " + e.getMessage());
            e.printStackTrace(); // In stack trace để debug
        }

    }
    // Them NCC
    public void addNCC(){
        // Lấy thông tin từ các trường nhập liệu trên form
        String maNCC = txtMaNCC.getText();
        String tenNCC = txtTenNCC.getText();
        String diaChiNCC = txtDiaChiNCC.getText();
        String sdtNCC = txtSdtNCC.getText();

        NhaCungCapEntity newNCC = new NhaCungCapEntity(maNCC, tenNCC, sdtNCC, diaChiNCC);

        // Thêm NCC mới vào cơ sở dữ liệu
        if (nhaCungCapDAO.addData(newNCC)) {
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm nhà cung cấp thành công.");
            clearFieldsNCC();
            showNCC();
        } else {
            // Hiển thị thông báo lỗi nếu thêm không thành công
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thêm nhà cung cấp thất bại.");
        }
    }
    // Button Sửa NCC
    public void updateNCC() {
        // Lấy thông tin khách hàng đã chọn từ bảng (nếu có)
        NhaCungCapEntity selectedNCC = tvNCC.getSelectionModel().getSelectedItem();

        // Kiểm tra xem đã chọn khách hàng để sửa chưa
        if (selectedNCC == null) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng chọn nhà cung cấp để sửa.");
            return;
        }
        // Lấy thông tin đã chỉnh sửa
        String tenNCC = txtTenNCC.getText();
        String diaChiNCC= txtDiaChiNCC.getText();
        String sdtNCC = txtSdtNCC.getText();

        // Kiểm tra xem người dùng đã nhập đủ thông tin hay chưa
        if (tenNCC.isEmpty() || diaChiNCC.isEmpty() || sdtNCC.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        // Cập nhật thông tin cho đối tượng selectedKH
        selectedNCC.setTenNcc(tenNCC);
        selectedNCC.setDiaChiNcc(diaChiNCC);
        selectedNCC.setSoDtNcc(sdtNCC);

        // Cập nhật thông tin khách hàng trong cơ sở dữ liệu
        if (nhaCungCapDAO.updateData(selectedNCC)) {
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Sửa thông tin nhà cung cấp thành công.");
            showNCC();
            clearFieldsNCC();
        } else {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Sửa thông tin nhà cung cấp thất bại.");
        }
    }

    // Button Xóa NCC
    public void deleteNCC() {
        // Lấy khách hàng được chọn từ TableView
        NhaCungCapEntity selected = tvNCC.getSelectionModel().getSelectedItem();

        // Kiểm tra xem người dùng đã chọn khách hàng để xóa chưa
        if (selected == null) {
            // Hiển thị thông báo lỗi nếu không có khách hàng nào được chọn
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một nhà cung cấp để xóa.");
            alert.showAndWait();
            return;
        }

        // Hiển thị hộp thoại xác nhận xóa
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận xóa");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Bạn có chắc chắn muốn xóa nhà cung cấp này?");

        // Kiểm tra xem người dùng đã chọn đồng ý hoặc hủy bỏ
        ButtonType result = confirmAlert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            // Nếu người dùng đồng ý, tiến hành xóa khách hàng
            nhaCungCapDAO.removeData(selected);

            // Cập nhật TableView sau khi xóa
            showNCC();

        }
    }

    // Button Xoa Trang NCC
    public void clearFieldsNCC(){
        txtMaNCC.setText("");
        txtMaNCC.setEditable(true);
        txtTenNCC.setText("");
        txtSdtNCC.setText("");
        txtDiaChiNCC.setText("");
    }

    /*----------------------------------------------------  KHÁCH HÀNG ---------------------------------------------------------------*/
    //Show KH lên bảng


    @FXML
    private TableView<KhachHangEntity> tvKhachHang;
    @FXML
    private TableColumn<KhachHangEntity, String> maKH, tenKH, gioiTinhKH, sdtKH;

    @FXML
    private TableColumn<KhachHangEntity, Date> ngaySinhKH;

    // Định nghĩa GridPane để Thêm, Sửa
//    @FXML
//    private GridPane gridPaneKH;

    @FXML
    private TextField txtMaKH, txtTenKH, txtSdtKH;
    @FXML
    private DatePicker dateNgaySinhKH;
    @FXML
    private RadioButton rdBtnNam, rdBtnNu;
    KhachHangDAO khachHangDAO = new KhachHangDAO();

    //Hiện khách hàng lên table
    @FXML
    public void showKH() {
        if (tvKhachHang == null) {
            // Xử lý trường hợp tvThuoc là null
            System.err.println("TableView tvKH is null. Initialization failed.");
            return; // Không thực hiện các thao tác khác nếu tvKH là null
        }
        // Thiết lập giá trị của từng cột
        try {
            maKH.setCellValueFactory(new PropertyValueFactory<>("maKh"));
            tenKH.setCellValueFactory(new PropertyValueFactory<>("tenKh"));
            gioiTinhKH.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
            sdtKH.setCellValueFactory(new PropertyValueFactory<>("sdtKh"));
            ngaySinhKH.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));


            List<KhachHangEntity> li = khachHangDAO.getAll();

            if (li != null && !li.isEmpty()) {
                ObservableList<KhachHangEntity> allKH = FXCollections.observableList(li);
                tvKhachHang.setItems(allKH);


                tvKhachHang.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        KhachHangEntity selectedKH = tvKhachHang.getSelectionModel().getSelectedItem();
                        if (selectedKH != null) {
                            txtMaKH.setText(selectedKH.getMaKh());
                            txtMaKH.setEditable(false);
                            if (selectedKH.getGioiTinh().equals("Nam")) {
                                rdBtnNam.setSelected(true);
                                rdBtnNu.setSelected(false);
                            } else {
                                rdBtnNu.setSelected(true);
                                rdBtnNam.setSelected(false);
                            }
                            txtTenKH.setText(selectedKH.getTenKh());
                            txtSdtKH.setText(selectedKH.getSdtKh());
                            //DatePicker
                            Date ngaySinh = selectedKH.getNgaySinh();
                            // Chuyển đổi từ java.sql.Date sang java.util.Date
                            java.util.Date utilDate = new java.util.Date(ngaySinh.getTime());
                            LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            dateNgaySinhKH.setValue(localDate);
                        }
                    }
                });


            } else {
                // Xử lý trường hợp danh sách rỗng
                System.err.println("List of Thuốc is null or empty. No data to display.");

            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có bất kỳ lỗi nào xảy ra trong quá trình khởi tạo
            System.err.println("An error occurred during initialization: " + e.getMessage());
            e.printStackTrace(); // In stack trace để debug
        }

    }

    //Thêm khách hàng
    @FXML
    public void addKH() {
        // Lấy thông tin từ các trường nhập liệu trên form
        String maKH = txtMaKH.getText();
        String tenKH = txtTenKH.getText();
        String gioiTinh = rdBtnNam.isSelected() ? "Nam" : "Nữ";
        String sdtKH = txtSdtKH.getText();
        java.sql.Date ngaySinh = java.sql.Date.valueOf(dateNgaySinhKH.getValue()); // Lấy ngày sinh từ DatePicker

        // Kiểm tra xem người dùng đã nhập đủ thông tin hay chưa
        if (maKH.isEmpty() || tenKH.isEmpty() || sdtKH.isEmpty() || ngaySinh == null) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ thông tin.");
            return;
        }
        khachHangDAO = new KhachHangDAO();
        KhachHangEntity newKH = new KhachHangEntity(maKH, tenKH, gioiTinh, ngaySinh, sdtKH);

        // Thêm khách hàng mới vào cơ sở dữ liệu bằng cách sử dụng KhachHangDAO
        if (khachHangDAO.addData(newKH)) {
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm khách hàng thành công.");
            clearFieldsKH();
            showKH();
        } else {
            // Hiển thị thông báo lỗi nếu thêm không thành công
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Thêm khách hàng thất bại.");
        }
    }

    //Xóa Khách hàng
    public void deleteKH() {
        // Lấy khách hàng được chọn từ TableView
        KhachHangEntity selected = tvKhachHang.getSelectionModel().getSelectedItem();

        // Kiểm tra xem người dùng đã chọn khách hàng để xóa chưa
        if (selected == null) {
            // Hiển thị thông báo lỗi nếu không có khách hàng nào được chọn
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một khách hàng để xóa.");
            alert.showAndWait();
            return;
        }

        // Hiển thị hộp thoại xác nhận xóa
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận xóa");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Bạn có chắc chắn muốn xóa khách hàng này?");

        // Kiểm tra xem người dùng đã chọn đồng ý hoặc hủy bỏ
        ButtonType result = confirmAlert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            // Nếu người dùng đồng ý, tiến hành xóa khách hàng
            khachHangDAO.removeData(selected);

            // Cập nhật TableView sau khi xóa
            showKH();

        }
    }

    //Sửa khách hàng
    public void updateKH() {
        // Lấy thông tin khách hàng đã chọn từ bảng (nếu có)
        KhachHangEntity selectedKH = tvKhachHang.getSelectionModel().getSelectedItem();

        // Kiểm tra xem đã chọn khách hàng để sửa chưa
        if (selectedKH == null) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng chọn khách hàng để sửa.");
            return;
        }
        // Lấy thông tin đã chỉnh sửa
        String tenKH = txtTenKH.getText();
        String gioiTinh = rdBtnNam.isSelected() ? "Nam" : "Nữ";
        String sdtKH = txtSdtKH.getText();
        java.sql.Date ngaySinh = java.sql.Date.valueOf(dateNgaySinhKH.getValue());

        // Kiểm tra xem người dùng đã nhập đủ thông tin hay chưa
        if (tenKH.isEmpty() || sdtKH.isEmpty() || ngaySinh == null) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        // Cập nhật thông tin cho đối tượng selectedKH
        selectedKH.setTenKh(tenKH);
        selectedKH.setGioiTinh(gioiTinh);
        selectedKH.setSdtKh(sdtKH);
        selectedKH.setNgaySinh(ngaySinh);

        // Cập nhật thông tin khách hàng trong cơ sở dữ liệu
        if (khachHangDAO.updateData(selectedKH)) {
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Sửa thông tin khách hàng thành công.");
            showKH();
            clearFieldsKH();
        } else {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Sửa thông tin khách hàng thất bại.");
        }
    }


    @FXML
    private TextField txtSearchKH;
    @FXML
    public void searchCustomerByPhoneNumber() {
        String sdt = txtSearchKH.getText(); // Lấy số điện thoại từ TextField

        // Kiểm tra xem có nhập số điện thoại hay không
        if (sdt.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập số điện thoại.");
            return;
        }

        // Gọi hàm tìm kiếm khách hàng theo số điện thoại từ DAO
        List<KhachHangEntity> result = khachHangDAO.findCustomerByPhoneNumber(sdt);

        if (result != null && !result.isEmpty()) {
            // Nếu tìm thấy khách hàng, hiển thị thông tin trong TableView hoặc ListView
            ObservableList<KhachHangEntity> observableResult = FXCollections.observableArrayList(result);
            tvKhachHang.setItems(observableResult); // tvKhachHang là TableView hoặc ListView
        } else {
            // Nếu không tìm thấy khách hàng, hiển thị thông báo
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Không tìm thấy khách hàng với số điện thoại đã nhập.");
        }
    }

    //Xóa trắng KH
    public void clearFieldsKH() {
        txtMaKH.setText("");
        txtMaKH.setEditable(true);
        txtTenKH.setText("");
        rdBtnNam.setSelected(false);
        rdBtnNu.setSelected(false);
        txtSdtKH.setText("");
        dateNgaySinhKH.setValue(null);
    }

    /*----------------------------------------------------  NHÂN VIÊN  ---------------------------------------------------------------*/

// Show nhân viên lên bảng


    @FXML
    private TableView<NhanVienEntity> tvNhanVien;
    @FXML
    private TableColumn<NhanVienEntity, String> maNV, tenNV, gioiTinhNV, sdtNV, emailNV, taiKhoan, cccd,diaChiNV, chucVu;

    @FXML
    private TableColumn<NhanVienEntity, Date> ngaySinhNV;

    // Định nghĩa GridPane và các Label
//    @FXML
//    private GridPane gridPaneNV;

    @FXML
    private TextField txtMaNV, txtTenNV, txtSdtNV,
            txtCccdNV, txtEmailNV, txtDiaChiNV, txtTkNV;
    @FXML
    private RadioButton rdNamNV, rdNuNV;
    @FXML
    private DatePicker dateNgaySinhNV;

    @FXML
    private ComboBox<String> roleEmployeeChoiceBox;
    NhanVienDAO nhanVienDAO = new NhanVienDAO();

    @FXML
    public void showNV() {
        ObservableList<String> choices = FXCollections.observableArrayList("Nhân viên", "Quản lý");
        roleEmployeeChoiceBox.setItems(choices);

        if (tvNhanVien == null) {
            // Xử lý trường hợp tvThuoc là null
            System.err.println("TableView tvNhanVien is null. Initialization failed.");
            return; // Không thực hiện các thao tác khác nếu tvKH là null
        }
        // Thiết lập giá trị của từng cột
        try {
            maNV.setCellValueFactory(new PropertyValueFactory<>("maNv"));
            tenNV.setCellValueFactory(new PropertyValueFactory<>("tenNv"));
            gioiTinhNV.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
            taiKhoan.setCellValueFactory(new PropertyValueFactory<>("maTaiKhoan"));
            cccd.setCellValueFactory(new PropertyValueFactory<>("cccd"));
            emailNV.setCellValueFactory(new PropertyValueFactory<>("email"));
            sdtNV.setCellValueFactory(new PropertyValueFactory<>("sdtNv"));
            ngaySinhNV.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
            diaChiNV.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
            chucVu.setCellValueFactory(new PropertyValueFactory<>("chucVu"));


            List<NhanVienEntity> li = nhanVienDAO.getAll();

            if (li != null && !li.isEmpty()) {
                ObservableList<NhanVienEntity> allNV = FXCollections.observableList(li);
                tvNhanVien.setItems(allNV);


                tvNhanVien.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        NhanVienEntity selectedNV = tvNhanVien.getSelectionModel().getSelectedItem();
                        if (selectedNV != null) {
                            txtMaNV.setText(selectedNV.getMaNv());
                            txtMaNV.setEditable(false);
                            txtTenNV.setText(selectedNV.getTenNv());
                            //DatePicker
                            Date ngaySinh = selectedNV.getNgaySinh();
                            // Chuyển đổi từ java.sql.Date sang java.util.Date
                            java.util.Date utilDate = new java.util.Date(ngaySinh.getTime());
                            LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            dateNgaySinhNV.setValue(localDate);

                            if (selectedNV.getGioiTinh().equals("Nam")) {
                                rdNamNV.setSelected(true);
                                rdNuNV.setSelected(false);
                            } else {
                                rdNuNV.setSelected(true);
                                rdNamNV.setSelected(false);
                            }
                            roleEmployeeChoiceBox.setValue(selectedNV.getChucVu());
                            txtTkNV.setText(selectedNV.getMaTaiKhoan());
                            txtSdtNV.setText(selectedNV.getSdtNv());
                            txtEmailNV.setText(selectedNV.getEmail());
                            txtCccdNV.setText(selectedNV.getCccd());
                            txtDiaChiNV.setText(selectedNV.getDiaChi());

                        }
                    }
                });


            } else {
                // Xử lý trường hợp danh sách rỗng
                System.err.println("List of Nhân Viên is null or empty. No data to display.");

            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có bất kỳ lỗi nào xảy ra trong quá trình khởi tạo
            System.err.println("An error occurred during initialization: " + e.getMessage());
            e.printStackTrace(); // In stack trace để debug
        }

    }

    //Thêm nhân viên

    @FXML
    public void addNV() {
        // Lấy thông tin từ các trường nhập liệu trên form
        String maNV = txtMaNV.getText();
        String tenNV = txtTenNV.getText();
        java.sql.Date ngaySinh = java.sql.Date.valueOf(dateNgaySinhNV.getValue()); // Lấy ngày sinh từ DatePicker
        String gioiTinh = rdNamNV.isSelected() ? "Nam" : "Nữ";
        String chucVu = roleEmployeeChoiceBox.getValue();
        String tkNv = txtTkNV.getText();
        String sdtNV = txtSdtNV.getText();
        String email = txtEmailNV.getText();
        String cccd = txtCccdNV.getText();
        String diaChi = txtDiaChiNV.getText();

        // Kiểm tra xem người dùng đã nhập đủ thông tin hay chưa
        if (maNV.isEmpty() || tenNV.isEmpty() || sdtNV.isEmpty() || ngaySinh == null
                || chucVu==null|| tkNv.isEmpty() || email.isEmpty() || cccd.isEmpty() || diaChi.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ thông tin.");
            return;
        }
            NhanVienEntity newNV = new NhanVienEntity(maNV, tenNV, gioiTinh, cccd, chucVu, sdtNV, email, diaChi, ngaySinh, tkNv);
            // Thêm khách hàng mới vào cơ sở dữ liệu bằng cách sử dụng KhachHangDAO
            if (nhanVienDAO.addData(newNV)) {
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm nhân viên thành công.");
                clearFieldsNV();
                showNV();
            } else {
                // Hiển thị thông báo lỗi nếu thêm không thành công
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "thêm thông tin nhân viên thành công.");
                showNV();
            }
    }


    //Xóa Nhân viên
    public void deleteNV() {
        // Lấy khách hàng được chọn từ TableView
        NhanVienEntity selected = tvNhanVien.getSelectionModel().getSelectedItem();

        // Kiểm tra xem người dùng đã chọn khách hàng để xóa chưa
        if (selected == null) {
            // Hiển thị thông báo lỗi nếu không có khách hàng nào được chọn
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một nhân viên để xóa.");
            alert.showAndWait();
            return;
        }

        // Hiển thị hộp thoại xác nhận xóa
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận xóa");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Bạn có chắc chắn muốn xóa nhân viên này?");

        // Kiểm tra xem người dùng đã chọn đồng ý hoặc hủy bỏ
        ButtonType result = confirmAlert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            // Nếu người dùng đồng ý, tiến hành xóa khách hàng
            nhanVienDAO = new NhanVienDAO();
            nhanVienDAO.removeData(selected);

            // Cập nhật TableView sau khi xóa
            showNV();
        }
    }

    // Sửa nhân viên
    @FXML
    public void updateNV() {
        // Lấy thông tin từ các trường nhập liệu trên form
        String maNV = txtMaNV.getText();
        String tenNV = txtTenNV.getText();
        java.sql.Date ngaySinh = java.sql.Date.valueOf(dateNgaySinhNV.getValue()); // Lấy ngày sinh từ DatePicker
        String gioiTinh = rdNamNV.isSelected() ? "Nam" : "Nữ";
        String chucVu = roleEmployeeChoiceBox.getValue() != null ? roleEmployeeChoiceBox.getValue().toString() : null;
        String tkNv = txtTkNV.getText();
        String sdtNV = txtSdtNV.getText();
        String email = txtEmailNV.getText();
        String cccd = txtCccdNV.getText();
        String diaChi = txtDiaChiNV.getText();

        // Kiểm tra xem người dùng đã nhập đủ thông tin hay chưa
        if (maNV.isEmpty() || tenNV.isEmpty() || sdtNV.isEmpty() || ngaySinh == null
                || chucVu == null || tkNv.isEmpty() || email.isEmpty() || cccd.isEmpty() || diaChi.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        // Tạo đối tượng Nhân viên mới
        NhanVienEntity editedNV = new NhanVienEntity(maNV, tenNV, gioiTinh, cccd, chucVu, sdtNV, email, diaChi, ngaySinh, tkNv);

        // Gọi phương thức cập nhật dữ liệu trong DAO
        if (nhanVienDAO.updateData(editedNV)) {
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Sửa thông tin nhân viên thành công.");
            clearFieldsNV();
            showNV();
        } else {
            // Hiển thị thông báo lỗi nếu sửa không thành công
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Sửa thông tin nhân viên thành công.");
            showNV();
        }
    }

    //Tìm nv theo sdt
    @FXML
    private TextField txtSearchNVKeyword;
    @FXML
    private void searchEmployees(ActionEvent event) {
        String keyword = txtSearchNVKeyword.getText().trim();
        if (!keyword.isEmpty()) {
            // Gọi DAO để thực hiện tìm kiếm
            List<NhanVienEntity> resultList = nhanVienDAO.searchEmployeesByNameOrPhone(keyword);
            if (resultList != null && !resultList.isEmpty()) {
                // Nếu tìm thấy kết quả, hiển thị vào bảng
                ObservableList<NhanVienEntity> observableResultList = FXCollections.observableArrayList(resultList);
                tvNhanVien.setItems(observableResultList);
            } else {
                // Nếu không tìm thấy kết quả, hiển thị thông báo
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Không tìm thấy nhân viên nào phù hợp.");
            }
        } else {
            // Nếu không nhập từ khóa, hiển thị thông báo
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng nhập từ khóa để tìm kiếm.");
        }
    }
    public void clearFieldsNV() {
        txtMaNV.setText("");
        txtMaNV.setEditable(true);
        txtTenNV.setText("");
        dateNgaySinhNV.setValue(null);
        rdNamNV.setSelected(false);
        rdNuNV.setSelected(false);
        roleEmployeeChoiceBox.setValue(null);
        txtTkNV.setText("");
        txtSdtNV.setText("");
        txtEmailNV.setText("");
        txtCccdNV.setText("");
        txtDiaChiNV.setText("");
    }
}