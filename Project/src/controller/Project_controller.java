// Name: Duong Anh Vu
// Student ID: 20227187
// Class:  150328
// Project: Chu de 5 - Xay dung chuong trinh quan ly sinh vien
// Date: 21/06/2024
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Project_view;

public class Project_controller implements ActionListener{
	private Project_view view;
	
	// Khởi tạo
	public Project_controller (Project_view view) {
		this.view = view;
	}

	// Thực hiện chức năng chương trình
	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		
		if (src.equals("Thêm SV")) {
			this.view.themSinhVien();
		}
		else if (src.equals("Thêm HP")) {
			this.view.themHocPhan();
		}
		else if (src.equals("Thêm Điểm")) {
			this.view.themDiem();
		}
		else if (src.equals("Sửa SV")) {
			this.view.suaSinhVien();
		}
		else if (src.equals("Sửa HP")) {
			this.view.suaHocPhan();
		}
		else if (src.equals("Sửa Điểm")) {
			this.view.suaDiem();
		}
		else if (src.equals("Xoá SV")) {
			this.view.xoaSinhVien();
		}
		else if (src.equals("Xoá HP")) {
			this.view.xoaHocPhan();
		}
		else if (src.equals("Xoá Điểm")) {
			this.view.xoaDiem();
		}
		else if (src.equals("Tìm SV")) {
			this.view.timSinhVien();
		}
		else if (src.equals("Tìm HP")) {
			this.view.timHocPhan();
		}
		else if (src.equals("Tìm Điểm")) {
			this.view.timDiem();
		}
		else if (src.equals("Xem bảng điểm")) {
			this.view.xemBangDiem();
		}
		else if (src.equals("New")) {
			this.view.lamMoi();
		}
		else if (src.equals("Open")) {
			this.view.moFile();
		}
		else if (src.equals("Save")) {
			this.view.luuDuLieu();
		}
		else if (src.equals("Refresh")) {
			this.view.taiLai();
		}
		else if (src.equals("Exit")) {
			this.view.thoat();
		}
		else if (src.equals("Open_Data")) {
			this.view.moData();
		}
		else if (src.equals("Save_Data")) {
			this.view.luuData();
		}
	}
}
