/**
 * A class useful when the Grammar is not 
 * correctly defined
 */
package parser;

public class MalformedGrammarException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1540652671509390361L;

	MalformedGrammarException() {
		super();
	}

	MalformedGrammarException(String s) {
		super(s);
	}
}
