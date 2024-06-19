// Name: Duong Anh Vu
// Student ID: 20227187
// Class:  150328
// Project: Chu de 5 - Xay dung chuong trinh quan ly sinh vien
// Date: 21/06/2024
package model;

import java.io.Serializable;

public class Diem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Mã học phần, Mã số sinh viên, Điểm học phần
	private String maHocPhan;
	private int mssv;
	private float diem;
	
	// Tạo Diem không có thông tin
	public Diem() {
	}

	// Tạo Diem có đầy đủ thông tin: Mã học phần, Mã số sinh viên, Điểm học phần
	public Diem(String maHocPhan, int mssv, float diem) {
		this.maHocPhan = maHocPhan;
		this.mssv = mssv;
		this.diem = diem;
	}

	// Các getter, setter
	public String getMaHocPhan() {
		return maHocPhan;
	}

	public void setMaHocPhan(String maHocPhan) {
		this.maHocPhan = maHocPhan;
	}

	public int getMssv() {
		return mssv;
	}

	public void setMssv(int mssv) {
		this.mssv = mssv;
	}

	public float getDiem() {
		return diem;
	}

	public void setDiem(float diem) {
		this.diem = diem;
	}
}
