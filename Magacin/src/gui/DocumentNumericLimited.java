package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DocumentNumericLimited extends PlainDocument {

	private int limit;

	public DocumentNumericLimited(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		// TODO Auto-generated method stub
		if (str == null)
			return;

		// How many spaces are free?
		int available = limit - getLength();

		// If full already, quit
		if (available <= 0)
			return;

		char[] digits = new char[str.length()];
		int count = 0;

		// Copy only digits to buffer; stop when we have enough
		// or when we reach the end of string
		for (; count < str.length() && count < available; count++) {
			char ch = str.charAt(count);
			if (Character.isDigit(ch)) {
				digits[count] = ch;
			}
		}
		// Insert the number of digits copied
		try {
			int k = Integer.parseInt(new String(digits, 0, count));
			super.insertString(offs, new String(digits, 0, count), a);
		} catch (NumberFormatException e) {
			
		}
	}

}
