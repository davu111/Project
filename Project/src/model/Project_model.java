package model;

import java.io.File;

import dao.DiemDAO;
import dao.HocPhanDAO;
import dao.SinhVienDAO;

public class Project_model {
	private DanhSachSinhVien dsSinhVien;
	private DanhSachHocPhan dsHocPhan;
	private BangDiem bangDiem;
	private SinhVienDAO logSinhVien;
	private HocPhanDAO logHocPhan;
	private DiemDAO logDiem;
	
	// Khởi tạo 
	public Project_model() {
		this.dsSinhVien = new DanhSachSinhVien();
		this.dsHocPhan =  new DanhSachHocPhan();
		this.bangDiem = new BangDiem();
		this.logSinhVien = new SinhVienDAO();
		this.logHocPhan = new HocPhanDAO();
		this.logDiem = new DiemDAO();
	}
	
	// Lấy danh sách sinh viên
	public DanhSachSinhVien getDsSinhVien() {
		return this.dsSinhVien;
	}

	// Lấy danh sách học phần
	public DanhSachHocPhan getDsHocPhan() {
		return this.dsHocPhan ;
	}

	// Lấy danh sách điểm
	public BangDiem getBangDiem() {
		return this.bangDiem ;
	}

	// Lấy dữ liệu danh sách sinh viên từ file đã chọn 
	public void moFileSinhVien(File file) {
		dsSinhVien.layDuLieuFile(file);
	}
	
	// Lấy dữ liệu danh sách học phần từ file đã chọn
	public void moFileHocPhan(File file) {
		dsHocPhan.layDuLieuFile(file);
	}
	
	// Lấy dữ liệu danh sách điểm từ file đã chọn
	public void moFileDiem(File file) {
		bangDiem.layDuLieuFile(file);
	}
	
	// Thêm sinh viên
	public boolean themSinhVien(SinhVien sv) {
		return dsSinhVien.themSinhVien(sv);
	}

	// Thêm học phần
	public boolean themHocPhan(HocPhan hp) {
		return dsHocPhan.themHocPhan(hp);
	}

	// Thêm điểm
	public boolean themDiem(Diem diem) {
		return bangDiem.themDiem(diem);
	}

	// Sửa sinh viên
	public boolean suaSinhVien(SinhVien sv) {
		return dsSinhVien.suaSinhVien(sv);
	}

	// Sửa học phần
	public boolean suaHocPhan(HocPhan hp) {
		return dsHocPhan.suaHocPhan(hp);
	}
	
	// Sửa điểm
	public boolean suaDiem(Diem diem) {
		return bangDiem.suaDiem(diem);
	}

	// Xoá sinh viên
	public boolean xoaSinhVien(int mssv) {
		return dsSinhVien.xoaSinhVien(mssv);
	}
	
	// Xoá học phần
	public boolean xoaHocPhan(String maHP) {
		return dsHocPhan.xoaHocPhan(maHP);
	}
	
	// Xoá điểm
	public boolean xoaDiem(Diem diem) {
		return bangDiem.xoaDiem(diem);
	}
	
	// Tìm sinh viên
	public boolean timSinhVien(String mssv, String hoTen) {
		return dsSinhVien.timSinhVien(mssv, hoTen);
	}
	
	// Tìm học phần
	public boolean timHocPhan(String maHP, String tenHP) {
		return dsHocPhan.timHocPhan(maHP, tenHP);
	}
	
	// Tìm điểm
	public boolean timDiem(Diem diem) {
		return bangDiem.timDiem(diem);
	}
	
	// Xem bảng điểm
	public boolean xemDiem(int mssv) {
		return bangDiem.xemDiem(mssv);
	}
	
	// Lưu dữ liệu sinh viên
	public void luuSinhVien() {
		this.dsSinhVien.luuDuLieuFile(this.dsSinhVien.getFile(), this.dsSinhVien.getDs());
	}
	
	// Lưu dữ liệu học phần
	public void luuHocPhan() {
		this.dsHocPhan.luuDuLieuFile(this.dsHocPhan.getFile(), this.dsHocPhan.getDs());
	}
	
	// Lưu dữ liệu điểm
	public void luuDiem() {
		this.bangDiem.luuDuLieuFile(this.bangDiem.getFile(), this.bangDiem.getDs());
	}
	
	//**Cở sở dữ liệu**//
	
	// Thêm log sinh viên
	public void themLogSinhVien(SinhVien sv) {
		this.logSinhVien.insert(sv);
	}
	
	// Sửa log sinh viên
	public void suaLogSinhVien(SinhVien sv) {
		this.logSinhVien.update(sv);
	}

	// Xoá log sinh viên
	public void xoaLogSinhVien(int mssv) {
		SinhVien sv = new SinhVien(mssv, null, true, null);
		this.logSinhVien.delete(sv);
	}
	
	// Thêm log học phần
	public void themLogHocPhan(HocPhan hp) {
		this.logHocPhan.insert(hp);
	}
	
	// Sửa log học phần
	public void suaLogHocPhan(HocPhan hp) {
		this.logHocPhan.update(hp);
	}

	// Xoá log học phần
	public void xoaLogHocPhan(String maHP) {
		HocPhan hp = new HocPhan(maHP, null);
		this.logHocPhan.delete(hp);
	}
	
	// Thêm log điểm
	public void themLogDiem(Diem diem) {
		this.logDiem.insert(diem);
	}
	
	// Sửa log điểm
	public void suaLogDiem(Diem diem) {
		this.logDiem.update(diem);
	}

	// Xoá log điểm
	public void xoaLogDiem(Diem diem) {
		this.logDiem.delete(diem);
	}
	
	// Lấy dữ liệu CSDL
	public void layDuLieuCSDL() {
		this.dsSinhVien.setDs(logSinhVien.selectAll());
		this.dsHocPhan.setDs(logHocPhan.selectAll());
		this.bangDiem.setDs(logDiem.selectAll());
	}
	
	// Lưu dữ liệu xuống CSDL
	public void luuDuLieuCSDL() {
		this.logSinhVien.luu();
		this.logHocPhan.luu();
		this.logDiem.luu();
	}

}
