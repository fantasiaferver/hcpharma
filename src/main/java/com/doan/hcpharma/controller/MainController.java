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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private Button btnClearEmployee;

    @FXML
    private Button btnAddMedicine;

    @FXML
    private Button btnEditMedicine;

    @FXML
    private Button btnDeleteMedicine;

    @FXML
    private Button btnDetailMedicine;

    @FXML
    private Button btnClearMedicine;

    @FXML
    private Button btnAddStorage;

    @FXML
    private Button btnEditStorage;

    @FXML
    private Button btnDeleteStorage;

    @FXML
    private Button btnClearStorage;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnEditCustomer;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnClearCustomer;

    @FXML
    private Button btnAddSupplier;

    @FXML
    private Button btnEditSupplier;

    @FXML
    private Button btnDeleteSupplier;

    @FXML
    private Button btnClearSupplier;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private ChoiceBox<String> kindOfMedicineChoiceBox;

    @FXML
    private ChoiceBox<String> sexEmployeeChoiceBox;

    @FXML
    private ChoiceBox<String> roleEmployeeChoiceBox;



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
        kindOfMedicineChoiceBox.getItems().addAll("Kháng dị ứng"
                ,"Kháng viêm"
                ,"Ngừa thai"
                ,"Cảm lạnh"
                ,"Da liễu"
                ,"Giảm cân"
                ,"Mắt tai mũi"
                ,"Tiêu hóa"
                ,"Giảm đau hạ sốt"
                ,"Thuốc cho Nam"
                ,"Thuốc cho Nữ"
                ,"Thuốc thần kinh"
                ,"Thuốc xương khớp"
                ,"Vitamin và khoáng chất");
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
        sexEmployeeChoiceBox.getItems().addAll("Nam", "Nữ");
        roleEmployeeChoiceBox.getItems().addAll("Nhân viên", "Quản lý");
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


    // Xử lý button xóa rỗng
    @FXML
    private TextField txtDonViTInh;

    @FXML
    private TextField txtGiaBan;

    @FXML
    private TextField txtGiaNhap;

    @FXML
    private TextField txtKVLT;

    @FXML
    private TextField txtMaThuoc;

    @FXML
    private TextField txtSoLuongThuoc;

    @FXML
    private TextArea txtTacDung;

    @FXML
    private TextField txtTenThuoc;

    @FXML
    private TextArea txtThanhPhan;

    @FXML
    private TextField txtThuongHieu;

    @FXML
    private TextField txtDoiTuongSD;


    @FXML
    private void clearTextFieldMedicine() {
        txtThanhPhan.setText("");
        txtThuongHieu.setText("");
        txtTenThuoc.setText("");
        txtTacDung.setText("");
        txtSoLuongThuoc.setText("");
        txtMaThuoc.setText("");
        txtKVLT.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtDonViTInh.setText("");
        txtDoiTuongSD.setText("");
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

    /*----------------------------------------------------  THUỐC  ---------------------------------------------------------------*/
    /*----------------------------------------------------------------------------------------------------------------------------*/

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
                            txtTenThuoc.setText(selectedThuoc.getTenThuoc());
                            kindOfMedicineChoiceBox.setValue(selectedThuoc.getMaLoaiThuoc());
                            txtDonViTInh.setText(selectedThuoc.getDonViTinh());
                            txtTacDung.setText(selectedThuoc.getMoTa());
                            txtGiaBan.setText(String.valueOf(selectedThuoc.getDonGia()));
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
    /*----------------------------------------------------  KHÁCH HÀNG ---------------------------------------------------------------*/
    //Show KH lên bảng

    @FXML
    private TableView<KhachHangEntity> tvKhachHang;
    @FXML
    private TableColumn<KhachHangEntity, String> maKH, tenKH,gioiTinhKH, sdtKH ;

    @FXML
    private TableColumn<KhachHangEntity, Date> ngaySinhKH;

    // Định nghĩa GridPane để Thêm, Sửa
//    @FXML
//    private GridPane gridPaneKH;

    @FXML
    private TextField txtMaKH,txtTenKH,txtSdtKH;
    @FXML
    private DatePicker dateNgaySinhKH;
    @FXML
    private RadioButton rdBtnNam,rdBtnNu;
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
                            if(selectedKH.getGioiTinh().equals("Nam")){
                                rdBtnNam.setSelected(true);
                                rdBtnNu.setSelected(false);
                            }else {
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
        khachHangDAO=new KhachHangDAO();
        KhachHangEntity newKH = new KhachHangEntity(maKH, tenKH, gioiTinh, ngaySinh, sdtKH );

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

    // Phương thức hiển thị hộp thoại thông báo
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
            clearFieldsKH();
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
        if (khachHangDAO.updateData(selectedKH)){
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Sửa thông tin khách hàng thành công.");
            showKH();
            clearFieldsKH();
        } else {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Sửa thông tin khách hàng thất bại.");
        }
    }


    //Xóa trắng KH
    public void clearFieldsKH(){
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

