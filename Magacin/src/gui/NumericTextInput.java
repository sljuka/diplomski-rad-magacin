package gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.PlainDocument;

import numeric.textField.NumericTextField;

public class NumericTextInput extends Input {

	private NumericTextField textField;
	private String label;
	
	public NumericTextInput(String label, int length) {
		textField = new NumericTextField(length, new DecimalFormat("0000.00"));
		this.label = label;
		
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				super.focusLost(e);
				inputChanged(null);
			}
		});
		
	}
	
	@Override
	public String getText() {
		return textField.getText();
	}

	@Override
	public void setUserEditable(boolean b) {
		textField.setEditable(b);
	}

	@Override
	public JComponent getComponent() {
		JPanel pan = new JPanel();
		pan.add(new JLabel(label));
		pan.add(textField);
		return pan;
	}

	@Override
	public void setText(String text) {
		textField.setText(text);
		inputChanged(null);
	}

	@Override
	public void setDocument(PlainDocument p) {

	}

}
