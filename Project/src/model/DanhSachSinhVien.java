package model;

import java.io.File;
import java.util.ArrayList;

public class DanhSachSinhVien extends Project_father<SinhVien>{
	private ArrayList<SinhVien> ds;
	private File file;
	
	// Khởi tạo
	public DanhSachSinhVien() {
		this.ds = new ArrayList<SinhVien>();
	}
	
	// Các getter, setter
	public ArrayList<SinhVien> getDs(){
		return this.ds;
	}
	
	public void setDs(ArrayList<SinhVien> ds) {
		this.ds = ds;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	// Lấy dữ liệu từ file
	// Input: Biến file kiểu File chứa dữ liệu danh sách SinhVien
	// Output: Giá trị của this.file được đặt thành file truyền vào
	//		   Giá trị của this.ds được lấy từ hàm layFile(file) kế thừa từ class Project_father
	public void layDuLieuFile(File file) {
		this.setFile(file);
		this.setDs(this.layFile(file));
	}
	
	// Thêm mới sinh viên
	public boolean themSinhVien(SinhVien sinhVienMoi) {
		for (SinhVien sinhVien : this.ds) {
			if (sinhVienMoi.getMssv() == sinhVien.getMssv()) {
				return false;
			}
		}
		this.ds.add(sinhVienMoi);
		return true;
	}
	
	// Sửa sinh viên
	public boolean suaSinhVien(SinhVien sinhVienMoi) {
		for (SinhVien sinhVien : this.ds) {
			if (sinhVienMoi.getMssv() == sinhVien.getMssv()) {
				this.ds.remove(sinhVien);
				this.ds.add(sinhVienMoi);
				return true;
			}
		}
		return false;
	}
	
	// Xoá sinh viên
	public boolean xoaSinhVien(int mssv) {
		for (SinhVien sinhVien : this.ds) {
			if (sinhVien.getMssv() == mssv) {
				this.ds.remove(sinhVien);
				return true;
			}
		}
		return false;
	}
	
	// Tìm kiếm sinh viên dựa trên phần đầu mã số sinh viên
	// và phần đầu họ tên
	public boolean timSinhVien(String mssv, String hoTen) {
		int n = mssv.length();
		int m = hoTen.length();
		for (SinhVien sinhVien: this.ds) {
			if ((sinhVien.getMssv()+"").length() >= n && 
				(sinhVien.getMssv()+"").substring(0, n).equals(mssv)&&
				sinhVien.getHoTen().length() >= m &&
				sinhVien.getHoTen().substring(0, m).equalsIgnoreCase(hoTen))
				return true;
		}
		return false;
	}
}
