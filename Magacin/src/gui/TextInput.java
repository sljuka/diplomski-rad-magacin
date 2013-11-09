package gui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class TextInput extends JTextField implements IInput {

	String label;
	
	public TextInput(int length, String label, PlainDocument p) {
		super(length);
		this.label = label;
		if(p!=null)
			super.setDocument(p);
	}
	
	@Override
	public void setUserEditable(boolean b) {
		// TODO Auto-generated method stub
		setEditable(b);
	}

	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		JPanel pan = new JPanel();
		pan.add(new JLabel(label));
		pan.add(this);
		return pan;
	}

	@Override
	public void setDocument(PlainDocument p) {
		// TODO Auto-generated method stub
		super.setDocument(p);
	}

}
