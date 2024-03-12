package com.doan.hcpharma.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicineController implements Initializable {

    @FXML
    private ChoiceBox<String> kindOfMedicineChoiceBox;

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
}
