package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DocumentDecimal extends PlainDocument {

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		// TODO Auto-generated method stub
		//if (str == null)
		//	return;

		char[] digits = new char[str.length()];

		// Copy only digits to buffer; stop when we have enough
		// or when we reach the end of string
		if (digits[0]=='.')
			super.insertString(offs, new String(digits, 0, 1), a);
			
		try {
			int k = Integer.parseInt(new String(digits, 0, 1));
			super.insertString(offs, new String(digits, 0, 1), a);
		} catch (NumberFormatException e) {
		}
	}
}
