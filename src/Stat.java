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

	private Stat(boolean var3) {
		this.hidden = var3;
	}

	public boolean isHidden() {
		return this.hidden;
	}
}