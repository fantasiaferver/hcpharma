package com.doan.hcpharma.model;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "ct_hoadon", schema = "qlnhathuoc", catalog = "")
public class CT_HoaDonEntity {
    @Basic
    @javax.persistence.Column(name = "maHoaDon", nullable = true, length = 255)
    private String maHoaDon;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    @Basic
    @javax.persistence.Column(name = "maThuoc", nullable = true)
    private Integer maThuoc;

    public Integer getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(Integer maThuoc) {
        this.maThuoc = maThuoc;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "soLuong", nullable = false)
    private int soLuong;

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
