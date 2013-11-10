package gui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class TextInput extends Input {

	String label;
	JTextField textField;
	
	public TextInput(int length, String label, PlainDocument p) {
		textField = new JTextField(length);
		this.label = label;
		if(p!=null)
			textField.setDocument(p);
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
	public void setDocument(PlainDocument p) {
		textField.setDocument(p);
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return textField.getText().equals("");
	}

	@Override
	public String getText() {
		return textField.getText();
	}

	@Override
	public void setText(String text) {
		textField.setText(text);
		inputChanged(null);
	}

}
