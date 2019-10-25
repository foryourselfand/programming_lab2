//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Pokemon {
	private String name;
	private List<Type> types;
	private List<Move> moves;
	private Move preparedMove;
	private Effect stage;
	private Effect condition;
	private List<Effect> effects;
	private int confusion;
	private int level;
	private double[] base;

	public Pokemon(String var1, int var2) {
		this.types = new LinkedList();
		this.moves = new LinkedList();
		this.stage = new Effect();
		this.condition = new Effect();
		this.effects = new LinkedList();
		this.level = 1;
		this.base = new double[Stat.values().length];
		this.types.add(Type.NONE);
		this.moves.add(Move.getStruggleMove());
		this.name = var1;
		this.setLevel(var2);
	}

	public Pokemon() {
		this(Messages.get("noname"), 1);
	}

	public final void setStats(double var1, double var3, double var5, double var7, double var9, double var11) {
		this.base[Stat.HP.ordinal()] = var1;
		this.base[Stat.ATTACK.ordinal()] = var3;
		this.base[Stat.DEFENSE.ordinal()] = var5;
		this.base[Stat.SPECIAL_ATTACK.ordinal()] = var7;
		this.base[Stat.SPECIAL_DEFENSE.ordinal()] = var9;
		this.base[Stat.SPEED.ordinal()] = var11;
	}

	public final double getStat(Stat var1) {
		double var2 = 15.0D;
		double var4 = 0.0D;
		double var6 = this.base[var1.ordinal()];
		double var8 = (double) this.stage.stat(var1);
		var8 += this.condition.success() ? (double) this.condition.stat(var1) : 0.0D;

		Effect var11;
		for (Iterator var10 = this.effects.iterator(); var10.hasNext(); var8 += var11.success() ? (double) var11.stat(var1) : 0.0D) {
			var11 = (Effect) var10.next();
		}

		if (Math.abs(var8) > 6.0D) {
			var8 = var8 > 0.0D ? 6.0D : - 6.0D;
		}

		double var14 = var1.isHidden() ? 0.0D : (var1 == Stat.HP ? (double) this.level + 10.0D : 5.0D);
		double var12 = var1.isHidden() ? 3.0D : 2.0D;
		var6 *= var1 == Stat.HP ? 1.0D : (var8 > 0.0D ? (var12 + var8) / var12 : var12 / (var12 + var8));
		var6 = var1.isHidden() ? var6 : (var6 * 2.0D + var2 + Math.sqrt(var4) / 4.0D) * (double) this.level / 100.0D;
		var6 += var14;
		return var6;
	}

	public final boolean hasType(Type var1) {
		Iterator var2 = this.types.iterator();

		Type var3;
		do {
			if (! var2.hasNext()) {
				return false;
			}

			var3 = (Type) var2.next();
		} while (var3 != var1);

		return true;
	}

	public final void addEffect(Effect var1) {
		if (var1.condition() == Status.NORMAL) {
			this.effects.add(var1);
		} else {
			this.setCondition(var1);
		}

	}

	public final Status getCondition() {
		return this.condition.condition();
	}

	public final void setCondition(Effect var1) {
		if (var1.success() && this.condition.condition() != var1.condition()) {
			this.condition = var1;
			String var2 = "";
			switch (var1.condition()) {
				case BURN:
					var2 = Messages.get("burn");
					break;
				case FREEZE:
					var2 = Messages.get("freeze");
					break;
				case PARALYZE:
					var2 = Messages.get("paralyze");
					break;
				case POISON:
					var2 = Messages.get("poison");
					break;
				case SLEEP:
					var2 = Messages.get("sleep");
			}

			System.out.println(this + " " + var2);
		}

	}

	public final void confuse() {
		this.confusion = (int) (Math.random() * 4.0D + 1.0D);
	}

	public final void restore() {
		this.base[Stat.ACCURACY.ordinal()] = 1.0D;
		this.base[Stat.EVASION.ordinal()] = 1.0D;
		this.condition.clear();
		this.stage.clear();
		this.effects.clear();
	}

	public final double getHP() {
		return this.getStat(Stat.HP) - (double) this.stage.stat(Stat.HP);
	}

	public final void setMod(Stat var1, int var2) {
		if (var2 != 0) {
			int var3 = var2 + this.stage.stat(var1);
			String var4 = "";
			if (var1 == Stat.HP) {
				var4 = Messages.get(var2 > 0 ? "minusHP" : "plusHP") + " " + Math.abs(var2);
			} else {
				if (Math.abs(var3) > 6) {
					var3 = var3 > 0 ? 6 : - 6;
				}

				var4 = Messages.get(var2 < 0 ? "minusStat" : "plusStat");
			}

			this.stage.stat(var1, var3);
			System.out.println(this + " " + var4 + " " + Messages.get(var1.toString()) + ".");
		}

	}

	public final Type[] getTypes() {
		return (Type[]) this.types.toArray(new Type[0]);
	}

	public final int getLevel() {
		return this.level;
	}

	public final void setLevel(int var1) {
		if (var1 < 1) {
			var1 = 1;
		}

		if (var1 > 100) {
			var1 = 100;
		}

		this.level = var1;
	}

	private double getAttackChance() {
		double var1 = this.stage.attack();
		var1 *= this.condition.attack();

		Effect var4;
		for (Iterator var3 = this.effects.iterator(); var3.hasNext(); var1 *= var4.attack()) {
			var4 = (Effect) var3.next();
		}

		return var1;
	}

	public final void prepareMove() {
		if (this.getAttackChance() > Math.random()) {
			if (this.moves.size() == 0) {
				this.preparedMove = Move.getStruggleMove();
			} else if (this.confusion > 0 && Math.random() < 0.33D) {
				this.preparedMove = Move.getConfusionMove();
				-- this.confusion;
			} else {
				this.preparedMove = (Move) this.moves.get((int) Math.floor(Math.random() * (double) this.moves.size()));
			}
		} else {
			this.preparedMove = Move.getNoMove();
		}

	}

	public final boolean isAlive() {
		return this.getStat(Stat.HP) > (double) this.stage.stat(Stat.HP);
	}

	public final boolean attack(Pokemon var1) {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException var3) {
		}

		this.preparedMove.attack(this, var1);
		if (this.isAlive() && var1.isAlive()) {
			System.out.println();
			return false;
		} else {
			if (! this.isAlive() && ! var1.isAlive()) {
				System.out.println(Messages.get("bothFaint"));
			} else {
				System.out.println((this.isAlive() ? var1 : this) + " " + Messages.get("faint"));
			}

			return true;
		}
	}

	public final void turn() {
		this.setMod(Stat.HP, this.condition.stat(Stat.HP));
		if (this.condition.turn()) {
			this.condition.clear();
		}

		if (this.condition.condition() == Status.FREEZE && Math.random() < 0.2D) {
			this.condition.clear();
			System.out.println(this + " " + Messages.get("thawn"));
		}

		Iterator var1 = this.effects.iterator();

		while (var1.hasNext()) {
			Effect var2 = (Effect) var1.next();
			this.setMod(Stat.HP, var2.stat(Stat.HP));
			if (var2.turn()) {
				var2.clear();
			}
		}

	}

	protected final void setType(Type... var1) {
		this.types.clear();
		if (var1 == null) {
			this.types.add(Type.NONE);
		} else {
			Type[] var2 = var1;
			int var3 = var1.length;

			for (int var4 = 0; var4 < var3; ++ var4) {
				Type var5 = var2[var4];
				this.types.add(var5);
				if (this.types.size() >= 2) {
					break;
				}
			}
		}

	}

	protected final void addType(Type var1) {
		if (this.types.size() < 2 && ! this.types.contains(var1)) {
			this.types.add(var1);
		}

	}

	protected final void setMove(Move... var1) {
		this.moves.clear();
		if (var1 == null) {
			this.moves.add(Move.getStruggleMove());
		} else {
			Move[] var2 = var1;
			int var3 = var1.length;

			for (int var4 = 0; var4 < var3; ++ var4) {
				Move var5 = var2[var4];
				this.moves.add(var5);
				if (this.moves.size() >= 4) {
					break;
				}
			}
		}

	}

	protected final void addMove(Move var1) {
		this.moves.add(var1);

		while (this.moves.size() > 4) {
			this.moves.remove(0);
		}

	}

	protected final Move getPreparedMove() {
		return this.preparedMove;
	}

	public final String toString() {
		return (this.getClass().isAnonymousClass() ? Messages.get("poke") : this.getClass().getSimpleName()) + " " + this.name;
	}
}
