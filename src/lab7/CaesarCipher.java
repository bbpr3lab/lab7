package lab7;


public class CaesarCipher {

	private int n;

	public CaesarCipher(char c) {
		this.setOffset(c);
	}
	
	private String codec(String string, boolean encode) {
		char[] chars = string.toUpperCase().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isLetter(chars[i]))
				chars[i] = (char) (encode ? shift(chars[i]) : unshift(chars[i]));
		}
		
		return new String(chars);
	}
	
	public String encode(String string) {
		return codec(string, true)	;
	}
	
	public String decode(String string) {
		return codec(string, false);
	}
	
	public void setOffset(char c) {
		char uc = Character.toUpperCase(c);
		int cp = String.valueOf(uc).codePointAt(0);
		this.n = cp - 65;
	}
	
	private int shift(int i) {
		return (((i - 65) + n) % 26) + 65;
	}
	
	private int unshift(int i) {
		int j = ((i - 65) - n) % 26;
		if (j < 0)
			j += 26;
		return j + 65;
	}
}
