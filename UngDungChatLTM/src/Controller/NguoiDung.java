/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Date;

/**
 *
 * @author Nguyen Son
 */
public class NguoiDung {
    private String tenTk;   
    private String pass;
    private String ten;
    private String gioiTinh;
    private int sdt;
    private Date ngaySinh;

    public NguoiDung() {
    }

    public NguoiDung(String tenTk, String pass) {
        this.tenTk = tenTk;
        this.pass = pass;
    }
    public NguoiDung(String tenTk) {
          this.tenTk = tenTk;
      }
    public NguoiDung(String tenTk, String pass, String ten, String gioiTinh, int sdt, Date ngaySinh) {
        this.tenTk = tenTk;
        this.pass = pass;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
    }

    public String getTenTk() {
        return tenTk;
    }

    public void setTenTk(String tenTk) {
        this.tenTk = tenTk;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
}
