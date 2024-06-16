package model;

import java.io.Serializable;
import java.sql.Date;

public class SinhVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Mã số sinh viên, Họ tên sinh viên, Giới tính, Ngày sinh
	private int mssv;
	private String hoTen;
	private boolean gioiTinh;
	private Date ngaySinh;
	
	// Tạo SinhVien không có thông tin
	public SinhVien() {
	}
	
	//Tạo SinhVien với đầy đủ thông tin: Mã số sinh viên, Họ tên sinh viên, Giới tính, Ngày sinh
	public SinhVien(int mssv, String hoTen, boolean gioiTinh, Date ngaySinh) {
		this.mssv = mssv;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
	}
	
	// Các getter, setter
	public int getMssv() {
		return mssv;
	}
	
	public void setMssv(int mssv) {
		this.mssv = mssv;
	}
	
	public String getHoTen() {
		return hoTen;
	}
	
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
	public Date getNgaySinh() {
		return ngaySinh;
	}
	
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
}
