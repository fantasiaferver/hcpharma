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
    @Column(name = "CCCD", nullable = true, length = 255)
    private String cccd;
    @Basic
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @Basic
    @Column(name = "gioiTinh", nullable = false, length = 10)
    private String gioiTinh;
    @Basic
    @Column(name = "ngaySinh", nullable = true)
    private Date ngaySinh;
    @Basic
    @Column(name = "sdtNV", nullable = true, length = 255)
    private String sdtNv;
    @Basic
    @Column(name = "tenNV", nullable = true, length = 255)
    private String tenNv;
    @Basic
    @Column(name = "maTaiKhoan", nullable = true, length = 255)
    private String maTaiKhoan;

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSdtNv() {
        return sdtNv;
    }

    public void setSdtNv(String sdtNv) {
        this.sdtNv = sdtNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }
}
