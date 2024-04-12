package com.doan.hcpharma.model;

import javax.persistence.*;

@Entity
@Table(name = "nhacungcap", schema = "qlnhathuoc", catalog = "")
public class NhaCungCapEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maNCC", nullable = false, length = 255)
    private String   maNcc;
    @Basic
    @Column(name = "tenNCC", nullable = true, length = 255)
    private String tenNcc;
    @Basic
    @Column(name = "soDtNCC", nullable = true, length = 255)
    private String soDtNcc;
    @Basic
    @Column(name = "diaChiNCC", nullable = true, length = 255)
    private String diaChiNcc;

    public NhaCungCapEntity() {
    }

    public NhaCungCapEntity(String maNcc, String tenNcc, String soDtNcc, String diaChiNcc) {
        this.maNcc = maNcc;
        this.tenNcc = tenNcc;
        this.soDtNcc = soDtNcc;
        this.diaChiNcc = diaChiNcc;
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }

    public String getSoDtNcc() {
        return soDtNcc;
    }

    public void setSoDtNcc(String soDtNcc) {
        this.soDtNcc = soDtNcc;
    }

    public String getDiaChiNcc() {
        return diaChiNcc;
    }

    public void setDiaChiNcc(String diaChiNcc) {
        this.diaChiNcc = diaChiNcc;
    }

}