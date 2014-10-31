package ca.csf.TP2_Demineur;

public enum ButtonImage {
	EMPTY(0, null), ONE(1, "1.png"), FLAG(9, "Flag.png"), QUESTION_MARK(10,
			"QuestionMark.png"), MINE_FLAG(11, "MineCross.png"), MINE_NORMAL(12, "Mine.png");

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
		System.out.println(System.getProperty("user.dir") + "/ressource/" + URL);
		return URL ;
		//return System.getProperty("user.dir")+  +URL;
	}

	public ButtonImage getTypeFromInt(int index) {
		return null;
	}
}
