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

	public Pokemon(String name, int level) {
		this.types = new LinkedList();
		this.moves = new LinkedList();
		this.stage = new Effect();
		this.condition = new Effect();
		this.effects = new LinkedList();
		this.level = 1;
		this.base = new double[Stat.values().length];
		this.types.add(Type.NONE);
		this.moves.add(Move.getStruggleMove());
		this.name = name;
		this.setLevel(level);
	}

	public Pokemon() {
		this(Messages.get("noname"), 1);
	}

	public final void setStats(double hp, double attack, double defense, double special_attack, double special_defense, double speed) {
		this.base[Stat.HP.ordinal()] = hp;
		this.base[Stat.ATTACK.ordinal()] = attack;
		this.base[Stat.DEFENSE.ordinal()] = defense;
		this.base[Stat.SPECIAL_ATTACK.ordinal()] = special_attack;
		this.base[Stat.SPECIAL_DEFENSE.ordinal()] = special_defense;
		this.base[Stat.SPEED.ordinal()] = speed;
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

	public final boolean hasType(Type type_input) {
		Iterator iterator = this.types.iterator();

		Type type_iter;
		do {
			if (! iterator.hasNext()) {
				return false;
			}

			type_iter = (Type) iterator.next();
		} while (type_iter != type_input);

		return true;
	}

	public final void addEffect(Effect effect) {
		if (effect.condition() == Status.NORMAL) {
			this.effects.add(effect);
		} else {
			this.setCondition(effect);
		}

	}

	public final Status getCondition() {
		return this.condition.condition();
	}

	public final void setCondition(Effect effect) {
		if (effect.success() && this.condition.condition() != effect.condition()) {
			this.condition = effect;
			String message = "";
			switch (effect.condition()) {
				case BURN:
					message = Messages.get("burn");
					break;
				case FREEZE:
					message = Messages.get("freeze");
					break;
				case PARALYZE:
					message = Messages.get("paralyze");
					break;
				case POISON:
					message = Messages.get("poison");
					break;
				case SLEEP:
					message = Messages.get("sleep");
			}
			System.out.println(this + " " + message);
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

	public final void setMod(Stat stat, int var2) {
		if (var2 != 0) {
			int var3 = var2 + this.stage.stat(stat);
			String var4;
			if (stat == Stat.HP) {
				var4 = Messages.get(var2 > 0 ? "minusHP" : "plusHP") + " " + Math.abs(var2);
			} else {
				if (Math.abs(var3) > 6) {
					var3 = var3 > 0 ? 6 : - 6;
				}

				var4 = Messages.get(var2 < 0 ? "minusStat" : "plusStat");
			}

			this.stage.stat(stat, var3);
			System.out.println(this + " " + var4 + " " + Messages.get(stat.toString()) + ".");
		}

	}

	public final Type[] getTypes() {
		return this.types.toArray(new Type[0]);
	}

	public final int getLevel() {
		return this.level;
	}

	public final void setLevel(int level) {
		if (level < 1) {
			level = 1;
		}

		if (level > 100) {
			level = 100;
		}

		this.level = level;
	}

	private double getAttackChance() {
		double attack = this.stage.attack();
		attack *= this.condition.attack();

		Effect effect;
		for (Iterator effectIterator = this.effects.iterator(); effectIterator.hasNext(); attack *= effect.attack()) {
			effect = (Effect) effectIterator.next();
		}

		return attack;
	}

	public final void prepareMove() {
		if (this.getAttackChance() > Math.random()) {
			if (this.moves.size() == 0) {
				this.preparedMove = Move.getStruggleMove();
			} else if (this.confusion > 0 && Math.random() < 0.33D) {
				this.preparedMove = Move.getConfusionMove();
				-- this.confusion;
			} else {
				this.preparedMove = this.moves.get((int) Math.floor(Math.random() * (double) this.moves.size()));
			}
		} else {
			this.preparedMove = Move.getNoMove();
		}

	}

	public final boolean isAlive() {
		return this.getStat(Stat.HP) > (double) this.stage.stat(Stat.HP);
	}

	public final boolean attack(Pokemon pokemon) {
		try {
			Thread.sleep(0L);
		} catch (InterruptedException e) {
		}

		this.preparedMove.attack(this, pokemon);
		if (this.isAlive() && pokemon.isAlive()) {
			System.out.println();
			return false;
		} else {
			if (! this.isAlive() && ! pokemon.isAlive()) {
				System.out.println(Messages.get("bothFaint"));
			} else {
				System.out.println((this.isAlive() ? pokemon : this) + " " + Messages.get("faint"));
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

		for (Effect effect : this.effects) {
			this.setMod(Stat.HP, effect.stat(Stat.HP));
			if (effect.turn()) {
				effect.clear();
			}
		}

	}

	protected final void setType(Type... types) {
		this.types.clear();
		if (types == null) {
			this.types.add(Type.NONE);
		} else {
			for (Type type : types) {
				this.types.add(type);
				if (this.types.size() >= 2) {
					break;
				}
			}
		}
	}

	protected final void addType(Type type) {
		if (this.types.size() < 2 && ! this.types.contains(type)) {
			this.types.add(type);
		}
	}

	protected final void setMove(Move... moves) {
		this.moves.clear();
		if (moves == null) {
			this.moves.add(Move.getStruggleMove());
		} else {
			for (Move move : moves) {
				this.moves.add(move);
				if (this.moves.size() >= 4) {
					break;
				}
			}
		}

	}

	protected final void addMove(Move move) {
		this.moves.add(move);

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
