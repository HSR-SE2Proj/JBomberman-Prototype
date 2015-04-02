package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartFrame extends JFrame{
	
	public StartFrame(JPanel panel) {
		setTitle("JBomberman");
//		setSize(650, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(panel);		

		setLocationRelativeTo(null);

		pack();
		setResizable(false);
		setVisible(true);
	}
}
