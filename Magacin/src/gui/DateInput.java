package gui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.PlainDocument;

public class DateInput extends Input {

	private DatePickerComponent datePicker;
	private String label;
	
	public DateInput(String label) {
		datePicker = new DatePickerComponent();
		this.label = label;
	}
	
	@Override
	public String getText() {
		return datePicker.getText();
	}

	@Override
	public void setUserEditable(boolean b) {
		datePicker.setEditable(b);
	}

	@Override
	public JComponent getComponent() {
		JPanel pan = new JPanel();
		pan.add(new JLabel(label));
		pan.add(datePicker);
		return pan;
	}

	@Override
	public void setText(String text) {
		datePicker.setText(text);
		inputChanged(null);
	}

	@Override
	public void setDocument(PlainDocument p) {

	}

}
