package com.doan.hcpharma.model;

import javax.persistence.*;

@Entity
@Table(name = "khuvucluutru", schema = "qlnhathuoc", catalog = "")
public class KhuVucLuuTruEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maKV", nullable = false, length = 255)
    private String maKv;
    @Basic
    @Column(name = "tenKV", nullable = true, length = 255)
    private String tenKv;

    public String getMaKv() {
        return maKv;
    }

    public void setMaKv(String maKv) {
        this.maKv = maKv;
    }

    public String getTenKv() {
        return tenKv;
    }

    public void setTenKv(String tenKv) {
        this.tenKv = tenKv;
    }
}
