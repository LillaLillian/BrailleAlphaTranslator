package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Alfa;

class AlfaTest {

	private Alfa alfa;
	
	@BeforeEach
	void setUp() throws Exception {
		alfa = new Alfa();
	}
	
	@Test
	void splitMethodReturnsStringArray() {
		alfa.setStringToTranslate("\u2801\u2803\u2809 \u2801\u2803\u2809 \u2801\u2803\u2809\u2824\u2801\u2803\u2809");
		String[] arraySentence = alfa.split();
		assertEquals(arraySentence[0],("\u2801\u2803\u2809"));
		assertEquals(arraySentence[1], " ");
		assertEquals(arraySentence[2],("\u2801\u2803\u2809"));
		assertEquals(arraySentence[3], " ");
		assertEquals(arraySentence[4],("\u2801\u2803\u2809\u2824\u2801\u2803\u2809"));
		assertEquals(arraySentence.length, 5);
		assertEquals(String.join("", arraySentence), "\u2801\u2803\u2809 \u2801\u2803\u2809 \u2801\u2803\u2809\u2824\u2801\u2803\u2809");
	}
	
	@Test
	void splitMethodWithNewLineReturnsStringArray() {
		alfa.setStringToTranslate("\u2801\u2803\u2809 \u2824 \u2801\u2803\u2809 \n\u2801\u2803\u2809");
		String[] arraySentence = alfa.split();
		assertEquals(arraySentence[0], "\u2801\u2803\u2809");
		assertEquals(arraySentence[1], " ");
		assertEquals(arraySentence[2], "\u2824");
		assertEquals(arraySentence[3], " ");
		assertEquals(arraySentence[4], "\u2801\u2803\u2809");
		assertEquals(arraySentence[5], " ");
		assertEquals(arraySentence[6], "\n");
		assertEquals(arraySentence[7], "\u2801\u2803\u2809");
		assertEquals(arraySentence.length, 8);
		assertEquals(String.join("", arraySentence), "\u2801\u2803\u2809 \u2824 \u2801\u2803\u2809 \n\u2801\u2803\u2809");
	}
	
	@Test
	void brailleNumberIsTranslatedIntoAlfa() {
		alfa.setWordToTranslate("\u283C\u2801");
		assertEquals("1", alfa.brailleToNumbers());
	}
	
	@Test
	void severalNumbersAreTranslatedIntoAlfa() {
		alfa.setWordToTranslate("\u283C\u2801\u2803\u2809");
		assertEquals("123",  alfa.brailleToNumbers());
	}
	
	@Test
	void stringWithMixOfLettersAndNumbersReturnsWithNumbersTranslated() {
		alfa.setWordToTranslate("\u283C\u2801\u2803\u2809\u2806\u2813\u2811\u280A\u283C\u2801\u2806");
		assertEquals("123\u2813\u2811\u280A1",  alfa.brailleToNumbers());
	}
	
	@Test 
	void stringWithNumbersPunctuationsAndTextReturnsStringWithNumbersTranslated() {
		alfa.setWordToTranslate("\u283C\u2801\u2803\u2809\u2806\u2824\u283C\u2819\u2811\u280B\u2806\u2824\u2801\u2803\u2809");
		assertEquals("123\u2824456\u2824\u2801\u2803\u2809", alfa.brailleToNumbers());
	}

	@Test
	void IndicatorAndLetterIsReturnedAsUpperCaseLetter() {
		alfa.setWordToTranslate("\u2820\u2801");
		assertEquals("A", alfa.brailleToLetters());
	}
	
	@Test
	void translateMultipleToOnlyCapitalLetter() {
		alfa.setWordToTranslate("\u2820\u281A\u2820\u2825\u2820\u281D\u2820\u2805");
		assertEquals("JUNK", alfa.brailleToLetters());
	}
	
	@Test
	void returnOneCapitalLetterSmallLetters() {
		alfa.setWordToTranslate("\u2820\u280E\u283A\u2811\u2811\u281E");
		assertEquals("Sweet", alfa.brailleToLetters());
	}
	
	@Test
	void stringWithNumbersPunctuationsAndTextReturnsStringWithCapitalLetter() {
		alfa.setWordToTranslate("\u283C\u2801\u2803\u2809\u2806\u2824\u283C\u2819\u2811\u280B\u2806\u2824\u2820\u2801\u2820\u2803\u2820\u2809");
		assertEquals("\u283C\u2801\u2803\u2809\u2806\u2824\u283C\u2819\u2811\u280B\u2806\u2824ABC", alfa.brailleToLetters());
	}
	
	@Test
	void stringWithNumbersAndLettersReturnWithTheseConverted() {
		alfa.setWordToTranslate("\u2820\u2801\u2803\u2809\u283C\u2801\u2803\u2809\u2806\u2801");
		alfa.setWordToTranslate(alfa.brailleToNumbers());
		assertEquals("Abc123a", alfa.brailleToLetters());
	}
	
	@Test
	void stringWithNumbersPunctuationsAndTextReturnsStringWithTranslatedNumbersAndCapitalLetter() {
		alfa.setWordToTranslate("\u2820\u2801\u2803\u2809\u2824\u283C\u2801\u2803\u2809\u2806\u2801");
		alfa.setWordToTranslate(alfa.brailleToNumbers());
		assertEquals("Abc\u2824123a", alfa.brailleToLetters());
	}
	
	@Test
	void contractedBrailleIsTranslatedIntoWord() {
		alfa.setWordToTranslate("\u2803");
		assertEquals("but", alfa.getWord());
	}
	
	@Test
	void severalContractableWordsAreReturnedAsBrailleSymbols() {
		alfa.setStringToTranslate("\u2809 \u2817 \u2816");
		assertEquals("can rather to", alfa.translateToAlfa()); 
	}

	@Test
	void stringWithLowerCaseAndContractionsReturnsWithTheseConverted() {
		alfa.setStringToTranslate("\u2801\u2803\u2809 \u2809 \u283C\u2801\u2803\u2809");
		assertEquals("abc can 123", alfa.translateToAlfa());
	}
	
	@Test
	void stringWithNumbersAndUpperCaseLettersReturnsWithTheseConverted() {
		alfa.setStringToTranslate("\u283C\u2801\u2803\u2809 \u2820\u281A\u2820\u2825\u2820\u281D\u2820\u2805");
		assertEquals("123 JUNK", alfa.translateToAlfa());
	}
	
	@Test
	void translateShavingsMethodTranslatesExclamationMark() {
		alfa.setWordToTranslate("\u2816");
		assertEquals("!", alfa.translateShavings());
	}
	
	@Test
	void stringWithNumbersPunctuationsAndLettersReturnsWithShavingsTranslated() {
		alfa.setWordToTranslate("\u283C\u2801\u2803\u2809\u2806\u2824\u283C\u2819\u2811\u280B\u2806\u2824\u2820\u281A\u2820\u2825\u2820\u281D\u2820\u2805");
		assertEquals("\u283C\u2801\u2803\u2809\u2806-\u283C\u2819\u2811\u280B\u2806-\u2820\u281A\u2820\u2825\u2820\u281D\u2820\u2805", alfa.translateShavings());
	}
	
	@Test
	void translateShavingsMethodTranslatesParantecesCorrect() {
		alfa.setWordToTranslate("\u2836\u2836");
		assertEquals("()", alfa.translateShavings());
	}
	
	@Test
	void severalSymbolsAreTranslated() {
		alfa.setWordToTranslate("\u2816\u2826\u2832\u2838\u280C\u283C");
		assertEquals("!?._/#", alfa.translateShavings());
	}
	
	@Test
	void lowerCaseLettersAreTranslated() {
		alfa.setWordToTranslate("\u2801\u2803\u2809");
		assertEquals("abc", alfa.brailleToLetters());
	}
	
	@Test
	void onlyLowerCaseLettersAreTranslated() {
		alfa.setWordToTranslate("\u2820\u2801\u2803\u2809");
		assertEquals("Abc", alfa.brailleToLetters());
	}
	
	@Test
	void numbersAreTranslated() {
		alfa.setStringToTranslate("\u283C\u2801\u2803\u2809");
		assertEquals("123",  alfa.translateToAlfa());
	}
	
	@Test
	void numbersAndWordsAreTranslated() {
		alfa.setStringToTranslate("\u2801\u2803\u2809 \u2809 \u283C\u2801\u2803\u2809");
		assertEquals("abc can 123", alfa.translateToAlfa());
	}
	
	@Test
	void numbersAndWordsAndSymbolsAreTranslated() {
		alfa.setStringToTranslate("\u283C\u2801\u2803\u2809\u2806\u2824\u283C\u2819\u2811\u280B\u2806\u2824\u2820\u281A\u2825\u281D\u2805");
		assertEquals("123-456-Junk", alfa.translateToAlfa());
	}
	
	@Test
	void numbersAndWordsAndSymbolsAndSpacesAreTranslated() {
		alfa.setStringToTranslate("\u2801\u2803\u2809 \u2820\u2801\u2820\u2803\u2820\u2809 \u283C\u2801\u2803\u2809 \u2803 \u2816\u2816");
		assertEquals("abc ABC 123 but to!", alfa.translateToAlfa());
	}
}
