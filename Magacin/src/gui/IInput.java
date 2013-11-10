package gui;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.text.PlainDocument;

public interface IInput {
	
	public String getText();
	
	public void setUserEditable(boolean b);
	
	public JComponent getComponent();
	
	public void setText(String text);
	
	public void setDocument(PlainDocument p);
	
	public boolean isEmpty();
	
}
