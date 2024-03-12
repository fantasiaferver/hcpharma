package com.doan.hcpharma.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "khachhang", schema = "qlnhathuoc", catalog = "")
public class KhachHangEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maKH", nullable = false, length = 255)
    private String maKh;
    @Basic
    @Column(name = "tenKH", nullable = true, length = 255)
    private String tenKh;
    @Basic
    @Column(name = "gioiTinh", nullable = true, length = 10)
    private String gioiTinh;
    @Basic
    @Column(name = "ngaySinh", nullable = true)
    private Date ngaySinh;
    @Basic
    @Column(name = "sdtKH", nullable = true, length = 255)
    private String sdtKh;

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdtKh() {
        return sdtKh;
    }

    public void setSdtKh(String sdtKh) {
        this.sdtKh = sdtKh;
    }
}
