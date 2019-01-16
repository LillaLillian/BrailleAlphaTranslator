package main;

import java.util.LinkedHashMap;

@SuppressWarnings("serial")
public class AlfaMap extends LinkedHashMap<String, String> {
	public final static Character CAPITAL_LETTER = '\u2820'; 
	public final static Character NUMBER_INDICATOR = '\u283C';
	public final static Character NUMBEREND_INDICATOR = '\u2806';

	public AlfaMap()  {
		/*	Numerals
		 *	The same braille characters as the first 10 letters in the alphabet. 
		 *	Use NUMBER_INDICATOR to distinguish between the letter and numeral.
		 */
		this.put("N\u2801", "1"); // 1
		this.put("N\u2803", "2"); // 2
		this.put("N\u2809", "3"); // 3
		this.put("N\u2819", "4"); // 4
		this.put("N\u2811", "5"); // 5
		this.put("N\u280B", "6"); // 6
		this.put("N\u281B", "7"); // 7
		this.put("N\u2813", "8"); // 8
		this.put("N\u280A", "9"); // 9
		this.put("N\u281A", "0"); // 0

		/* 	Alphabet
		 * 	Use CAPITAL_LETTER before character if the letter is supposed to be capital.
		 * 
		 *  https://en.wikipedia.org/wiki/Scandinavian_Braille æ ø å
		 *  The braille letters for the French print vowels â, œ, ä are used 
		 *  for the print vowels å, ö/ø, ä/æ of the Scandinavian alphabets.
		 */
		this.put("\u2801", "a"); // A
		this.put("\u2803", "b"); // B
		this.put("\u2809", "c"); // C
		this.put("\u2819", "d"); // D
		this.put("\u2811", "e"); // E
		this.put("\u280B", "f"); // F
		this.put("\u281B", "g"); // G
		this.put("\u2813", "h"); // H
		this.put("\u280A", "i"); // I
		this.put("\u281A", "j"); // J
		this.put("\u2805", "k"); // K
		this.put("\u2807", "l"); // L
		this.put("\u280D", "m"); // M
		this.put("\u281D", "n"); // N
		this.put("\u2815", "o"); // O
		this.put("\u280F", "p"); // P
		this.put("\u281F", "q"); // Q
		this.put("\u2817", "r"); // R
		this.put("\u280E", "s"); // S
		this.put("\u281E", "t"); // T
		this.put("\u2825", "u"); // U
		this.put("\u2827", "v"); // V
		this.put("\u283A", "w"); // W
		this.put("\u282D", "x"); // X
		this.put("\u283D", "y"); // Y
		this.put("\u2835", "z"); // Z
		this.put("\u281C", "æ"); // Æ
		this.put("\u282A", "ø"); // Ø
		this.put("\u2821", "å"); // Å
		
		/* 	Punctuation
		 *	Got their own braille character most of the time. Some of the characters also
		 *	describe contractions, but those are not in our assignment
		 */ 
		this.put("S\u2802", ","); // ,
		this.put("S\u2806", ";"); // ;
		this.put("S\u2812", ":"); // :
		this.put("S\u2832", "."); // .
		this.put("S\u2816", "!"); // !
		this.put("S\u2836", "("); // ( Same sign for both opening and closing parenthesis
		this.put("S2\u2836", ")"); // ) Same sign for both opening and closing parenthesis
		this.put("S\u2826", "?"); // ?
		this.put("S\u280C", "/"); // /
		this.put("S\u283C", "#"); // #
		this.put("S\u2833","\\");//	\
		this.put("S\u2804", "'"); // '
		this.put("S\u2824", "-"); // -
		this.put("S\u2838", "_"); // _
		

		/*	Contractions
		 * 	Often same braille character as the letter the word starts with.
		 * 	Example: "b" and "but" is U+2803
		 */
		this.put("W\u2803", "but"); // but
		this.put("W\u2809", "can"); // can
		this.put("W\u2819", "do"); // do
		this.put("W\u2811", "every"); // every
		this.put("W\u280B", "from"); // from
		this.put("W\u281B", "go"); // go
		this.put("W\u2813", "have"); // have
		this.put("W\u2807", "just"); // just
		this.put("W\u2828", "knowledge"); // knowledge
		this.put("W\u2807", "like"); // like
		this.put("W\u280D", "more"); // more
		this.put("W\u281D", "not"); // not
		this.put("W\u280F", "people"); // people
		this.put("W\u281F", "quite"); // quite
		this.put("W\u2817", "rather"); // rather
		this.put("W\u280E", "so"); // so
		this.put("W\u281E", "that"); // that
		this.put("W\u2825", "us"); // us
		this.put("W\u2827", "very"); // very
		this.put("W\u282D", "it"); // it
		this.put("W\u283D", "you"); // you
		this.put("W\u2835", "as"); // as
		this.put("W\u282F", "and"); // and
		this.put("W\u283F", "for"); // for
		this.put("W\u2837", "of");  // of
		this.put("W\u282E", "the"); // the
		this.put("W\u283E", "with"); // with	
		this.put("W\u283A", "will"); // will
		this.put("W\u2826", "his"); // his
		this.put("W\u2814", "in"); // in
		this.put("W\u2834", "was"); // was
		this.put("W\u2816", "to"); // to
		
	}
}
