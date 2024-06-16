package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.StringJoiner;
import java.sql.Date;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.Project_controller;
import model.Diem;
import model.HocPhan;
import model.Project_model;
import model.SinhVien;

import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Project_view extends JFrame {

	private static final long serialVersionUID = 1L;
	private Project_model model;
	private JPanel contentPane;
	private JTextField mssv_tim;
	private JTable table_sinhVien;
	private JTextField mssv;
	private JTextField hoTen;
	private JTextField ngaySinh;
	private JTextField maHP;
	private JTextField tenHP;
	private JTextField mssvDiem;
	private JTextField maHPDiem;
	private JTextField ketQua;
	private ButtonGroup group_gioiTinh;
	private JRadioButton rdbt_nam;
	private JRadioButton rdbt_nu;
	private JTable table_hocPhan;
	private JTable table_bangDiem;
	private JTable table_in;
	private JLabel lb_tongKet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project_view frame = new Project_view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Project_view() {
		this.model = new Project_model();
		Project_controller ac = new Project_controller(this);
		
		this.setTitle("Chương trình quản lý sinh viên");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Không tự động đóng cửa sổ

        // Thêm WindowListener để xử lý sự kiện đóng cửa sổ
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(
                        Project_view.this,
                        "Bạn có chắc chắn muốn thoát chương trình?",
                        "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmed == JOptionPane.YES_OPTION) {
                    Project_view.this.dispose(); // Đóng cửa sổ
                }
            }
        });
		setBounds(40, 20, 1459, 775);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// In bảng điểm
		JLabel lb_timMssv = new JLabel("Mã sinh viên: ");
		lb_timMssv.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lb_timMssv.setBounds(20, 28, 119, 31);
		contentPane.add(lb_timMssv);
		
		mssv_tim = new JTextField();
		mssv_tim.setBounds(130, 28, 222, 31);
		contentPane.add(mssv_tim);
		mssv_tim.setColumns(10);
		
		JButton bt_xemDiem = new JButton("Xem bảng điểm");
		bt_xemDiem.setBounds(400, 28, 127, 31);
		bt_xemDiem.addActionListener(ac);
		contentPane.add(bt_xemDiem);
		
		// Table sinh viên
		table_sinhVien = new JTable();
		table_sinhVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		DefaultTableModel model_sinhVien = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"MSSV", "H\u1ECD v\u00E0 t\u00EAn", "Gi\u1EDBi t\u00EDnh", "Ng\u00E0y sinh"
				}
		);
		table_sinhVien.setRowHeight(20);
		table_sinhVien.setModel(model_sinhVien);
		
		JScrollPane pane_sinhVien = new JScrollPane(table_sinhVien);
		pane_sinhVien.setBounds(20, 86, 1425, 140);
		contentPane.add(pane_sinhVien);
		
		// Table học phần
		table_hocPhan = new JTable();
		table_hocPhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultTableModel model_hocPhan = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã học phần", "Tên học phần"
				}
		);
		table_hocPhan.setRowHeight(20);
		table_hocPhan.setModel(model_hocPhan);
		
		JScrollPane pane_hocPhan = new JScrollPane(table_hocPhan);
		pane_hocPhan.setBounds(20, 246, 1425, 140);
		contentPane.add(pane_hocPhan);
		
		// Table bảng điểm
		table_bangDiem = new JTable();
		table_bangDiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultTableModel model_diem = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"MSSV", "Mã học phần", "Điểm"
				}
		);
		table_bangDiem.setRowHeight(20);
		table_bangDiem.setModel(model_diem);
		
		JScrollPane pane_bangDiem = new JScrollPane(table_bangDiem);
		pane_bangDiem.setBounds(20, 406, 1425, 140);
		contentPane.add(pane_bangDiem);
		
		// Vùng nhập thông tin sinh viên
		JLabel lb_mssv = new JLabel("MSSV:");
		lb_mssv.setBounds(20, 575, 53, 32);
		contentPane.add(lb_mssv);
		
		mssv = new JTextField();
		mssv.setBounds(71, 575, 103, 32);
		contentPane.add(mssv);
		mssv.setColumns(10);
		
		JLabel lb_hoTen = new JLabel("Họ tên:");
		lb_hoTen.setBounds(200, 575, 53, 32);
		contentPane.add(lb_hoTen);
		
		hoTen = new JTextField();
		hoTen.setColumns(10);
		hoTen.setBounds(245, 575, 103, 32);
		contentPane.add(hoTen);
		
		JLabel lb_ngaySinh = new JLabel("Ngày sinh:");
		lb_ngaySinh.setBounds(565, 575, 53, 32);
		contentPane.add(lb_ngaySinh);
		
		ngaySinh = new JTextField();
		ngaySinh.setColumns(10);
		ngaySinh.setBounds(626, 575, 103, 32);
		contentPane.add(ngaySinh);
		
		JLabel lb_gioiTinh = new JLabel("Giới tính:");
		lb_gioiTinh.setBounds(388, 575, 53, 32);
		contentPane.add(lb_gioiTinh);
		
		rdbt_nam = new JRadioButton("Nam");
		rdbt_nam.setBounds(447, 580, 52, 21);
		contentPane.add(rdbt_nam);
		
		rdbt_nu = new JRadioButton("Nữ");
		rdbt_nu.setBounds(500, 580, 52, 21);
		contentPane.add(rdbt_nu);
		
		group_gioiTinh = new ButtonGroup();
		group_gioiTinh.add(rdbt_nam);
		group_gioiTinh.add(rdbt_nu);
		
		// Vùng nhập thông tin học phần
		JLabel lb_maHP = new JLabel("Mã HP:");
		lb_maHP.setBounds(20, 625, 53, 32);
		contentPane.add(lb_maHP);
		
		maHP = new JTextField();
		maHP.setColumns(10);
		maHP.setBounds(71, 625, 103, 32);
		contentPane.add(maHP);
		
		JLabel lb_tenHP = new JLabel("Tên HP:");
		lb_tenHP.setBounds(200, 625, 53, 32);
		contentPane.add(lb_tenHP);
		
		tenHP = new JTextField();
		tenHP.setColumns(10);
		tenHP.setBounds(245, 625, 103, 32);
		contentPane.add(tenHP);
		
		// Vùng nhập thông tin điểm
		JLabel lb_mssvDiem = new JLabel("MSSV:");
		lb_mssvDiem.setBounds(20, 675, 53, 32);
		contentPane.add(lb_mssvDiem);
		
		mssvDiem = new JTextField();
		mssvDiem.setColumns(10);
		mssvDiem.setBounds(71, 675, 103, 32);
		contentPane.add(mssvDiem);
		
		JLabel lb_maHPDiem = new JLabel("Mã HP:");
		lb_maHPDiem.setBounds(200, 675, 53, 32);
		contentPane.add(lb_maHPDiem);
		
		maHPDiem = new JTextField();
		maHPDiem.setColumns(10);
		maHPDiem.setBounds(245, 675, 103, 32);
		contentPane.add(maHPDiem);
		
		JLabel lb_diem = new JLabel("Điểm:");
		lb_diem.setBounds(388, 675, 53, 32);
		contentPane.add(lb_diem);
		
		ketQua = new JTextField();
		ketQua.setColumns(10);
		ketQua.setBounds(447, 675, 103, 32);
		contentPane.add(ketQua);
		
		// Các chức năng sinh viên
		JButton bt_themSV = new JButton("Thêm SV");
		bt_themSV.setBounds(830, 575, 100, 30);
		bt_themSV.addActionListener(ac);
		contentPane.add(bt_themSV);
		
		JButton bt_suaSV = new JButton("Sửa SV");
		bt_suaSV.setBounds(980, 575, 100, 30);
		bt_suaSV.addActionListener(ac);
		contentPane.add(bt_suaSV);
		
		JButton bt_xoaSV = new JButton("Xoá SV");
		bt_xoaSV.setBounds(1130, 575, 100, 30);
		bt_xoaSV.addActionListener(ac);
		contentPane.add(bt_xoaSV);
		
		JButton bt_timSV = new JButton("Tìm SV");
		bt_timSV.setBounds(1280, 575, 100, 30);
		bt_timSV.addActionListener(ac);
		contentPane.add(bt_timSV);
		
		// Các chức năng học phần
		JButton bt_themHP = new JButton("Thêm HP");
		bt_themHP.setBounds(830, 625, 100, 30);
		bt_themHP.addActionListener(ac);
		contentPane.add(bt_themHP);
		
		JButton bt_suaHP = new JButton("Sửa HP");
		bt_suaHP.setBounds(980, 625, 100, 30);
		bt_suaHP.addActionListener(ac);
		contentPane.add(bt_suaHP);
		
		JButton bt_xoaHP = new JButton("Xoá HP");
		bt_xoaHP.setBounds(1130, 625, 100, 30);
		bt_xoaHP.addActionListener(ac);
		contentPane.add(bt_xoaHP);
		
		JButton bt_timHP = new JButton("Tìm HP");
		bt_timHP.setBounds(1280, 625, 100, 30);
		bt_timHP.addActionListener(ac);
		contentPane.add(bt_timHP);
		
		// Các chức năng điểm
		JButton bt_themDiem = new JButton("Thêm Điểm");
		bt_themDiem.setBounds(830, 675, 100, 30);
		bt_themDiem.addActionListener(ac);
		contentPane.add(bt_themDiem);
		
		JButton bt_suaDiem = new JButton("Sửa Điểm");
		bt_suaDiem.setBounds(980, 675, 100, 30);
		bt_suaDiem.addActionListener(ac);
		contentPane.add(bt_suaDiem);
		
		JButton bt_xoaDiem = new JButton("Xoá Điểm");
		bt_xoaDiem.setBounds(1130, 675, 100, 30);
		bt_xoaDiem.addActionListener(ac);
		contentPane.add(bt_xoaDiem);
		
		JButton bt_timDiem = new JButton("Tìm Điểm");
		bt_timDiem.setBounds(1280, 675, 100, 30);
		bt_timDiem.addActionListener(ac);
		contentPane.add(bt_timDiem);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 560, 1410, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 73, 1410, 2);
		contentPane.add(separator_1);
		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1445, 22);
		contentPane.add(menuBar);
		
		JMenu mn_file = new JMenu("File");
		menuBar.add(mn_file);
		
		JMenuItem item_new = new JMenuItem("New", KeyEvent.VK_N);
		item_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		item_new.addActionListener(ac);
		mn_file.add(item_new);
		
		JMenuItem item_open = new JMenuItem("Open", KeyEvent.VK_O);
		item_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		item_open.addActionListener(ac);
		mn_file.add(item_open);
		
		JMenuItem item_save = new JMenuItem("Save", KeyEvent.VK_S);
		item_save.addActionListener(ac);
		item_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)); 
		mn_file.add(item_save);
		
		JMenuItem item_refresh = new JMenuItem("Refresh", KeyEvent.VK_R);
		item_refresh.addActionListener(ac);
		item_refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK)); 
		mn_file.add(item_refresh);
		
		JSeparator separator_2 = new JSeparator();
		mn_file.add(separator_2);
		
		JMenuItem item_exit = new JMenuItem("Exit", KeyEvent.VK_X);
		item_exit.addActionListener(ac);
		item_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK)); 
		mn_file.add(item_exit);
		
		JMenu mn_more = new JMenu("More");
		menuBar.add(mn_more);
		
		JMenuItem item_openData = new JMenuItem("Open_Data", KeyEvent.VK_T);
		item_openData.addActionListener(ac);
		item_openData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK)); 
		mn_more.add(item_openData);
		
		JMenuItem item_saveData = new JMenuItem("Save_Data", KeyEvent.VK_T);
		item_saveData.addActionListener(ac);
		item_saveData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_DOWN_MASK)); 
		mn_more.add(item_saveData);
		
		this.layDuLieuGoc();
		this.setVisible(true);

	}
	
	// Lấy dữ liệu từ file gốc 
	private void layDuLieuGoc() {
		this.model.moFileSinhVien(new File("SV.bin"));
		this.model.moFileHocPhan(new File("HP.bin"));
		this.model.moFileDiem(new File("Diem.bin"));
		
		this.taiLai(); // Đưa dữ liệu lên bảng hiển thị
	}
	
	// Đưa các vùng nhập về trạng thái rỗng
	private void lamRong() {
		this.mssv.setText("");
		this.hoTen.setText("");
		this.group_gioiTinh.clearSelection();
		this.ngaySinh.setText("");
		this.maHP.setText("");
		this.tenHP.setText("");
		this.mssvDiem.setText("");
		this.maHPDiem.setText("");
		this.ketQua.setText("");
		this.mssv_tim.setText("");
	}
	
	// Chuẩn hoá họ tên
	private String chuanHoaHoTen(String xau) {
		if (xau == null || xau.isEmpty()) {
	        return xau;
	    }
		//Tách chuỗi bằng các ký tự khoảng trắng (khoảng trắng, tab, v.v.).
	    String[] dsTu = xau.split("\\s+");
	    
	    StringJoiner xauChuanHoa = new StringJoiner(" "); 
	    for (String st : dsTu) {
	        if (st.length() > 0) {
	            String chuanHoa = st.substring(0, 1).toUpperCase() + st.substring(1).toLowerCase();
	            xauChuanHoa.add(chuanHoa);
	        } else {
	            xauChuanHoa.add(st);
	        }
	    }
	    return xauChuanHoa.toString().trim(); // Loại bỏ khoảng trắng đầu cuối
	}

	// Thêm sinh viên vào bảng hiển thị
	private void themBangSinhVien(SinhVien sv) {
		DefaultTableModel model_table = (DefaultTableModel) table_sinhVien.getModel();
		
		model_table.addRow(new Object[]{
			sv.getMssv()+"",
			sv.getHoTen(),
			(sv.isGioiTinh()?"Nam":"Nữ"),
			sv.getNgaySinh()
		});
	}
	
	// Sửa sinh viên ở bảng hiển thị
	private void suaBangSinhVien(SinhVien sv) {
		DefaultTableModel model_table = (DefaultTableModel) table_sinhVien.getModel();
		
		for(int i = 0; i < model_table.getRowCount(); i++) {
			int mssv = Integer.valueOf(model_table.getValueAt(i, 0)+"");
			if(mssv == sv.getMssv()) {
				model_table.setValueAt(sv.getHoTen(), i, 1);
				model_table.setValueAt((sv.isGioiTinh()?"Nam":"Nữ"), i, 2);
				model_table.setValueAt(sv.getNgaySinh(), i, 3);
				break;
			}
		}
	}
	
	// Xoá sinh viên khỏi bảng hiển thị
	private void xoaBangSinhVien(int mssv) {
		DefaultTableModel model_table = (DefaultTableModel) table_sinhVien.getModel();
		
		int idx = table_sinhVien.getSelectedRow();
		
		if(idx != -1) model_table.removeRow(idx);
		else {
			for(int i = 0; i < model_table.getRowCount(); i++) {
				int id = Integer.valueOf(model_table.getValueAt(i, 0)+"");
				if(id == mssv) {
					model_table.removeRow(i);
					break;
				}
			}
		}
	}
	
	// Tải lại bảng sinh viên về trạng thái ban đầu
	private void taiLaiBangSinhVien() {
		DefaultTableModel model_table = (DefaultTableModel) table_sinhVien.getModel();
		
		int soDong = table_sinhVien.getRowCount();
		while(soDong > 0) {
			model_table.removeRow(0);
			soDong--;
		}
		
		for(SinhVien sv : this.model.getDsSinhVien().getDs())
			this.themBangSinhVien(sv);
	}
	
	// Tìm thông tin sinh viên trong bảng hiển thị
	private void timBangSinhVien(String mssv, String hoTen) {
		this.taiLaiBangSinhVien(); // Khôi phục dữ liệu cho bảng
		DefaultTableModel model_table = (DefaultTableModel) table_sinhVien.getModel();
		
		int n = mssv.length();
		int m = hoTen.length();
		int soDong = model_table.getRowCount();
		int i = 0;
		while(i < soDong) {
			String mssv_table = model_table.getValueAt(i, 0)+"";
			String hoTen_table = model_table.getValueAt(i, 1)+"";
			if(mssv_table.length() < n || mssv_table.substring(0, n).equals(mssv) == false
			|| hoTen_table.length() < m || hoTen_table.substring(0, m).equalsIgnoreCase(hoTen) == false ) {
				model_table.removeRow(i);
				soDong--;
			}
			else i++;
		}
	}
	
	// Lấy thông tin sinh viên từ bảng hiển thị
	private int laySinhVienTuBang() {
		DefaultTableModel model_table = (DefaultTableModel) table_sinhVien.getModel();
		
		int i = table_sinhVien.getSelectedRow();
		if(i < 0) return -1;
		int mssv = Integer.valueOf(model_table.getValueAt(i, 0)+"");
		
		return mssv;
	}
	
	// Lấy dữ liệu sinh viên được nhập từ bàn phím
	private SinhVien laySinhVienDaNhap() {
		SinhVien sv = null;
		try {
			if(this.mssv.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập MSSV");
				return null;
			}
			int mssv = Integer.valueOf(this.mssv.getText());
			if(mssv < 0) {
				JOptionPane.showMessageDialog(
						this, 
						"Vui lòng nhập MSSV là một dãy số nguyên dương",
						"Lỗi định dạng MSSV!",
						JOptionPane.ERROR_MESSAGE
				);
			}
			else {
				String hoTen = chuanHoaHoTen(this.hoTen.getText());
				boolean gioiTinh = true;
				if(rdbt_nu.isSelected()) gioiTinh = false;
				Date ngaySinh = null;
				if(this.ngaySinh.getText().isEmpty() == false) ngaySinh = Date.valueOf(this.ngaySinh.getText());
				sv = new SinhVien(mssv, hoTen, gioiTinh, ngaySinh);
			}
		} catch (NumberFormatException e) {			
			JOptionPane.showMessageDialog(
					this, 
					"Vui lòng nhập MSSV là một dãy số nguyên dương",
					"Lỗi định dạng MSSV!",
					JOptionPane.ERROR_MESSAGE
			);

		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(
					this, 
					"Vui lòng nhập ngày theo định dạng yyyy-MM-dd",
					"Lỗi định dạng ngày!",
					JOptionPane.ERROR_MESSAGE
			);

		}
		
		return sv;
	}

	// Mở file lấy dữ liệu sinh viên
	private void moFileSinhVien() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			this.model.moFileSinhVien(file);
			this.taiLaiBangSinhVien(); // Hiển thị dữ liệu đã đọc lên bảng
		}
	}
	
	// Lưu dữ liệu sinh viên vào file khác file lấy dữ liệu
	private void chonFileLuuSinhVien() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			this.model.getDsSinhVien().setFile(file);
		}
	}

	// Thêm sinh viên
	public void themSinhVien() {
		SinhVien sv = this.laySinhVienDaNhap();
		if(sv != null) {
			if(this.model.themSinhVien(sv) == false) {
				JOptionPane.showMessageDialog(this, "Sinh viên đã tồn tại!");
			}
			else {
				this.themBangSinhVien(sv);
				this.model.themLogSinhVien(sv);
				this.lamRong();
				JOptionPane.showMessageDialog(this, "Thêm thành công!");
			}
		}
	}
	
	// Sửa sinh viên
	public void suaSinhVien() {
		SinhVien sv = this.laySinhVienDaNhap();
		if(sv != null) {
			if(this.model.suaSinhVien(sv) == false) {
				JOptionPane.showMessageDialog(this, "Sinh viên không tồn tại!");
			}
			else {
				this.suaBangSinhVien(sv);
				this.model.suaLogSinhVien(sv);
				this.lamRong();
				JOptionPane.showMessageDialog(this, "Sửa thành công!");
			}
		}
	}
	
	// Xoá sinh viên
	public void xoaSinhVien() {
		int mssv = -1;

		if(this.mssv.getText().equals("")) {
			// Nếu người dùng không nhập thông tin ở vùng nhập thử xem người dùng có chọn từ bảng hay không
			mssv = this.laySinhVienTuBang();
			if(mssv == -1) JOptionPane.showMessageDialog(
					this,
					"Vui lòng nhập MSSV hoặc chọn từ bảng!",
					"Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			SinhVien sv = this.laySinhVienDaNhap();
			if(sv != null) mssv = sv.getMssv();
		}
		
		if(mssv > 0) {
			// Nếu mssv đã hợp lệ
			if(this.model.xoaSinhVien(mssv) == false) {
				JOptionPane.showMessageDialog(this, "Sinh viên không tồn tại!");
			}
			else {
				int luachon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá sinh viên này?");
				if(luachon == JOptionPane.YES_OPTION) {
					this.xoaBangSinhVien(mssv);
					this.model.xoaLogSinhVien(mssv);
					this.lamRong();
					JOptionPane.showMessageDialog(this, "Xoá thành công!");
				}
			}
		}
	}
	
	// Tìm kiếm sinh viên
	public void timSinhVien() {
		if(this.mssv.getText().equals("") == false) {
			SinhVien sv = this.laySinhVienDaNhap();
			if(sv != null) {
				int mssv = sv.getMssv();
				String hoTen = sv.getHoTen();
				if(this.model.timSinhVien(mssv+"", hoTen) == false) {
					JOptionPane.showMessageDialog(this, "Sinh viên không tồn tại");
				}
				else {
					this.timBangSinhVien(mssv+"", this.hoTen.getText());
					this.lamRong();
				}
			}
		}
		else if(this.hoTen.getText().equals("") == false) {
			String hoTen = this.hoTen.getText();
			if(this.model.timSinhVien("",hoTen) == false) {
				JOptionPane.showMessageDialog(this, "Sinh viên không tồn tại");
			}
			else {
				this.timBangSinhVien("", hoTen);
				this.lamRong();
			}
		}
		else this.taiLaiBangSinhVien();
	}
	
	// Chuẩn hoá tên học phần
	private String chuanHoaHocPhan(String tenHP) {
		if (tenHP == null || tenHP.isEmpty()) {
            return tenHP;
        }
        // Chuyển đổi chuỗi thành chữ thường trước
        String chuThuong = tenHP.toLowerCase();
        // Chuyển đổi chữ cái đầu tiên của chuỗi thành chữ hoa
        String chuDau = chuThuong.substring(0, 1).toUpperCase();
        // Kết hợp lại và trả về kết quả
        if (tenHP.length() > 1) {
            return (chuDau + chuThuong.substring(1)).trim();
        } else {
            return chuDau.trim();
        }
    }
	
	// Thêm học phần vào bảng hiển thị
	private void themBangHocPhan(HocPhan hp) {
		DefaultTableModel model_table = (DefaultTableModel) table_hocPhan.getModel();
		
		model_table.addRow(new Object[] {
				hp.getMaHocPhan(),
				hp.getTenHocPhan()
		});
	}
	
	// Sửa học phần ở bảng hiển thị
	private void suaBangHocPhan(HocPhan hp) {
		DefaultTableModel model_table = (DefaultTableModel) table_hocPhan.getModel();
		
		for(int i = 0; i < model_table.getRowCount(); i++) {
			String maHP = model_table.getValueAt(i, 0)+"";
			if(maHP.equals(hp.getMaHocPhan())){
				model_table.setValueAt(hp.getTenHocPhan(), i, 1);
			}
		}
	}
	
	// Xoá học phần khỏi bảng hiển thị
	private void xoaBangHocPhan(String maHP) {
		DefaultTableModel model_table = (DefaultTableModel) table_hocPhan.getModel();
		
		int idx = table_hocPhan.getSelectedRow();
		
		if(idx != -1) model_table.removeRow(idx);
		else {
			for(int i = 0; i < model_table.getRowCount(); i++) {
				String id = model_table.getValueAt(i, 0)+"";
				if(id.equals(maHP)) {
					model_table.removeRow(i);
					break;
				}
			}
		}
	}
	
	// Tải lại bảng học phần về trạng thái ban đầu
	private void taiLaiBangHocPhan() {
		DefaultTableModel model_table = (DefaultTableModel) table_hocPhan.getModel();
		
		int soDong = table_hocPhan.getRowCount();
		while(soDong > 0) {
			model_table.removeRow(0);
			soDong--;
		}
		
		for(HocPhan hp : this.model.getDsHocPhan().getDs())
			this.themBangHocPhan(hp);
	}
	
	// Tìm thông tin học phần trong bảng hiển thị
	private void timBangHocPhan(String maHP, String tenHP) {
		this.taiLaiBangHocPhan(); // Khôi phục dữ liệu cho bảng
		DefaultTableModel model_table = (DefaultTableModel) table_hocPhan.getModel();

		int n = maHP.length();
		int m = tenHP.length();
		int soDong = model_table.getRowCount();
		int i = 0;
		while(i < soDong) {
			String maHP_table = model_table.getValueAt(i, 0)+"";
			String tenHP_table = model_table.getValueAt(i, 1)+"";
			if(maHP_table.length() < n || maHP_table.substring(0, n).equals(maHP) == false
			|| tenHP_table.length() < m || tenHP_table.substring(0, m).equalsIgnoreCase(tenHP) == false) {
				model_table.removeRow(i);
				soDong--;
			}
			else i++;
		}
	}
	
	// Lấy thông tin học phần từ bảng hiển thị
	private String layHocPhanTuBang() {
		DefaultTableModel model_table = (DefaultTableModel) table_hocPhan.getModel();
		
		int i = table_hocPhan.getSelectedRow();
		if(i < 0) return "";
		String maHP = model_table.getValueAt(i, 0)+"";
		
		return maHP;
	}
	
	// Lấy dữ liệu học phần được nhập từ bàn phím
	private HocPhan layHocPhanDaNhap() {
		HocPhan hp = null;
		try {
			if(this.maHP.getText().equals("") || this.maHP.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã học phần");
				return null;
			}
			String maHP = this.maHP.getText().toUpperCase().trim();
			String tenHP = chuanHoaHocPhan(this.tenHP.getText().trim());
			hp = new HocPhan(maHP, tenHP);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					this, 
					"Vui lòng nhập Mã HP hợp lệ",
					"Lỗi định dạng Mã HP!",
					JOptionPane.ERROR_MESSAGE
			);		
			}
		
		return hp;
	}
	
	// Mở file chứa dữ liệu học phần
	private void moFileHocPhan() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			this.model.moFileHocPhan(file);
			this.taiLaiBangHocPhan(); // Hiển thị dữ liệu đã đọc lên bảng
		}
	}
	
	// Lưu dữ liệu học phần vào file khác file lấy dữ liệu
	private void chonFileLuuHocPhan() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			this.model.getDsHocPhan().setFile(file);
		}
	}

	// Thêm học phần
	public void themHocPhan() {
		HocPhan hp = this.layHocPhanDaNhap();
		if(hp != null) {
			if(this.model.themHocPhan(hp) == false) {
				JOptionPane.showMessageDialog(this, "Học phần đã tồn tại!");
			}
			else {
				this.themBangHocPhan(hp);
				this.model.themLogHocPhan(hp);
				this.lamRong();
				JOptionPane.showMessageDialog(this, "Thêm thành công!");
			}
		}
	}
	
	// Sửa học phần
	public void suaHocPhan() {
		HocPhan hp = this.layHocPhanDaNhap();
		if(hp != null) {
			if(this.model.suaHocPhan(hp) == false) {
				JOptionPane.showMessageDialog(this, "Học phần không tồn tại!");
			}
			else {
				this.suaBangHocPhan(hp);
				this.model.suaLogHocPhan(hp);
				this.lamRong();
				JOptionPane.showMessageDialog(this, "Sửa thành công!");
			}
		}
	}
	
	// Xoá học phần
	public void xoaHocPhan() {
		String maHP = "";

		if(this.maHP.getText().equals("")) {
			// Nếu người dùng không nhập thông tin ở vùng nhập thử xem người dùng có chọn từ bảng hay không
			maHP = this.layHocPhanTuBang();
			if(maHP.equals("")) JOptionPane.showMessageDialog(
					this,
					"Vui lòng nhập Mã học phần hoặc chọn từ bảng!",
					"Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			HocPhan hp = this.layHocPhanDaNhap();
			maHP = hp.getMaHocPhan();
		}
		
		if(maHP.equals("") == false) {
			// Nếu mã học phần đã hợp lệ
			if(this.model.xoaHocPhan(maHP) == false) {
				JOptionPane.showMessageDialog(this, "Học phần không tồn tại!");
			}
			else {
				int luachon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá học phần này?");
				if(luachon == JOptionPane.YES_OPTION) {
					this.xoaBangHocPhan(maHP);
					this.model.xoaLogHocPhan(maHP);
					this.lamRong();
					JOptionPane.showMessageDialog(this, "Xoá thành công!");
				}
			}
		}
	}
	
	// Tìm kiếm học phần
	public void timHocPhan() {
		if(this.maHP.getText().equals("") == false) {
			HocPhan hp = this.layHocPhanDaNhap();
			if(hp != null) {
				String maHP = hp.getMaHocPhan();
				String tenHP = hp.getTenHocPhan();
				if(this.model.timHocPhan(maHP, tenHP) == false) {
					JOptionPane.showMessageDialog(this, "Học phần không tồn tại");
				}
				else {
					this.timBangHocPhan(maHP, tenHP);
					this.lamRong();
				}
			}
		}
		else if(this.tenHP.getText().equals("") == false) {
			String tenHP = this.tenHP.getText();
			if(this.model.timHocPhan("",tenHP) == false) {
				JOptionPane.showMessageDialog(this, "Học phần không tồn tại");
			}
			else {
				this.timBangHocPhan("",tenHP);
				this.lamRong();
			}
		}
		else this.taiLaiBangHocPhan();
	}
	
	// Thêm điểm vào bảng hiển thị
	private void themBangDiem(Diem diem) {
		DefaultTableModel model_table = (DefaultTableModel) table_bangDiem.getModel();
		
		model_table.addRow(new Object[] {
				diem.getMssv(),
				diem.getMaHocPhan(),
				diem.getDiem()+""
		});
	}
	
	// Sửa điểm ở bảng hiển thị
	private void suaBangDiem(Diem diem) {
		DefaultTableModel model_table = (DefaultTableModel) table_bangDiem.getModel();
		
		for(int i = 0; i < model_table.getRowCount(); i++) {
			int mssv = Integer.valueOf(model_table.getValueAt(i, 0)+"");
			String maHP = model_table.getValueAt(i, 1)+"";
			if(mssv == diem.getMssv() && maHP.equals(diem.getMaHocPhan())) {
				model_table.setValueAt(diem.getDiem()+"", i, 2);
			}
		}
	}
	
	// Xoá điểm khỏi bảng hiển thị
	private void xoaBangDiem(Diem diem) {
		DefaultTableModel model_table = (DefaultTableModel) table_bangDiem.getModel();
		
		int idx = table_bangDiem.getSelectedRow();
		
		if(idx != -1) model_table.removeRow(idx);
		else {
			for(int i = 0; i < model_table.getRowCount(); i++) {
				int mssv = Integer.valueOf(model_table.getValueAt(i, 0)+"");
				String maHP = model_table.getValueAt(i, 1)+"";
				if(mssv == diem.getMssv() && maHP.equals(diem.getMaHocPhan())) {
					model_table.removeRow(i);
					break;
				}
			}
		}
	}
	
	// Tải lại bảng điểm về trạng thái ban đầu
	private void taiLaiBangDiem() {
		DefaultTableModel model_table = (DefaultTableModel) table_bangDiem.getModel();
		
		int soDong = table_bangDiem.getRowCount();
		while(soDong > 0) {
			model_table.removeRow(0);
			soDong--;
		}
		
		for(Diem diem : this.model.getBangDiem().getDs())
			this.themBangDiem(diem);
	}
	
	// Tìm thông tin điểm trong bảng hiển thị
	private void timBangDiem(Diem diem) {
		this.taiLaiBangDiem(); // Khôi phục dữ liệu cho bảng
		DefaultTableModel model_table = (DefaultTableModel) table_bangDiem.getModel();

		int soDong = model_table.getRowCount();
		while(soDong > 1) {
			for(int i = 0; i < soDong; i++) {
				if(Integer.valueOf(model_table.getValueAt(i, 0)+"") != diem.getMssv() 
				|| (model_table.getValueAt(i, 1)+"").equals(diem.getMaHocPhan()) == false){
					model_table.removeRow(i);
					soDong--;
				}
			}
		}
	}
	
	// Lấy thông tin điểm từ bảng hiển thị
	private Diem layDiemTuBang() {
		DefaultTableModel model_table = (DefaultTableModel) table_bangDiem.getModel();
		
		int i = table_bangDiem.getSelectedRow();
		if(i < 0) return null;
		int mssv = Integer.valueOf(model_table.getValueAt(i, 0)+"");
		String maHP = model_table.getValueAt(i, 1)+"";
		
		Diem diem = new Diem(maHP, mssv, 0);
		
		return diem;
	}
	
	// Lấy dữ liệu điểm mà người dùng đã nhập từ bàn phím
	private Diem layDiemDaNhap() {
		Diem diem = null;
		try {
			if(this.mssvDiem.getText().equals("") || this.maHPDiem.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ MSSV và Mã học phần");
				return null;
			}
			
			int mssv = Integer.valueOf(this.mssvDiem.getText());
			String maHP = this.maHPDiem.getText().toUpperCase().trim();
			float kq = 0;
			if(ketQua.getText().equals("") == false) kq = Float.valueOf(ketQua.getText());
			if(mssv < 0 || kq < 0) {
				JOptionPane.showMessageDialog(
						this, 
						"Vui lòng nhập MSSV, Điểm số hợp lệ",
						"Lỗi định dạng!",
						JOptionPane.ERROR_MESSAGE
				);	
			}
			else {
				diem = new Diem(maHP, mssv, kq);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(
					this, 
					"Vui lòng nhập MSSV, Điểm số là một dãy số nguyên dương",
					"Lỗi định dạng!",
					JOptionPane.ERROR_MESSAGE
			);	
		}
		return diem;
	}
	
	// Mở file chứa dữ liệu điểm
	private void moFileDiem() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			this.model.moFileDiem(file);
			this.taiLaiBangDiem(); // Hiển thị dữ liệu đã đọc lên bảng
		}
	}
	
	// Lưu dữ liệu điểm vào file khác file lấy dữ liệu
	private void chonFileLuuDiem() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			this.model.getBangDiem().setFile(file);
		}
	}

	// Thêm điểm
	public void themDiem() {
		Diem diem = this.layDiemDaNhap();
		if(diem != null) {
			if(this.model.themDiem(diem) == false) {
				JOptionPane.showMessageDialog(this, "Điểm đã tồn tại!");
			}
			else {
				this.themBangDiem(diem);
				this.model.themLogDiem(diem);
				this.lamRong();
				JOptionPane.showMessageDialog(this, "Thêm thành công!");
			}
		}
	}
	
	// Sửa điểm
	public void suaDiem() {
		Diem diem = this.layDiemDaNhap();
		if(diem != null) {
			if(this.model.suaDiem(diem) == false) {
				JOptionPane.showMessageDialog(this, "Điểm không tồn tại!");
			}
			else {
				this.suaBangDiem(diem);
				this.model.suaLogDiem(diem);
				this.lamRong();
				JOptionPane.showMessageDialog(this, "Sửa thành công!");
			}
		}
	}
	
	// Xoá điểm
	public void xoaDiem() {
		Diem diem = null;
		
		if(this.mssvDiem.getText().equals("") && this.maHPDiem.getText().equals("")) {
			// Nếu người dùng không nhập thông tin ở vùng nhập thử xem người dùng có chọn từ bảng hay không
			diem = this.layDiemTuBang();
			if(diem == null) JOptionPane.showMessageDialog(
					this,
					"Vui lòng nhập MSSV, Mã học phần hoặc chọn từ bảng!",
					"Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
		else if(this.mssvDiem.getText().equals("") || this.maHPDiem.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập MSSV, Mã học phần hoặc chọn từ bảng!",
					"Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		else diem = this.layDiemDaNhap();
		
		if(diem != null) {
			// Nếu điểm đã hợp lệ
			if(this.model.xoaDiem(diem) == false) {
				JOptionPane.showMessageDialog(this, "Điểm không tồn tại!");
			}
			else {
				int luachon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá điểm này?");
				if(luachon == JOptionPane.YES_OPTION) {
					this.xoaBangDiem(diem);
					this.model.xoaLogDiem(diem);
					this.lamRong();
					JOptionPane.showMessageDialog(this, "Xoá thành công!");
				}
			}
		}
	}
	
	// Tìm kiếm điểm
	public void timDiem() {
		if(this.mssvDiem.getText().equals("") == false && this.maHPDiem.getText().equals("") == false) {
			Diem diem = this.layDiemDaNhap();
			if(diem != null) {
				if(this.model.timDiem(diem) == false) JOptionPane.showMessageDialog(this, "Điểm không tồn tại");
				else {
					this.timBangDiem(diem);
					this.lamRong();
				}
			}
		}
		else if(this.mssvDiem.getText().equals("") && this.maHPDiem.getText().equals("")) this.taiLaiBangDiem();
		else JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ MSSV và Mã học phần");
	}
	
	// Cửa sổ giao diện bảng điểm
	private void hienThiCuaSoBangDiem(int mssv) {
		JFrame frame_in = new JFrame("Bảng điểm");
		frame_in.setSize(500, 500);
		frame_in.setLocationRelativeTo(null);
		frame_in.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame_in.getContentPane().setLayout(new BorderLayout());
		
		// Bảng điểm
		table_in = new JTable();
		table_in.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultTableModel model_in = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã học phần", "Tên học phần", "Điểm tổng kết", "Điểm chữ", "Điểm quy đổi"
				}
		);
		table_in.setModel(model_in);
		TableRowSorter<DefaultTableModel> sorter_in = new TableRowSorter<>(model_in);
		sorter_in.toggleSortOrder(0);
		table_in.setRowSorter(sorter_in);
		table_in.setRowHeight(20);
		
		JScrollPane pane_in = new JScrollPane(table_in);
		frame_in.getContentPane().add(pane_in, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2,1));
		
		// Điểm tổng kết và nút "In"
		lb_tongKet = new JLabel();
		JButton bt_in = new JButton("In");
		bt_in.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inBangDiem(); // Khi nhấn vào nút "In" sẽ thực hiện chức năng in
			}
		});
		southPanel.add(lb_tongKet);
		southPanel.add(bt_in);
		frame_in.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		this.layDuLieuIn(mssv);
		frame_in.setVisible(true);
	}
	
	// Lấy tên học phần dựa trên mã học phần
	private String layTenHocPhan(String maHP) {
		for(HocPhan hp : this.model.getDsHocPhan().getDs())
			if(hp.getMaHocPhan().equals(maHP)) {
				return hp.getTenHocPhan();
			}
		return null;
	}
	
	// Quy đổi điểm chữ từ điểm thang 10
	private String quyDoiDiemChu(double diem) {
		if(diem <= 3.9) return "F";
		else if(diem <= 4.9) return "D";
		else if(diem <= 5.4) return "D+";
		else if(diem <= 6.4) return "C";
		else if(diem <= 6.9) return "C+";
		else if(diem <= 7.9) return "B";
		else if(diem <= 8.4) return "B+";
		else if(diem <= 9.4) return "A";
		return "A+";
	}
	
	// Quy đổi điểm thang 4 từ điểm thang 10
	private double quyDoiDiemThang4(double diem) {
		if(diem <= 3.9) return 0;
		else if(diem <= 4.9) return 1;
		else if(diem <= 5.4) return 1.5;
		else if(diem <= 6.4) return 2.0;
		else if(diem <= 6.9) return 2.5;
		else if(diem <= 7.9) return 3;
		else if(diem <= 8.4) return 3.5;
		return 4;
	}
	
	// Xếp loại sinh viên dựa trên điểm thang 4
	private String xepLoaiSinhVien(double diem) {
		if(diem < 1) return "Kém";
		else if(diem <= 1.49) return "Yếu";
		else if(diem <= 1.99) return "TB yếu";
		else if(diem <= 2.49) return "Trung bình";
		else if(diem <= 3.19) return "Khá";
		else if(diem <= 3.59) return "Giỏi";
		return "Xuất sắc";
	}
	
	// Tính toán điểm tổng kết, điểm quy đổi, điểm chữ và xếp loại
	private void layDuLieuIn(int mssv) {
		DefaultTableModel model_table = (DefaultTableModel) table_in.getModel();
		double diemThang10 = 0;
		double diemThang4 = 0;
		int dem = 0;	
			
		for(Diem diem : this.model.getBangDiem().getDs())
		if(diem.getMssv() == mssv) {
			model_table.addRow(new Object[] {
			diem.getMaHocPhan()+"",
			this.layTenHocPhan(diem.getMaHocPhan()),
			diem.getDiem()+"",
			this.quyDoiDiemChu(diem.getDiem()),
			this.quyDoiDiemThang4(diem.getDiem())
		});
			diemThang10 += diem.getDiem();
			diemThang4 += this.quyDoiDiemThang4(diem.getDiem());
			dem++;
		}
		
		DecimalFormat df = new DecimalFormat("#.##");
		diemThang10 = diemThang10/dem;
		diemThang4 = diemThang4/dem;
		lb_tongKet.setText("Điểm tổng kết: " + df.format(diemThang10) + "   Điểm quy đổi: " + df.format(diemThang4) 
							+ "   Xếp loại:" + this.xepLoaiSinhVien(diemThang4));
	}
	
	// Xem bảng điểm dựa trên mã số sinh viên
	public void	xemBangDiem() {
		try {
			int mssv = Integer.valueOf(this.mssv_tim.getText());
			
			if(mssv < 0) {
				JOptionPane.showMessageDialog(
						this, "Vui lòng nhập MSSV là một dãy số nguyên dương!",
						"Lỗi định đạng MSSV", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if(this.model.xemDiem(mssv) == false) JOptionPane.showMessageDialog(this, "Sinh viên chưa có điểm!");
				else this.hienThiCuaSoBangDiem(mssv);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					this, "Vui lòng nhập MSSV là một dãy số nguyên dương!",
					"Lỗi định đạng MSSV", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// In bảng điểm dưới dạng file pdf
	private void inBangDiem() {
		MessageFormat header = new MessageFormat("Bảng điểm học phần");
		MessageFormat footer = new MessageFormat("");
		try {
			PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
			set.add(OrientationRequested.PORTRAIT);
			table_in.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "In thất bại", "In bảng điểm", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Làm mới chương trình
		public void lamMoi() {
			this.model = new Project_model();
			this.taiLai();
	}
	
	// Mở file lấy dữ liệu
	public void moFile() {
		this.moFileSinhVien();
		this.moFileHocPhan();
		this.moFileDiem();
	}
	
	// Lưu dữ liệu sinh viên
	private void luuSinhVien() {
		int luachon = JOptionPane.showConfirmDialog(
				this, 
				"Bạn có chắc chắn muốn lưu vào file hiện tại?",
				"Lưu sinh viên",
				JOptionPane.YES_NO_CANCEL_OPTION
				);
		if (luachon == JOptionPane.NO_OPTION ||
			this.model.getDsSinhVien().getFile() == null) this.chonFileLuuSinhVien();	
		if (luachon != JOptionPane.CANCEL_OPTION) this.model.luuSinhVien();
	}
	
	// Lưu dữ liệu học phần
	private void luuHocPhan() {
		int luachon = JOptionPane.showConfirmDialog(
				this, 
				"Bạn có chắc chắn muốn lưu vào file hiện tại?",
				"Lưu học phần",
				JOptionPane.YES_NO_CANCEL_OPTION
				);
		if (luachon == JOptionPane.NO_OPTION ||
			this.model.getDsHocPhan().getFile() == null) this.chonFileLuuHocPhan();	
		if (luachon != JOptionPane.CANCEL_OPTION) this.model.luuHocPhan();
	}
	
	// Lưu dữ liệu điểm
	private void luuDiem() {
		int luachon = JOptionPane.showConfirmDialog(
				this, 
				"Bạn có chắc chắn muốn lưu vào file hiện tại?",
				"Lưu điểm",
				JOptionPane.YES_NO_CANCEL_OPTION
				);
		if (luachon == JOptionPane.NO_OPTION ||
			this.model.getBangDiem().getFile() == null) this.chonFileLuuDiem();	
		if (luachon != JOptionPane.CANCEL_OPTION) this.model.luuDiem();
	}
	
	// Lưu dữ liệu xuống file
	public void luuDuLieu() {
		this.luuSinhVien();
		this.luuHocPhan();
		this.luuDiem();
	}
	
	// Tải lại các bảng
	public void taiLai() {
		this.taiLaiBangSinhVien();
		this.taiLaiBangHocPhan();
		this.taiLaiBangDiem();
	}

	// Thoát khỏi chương trình
	public void thoat() {
		int luachon = JOptionPane.showConfirmDialog(
				this, 
				"Bạn có chắc chắn muốn thoát chương trình?",
				"Thoát chương trình",
				JOptionPane.YES_NO_OPTION
				);
		if (luachon == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	// Lấy dữ liệu từ CSDL
	public void moData() {
		this.model.layDuLieuCSDL();
		this.taiLai();
	}
	
	// Lưu dữ liệu xuống CSDL
	public void luuData() {
		int luachon = JOptionPane.showConfirmDialog(
			this, 
			"Bạn có chắc chắn muốn lưu vào CSDL?",
			"Lưu",
			JOptionPane.YES_NO_OPTION
			);
		if (luachon == JOptionPane.YES_OPTION) this.model.luuDuLieuCSDL();	
	}
}
