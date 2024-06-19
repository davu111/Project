// Name: Duong Anh Vu
// Student ID: 20227187
// Class:  150328
// Project: Chu de 5 - Xay dung chuong trinh quan ly sinh vien
// Date: 21/06/2024
package dao;

import java.util.ArrayList;

public interface DAOInterface<T> {
	// Thêm
	public void insert(T t);

	// Sửa
	public void update(T t);
	
	// Xoá
	public void delete(T t);

	// Lấy tất cả
	public ArrayList<T> selectAll();
	
	// Lưu
	public void luu();
}
