// Name: Duong Anh Vu
// Student ID: 20227187
// Class:  150328
// Project: Chu de 5 - Xay dung chuong trinh quan ly sinh vien
// Date: 21/06/2024
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Diem;

public class DiemDAO implements DAOInterface<Diem>{
	private ArrayList<PreparedStatement> list_st ;
    private Connection connect;

	// Khởi tạo
	public DiemDAO() {
		this.list_st = new ArrayList<PreparedStatement>();
		try {
            this.connect = JDBCUtil.setConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	// Thêm điểm
	@Override
	public void insert(Diem t) {
		try {			
			String sql = "INSERT INTO DiemSo(MSSV, maHP, diem)\n"+
						"VALUES (?,?,?);";
			
			PreparedStatement st = connect.prepareStatement(sql);
			st.setInt(1, t.getMssv());
			st.setString(2, t.getMaHocPhan());
			st.setFloat(3, t.getDiem());
			
			this.list_st.add(st);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Cập nhật điểm
	@Override
	public void update(Diem t) {
		try {				
			String sql = "UPDATE diemso\n" + 
						"SET\n" +
						"diem = ?" +
						"WHERE maHP = ? AND MSSV = ?";
			
			PreparedStatement st = connect.prepareStatement(sql);
			st.setFloat(1, t.getDiem());
			st.setString(2, t.getMaHocPhan());
			st.setInt(3, t.getMssv());

			this.list_st.add(st);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Xoá điểm
	@Override
	public void delete(Diem t) {
		try {
			String sql = "DELETE FROM diemso\n"+
						"WHERE maHP = ? AND MSSV = ?";

			PreparedStatement st = connect.prepareStatement(sql);
			st.setString(1, t.getMaHocPhan());
			st.setInt(2, t.getMssv());
				
			this.list_st.add(st);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Lấy tất cả
	@Override
	public ArrayList<Diem> selectAll() {
		ArrayList<Diem> ketqua = new ArrayList<Diem>();
		try {
			Connection connect  = JDBCUtil.setConnection();
			
			String sql = "SELECT * FROM DiemSo";
			
			PreparedStatement st = connect.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int mssv = rs.getInt("MSSV");
				String maHP = rs.getString("maHP");
				Float diem = rs.getFloat("diem");
				
				Diem t = new Diem(maHP, mssv, diem);
				ketqua.add(t);
			}
			
			JDBCUtil.disConnection(connect);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}
	
	// Lưu
	@Override
	public void luu() {
		try {
			for (PreparedStatement st : this.list_st) {
				st.executeUpdate();
			}
			this.list_st.clear();
			JDBCUtil.disConnection(connect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
