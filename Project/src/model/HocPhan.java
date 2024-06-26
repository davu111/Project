// Name: Duong Anh Vu
// Student ID: 20227187
// Class:  150328
// Project: Chu de 5 - Xay dung chuong trinh quan ly sinh vien
// Date: 21/06/2024
package model;

import java.io.Serializable;

public class HocPhan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Mã học phần, Tên học phần
	private String maHocPhan;
	private String tenHocPhan;
	
	// Tạo HocPhan không có thông tin
	public HocPhan() {
	}
	
	// Tạo HocPhan có đầy đủ thông tin: Mã học phần, Tên học phần
	public HocPhan(String maHocPhan, String tenHocPhan) {
		this.maHocPhan = maHocPhan;
		this.tenHocPhan = tenHocPhan;
	}
	
	// Các getter, setter
	public String getMaHocPhan() {
		return maHocPhan;
	}
	
	public void setMaHocPhan(String maHocPhan) {
		this.maHocPhan = maHocPhan;
	}
	
	public String getTenHocPhan() {
		return tenHocPhan;
	}
	
	public void setTenHocPhan(String tenHocPhan) {
		this.tenHocPhan = tenHocPhan;
	}
}
