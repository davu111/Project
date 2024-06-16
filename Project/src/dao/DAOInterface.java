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
