package main;

import java.util.LinkedHashMap;

@SuppressWarnings("serial")
public class BrailleMap extends LinkedHashMap<String, Character> {

	public final static Character CAPITAL_LETTER = '\u2820'; 
	public final static Character NUMBER_INDICATOR = '\u283C';
	public final static Character NUMBEREND_INDICATOR = '\u2806';

	public BrailleMap() {
		/*	Numerals
		 *	The same braille characters as the first 10 letters in the alphabet. 
		 *	Use NUMBER_INDICATOR to distinguish between the letter and numeral.
		 */
		this.put("1", '\u2801'); // 1
		this.put("2", '\u2803'); // 2
		this.put("3", '\u2809'); // 3
		this.put("4", '\u2819'); // 4
		this.put("5", '\u2811'); // 5
		this.put("6", '\u280B'); // 6
		this.put("7", '\u281B'); // 7
		this.put("8", '\u2813'); // 8
		this.put("9", '\u280A'); // 9
		this.put("0", '\u281A'); // 0

		/* 	Alphabet
		 * 	Use CAPITAL_LETTER before character if the letter is supposed to be capital.
		 * 
		 *  https://en.wikipedia.org/wiki/Scandinavian_Braille æ ø å
		 *  The braille letters for the French print vowels â, œ, ä are used 
		 *  for the print vowels å, ö/ø, ä/æ of the Scandinavian alphabets.
		 */
		this.put("a", '\u2801'); // A
		this.put("b", '\u2803'); // B
		this.put("c", '\u2809'); // C
		this.put("d", '\u2819'); // D
		this.put("e", '\u2811'); // E
		this.put("f", '\u280B'); // F
		this.put("g", '\u281B'); // G
		this.put("h", '\u2813'); // H
		this.put("i", '\u280A'); // I
		this.put("j", '\u281A'); // J
		this.put("k", '\u2805'); // K
		this.put("l", '\u2807'); // L
		this.put("m", '\u280D'); // M
		this.put("n", '\u281D'); // N
		this.put("o", '\u2815'); // O
		this.put("p", '\u280F'); // P
		this.put("q", '\u281F'); // Q
		this.put("r", '\u2817'); // R
		this.put("s", '\u280E'); // S
		this.put("t", '\u281E'); // T
		this.put("u", '\u2825'); // U
		this.put("v", '\u2827'); // V
		this.put("w", '\u283A'); // W
		this.put("x", '\u282D'); // X
		this.put("y", '\u283D'); // Y
		this.put("z", '\u2835'); // Z
		this.put("æ", '\u281C'); // Æ
		this.put("ø", '\u282A'); // Ø
		this.put("å", '\u2821'); // Å
		
		/* 	Punctuation
		 *	Got their own braille character most of the time. Some of the characters also
		 *	describe contractions, but those are not in our assignment
		 */ 
		// this.put(" ", '\u2800'); // Blank space UNNECCESSARY
		this.put(",", '\u2802'); // ,
		this.put(";", '\u2806'); // ;
		this.put(":", '\u2812'); // :
		this.put(".", '\u2832'); // .
		this.put("!", '\u2816'); // !
		this.put("(", '\u2836'); // ( Same sign for both opening and closing parenthesis
		this.put(")", '\u2836'); // ) Same sign for both opening and closing parenthesis
		this.put("?", '\u2826'); // ?
		this.put("/", '\u280C'); // /
		this.put("#", '\u283C'); // #
		this.put("\\",'\u2833');//	\
		this.put("'", '\u2804'); // '
		this.put("-", '\u2824'); // -
		this.put("_", '\u2838'); // _
		

		/*	Contractions
		 * 	Often same braille character as the letter the word starts with.
		 * 	Example: 'b' and "but" is U+2803
		 */
		this.put("but", '\u2803'); // but
		this.put("can", '\u2809'); // can
		this.put("do", '\u2819'); // do
		this.put("every", '\u2811'); // every
		this.put("from", '\u280B'); // from
		this.put("go", '\u281B'); // go
		this.put("have", '\u2813'); // have
		this.put("just", '\u2807'); // just
		this.put("knowledge", '\u2828'); // knowledge
		this.put("like", '\u2807'); // like
		this.put("more", '\u280D'); // more
		this.put("not", '\u281D'); // not
		this.put("people", '\u280F'); // people
		this.put("quite", '\u281F'); // quite
		this.put("rather", '\u2817'); // rather
		this.put("so", '\u280E'); // so
		this.put("that", '\u281E'); // that
		this.put("us", '\u2825'); // us
		this.put("very", '\u2827'); // very
		this.put("it", '\u282D'); // it
		this.put("you", '\u283D'); // you
		this.put("as", '\u2835'); // as
		this.put("and", '\u282F'); // and
		this.put("for", '\u283F'); // for
		this.put("of", '\u2837');  // of
		this.put("the", '\u282E'); // the
		this.put("with", '\u283E'); // with	
		this.put("will", '\u283A'); // will
		this.put("his", '\u2826'); // his
		this.put("in", '\u2814'); // in
		this.put("was", '\u2834'); // was
		this.put("to", '\u2816'); // to
		
	}
}