package gui;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.PlainDocument;

public class ComboBoxInput extends Input {

	private List<ComboListitem> listItems;
	private String label;
	
	private JComboBox comboBox;
	
	public ComboBoxInput(List<ComboListitem> listItems, String label) {
		this.listItems = listItems;
		this.comboBox = new JComboBox();
		this.label = label;
		for (ComboListitem item : listItems) {
			comboBox.addItem(item);
		}
	}
	
	@Override
	public String getText() {
		ComboListitem item = (ComboListitem)comboBox.getSelectedItem();
		return item.getValue();
	}

	@Override
	public void setUserEditable(boolean b) {
		// TODO Auto-generated method stub
		comboBox.setEnabled(b);
	}

	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		JPanel pan = new JPanel();
		pan.add(new JLabel(label));
		pan.add(comboBox);
		return pan;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		for (ComboListitem item : listItems) {
			String val1 = text.toLowerCase().trim();
			String val2 = item.getValue().toLowerCase().trim();
			if(val1.equals(val2))
				comboBox.setSelectedItem(item);
		}
		inputChanged(null);
	}

	@Override
	public void setDocument(PlainDocument p) {
		// TODO Auto-generated method stub
		
	}

}
