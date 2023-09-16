package brackets;

/** Bracket Types.
 */
public enum BracketType {

	/**
	 * Curly brackets. {}
	 */
	CURLY,
	/**
	 * Parentheses. ()
	 */
	PARENTHESIS,
	/**
	 * Square brackets. []
	 */
	SQUARE,

	/**
	 * Angle brackets. <>
	 */
	ANGLE;

	/** Get the opening bracket.
	 * @return The opening bracket character.
	 */
	public final char getOpen() {
		return switch (this) {
			case CURLY -> '{';
			case PARENTHESIS -> '(';
			case SQUARE -> '[';
			default -> '<';
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
			default -> '>';
		};
	}

}