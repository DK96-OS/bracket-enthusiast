package brackets;

/** Bracket Types.
 */
public enum BracketType {

	CURLY,
	PARENTHESIS,
	SQUARE;

	/** Get the opening bracket.
	 * @return The opening bracket character.
	 */
	public final char getOpen() {
		return switch (this) {
			case CURLY -> '{';
			case PARENTHESIS -> '(';
			case SQUARE -> '[';
		};
	}

	/** Get the closing bracket.
	 * @return The closing bracket character.
	 */
	public final char getClose() {
		return switch (this) {
			case CURLY -> '}';
			case PARENTHESIS -> ')';
			case SQUARE -> ']';
		};
	}

}
