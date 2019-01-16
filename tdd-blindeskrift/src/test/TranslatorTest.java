package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import main.Translator;

@ExtendWith(MockitoExtension.class)
class TranslatorTest {

	@Mock
	private BufferedWriter writerMock;

	@Mock
	private BufferedReader readerMock;

	@InjectMocks
	private Translator translator;

	@BeforeEach
	void setUp() {

	}

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	void MainMethodWithNoArgsReturnsUsage() throws IOException {
		Translator.main("".split("\\s"));
		assertEquals(outContent.toString(), Translator.printUsage());
	}

	@Test
	void MainMethodWithCorrectArgsFromBrailleReturnsWorking() throws IOException {
		Translator.main("--frombraille translatethisfrombraille.txt alfafrombrailleoutput.txt".split("\\s"));
		assertEquals(translator.getOption(), "--frombraille");
	}

	@Test
	void MainMethodWithCorrectArgsToBrailleReturnsWorking() throws IOException {
		Translator.main("--tobraille translatethisfromalfa.txt braillefromalfaoutput.txt".split("\\s"));
		assertEquals(translator.getOption(), "--tobraille");
	}

	@Test
	void MainMethodWithCorrectLengthButWrongOptionReturnsUsage() throws IOException {
		Translator.main("--lol input.txt output.txt".split("\\s"));
		assertEquals(outContent.toString(), Translator.printUsage());
	}

	@Test
	void MainMethodWithTooManyArgsReturnsUsage() throws IOException {
		Translator.main("--frombraille input.txt output.txt hey".split("\\s"));
		assertEquals(outContent.toString(), Translator.printUsage());
	}

	// Test at metode for filoversetting fra Braille til Alfa returnerer korrekt
	@Test
	public void brailleToAlfaTranslation() throws IOException {
		String toTest = "\u283C\u2801\u2803\u2809\u2806\u2824\u283C\u2819\u2811\u280B\u2806\u2824\u2820\u281A\u2820\u2825\u2820\u281D\u2820\u2805"
				+ "\u283C\u280A\u280A\u280A" + "\u2813\u2801\u2813\u2801\u2816" + "\u283A";

		BufferedWriter bw = new BufferedWriter(new FileWriter("translatethisfrombraille.txt"));
		bw.write(toTest);
		bw.close();

		Translator.main("--frombraille translatethisfrombraille.txt alfafrombrailleoutput.txt".split("\\s"));

		BufferedReader br = new BufferedReader(new FileReader("alfafrombrailleoutput.txt"));

		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append((line));
		}
		br.close();
	}

	// Test at metode for filoversetting fra Alfa til Braille returnerer
	@Test
	public void alfaToBrailleTranslation() throws IOException {
		String toTest = "123-456-JUNK" + "\n999" + "\nhaha!" + "\nwill";
		BufferedWriter bw = new BufferedWriter(new FileWriter("translatethisfromalfa.txt"));
		bw.write(toTest);
		bw.close();

		Translator.main("--fromalfa translatethisfromalfa.txt braillefromalfaoutput.txt".split("\\s"));

		BufferedReader br = new BufferedReader(new FileReader("braillefromalfaoutput.txt"));

		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append((line));
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		br.close();

		String translated = "\u283C\u2801\u2803\u2809\u2806\u2824\u283C\u2819\u2811\u280B\u2806\u2824\u2820\u281A\u2820\u2825\u2820\u281D\u2820\u2805"
				+ "\n\u283C\u280A\u280A\u280A" + "\n\u2813\u2801\u2813\u2801\u2816" + "\n\u283A";

		assertEquals(translated, sb.toString());
	}

	// Mock at metode for lesing av fil leser korrekt
	@Test
	public void readLineReturnsLine() throws IOException {
		when(readerMock.readLine()).thenReturn("Test");
		assertEquals(translator.readLine(), "Test");
	}

	// Mock at metode for skriving til fil skriver korrekt
	@Test
	public void verifyWriteLineReturnsTranslationAndMockWriteToFile() throws IOException {
		when(readerMock.readLine()).thenReturn("123-456-JUNK", "", null);

		translator.translateToBraille();
		Mockito.verify(writerMock).write(
				"\u283C\u2801\u2803\u2809\u2806\u2824\u283C\u2819\u2811\u280B\u2806\u2824\u2820\u281A\u2820\u2825\u2820\u281D\u2820\u2805");
	}



}
