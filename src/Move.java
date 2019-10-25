public abstract class Move {
	protected Type type;
	protected double power;
	protected double accuracy;
	protected int priority;
	protected int hits;
	private static final Move noMove;
	private static final Move struggleMove;
	private static final Move confusionMove;

	public Move() {
		this(Type.NONE, 0.0D, 1.0D, 0, 1);
	}

	public Move(Type var1, double var2, double var4) {
		this(var1, var2, var4, 0, 1);
	}

	public Move(Type var1, double var2, double var4, int var6, int var7) {
		this.power = 0.0D;
		this.accuracy = 1.0D;
		this.priority = 0;
		this.hits = 1;
		this.type = var1;
		this.accuracy = var4;
		this.power = var2;
		this.priority = var6;
		this.hits = var7;
	}

	protected abstract void attack(Pokemon var1, Pokemon var2);

	protected boolean checkAccuracy(Pokemon var1, Pokemon var2) {
		return this.accuracy * var1.getStat(Stat.ACCURACY) / var2.getStat(Stat.EVASION) > Math.random();
	}

	public final int getPriority() {
		return this.priority;
	}

	protected String describe() {
		return Messages.get("attack");
	}

	protected void applyOppEffects(Pokemon var1) {
	}

	protected void applySelfEffects(Pokemon var1) {
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

	static {
		noMove = new Move(Type.NONE, 0.0D, 0.0D, -100, 0) {
			public final void attack(Pokemon var1, Pokemon var2) {
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
				var1.setMod(Stat.HP, (int)Math.round(var2 / 4.0D));
			}
		};
		confusionMove = new PhysicalMove(Type.NONE, 40.0D, 1.0D) {
			public final String describe() {
				return Messages.get("confusion");
			}

			public final void applySelfDamage(Pokemon var1, double var2) {
				var1.setMod(Stat.HP, (int)var2);
			}

			public double calcCriticalHit(Pokemon var1, Pokemon var2) {
				return 1.0D;
			}

			protected void applyOppDamage(Pokemon var1, double var2) {
			}
		};
	}
}
