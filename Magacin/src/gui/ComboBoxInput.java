package gui;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.text.PlainDocument;

public class ComboBoxInput extends JComboBox implements IInput {

	private List<ComboListitem> listItems;
	
	public ComboBoxInput(List<ComboListitem> listItems) {
		this.listItems = listItems;
		for (ComboListitem item : listItems) {
			addItem(item);
		}
	}
	
	@Override
	public String getText() {
		ComboListitem item = (ComboListitem)getSelectedItem();
		return item.value;
	}

	@Override
	public void setUserEditable(boolean b) {
		// TODO Auto-generated method stub
		setEditable(b);
	}

	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		for (ComboListitem item : listItems) {
			if(item.text.equals(text))
				setSelectedItem(item);
		}
	}
	
	public class ComboListitem {
		
		private String text;
		private String value;
		
		public ComboListitem(String text, String value) {
			super();
			this.text = text;
			this.value = value;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return text;
		}
		
	}

	@Override
	public void setDocument(PlainDocument p) {
		// TODO Auto-generated method stub
		
	}

}
