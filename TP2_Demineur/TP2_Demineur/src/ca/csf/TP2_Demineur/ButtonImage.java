package ca.csf.TP2_Demineur;

public enum ButtonImage {
	EMPTY(0, null), ONE(1, "1.png"), TWO(2, "2.png"), TREE(3, "3.png"), FOUR(4,
			"4.png"), FIVE(5, "5.png"), SIX(6, "6.png"), SEVEN(7, "7.png"), EIGHT(
			8, "8.png"), FLAG(9, "Flag.png"), QUESTION_MARK(10,
			"QuestionMark.png"), MINE_FLAG(11, "MineCross.png"), MINE_NORMAL(
			12, "Mine.png");

	private final int index;
	private final String URL;

	private ButtonImage(int index, String URL) {
		this.index = index;
		this.URL = URL;
	}

	public int index() {
		return index;
	}

	public String URL() {
		System.out
				.println(System.getProperty("user.dir") + "/ressource/" + URL);
		return URL;
	}

	public static ButtonImage getTypeFromInt(int index) {

		switch (index) {
		case 0:
			return EMPTY;
		case 1:
			return ONE;
		case 2:
			return TWO;
		case 3:
			return TREE;
		case 4:
			return FOUR;
		case 5:
			return FIVE;
		case 6:
			return SIX;
		case 7:
			return SEVEN;
		case 8:
			return EIGHT;
		default:
			return EMPTY;
		}

	}
}
