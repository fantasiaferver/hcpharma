package com.doan.hcpharma.controller;
import com.doan.hcpharma.dao.KhachHangDAO;
import com.doan.hcpharma.dao.NhanVienDAO;
import com.doan.hcpharma.dao.ThuocDAO;
import com.doan.hcpharma.model.KhachHangEntity;
import com.doan.hcpharma.model.NhanVienEntity;
import com.doan.hcpharma.model.ThuocEntity;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
    private Button btnAddEmployee;

    @FXML
    private Button btnEditEmployee;

    @FXML
    private Button btnDeleteEmployee;

    @FXML
    private Button btnAddMedicine;

    @FXML
    private Button btnEditMedicine;

    @FXML
    private Button btnDeleteMedicine;

    @FXML
    private Button btnDeltailMedicine;

    @FXML
    private Button btnAddStorage;

    @FXML
    private Button btnEditStorage;

    @FXML
    private Button btnDeleteStorage;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnEditCustomer;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnAddSupplier;

    @FXML
    private Button btnEditSupplier;

    @FXML
    private Button btnDeleteSupplier;



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
        showThuoc();
    }

    @FXML
    private void showStorageTab() {
        showTab(storageTab, "KHU VỰC LƯU TRỮ");
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
    private void showSupplierTab() {
        showTab(supplierTab, "NHÀ CUNG CẤP");
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
    private TextField tfMaKH,tfTenKH,tfSdtKH;
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

    //Show Thuốc lên bảng

    @FXML
    private TableView<ThuocEntity> tvThuoc;
    @FXML
    private TableColumn<ThuocEntity, String> maT, tenT, loaiT, donVi, xuatXu, kvlt, moTa;
    @FXML
    private TableColumn<ThuocEntity, Double> gia;

    @FXML
    private TableColumn<ThuocEntity, Date> ngaySX, ngayHH;

    // Định nghĩa GridPane và các Label
    @FXML
    private GridPane gridPane;

    @FXML
    private Label lblTenT,lblLoaiT,lblDVT,lblCongDung,lblXuatXu,lblGia;

    ThuocDAO thuocDAO = null;

    @FXML
    public void showThuoc() {
        if (tvThuoc == null) {
            // Xử lý trường hợp tvThuoc là null
            System.err.println("TableView tvKH is null. Initialization failed.");
            return; // Không thực hiện các thao tác khác nếu tvKH là null
        }
        // Thiết lập giá trị của từng cột
        try {
            maT.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
            tenT.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
            loaiT.setCellValueFactory(new PropertyValueFactory<>("maLoaiThuoc"));
            donVi.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
            gia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
            xuatXu.setCellValueFactory(new PropertyValueFactory<>("xuatXu") );
            ngaySX.setCellValueFactory(new PropertyValueFactory<>("ngaySx"));
            ngayHH.setCellValueFactory(new PropertyValueFactory<>("ngayHh"));
            kvlt.setCellValueFactory(new PropertyValueFactory<>("maKhuVuc"));
            moTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));

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
                            lblTenT.setText(selectedThuoc.getTenThuoc());
                            lblLoaiT.setText(selectedThuoc.getMaLoaiThuoc());
                            lblDVT.setText(selectedThuoc.getDonViTinh());
                            lblCongDung.setText(selectedThuoc.getMoTa());
                          //  lblXuatXu.setText(selectedThuoc.getXuatXu());
                            lblGia.setText(String.valueOf(selectedThuoc.getDonGia()));
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

    //Show KH lên bảng

    @FXML
    private TableView<KhachHangEntity> tvKhachHang;
    @FXML
    private TableColumn<KhachHangEntity, String> maKH, tenKH,gioiTinhKH, sdtKH ;

    @FXML
    private TableColumn<KhachHangEntity, Date> ngaySinhKH;

    // Định nghĩa GridPane và các Label
//    @FXML
//    private GridPane gridPaneKH;

    @FXML
    private Label lblMaKH,lblTenKH,lblSdtKH, lblNgaySinhKH;

    KhachHangDAO khachHangDAO = null;

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

            khachHangDAO = new KhachHangDAO();
            List<KhachHangEntity> li = khachHangDAO.getAll();

            if (li != null && !li.isEmpty()) {
                ObservableList<KhachHangEntity> allKH = FXCollections.observableList(li);
                tvKhachHang.setItems(allKH);


                tvKhachHang.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        KhachHangEntity selectedKH = tvKhachHang.getSelectionModel().getSelectedItem();
                        if (selectedKH != null) {
                            lblMaKH.setText(selectedKH.getMaKh());
                            lblTenKH.setText(selectedKH.getTenKh());
                            lblSdtKH.setText(selectedKH.getSdtKh());
                            lblNgaySinhKH.setText(String.valueOf(selectedKH.getNgaySinh()));
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
            khachHangDAO = new KhachHangDAO();
            khachHangDAO.removeData(selected);

            // Cập nhật TableView sau khi xóa
            showKH();
        }
    }

    //Sửa khách hàng
// Show nhân viên lên bảng


    @FXML
    private TableView<NhanVienEntity> tvNhanVien;
    @FXML
    private TableColumn<NhanVienEntity, String> maNV, tenNV,gioiTinhNV, sdtNV, emailNV, taiKhoan, cccd;

    @FXML
    private TableColumn<NhanVienEntity, Date> ngaySinhNV;

    // Định nghĩa GridPane và các Label
//    @FXML
//    private GridPane gridPaneNV;

    @FXML
    private Label lblMaNV,lblTenNV,lblSdtNV,
            lblNgaySinhNV,lblCCCD,lblEmailNV,
            lblGioiTinhNV,lblChucVu,lblDiaChi,lblTaiKhoan;

    NhanVienDAO nhanVienDAO = null;

    @FXML
    public void showNV() {
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
            //sdtNV.setCellValueFactory(new PropertyValueFactory<>("sdtNv"));
           // ngaySinhNV.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
            emailNV.setCellValueFactory(new PropertyValueFactory<>("email"));

            nhanVienDAO = new NhanVienDAO();
            List<NhanVienEntity> li = nhanVienDAO.getAll();

            if (li != null && !li.isEmpty()) {
                ObservableList<NhanVienEntity> allNV = FXCollections.observableList(li);
                tvNhanVien.setItems(allNV);


                tvNhanVien.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        NhanVienEntity selectedNV = tvNhanVien.getSelectionModel().getSelectedItem();
                        if (selectedNV != null) {
                            lblMaNV.setText(selectedNV.getMaNv());
                            lblTenNV.setText(selectedNV.getTenNv());
                            lblGioiTinhNV.setText(selectedNV.getGioiTinh());
                            lblChucVu.setText(selectedNV.getChucVu());
                            lblSdtNV.setText(selectedNV.getSdtNv());
                            lblEmailNV.setText(selectedNV.getEmail());
                            lblCCCD.setText(selectedNV.getCccd());
                            lblDiaChi.setText(selectedNV.getDiaChi());
                            lblNgaySinhNV.setText(String.valueOf(selectedNV.getNgaySinh()));
                            lblTaiKhoan.setText(selectedNV.getMaTaiKhoan());
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

//    public void refeshTableView() {
//        khachHangDAO = new KhachHangDAO();
//        List<KhachHangEntity> li = khachHangDAO.getAll();
//
//        if (li != null && !li.isEmpty()) {
//            ObservableList<KhachHangEntity> allKH = FXCollections.observableList(li);
//            tvKhachHang.setItems(allKH);
//        }
//    }
}

