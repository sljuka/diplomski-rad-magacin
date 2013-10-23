package gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class StatusBar extends JPanel {
	private JLabel status;
	public StatusBar(){
		setBackground(new Color(180,180,180));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,new Color(200,200,200),new Color(230,230,230)));
		status = new JLabel("Magacin");
		add(status);
	}
	
	public void setText(String text){
		status.setText(text);
	}
}
