package main;

import java.util.regex.Pattern;

public class Braille {
	protected BrailleMap brailleMap = new BrailleMap();
	protected String stringToTranslate;
	protected String wordToTranslate;

	// Konstruktør
	// ---------------------------------------------------------------------------------
	
	public Braille() {
	}
	
	public void setStringToTranslate(String s) {
		this.stringToTranslate = s;
	}
	
	public void setWordToTranslate(String w) {
		this.wordToTranslate = w;
	}
	
	// Hovedmetodene for oversettelsen
	// ---------------------------------------------------------------------------------
		
	public String translateToBraille() {
		// 1. Splitt teksten opp i ord
		String[] stringArray = split();

		// 2. Oversett shavings, tall, store bokstaver, contractions og små bokstaver
		for (int i = 0; i < stringArray.length; i++) {
			setWordToTranslate(stringArray[i]);
			setWordToTranslate(numbersToBraille());
			setWordToTranslate(upperCaseLettersToBraille());
			setWordToTranslate(contractWords());
			setWordToTranslate(shavingsToBraille());
			setWordToTranslate(lowerCaseLettersToBraille());
			stringArray[i] = wordToTranslate;
		}
		return String.join("", stringArray);
	}

	// Metoder som oversetter tall
	// ----------------------------------------------------------------------------
	public String numbersToBraille() {
		return numbersToBraille(wordToTranslate);
	} 
	
	private String numbersToBraille(String s) {
		char[] charArray = s.toCharArray();
		String translation = "";

		for (int i = 0; i < charArray.length; i++) {
			if (Character.isDigit(charArray[i]) && (i == 0 || !Character.isDigit(charArray[i - 1]))) {
				translation += BrailleMap.NUMBER_INDICATOR + numbersToBraille(charArray[i]);
			} else if (Character.isDigit(charArray[i]) && (i < charArray.length - 1)) {
				if (!Character.isDigit(charArray[i + 1])) {
					translation += numbersToBraille(charArray[i]) + BrailleMap.NUMBEREND_INDICATOR;
				} else {
					translation += numbersToBraille(charArray[i]);		
				}
			} else if (Character.isDigit(charArray[i]) && Character.isDigit(charArray[i - 1])) {
				translation += numbersToBraille(charArray[i]);
			} else {
				translation += charArray[i];
			}

		}
		return translation;
	}

	protected String numbersToBraille(char c) {
		return Character.toString(brailleMap.get(Character.toString(c)));
	}

	// Metoder som oversetter bokstaver
	// -------------------------------------------------------------------------------
	public String upperCaseLettersToBraille() {
		return upperCaseLettersToBraille(wordToTranslate);
	} 
	
	protected String upperCaseLettersToBraille(String s) {
		char[] charArray = s.toCharArray();
		String translation = "";

		for (int i = 0; i < charArray.length; i++) {
			translation += upperCaseLettersToBraille(charArray[i]);
		}
		return translation;

	}

	protected String upperCaseLettersToBraille(char c) {
		if (Character.isUpperCase(c)) {
			return Character.toString(BrailleMap.CAPITAL_LETTER) + Character.toLowerCase(c);
		} else {
			return Character.toString(c);
		}
	}
	
	public String lowerCaseLettersToBraille() {
		return lowerCaseLettersToBraille(wordToTranslate);
	} 

	protected String lowerCaseLettersToBraille(String s) {
		char[] charArray = s.toCharArray();
		String translation = "";
		
		for (int i = 0; i < charArray.length; i++) {
			translation += lowerCaseLettersToBraille(charArray[i]);
		}
		return translation;
	}

	protected String lowerCaseLettersToBraille(char c) {
		if (Character.isLowerCase(c)) {
			return Character.toString(brailleMap.get(Character.toString(c)));
		} else {
			return Character.toString(c);
		}
	}

	// Metode som oversetter sammentrekninger
	// ------------------------------------------------------------------------------------
	public String contractWords() {
		return contractWords(wordToTranslate);
	} 
	
	protected String contractWords(String w) {
		String[] stringArray = splitShavings(w);
		for (int i = 0; i < stringArray.length; i++) {
			if (!Pattern.matches("\\p{Punct}", stringArray[i])) {
				if (brailleMap.containsKey(stringArray[i])) {
					stringArray[i] =  Character.toString(brailleMap.get(stringArray[i]));
				}
			}
		}
		
		return String.join("", stringArray);
	}

	// Metode for å oversette shavings
	// ---------------------------------------------------------------------------------------
	public String shavingsToBraille() {
		return shavingsToBraille(wordToTranslate);
	} 
	
	protected String shavingsToBraille(String w) {
		String[] stringArray = splitShavings(w);
		for (int i = 0; i < stringArray.length; i++) {
			if (Pattern.matches("\\p{Punct}", stringArray[i])) {
				if (brailleMap.containsKey(stringArray[i])) {
					stringArray[i] = Character.toString(brailleMap.get(stringArray[i]));
				}
			}
		}
		return String.join("", stringArray);
	}
	
	//Metode for å splitte stringen og beholde whitespace
	// -------------------------------------------------------------------------------------
	public String[] split() {
		return split(stringToTranslate);
	} 
	
	protected String[] split(String s) {
		return s.trim().split("((?<=\\s+)|(?=\\s+))");
	}
	
	//Metode for å splitte stringen og beholde shavings
	// -------------------------------------------------------------------------------------
	public String[] splitShavings() {
		return splitShavings(stringToTranslate);
	} 
	
	protected String[] splitShavings(String s) {
		return s.split("(?<=[\\p{Punct}]+)|(?=[\\p{Punct}]+)");
	}

}