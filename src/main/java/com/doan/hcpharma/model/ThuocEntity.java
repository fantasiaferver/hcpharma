package com.doan.hcpharma.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "thuoc", schema = "qlnhathuoc", catalog = "")
public class ThuocEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maThuoc", nullable = false)
    private int maThuoc;
    @Basic
    @Column(name = "donGia", nullable = false, precision = 0)
    private double donGia;
    @Basic
    @Column(name = "donViTinh", nullable = true, length = 255)
    private String donViTinh;
    @Basic
    @Column(name = "moTa", nullable = true, length = 255)
    private String moTa;
    @Basic
    @Column(name = "ngayHH", nullable = true)
    private Date ngayHh;
    @Basic
    @Column(name = "ngaySX", nullable = true)
    private Date ngaySx;
    @Basic
    @Column(name = "soLuong", nullable = false)
    private int soLuong;
    @Basic
    @Column(name = "tenThuoc", nullable = true, length = 255)
    private String tenThuoc;
    @Basic
    @Column(name = "xuatXu", nullable = true, length = 255)
    private String xuatXu;
    @Basic
    @Column(name = "maKhuVuc", nullable = true, length = 255)
    private String maKhuVuc;
    @Basic
    @Column(name = "maLoaiThuoc", nullable = true, length = 255)
    private String maLoaiThuoc;

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayHh() {
        return ngayHh;
    }

    public void setNgayHh(Date ngayHh) {
        this.ngayHh = ngayHh;
    }

    public Date getNgaySx() {
        return ngaySx;
    }

    public void setNgaySx(Date ngaySx) {
        this.ngaySx = ngaySx;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getMaKhuVuc() {
        return maKhuVuc;
    }

    public void setMaKhuVuc(String maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }

    public String getMaLoaiThuoc() {
        return maLoaiThuoc;
    }

    public void setMaLoaiThuoc(String maLoaiThuoc) {
        this.maLoaiThuoc = maLoaiThuoc;
    }
}
