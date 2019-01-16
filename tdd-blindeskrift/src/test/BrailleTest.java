package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Braille;

class BrailleTest {
		
	/*	Test split functionality
	 *  String gets split into string array based on white space and new line
	 */	
	//static Braille braille = new Braille();
	private Braille braille;
	
	@BeforeEach
	void setUp() throws Exception {
		braille = new Braille();
	}
	
	@Test
	void splitMethodReturnsStringArray() {
		braille.setStringToTranslate("Jeg ser på Sjakk-VM");
		
		String[] arraySentence = braille.split();
		assertEquals(arraySentence[0], "Jeg");
		assertEquals(arraySentence[1], " ");
		assertEquals(arraySentence[2], "ser");
		assertEquals(arraySentence[3], " ");
		assertEquals(arraySentence[4], "på");
		assertEquals(arraySentence[5], " ");
		assertEquals(arraySentence[6], "Sjakk-VM");
		assertEquals(arraySentence.length, 7);
		assertEquals(String.join("", arraySentence), "Jeg ser på Sjakk-VM");
	}
	
	@Test
	void splitMethodWithNewLineReturnsStringArray() {
		braille.setStringToTranslate("Carlsen - Caruana, \nCarlsen ligger tynt ann.");
		String[] arraySentence = braille.split();
		assertEquals(arraySentence[0], "Carlsen");
		assertEquals(arraySentence[1], " ");
		assertEquals(arraySentence[2], "-");
		assertEquals(arraySentence[3], " ");
		assertEquals(arraySentence[4], "Caruana,");
		assertEquals(arraySentence[5], " ");
		assertEquals(arraySentence[6], "\n");
		assertEquals(arraySentence[7], "Carlsen");
		assertEquals(arraySentence[8], " ");
		assertEquals(arraySentence[9], "ligger");
		assertEquals(arraySentence[10], " ");
		assertEquals(arraySentence[11], "tynt");
		assertEquals(arraySentence[12], " ");
		assertEquals(arraySentence[13], "ann.");
		assertEquals(arraySentence.length, 14);
		assertEquals(String.join("", arraySentence), "Carlsen - Caruana, \nCarlsen ligger tynt ann.");
	}
	
	/*	Test splitShavings functionality
	 *  String gets split into string array with punctuations as delimiters. Keeps punctuations
	 */	
	@Test
	void splitShavingsMethodReturnsStringArrayWithShavingsIncluded() {
		braille.setStringToTranslate("hah!lol123-,#");
		String[] shavingsArray = braille.splitShavings();
		assertEquals(shavingsArray[0], "hah");
		assertEquals(shavingsArray[1], "!");
		assertEquals(shavingsArray[2], "lol123");
		assertEquals(shavingsArray[3], "-");
		assertEquals(shavingsArray[4], ",");
		assertEquals(shavingsArray[5], "#");
		assertEquals(shavingsArray.length, 6);
		assertEquals(String.join("", shavingsArray), "hah!lol123-,#");
	}
	
// numbersToBraille testing
// ---------------------------------------------------------------------------------
	/*	Test numbersToBraille functionality
	 * 	One digit is translated into braille with number indicator in front
	 */
	@Test
	void oneNumberIsTranslatedIntoBraille() {
		braille.setWordToTranslate("1");
		assertEquals("\u283C\u2801", braille.numbersToBraille());
	}
	
	/*
	 * Test numbersToBraille funcionality
	 * Group of numbers get only one number indicator at start.
	 */
	@Test 
	void severalNumbersAreTranslatedIntoBrailleWithIndicator(){
		braille.setWordToTranslate("123");
		assertEquals("\u283C\u2801\u2803\u2809",  braille.numbersToBraille());
	}
	
	/*
	 * Test numbersToBraille funcionality
	 * Numbers are translated with number indicator, letters return unchanged
	 */
	@Test
	void stringWithMixOfLettersAndNumbersReturnsWithNumbersTranslated() {
		braille.setWordToTranslate("123hei1");
		assertEquals("\u283C\u2801\u2803\u2809\u2806hei\u283C\u2801",  braille.numbersToBraille());
	}

	/*
	 * Test numbersToBraille funcionality
	 * Numbers are translated with number indicators, letters and punctuation return unchanged
	 */
	@Test 
	void stringWithNumbersPunctuationsAndTextReturnsStringWithNumbersTranslated() {
		braille.setWordToTranslate("123-456-JUNK");
		assertEquals("\u283C\u2801\u2803\u2809\u2806-\u283C\u2819\u2811\u280B\u2806-JUNK", braille.numbersToBraille());
	}
	
// convertUpperCaseLetters testing
// ---------------------------------------------------------------------------------	
	/*	Test convertUpperCaseLetters Functionality
	 * 	One upper case letter gets converted to lower case and a capital letter indicator is added
	 */
	@Test
	void upperCaseLetterIsReturnedAsLowerCaseWithIndicator() {
		braille.setWordToTranslate("A");
		assertEquals("\u2820a", braille.upperCaseLettersToBraille());
	}
	
	/*	Test convertUpperCaseLetters Functionality
	 * 	All upper case letters get converted to lower case and a capital letter indicator is added in front of each one
	 */
	@Test
	void stringWithOnlyCapitalLetterReturnsTranslatedString() {
		braille.setWordToTranslate("JUNK");
		assertEquals("\u2820j\u2820u\u2820n\u2820k", braille.upperCaseLettersToBraille());
	}
	
	/*	Test upperCaseLettersToBraille Functionality
	 * 	All upper case letters are translated, lower case letters return unchanged
	 */
	@Test
	void stringWithOneCapitalLetterReturnsTranslatedString() {
		braille.setWordToTranslate("Sweet");
		assertEquals("\u2820sweet", braille.upperCaseLettersToBraille());
	}
	
	/*	Test upperCaseLettersToBraille Functionality
	 * 	All upper case letters are translated, lower case letters, digits and punctuation return unchanged
	 */
	@Test
	void stringWithNumbersPunctuationsAndTextReturnsStringWithCapitalLetterIndicator() {
		braille.setWordToTranslate("123-456-JUNK");
		assertEquals("123-456-\u2820j\u2820u\u2820n\u2820k", braille.upperCaseLettersToBraille());
	}
	
	@Test
	void stringWithPunctuationandTextReturnsStringWithCapitalLetterIndicators() {
		braille.setWordToTranslate("!\"#Lol123hahH");
		assertEquals("!\"#\u2820lol123hah\u2820h", braille.upperCaseLettersToBraille());
	}

// contractWords testing
// ---------------------------------------------------------------------------------		
	/*	Test contractWord Functionality
	 * 	but is translated into single character
	 */
	@Test
	void butIsContractedIntoSingleBrailleSymbol() {
		braille.setWordToTranslate("but");
		assertEquals("\u2803", braille.contractWords());	// but = '\u2803'
	}
	
	@Test
	void stringWithContractionAndShavingsReturnsStringWithContractionTranslated() {
		braille.setWordToTranslate("!#will(");
		assertEquals("!#\u283A(", braille.contractWords());
	}	
	
	/*	Test numbersToBraille and upperCaseLettersToBraille Functionality
	 * 	Both numbers and upper case letters are translated. Numbers first, then upper case letters.
	 */
	@Test
	void stringWithNumbersAndUpperCaseLettersReturnWithTheseConverted() {
		braille.setWordToTranslate("Abrakadabra123");
		braille.setWordToTranslate(braille.numbersToBraille());
		assertEquals("\u2820abrakadabra\u283C\u2801\u2803\u2809", braille.upperCaseLettersToBraille());
	}
	
	/*	Test numbersToBraille and upperCaseLettersToBraille Functionality
	 * 	Both numbers and upper case letters are translated, lower case letters and punctuation remains the same
	 */
	@Test
	void stringWithNumbersPunctuationsAndTextReturnsStringWithTranslatedNumbersAndCapitalLetterIndicator() {
		braille.setWordToTranslate("123-456-JUNK");
		braille.setWordToTranslate(braille.numbersToBraille());
		assertEquals("\u283C\u2801\u2803\u2809\u2806-\u283C\u2819\u2811\u280B\u2806-\u2820j\u2820u\u2820n\u2820k", braille.upperCaseLettersToBraille());
	}
	
	
	
	/*
	 * Failer nå når små bokstaver oversettes
	 */
//	@Test
//	void sentenceWithDigitsUpperCaseLettersAndContractablesAreReturnedWithTheseTranslated() {
//		assertEquals("abc \u283C\u2801\u2803\u2809 \u2820a\u2820b\u2820c \u2803", Braille.translateToBraille("abc 123 ABC but"));
//	}
	
	/*
	 * 
	 */
	@Test
	void translateShavingsMethodTranslatesPunctuation() {
		braille.setWordToTranslate("!");
		assertEquals("\u2816", braille.shavingsToBraille());
	}
	
	/*
	 * 
	 */
	@Test
	void severalSymbolsOfPunctuationAreTranslated() {
		braille.setWordToTranslate("!?._/#");
		assertEquals("\u2816\u2826\u2832\u2838\u280C\u283C", braille.shavingsToBraille());
	}
	
	/*	Test lowerCaseLettersToBraille Functionality
	 *	abc is translated correcly
	 */	
	@Test
	void lowerCaseLettersAreTranslated() {
		braille.setWordToTranslate("abc");
		assertEquals("\u2801\u2803\u2809", braille.lowerCaseLettersToBraille());
	}
	
	/*	Test translateToBraille Functionality
	 *	abc ABC 123 but to! is correctly translated
	 */	
	@Test
	void digitsContractionsLowerAndUpperCaseLettersAndShavingsAreAllTranslated() {
		braille.setStringToTranslate("abc ABC 123 but to!");
		assertEquals("\u2801\u2803\u2809 \u2820\u2801\u2820\u2803\u2820\u2809 \u283C\u2801\u2803\u2809 \u2803 \u2816\u2816", braille.translateToBraille());
	}
	
	/*	Test translateToBraille Functionality
	 *	123-456-JUNK is correctly translated
	 */
	@Test
	void stringWithNumbersContractionsAndPunctuationReturnsTranslatedString() {
		braille.setStringToTranslate("123-456-JUNK");
		assertEquals("\u283C\u2801\u2803\u2809\u2806\u2824\u283C\u2819\u2811\u280B\u2806\u2824\u2820\u281A\u2820\u2825\u2820\u281D\u2820\u2805", braille.translateToBraille());
	}
	
		
	/*	Test translateToBraille Functionality
	 * 	can rather but is translated into three contractions
	 */
	@Test
	void severalContractableWordsAreReturnedAsBrailleSymbols() {
		braille.setStringToTranslate("can rather to");
		assertEquals("\u2809 \u2817 \u2816", braille.translateToBraille()); // can = '\u2809', rather = '\u2817' to = '\u2816'
	}
	
	/*	Test translateToBraille Functionality
	 * 	Heihvordangårdet?123 is correctly translated
	 */
	@Test
	void stringWithNumbersPunctuationsAndUpperCaseLettersReturnsWithTheseTranslated() {
		braille.setStringToTranslate("Heihvordangårdet?123");
		assertEquals("\u2820\u2813\u2811\u280A\u2813\u2827\u2815\u2817\u2819\u2801\u281D\u281B\u2821\u2817\u2819\u2811\u281E\u2826\u283C\u2801\u2803\u2809", braille.translateToBraille());	
	}

	
}