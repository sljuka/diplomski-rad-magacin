package gui;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.text.PlainDocument;

public abstract class Input implements IInput {

	private Set<IInputChangeListener> listeners;
	
	public Input() {
		listeners = new HashSet<IInputChangeListener>(); 
	}
	
	@Override
	public abstract String getText();
	
	@Override
	public abstract void setUserEditable(boolean b);

	@Override
	public abstract JComponent getComponent();

	@Override
	public abstract void setText(String text);

	@Override
	public abstract void setDocument(PlainDocument p);
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	public void inputChanged(Object message) {
		for (IInputChangeListener item : listeners) {
			item.inputChanged(message);
		}
	}
	
	public void addListener(IInputChangeListener listener) {
		listeners.add(listener);
	}

}
