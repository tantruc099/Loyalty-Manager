/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Manh
 */
public class Product {
    private String id;
    private String maSanPham;
    private String ten;
    private double gia;

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

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Product(String id, String maSanPham, String ten, double gia) {
        this.id = id;
        this.maSanPham = maSanPham;
        this.ten = ten;
        this.gia = gia;
    }

    public Product() {
    }
}
