package com.doan.hcpharma.model;

import javax.persistence.*;

@Entity
@Table(name = "taikhoan", schema = "qlnhathuoc", catalog = "")
public class TaikhoanEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maTK", nullable = false, length = 255)
    private String maTk;
    @Basic
    @Column(name = "matKhau", nullable = true, length = 255)
    private String matKhau;
    @Basic
    @Column(name = "tenTK", nullable = true, length = 255)
    private String tenTk;

    public String getMaTk() {
        return maTk;
    }

    public void setMaTk(String maTk) {
        this.maTk = maTk;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenTk() {
        return tenTk;
    }

    public void setTenTk(String tenTk) {
        this.tenTk = tenTk;
    }
}
