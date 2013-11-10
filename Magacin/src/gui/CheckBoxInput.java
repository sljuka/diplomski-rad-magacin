package gui;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.text.PlainDocument;

public class CheckBoxInput extends Input {

	private JCheckBox checkBox;
	
	public CheckBoxInput() {
		this.checkBox = new JCheckBox();
	}
	
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return checkBox.isSelected()?"True":"False";
	}

	@Override
	public void setUserEditable(boolean b) {
		// TODO Auto-generated method stub
		checkBox.setEnabled(b);
	}

	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		return checkBox;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		String firstLetter = text.toLowerCase();
		if(firstLetter.startsWith("t"))
			checkBox.setSelected(true);
		else
			checkBox.setSelected(false);
		inputChanged(null);
	}

	@Override
	public void setDocument(PlainDocument p) {
		// TODO Auto-generated method stub
		
	}

}
