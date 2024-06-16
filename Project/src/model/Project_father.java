package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Project_father<T> {
	@SuppressWarnings("unchecked")

	// Lấy dữ liệu từ file
	// Input: Biến file kiểu File chứa dữ liệu danh sách kiểu T
	// Output: ArrayList<T> là danh sách T đọc được từ file truyền vào
	public ArrayList<T> layFile(File file) {
		ArrayList<T> ds = new ArrayList<T>();
		try {
			// Nếu file tồn tại
			InputStream is = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(is);
			try {
				while(true) {
					T t = (T) ois.readObject();
					ds.add(t);
				} 
			} catch (EOFException e) {
				}
			ois.close();
		} catch (Exception e) {
			// Nếu file không tồn tại
			e.printStackTrace();
		}
		return ds;
	}
		
	// Lưu dữ liệu xuống file
	// Input: Biến file kiểu File là file muốn lưu dữ liệu xuống
	//		  Biến ds kiểu ArrayList<T> là danh sách chứa dữ liệu cần lưu
	// Output: Dữ liệu được lấy từ danh sách truyền vào ghi xuống file truyền vào
	public boolean luuDuLieuFile(File file, ArrayList<T> ds) {
		try {
			// Nếu file tồn tại
			OutputStream os = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			for (T t : ds) {
				oos.writeObject(t);
			}
			
			oos.flush();
			oos.close();
			return true;
		} catch (Exception e) {
			// Nếu file không tồn tại
			return false;
		}
	}
}
