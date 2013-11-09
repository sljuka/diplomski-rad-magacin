package gui;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.text.PlainDocument;

public class CheckBoxInput extends JCheckBox implements IInput {

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return isSelected()?"True":"False";
	}

	@Override
	public void setUserEditable(boolean b) {
		// TODO Auto-generated method stub
		setEnabled(b);
	}

	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		String firstLetter = text.toLowerCase();
		if(firstLetter.startsWith("t"))
			setSelected(true);
		else
			setSelected(false);
	}

	@Override
	public void setDocument(PlainDocument p) {
		// TODO Auto-generated method stub
		
	}

}
