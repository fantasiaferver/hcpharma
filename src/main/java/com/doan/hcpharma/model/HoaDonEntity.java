package com.doan.hcpharma.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "hoadon", schema = "qlnhathuoc", catalog = "")
public class HoaDonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maHoaDon", nullable = false, length = 255)
    private String maHoaDon;
    @Basic
    @Column(name = "ngayLapHD", nullable = true)
    private Date ngayLapHd;
    @Basic
    @Column(name = "tongTien", nullable = false, precision = 0)
    private double tongTien;
    @Basic
    @Column(name = "maKhachHang", nullable = true, length = 255)
    private String maKhachHang;
    @Basic
    @Column(name = "maNhanVien", nullable = true, length = 255)
    private String maNhanVien;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayLapHd() {
        return ngayLapHd;
    }

    public void setNgayLapHd(Date ngayLapHd) {
        this.ngayLapHd = ngayLapHd;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}
