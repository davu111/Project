package model;

import java.io.File;
import java.util.ArrayList;

public class BangDiem extends Project_father<Diem>{
	private ArrayList<Diem> ds;
	private File file;
	
	// Khởi tạo
	public BangDiem() {
		this.ds = new ArrayList<Diem>();
	}
	
	// Các getter, setter
	public ArrayList<Diem> getDs(){
		return this.ds;
	}
	
	public void setDs(ArrayList<Diem> ds){
		this.ds = ds;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	// Lấy dữ liệu từ file
	// Input: Biến file kiểu File chứa dữ liệu danh sách HocPhan
	// Output: Giá trị của this.file được đặt thành file truyền vào
	//		   Giá trị của this.ds được lấy từ hàm layFile(file) kế thừa từ class Project_father
	public void layDuLieuFile(File file) {
		this.setFile(file);
		this.setDs(this.layFile(file));
	}
	
	// Thêm mới điểm
	public boolean themDiem(Diem diemMoi) {
		for (Diem diem : this.ds) {
			if (diemMoi.getMssv() == diem.getMssv() && 
				diemMoi.getMaHocPhan().equals(diem.getMaHocPhan())) {
				return false;
			}
		}
		this.ds.add(diemMoi);
		return true;
	}
	
	// Sửa điểm
	public boolean suaDiem(Diem diemMoi) {
		for (Diem diem : this.ds) {
			if (diemMoi.getMssv() == diem.getMssv() && 
				diemMoi.getMaHocPhan().equals(diem.getMaHocPhan())) {
				this.ds.remove(diem);
				this.ds.add(diemMoi);
				return true;
			}
		}
		return false;
	}
	
	// Xoá điểm
	public boolean xoaDiem(Diem diemMoi) {
		for (Diem diem : this.ds) {
			if (diemMoi.getMssv() == diem.getMssv() && 
				diemMoi.getMaHocPhan().equals(diem.getMaHocPhan())) {
				this.ds.remove(diem);
				return true;
			}
		}
		return false;
	}
	
	// Tìm kiếm điểm
	public boolean timDiem(Diem diemMoi) {
		for (Diem diem : this.ds) {
			if (diemMoi.getMssv() == diem.getMssv() && 
				diemMoi.getMaHocPhan().equals(diem.getMaHocPhan())) {
				return true;
			}
		}
		return false;
	}
	
	// Xem bảng điểm
	public boolean xemDiem (int mssv) {
		for (Diem diem : this.ds)
			if (diem.getMssv() == mssv) return true;
		return false;
	}
}
