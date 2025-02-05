public abstract class Move {
	private static final Move noMove;
	private static final Move struggleMove;
	private static final Move confusionMove;

	static {
		noMove = new Move(Type.NONE, 0.0D, 0.0D, - 100, 0) {
			public final void attack(Pokemon pokemon1, Pokemon pokemon2) {
			}

			public String describe() {
				return Messages.get("noattack");
			}
		};
		struggleMove = new PhysicalMove(Type.NONE, 50.0D, 1.0D) {
			public final String describe() {
				return Messages.get("struggle");
			}

			public final void applySelfDamage(Pokemon var1, double var2) {
				var1.setMod(Stat.HP, (int) Math.round(var2 / 4.0D));
			}
		};
		confusionMove = new PhysicalMove(Type.NONE, 40.0D, 1.0D) {
			public final String describe() {
				return Messages.get("confusion");
			}

			public final void applySelfDamage(Pokemon var1, double var2) {
				var1.setMod(Stat.HP, (int) var2);
			}

			public double calcCriticalHit(Pokemon var1, Pokemon var2) {
				return 1.0D;
			}

			protected void applyOppDamage(Pokemon var1, double var2) {
			}
		};
	}

	protected Type type;
	protected double power;
	protected double accuracy;
	protected int priority;
	protected int hits;

	public Move() {
		this(Type.NONE, 0.0D, 1.0D, 0, 1);
	}

	public Move(Type type, double power, double accuracy) {
		this(type, power, accuracy, 0, 1);
	}

	public Move(Type type, double power, double accuracy, int priority, int hits) {
		this.power = 0.0D;
		this.accuracy = 1.0D;
		this.priority = 0;
		this.hits = 1;
		this.type = type;
		this.accuracy = accuracy;
		this.power = power;
		this.priority = priority;
		this.hits = hits;
	}

	public static final Move getNoMove() {
		return noMove;
	}

	public static final Move getStruggleMove() {
		return struggleMove;
	}

	public static final Move getConfusionMove() {
		return confusionMove;
	}

	protected abstract void attack(Pokemon pokemon1, Pokemon pokemon2);

	protected boolean checkAccuracy(Pokemon pokemon1, Pokemon pokemon2) {
		return this.accuracy * pokemon1.getStat(Stat.ACCURACY) / pokemon2.getStat(Stat.EVASION) > Math.random();
	}

	public final int getPriority() {
		return this.priority;
	}

	protected String describe() {
		return Messages.get("attack");
	}

	protected void applyOppEffects(Pokemon pokemon) {
	}

	protected void applySelfEffects(Pokemon pokemon) {
	}
}
