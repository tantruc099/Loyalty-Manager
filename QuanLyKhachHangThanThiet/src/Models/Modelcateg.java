/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Manh
 */
public class Modelcateg {

    private String id;
    private String maSanPham;
    private String ten;
    private String moTa;
    private double gia;
    private String maLoaiSanPham;
    private int tonKho;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public Modelcateg(String id, String maSanPham, String ten, String moTa, double gia, String maLoaiSanPham, int tonKho) {
        this.id = id;
        this.maSanPham = maSanPham;
        this.ten = ten;
        this.moTa = moTa;
        this.gia = gia;
        this.maLoaiSanPham = maLoaiSanPham;
        this.tonKho = tonKho;
    }

    public Modelcateg() {
    }
}
