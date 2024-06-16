package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import database.JDBCUtil;
import model.SinhVien;

public class SinhVienDAO implements DAOInterface<SinhVien>{
	private ArrayList<PreparedStatement> list_st ;
    private Connection connect;

	// Khởi tạo
	public SinhVienDAO() {
		this.list_st = new ArrayList<PreparedStatement>();
		try {
            this.connect = JDBCUtil.setConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	// Thêm sinh viên
	@Override
	public void insert(SinhVien t) {
		try {			
			String sql = "INSERT INTO SinhVien(MSSV, `Họ và tên`, `Giới tính`, `Ngày sinh`)\n"+
						"VALUES (?,?,?,?);";
			
			PreparedStatement st = connect.prepareStatement(sql);
			st.setInt(1, t.getMssv());
			st.setString(2, t.getHoTen());
			st.setString(3, (t.isGioiTinh()?"Nam":"Nữ"));
			// Chuyển đổi java.sql.Date sang String định dạng yyyy-MM-dd
            Date ngaySinh = t.getNgaySinh();
            String ngaySinhStr = null;
            if (ngaySinh != null) {
            	ngaySinhStr = new SimpleDateFormat("yyyy-MM-dd").format(ngaySinh);
            }
            st.setString(4, ngaySinhStr);
			
			this.list_st.add(st);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Cập nhật sinh viên
	public void update(SinhVien t) {
		try {			
			String sql = "UPDATE sinhvien\n" + 
						"SET\n" +
						"`Họ và tên` = ?, `Giới tính` = ?, `Ngày sinh` = ?" +
						"WHERE MSSV = ?";

			PreparedStatement st = connect.prepareStatement(sql);
			st.setString(1, t.getHoTen());
			st.setString(2, (t.isGioiTinh()?"Nam":"Nữ"));
			// Chuyển đổi java.sql.Date sang String định dạng yyyy-MM-dd
            Date ngaySinh = t.getNgaySinh();
            String ngaySinhStr = null;
            if (ngaySinh != null) {
            	ngaySinhStr = new SimpleDateFormat("yyyy-MM-dd").format(ngaySinh);
            }
            st.setString(3, ngaySinhStr);
			st.setInt(4, t.getMssv());

			this.list_st.add(st);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// Xoá sinh viên
	@Override
	public void delete(SinhVien t) {
		try {			
			String sql = "DELETE FROM sinhvien\n"+
						"WHERE MSSV = ?";

			PreparedStatement st = connect.prepareStatement(sql);
			st.setInt(1, t.getMssv());
			
			this.list_st.add(st);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Lấy tất cả
	@Override
	public ArrayList<SinhVien> selectAll() {
		ArrayList<SinhVien> ketqua = new ArrayList<SinhVien>();
		try {
			Connection connect  = JDBCUtil.setConnection();

			String sql = "SELECT * FROM SinhVien";
			PreparedStatement st = connect.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int mssv = rs.getInt("MSSV");
				String hoTen = rs.getString("Họ và tên");
				boolean gioiTinh = ((rs.getString("Giới tính").equals("Nam"))?true:false);
				Date ngaySinh = null;
                String ngaySinhStr = rs.getString("Ngày sinh");
                if (ngaySinhStr != null) {
                    ngaySinh = Date.valueOf(ngaySinhStr);
                }
				
				SinhVien t = new SinhVien(mssv, hoTen, gioiTinh, ngaySinh);
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
