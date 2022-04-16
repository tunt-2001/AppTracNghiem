package com.example.apptracnghiem.model;

public class ThongTin {
    private String hoTen;
    private String email;
    private String NgaySinh;
    private String sdt;

    public ThongTin() {
    }

    public ThongTin(String hoTen, String email, String ngaySinh, String sdt) {
        this.hoTen = hoTen;
        this.email = email;
        NgaySinh = ngaySinh;
        this.sdt = sdt;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
