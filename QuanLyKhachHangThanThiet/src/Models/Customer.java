/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author TanTruc
 */
public class Customer {

    private String id;
    private String maKhachHang;
    private String ten;
    private String email;
    private String dienThoai;
    private String diaChi;
    private String gioitinh;
    private TichLuy tichLuy;
    private Date ngayTao;

    // Getter và Setter cho tất cả các thuộc tính
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public TichLuy getTichLuy() {
        return tichLuy;
    }

    public void setTichLuy(TichLuy tichLuy) {
        this.tichLuy = tichLuy;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    // Constructor không tham số
    public Customer() {
    }

    // Constructor với tham số
    public Customer(String id, String maKhachHang, String ten, String email, String dienThoai, String diaChi, String gioitinh, TichLuy tichLuy, Date ngayTao) {
        this.id = id;
        this.maKhachHang = maKhachHang;
        this.ten = ten;
        this.email = email;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
        this.gioitinh = gioitinh;
        this.tichLuy = tichLuy;
        this.ngayTao = ngayTao;
    }

    // Lớp bên trong để biểu diễn thông tin tích lũy
    public static class TichLuy {

        private int tongDiemTichLuy;
        private String rank;

        public int getTongDiemTichLuy() {
            return tongDiemTichLuy;
        }

        public void setTongDiemTichLuy(int tongDiemTichLuy) {
            this.tongDiemTichLuy = tongDiemTichLuy;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public TichLuy() {
        }

        public TichLuy(int tongDiemTichLuy, String rank) {
            this.tongDiemTichLuy = tongDiemTichLuy;
            this.rank = rank;
        }
    }
}
