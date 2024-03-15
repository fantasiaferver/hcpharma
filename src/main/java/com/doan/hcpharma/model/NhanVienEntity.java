package com.doan.hcpharma.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "nhanvien", schema = "qlnhathuoc", catalog = "")
public class NhanVienEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maNV", nullable = false, length = 255)
    private String maNv;
    @Basic
    @Column(name = "tenNV", nullable = true, length = 255)
    private String tenNv;
    @Basic
    @Column(name = "gioiTinh", nullable = false, length = 10)
    private String gioiTinh;
    @Basic
    @Column(name = "CCCD", nullable = true, length = 255)
    private String cccd;

    @Basic
    @Column(name = "chucVu", nullable = true)
    private String chucVu;
    @Basic
    @Column(name = "sdtNV", nullable = true, length = 255)
    private String sdtNv;
    @Basic
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @Basic
    @Column(name = "diaChi", nullable = true, length = 255)
    private String diaChi;
    @Basic
    @Column(name = "ngaySinh", nullable = true)
    private Date ngaySinh;
    @Basic
    @Column(name = "maTaiKhoan", nullable = true, length = 255)
    private String maTaiKhoan;

    public NhanVienEntity() {
    }

    public NhanVienEntity(String maNv, String tenNv, String gioiTinh,
                          String cccd, String chucVu, String sdtNv,
                          String email, String diaChi, Date ngaySinh,
                          String maTaiKhoan) {
        this.maNv = maNv;
        this.tenNv = tenNv;
        this.gioiTinh = gioiTinh;
        this.cccd = cccd;
        this.chucVu = chucVu;
        this.sdtNv = sdtNv;
        this.email = email;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getSdtNv() {
        return sdtNv;
    }

    public void setSdtNv(String sdtNv) {
        this.sdtNv = sdtNv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }
}
