package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class DatePickerComponent extends JPanel {

	private JTextField tfDate;
	private JButton b;
	
	public DatePickerComponent() {
		// TODO Auto-generated constructor stub
		super();
		setLayout(new MigLayout());
		tfDate = new JTextField(8);
		tfDate.setEditable(false);
		b = new JButton("popup");
		add(tfDate, "growx");
		add(b, "grow 0");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tfDate.setText(new DatePicker().setPickedDate());
			}
		});
	}
	
	public String getText() {
		if (tfDate.getText().equals(""))
			return "";
		return tfDate.getText().substring(0, 10);
	}
	
	public void setText(String text) {
		if (text == null || text.equals("")) {
			tfDate.setText("");
			return;
		}
		tfDate.setText(text.substring(0, 10));
	}
	
	public void setEditable(boolean b) {
		tfDate.setEditable(b);
		this.b.setEnabled(b);
	}

}
