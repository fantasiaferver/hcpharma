package com.doan.hcpharma.controller;

import com.doan.hcpharma.dao.ThuocDAO;
import com.doan.hcpharma.model.ThuocEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicineController implements Initializable {

    @FXML
    private ChoiceBox<String> kindOfMedicineChoiceBox;

    @FXML
    private TextField tfMaThuoc,tfTenThuoc,tfDVT;
    @FXML
    private CheckBox ckbKeDon;
    @FXML
    private Button btnHoanThanh, btnHuy;
    @FXML
    private TextArea taCongDung;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    }

    ThuocDAO thuocDAO= null;
    public void themThuoc(){
        String maT= tfMaThuoc.getText();
        String tenT= tfTenThuoc.getText();
        String dvt=tfDVT.getText();
        String congdung= taCongDung.getText();

        //Get data từ checkbox
        int maTi= Integer.parseInt(maT);

        thuocDAO=new ThuocDAO();
        thuocDAO.addData(new ThuocEntity(maTi,tenT,dvt,congdung));
    }
}
