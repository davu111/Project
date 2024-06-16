package model;

import java.io.File;
import java.util.ArrayList;

public class DanhSachHocPhan extends Project_father<HocPhan> {
	private ArrayList<HocPhan> ds;
	private File file;
	
	// Khởi tạo
	public DanhSachHocPhan() {
		this.ds = new ArrayList<HocPhan>();
	}
	
	// Các getter, setter
	public ArrayList<HocPhan> getDs(){
		return this.ds;
	}
	
	public void setDs(ArrayList<HocPhan> ds) {
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
	
	// Thêm mới học phần
	public boolean themHocPhan(HocPhan hocPhanMoi) {
		for (HocPhan hocPhan : this.ds) {
			if (hocPhanMoi.getMaHocPhan().equals(hocPhan.getMaHocPhan())) {
				return false;
			}
		}
		this.ds.add(hocPhanMoi);
		return true;
	}
	
	// Sửa học phần
	public boolean suaHocPhan(HocPhan hocPhanMoi) {
		for (HocPhan hocPhan : this.ds) {
			if (hocPhanMoi.getMaHocPhan().equals(hocPhan.getMaHocPhan())) {
				this.ds.remove(hocPhan);
				this.ds.add(hocPhanMoi);
				return true;
			}
		}
		return false;
	}
	
	// Xoá học phần
	public boolean xoaHocPhan(String maHP) {
		for (HocPhan hocPhan : this.ds) {
			if (hocPhan.getMaHocPhan().equals(maHP)) {
				this.ds.remove(hocPhan);
				return true;
			}
		}
		return false;
	}
	
	// Tìm kiếm học phần dựa trên phần đầu của mã học phần
	// và phần đầu của tên học phần
	public boolean timHocPhan(String maHP, String tenHP) {
		int n = maHP.length();
		int m = tenHP.length();
		for (HocPhan hocPhan : this.ds) {
			if (hocPhan.getMaHocPhan().length() >= n &&
				hocPhan.getMaHocPhan().substring(0,n).equals(maHP) &&
				hocPhan.getTenHocPhan().length() >= m &&
				hocPhan.getTenHocPhan().substring(0,m).equalsIgnoreCase(tenHP)) 
				return true;
		}
		return false;
	}
}
