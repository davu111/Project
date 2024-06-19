// Name: Duong Anh Vu
// Student ID: 20227187
// Class:  150328
// Project: Chu de 5 - Xay dung chuong trinh quan ly sinh vien
// Date: 21/06/2024
package test;

import javax.swing.UIManager;
import view.Project_view;

public class Project_test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Project_view();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}
