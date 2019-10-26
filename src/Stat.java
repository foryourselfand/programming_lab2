public enum Stat {
	HP(false),
	ATTACK(false),
	DEFENSE(false),
	SPEED(false),
	SPECIAL_ATTACK(false),
	SPECIAL_DEFENSE(false),
	ACCURACY(true),
	EVASION(true);

	private boolean hidden;

	Stat(boolean hidded) {
		this.hidden = hidded;
	}

	public boolean isHidden() {
		return this.hidden;
	}
}