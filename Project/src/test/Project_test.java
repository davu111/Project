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
