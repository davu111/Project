// Name: Duong Anh Vu
// Student ID: 20227187
// Class:  150328
// Project: Chu de 5 - Xay dung chuong trinh quan ly sinh vien
// Date: 21/06/2024
package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	// Tạo kết nối
	public static Connection setConnection() {
		Connection c = null;
		try {
			// File chứa CSDL
			Class.forName("org.sqlite.JDBC");
	        String dbFileName = "qlsinhvien.db";
	        File dbFile = new File(dbFileName);
	
	        // Nếu file không tồn tại
	        if (!dbFile.exists()) {
	            createNewDatabase(dbFileName);
	        }
	        
	        // Tạo kết nối
	        return DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return c;
    }
	
	// Tạo cơ sở dữ liệu mới và các bảng cần thiết
    public static void createNewDatabase(String dbFileName) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFileName)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                // Tạo bảng SinhVien nếu chưa tồn tại
                String sqlCreateTable = "CREATE TABLE \"sinhvien\" ( "
                		+ "\"MSSV\" INTEGER NOT NULL, "
                		+ "\"Họ và tên\" VARCHAR(30), "
                		+ "\"Giới tính\" VARCHAR(5), "
                		+ "\"Ngày sinh\" DATE, "
                		+ "PRIMARY KEY(\"MSSV\") )";
                stmt.execute(sqlCreateTable);
                sqlCreateTable = "CREATE TABLE \"hocphan\" ( "
                		+ "\"maHP\" VARCHAR(10) NOT NULL, "
                		+ "\"tenHP\" VARCHAR(50), "
                		+ "PRIMARY KEY(\"maHP\") )";
                stmt.execute(sqlCreateTable);
                sqlCreateTable = "CREATE TABLE \"diemso\" ( "
                		+ "\"MSSV\" INTEGER NOT NULL, "
                		+ "\"maHP\" VARCHAR(10) NOT NULL, "
                		+ "\"diem\" FLOAT, "
                		+ "FOREIGN KEY(\"MSSV\") REFERENCES \"sinhvien\"(\"MSSV\") "
                		+ "ON DELETE CASCADE ON UPDATE CASCADE, "
                		+ "FOREIGN KEY(\"maHP\") REFERENCES \"hocphan\"(\"maHP\") "
                		+ "ON DELETE CASCADE ON UPDATE CASCADE, "
                		+ "PRIMARY KEY(\"MSSV\",\"maHP\") )";
                stmt.execute(sqlCreateTable);
                System.out.println("Database and table created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	// Ngắt kết nối
	public static void disConnection(Connection c) {
			try {
				if (c != null) c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
