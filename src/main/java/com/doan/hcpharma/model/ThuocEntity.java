package com.doan.hcpharma.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "thuoc", schema = "qlnhathuoc", catalog = "")
public class ThuocEntity {
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
    private String moTa;        //Mô tả tác dụng của thuốc
    @Basic
    @Column(name = "ngayHH", nullable = true)
    private Date ngayHh;
    @Basic
    @Column(name = "ngaySX", nullable = true)
    private Date ngaySx;
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
    @Basic
    @Column(name = "keDon")
    private Boolean keDon;
//    @Basic
//    @Column(name = "hinhAnh")
//    private String hinhAnh = "default_image_url";
    @Basic
    @Column(name = "donGiaNhap")
    private Double donGiaNhap;
    @Basic
    @Column(name = "doiTuongSD")
    private String doiTuongSD;


    public String getDoiTuongSD() {
        return doiTuongSD;
    }

    public void setDoiTuongSD(String doiTuongSD) {
        this.doiTuongSD = doiTuongSD;
    }

    public Double getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(Double donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

//    public String getHinhAnh() {
//        return hinhAnh;
//    }
//
//    public void setHinhAnh(String hinhAnh) {
//        this.hinhAnh = hinhAnh;
//    }

    public Boolean getKeDon() {
        return keDon;
    }

    public void setKeDon(Boolean keDon) {
        this.keDon = keDon;
    }
    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }


    public ThuocEntity() {
    }

    public ThuocEntity(int maTi, String tenT, String dvt, String congdung) {
        this.maThuoc=maTi;
        this.tenThuoc=tenT;
        this.donViTinh=dvt;
        this.moTa=congdung;
    }

    public ThuocEntity(int maThuoc, String tenThuoc, String xuatXu, double donGiaNhap, double donGia, String donViTinh, String moTa, Date ngayHh, Date ngaySx, String maKhuVuc, String maLoaiThuoc, Boolean keDon, String doiTuongSD) {
        this.maThuoc = maThuoc;
        this.donGia = donGia;
        this.donViTinh = donViTinh;
        this.moTa = moTa;
        this.ngayHh = ngayHh;
        this.ngaySx = ngaySx;
        this.tenThuoc = tenThuoc;
        this.xuatXu = xuatXu;
        this.maKhuVuc = maKhuVuc;
        this.maLoaiThuoc = maLoaiThuoc;
        this.keDon = keDon;
        this.donGiaNhap = donGiaNhap;
        this.doiTuongSD = doiTuongSD;
    }

    public int getMaThuoc() {
        return maThuoc;
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
