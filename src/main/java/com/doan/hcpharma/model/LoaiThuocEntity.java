package com.doan.hcpharma.model;

import javax.persistence.*;

@Entity
@Table(name = "loaithuoc", schema = "qlnhathuoc", catalog = "")
public class LoaiThuocEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maLoaiThuoc", nullable = false, length = 255)
    private String maLoaiThuoc;
    @Basic
    @Column(name = "tenLoaiThuoc", nullable = true, length = 255)
    private String tenLoaiThuoc;

    public String getMaLoaiThuoc() {
        return maLoaiThuoc;
    }

    public void setMaLoaiThuoc(String maLoaiThuoc) {
        this.maLoaiThuoc = maLoaiThuoc;
    }

    public String getTenLoaiThuoc() {
        return tenLoaiThuoc;
    }

    public void setTenLoaiThuoc(String tenLoaiThuoc) {
        this.tenLoaiThuoc = tenLoaiThuoc;
    }
}
