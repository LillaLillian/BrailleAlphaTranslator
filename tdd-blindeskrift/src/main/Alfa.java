package main;

public class Alfa {
	protected AlfaMap alfaMap = new AlfaMap();
	protected String stringToTranslate;
	protected String wordToTranslate;

	// Konstruktør
	// ---------------------------------------------------------------------------------
	
	public Alfa() {
	}
	
	public void setStringToTranslate(String s) {
		this.stringToTranslate = s;
	}
	
	public void setWordToTranslate(String w) {
		this.wordToTranslate = w;
	}
	
	// Hovedmetodene for oversettelsen
	// ---------------------------------------------------------------------------------
	public String translateToAlfa() {
		String[] stringArray = split();

		for (int i = 0; i < stringArray.length; i++) {
			setWordToTranslate(stringArray[i]);
			setWordToTranslate(brailleToNumbers());
			setWordToTranslate(getWord());
			setWordToTranslate(brailleToLetters());
			setWordToTranslate(translateShavings());
			stringArray[i] = wordToTranslate;
		}

		return String.join("", stringArray);
	}

	// Metoder for å finne tall
	// -------------------------------------------------------------------------------------

	public String brailleToNumbers() {
		return brailleToNumbers(wordToTranslate);
	} 
	
	protected String brailleToNumbers(String w) {
		char[] charArray = w.toCharArray();
		String translation = "";
		Boolean number = false;

		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == AlfaMap.NUMBER_INDICATOR) {
				number = true;
			} else if (charArray[i] == AlfaMap.NUMBEREND_INDICATOR) {
				number = false;
			}

			if (charArray[i] == AlfaMap.NUMBER_INDICATOR || charArray[i] == AlfaMap.NUMBEREND_INDICATOR) {
				translation += "";
			} else if (number) {
				translation += brailleToNumbers(charArray[i]);
			} else {
				translation += charArray[i];
			}
		}
		return translation;
	}

	protected String brailleToNumbers(char c) {
		return alfaMap.get("N" + c);
	}

	// Metoder for å finne bokstaver
	// -------------------------------------------------------------------------------------

	public String brailleToLetters() {
		return brailleToLetters(wordToTranslate);
	} 
	
	protected String brailleToLetters(String w) {
		char[] charArray = w.toCharArray();
		String translation = "";
		Boolean isUpperCase = false;
		Boolean isLetter = true;

		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == AlfaMap.CAPITAL_LETTER) {
				isUpperCase = true;
			} else if (isUpperCase && charArray[i] != AlfaMap.CAPITAL_LETTER) {
				translation += brailleToUpperCaseLetters(charArray[i]);
				isUpperCase = false;
			} else if (charArray[i] == AlfaMap.NUMBER_INDICATOR) {
				translation += charArray[i];
				isLetter = false;
			} else if (charArray[i] == AlfaMap.NUMBEREND_INDICATOR) {
				translation += charArray[i];
				isLetter = true;
			} else if (!isLetter) {
				translation += charArray[i];
			} else if (!isUpperCase && isLetter) {
				if (alfaMap.containsKey(Character.toString(charArray[i]))) {
					translation += brailleToLowerCaseLetters(charArray[i]);
				} else {
					translation += charArray[i];
				}
			}
		}
		return translation;
	}

	protected String brailleToUpperCaseLetters(char c) {
		return alfaMap.get(Character.toString(c)).toUpperCase();
	}

	protected String brailleToLowerCaseLetters(char c) {
		return alfaMap.get(Character.toString(c));
	}

	// Metoder for å finne ord
	// -------------------------------------------------------------------------------------

	public String getWord() {
		return getWord(wordToTranslate);
	} 
	
	protected String getWord(String w) {
		char[] charArray = w.toCharArray();
		String translation = "";
		int len = charArray.length;

		for (int i = 0; i < charArray.length; i++) {
			if (alfaMap.containsKey("W" + charArray[i])) {

					// Sannsynligvis forkortet ord, oversett
				if (len == 1) {
					translation += getWord(charArray[i]);

					// Hvis det er første tegnet og neste tegn ikke er en bokstav, sannsynligvis
					// forkortet ord, oversett
				} else if ((i == 0) && !alfaMap.containsKey(Character.toString(charArray[i + 1]))) {
					translation += getWord(charArray[i]);

					// Hvis det er siste tegnet og forrige tegn ikke er en bokstav, da er det
					// sannsynligvis et symbol, ikke oversett
				} else if (i == len - 1 && !alfaMap.containsKey(Character.toString(charArray[i - 1]))) {
					translation += charArray[i];
					
					// Finnes i sammentrukne-ord-listen, men sannsynligvis del av et lengre ord
				} else {
					translation += charArray[i];
				}

				// Hvis ikke symbolet finnes i sammentrukne-ord-listen
			} else {
				translation += charArray[i];
			}
		}

		return translation;
	}

	protected String getWord(char c) {
		return alfaMap.get("W" + c);
	}

	// Metode for å splitte stringen og beholde whitespace
	// -------------------------------------------------------------------------------------

	public String[] split() {
		return split(stringToTranslate);
	} 
	
	protected String[] split(String s) {
		return s.trim().split("((?<=\\s+)|(?=\\s+))");
	}

	// Metode for å oversette shavings/symboler
	// ---------------------------------------------------------------------------------------

	public String translateShavings() {
		return translateShavings(wordToTranslate);
	} 
	
	protected String translateShavings(String w) {
		char[] charArray = w.toCharArray();
		String translation = "";
		boolean numberIndicator = false;
		boolean secondParanteces = false;

		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == AlfaMap.NUMBER_INDICATOR) { // Registrer om startindikator
				numberIndicator = checkIfArrayContainsIsEndIndicator(i + 1, charArray); // Returnerer true dersom neste
																						// symbol er endIndicator, og
																						// false dersom startIndicator
																						// eller ingenting
			}

			if (!numberIndicator) { // Dersom ikke funnet start- og endIndikator -> Oversett
				if (alfaMap.containsKey("S" + charArray[i])) {
					translation += translateShavings(charArray[i], secondParanteces);
					secondParanteces = !secondParanteces && charArray[i] == '\u2836' ? true : false; // Sett true dersom
																										// første
																										// parantes
				} else {
					translation += charArray[i];
				}
			} else { // Dersom funnet startIndikator og endIndikator, men ikke nådd endIndikator enda
						// -> Ikke oversett
				translation += charArray[i];
			}

			// Dersom det er registrert en startindikator og er sluttindikator -> Nullstill
			// søk
			if (numberIndicator && charArray[i] == AlfaMap.NUMBEREND_INDICATOR) {
				numberIndicator = false;
			}
		}
		return translation;
	}

	protected boolean checkIfArrayContainsIsEndIndicator(int i, char[] charArray) {
		for (int j = i; j < charArray.length; j++) {
			if (charArray[j] == AlfaMap.NUMBER_INDICATOR) {
				return false;
			} else if (charArray[j] == AlfaMap.NUMBEREND_INDICATOR) {
				return true;
			}
		}
		return false;
	}
	
	protected String translateShavings(char c, boolean p) {
		String s = p && c == '\u2836' ? "S2" : "S";
		return alfaMap.get(s + Character.toString(c));
	}

}
