package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Translator {
	
	private static String option;
	private static String inputFile;
	private static String outputFile;
	
	private FileReader fr;
	private FileWriter fw;
	private BufferedReader br;
	private BufferedWriter bw;
	
	protected Braille braille = new Braille();
	protected Alfa alfa = new Alfa();
	
	public static void main(String[] args) throws IOException {
		if (args.length == 3) {
			option = args[0];
			inputFile = args[1];
			outputFile = args[2];
			if (option.equals("--frombraille")) {
				System.out.print("Translating from Braille to Alfa!\n\n");
				Translator translator = new Translator();
				translator.translateFromBraille();
			} else if (option.equals("--tobraille")) {
				System.out.print("Translating from Alfa to Braille!\n\n");
				Translator translator = new Translator();
				translator.translateToBraille();
			} else {
				System.out.print(printUsage());
			}
		} else {
			System.out.print(printUsage());
		}		
	}
	
	private void initIO() {
		try {
			fr = new FileReader(inputFile);
			fw = new FileWriter(outputFile);
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}
	
	public void translateToBraille() {
		initIO();

		String line;
		
		try {
			System.out.println("Reading " + inputFile);
			while ((line = br.readLine()) != null) {
				braille.setStringToTranslate(line);
				bw.write((braille.translateToBraille()));
				bw.newLine();
			}
			bw.close();
			
			System.out.println("Finished writing to " + outputFile);
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}

	public void translateFromBraille() {
		initIO();
		
		String line;
		
		try {
			System.out.println("Reading " + inputFile);
			while ((line = br.readLine()) != null) {
				alfa.setStringToTranslate(line);
				bw.write((alfa.translateToAlfa()));
				bw.newLine();
			}
			bw.close();
			
			System.out.println("Finished writing to " + outputFile);
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}

	}

	public static String printUsage() {
		return "Usage of Translator: java -jar Translator.jar [OPTION] [INPUTFILE] [OUTPUTFILE] \n"
				+ "Options: \n"
				+ "--frombraille Translates braille from INPUTFILE and outputs to OUTPUTFILE \n"
				+ "--tobraille Translates alpha from INPUTFILE and outputs to OUTPUTFILE.";
	}
	
	public String readLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String writeLine() {
		try {
			String s = "lol";
			bw.write(s);
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getOption() {
		return Translator.option;
	}

}
