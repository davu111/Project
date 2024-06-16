package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.JDBCUtil;
import model.HocPhan;

public class HocPhanDAO implements DAOInterface<HocPhan>{
	private ArrayList<PreparedStatement> list_st ;
    private Connection connect;

	// Khởi tạo
	public HocPhanDAO() {
		this.list_st = new ArrayList<PreparedStatement>();
		try {
            this.connect = JDBCUtil.setConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	// Thêm học phần
	@Override
	public void insert(HocPhan t) {
		try {			
			String sql = "INSERT INTO HocPhan(maHP, tenHP)\n"+
						"VALUES (?,?);";
			
			PreparedStatement st = connect.prepareStatement(sql);
			st.setString(1, t.getMaHocPhan());
			st.setString(2, t.getTenHocPhan());
			
			this.list_st.add(st);			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	
	// Cập nhật học phần
	@Override
	public void update(HocPhan t) {
		try {				
			String sql = "UPDATE hocphan\n" + 
						"SET\n" +
						"tenHP = ?" +
						"WHERE maHP = ?";

			PreparedStatement st = connect.prepareStatement(sql);
			st.setString(1, t.getTenHocPhan());
			st.setString(2, t.getMaHocPhan());

			this.list_st.add(st);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Xoá học phần
	@Override
	public void delete(HocPhan t) {
		try {				
			String sql = "DELETE FROM hocphan\n"+
						"WHERE maHP = ?";

			PreparedStatement st = connect.prepareStatement(sql);
			st.setString(1, t.getMaHocPhan());
				
			this.list_st.add(st);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Lấy tất cả
	@Override
	public ArrayList<HocPhan> selectAll() {
		ArrayList<HocPhan> ketqua = new ArrayList<HocPhan>();
		try {
			Connection connect  = JDBCUtil.setConnection();
			
			String sql = "SELECT * FROM HocPhan";
			
			PreparedStatement st = connect.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String maHP = rs.getString("maHP");
				String tenHP = rs.getString("tenHP");
				
				HocPhan t = new HocPhan(maHP, tenHP);
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
