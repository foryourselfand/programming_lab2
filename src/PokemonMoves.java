class IcyWind extends SpecialMove {
	IcyWind() {
		super(Type.ICE, 55, 95);
	}

	@Override
	protected void applyOppEffects(Pokemon pokemon) {
		pokemon.setMod(Stat.SPEED, - 1);
	}

	@Override
	protected String describe() {
		return "Применяет Icy Wind";
	}
}


class Peck extends PhysicalMove {
	Peck() {
		super(Type.FLYING, 35, 100);
	}

	// TODO: find out how this move works
	@Override
	protected String describe() {
		return "Использует Peck";
	}
}


class Scald extends SpecialMove {
	Scald() {
		super(Type.WATER, 80, 100);
	}

	@Override
	protected void applyOppEffects(Pokemon pokemon) {
		if (Math.random() <= 0.3) {
			Effect.burn(pokemon);
		}
	}

	@Override
	protected String describe() {
		return "Юзает Scald";
	}
}


class DoubleHit extends PhysicalMove {
	DoubleHit() {
		super(Type.NORMAL, 35, 90);
	}

	@Override
	protected void applyOppDamage(Pokemon pokemon, double v) {
		super.applyOppDamage(pokemon, 35);
	}

	@Override
	protected String describe() {
		return "Атакует дважды";
	}
}


class Facade extends PhysicalMove {
	Facade() {
		super(Type.NORMAL, 70, 100);
	}

	@Override
	protected void applyOppDamage(Pokemon pokemon, double v) {
		double damage = v;
		if (pokemon.getCondition() == Status.POISON || pokemon.getCondition() == Status.PARALYZE || pokemon.getCondition() == Status.BURN)
			damage *= 2;
		super.applyOppDamage(pokemon, damage);
	}

	@Override
	protected void applySelfEffects(Pokemon pokemon) {
		double newAttack = pokemon.getStat(Stat.ATTACK);

		if (pokemon.getCondition() == Status.BURN)
			newAttack = newAttack / 2;

		pokemon.setStats(pokemon.getHP(), newAttack, pokemon.getStat(Stat.DEFENSE),
				pokemon.getStat(Stat.SPECIAL_ATTACK), pokemon.getStat(Stat.SPECIAL_DEFENSE), pokemon.getStat(Stat.SPEED));
	}

	@Override
	protected String describe() {
		return "Применяет Facade";
	}
}


class DoubleTeam extends StatusMove {
	DoubleTeam() {
		super(Type.NORMAL, 0, 100);
	}

	@Override
	protected void applySelfEffects(Pokemon pokemon) {
		pokemon.setMod(Stat.EVASION, + 1);
	}

	@Override
	protected String describe() {
		return "применяет Double Team";
	}
}


class Swagger extends StatusMove {
	Swagger() {
		super(Type.NORMAL, 0, 85);
	}

	@Override
	protected void applyOppEffects(Pokemon pokemon) {
		pokemon.confuse();
		pokemon.setMod(Stat.ATTACK, + 2);
	}

	@Override
	protected String describe() {
		return "Использует Swagger";
	}
}


class IceBeam extends SpecialMove {
	IceBeam() {
		super(Type.ICE, 90, 100);
	}

	@Override
	protected void applyOppEffects(Pokemon pokemon) {
		if (Math.random() <= 0.1) {
			Effect.freeze(pokemon);
		}
	}

	@Override
	protected String describe() {
		return "применяет Ice Beam";
	}
}

class Thunderbolt extends SpecialMove {
	Thunderbolt() {
		super(Type.ELECTRIC, 90, 100);
	}

	@Override
	protected void applyOppEffects(Pokemon pokemon) {
		if (Math.random() <= 0.1) {
			Effect.paralyze(pokemon);
		}
	}

	@Override
	protected String describe() {
		return "Запускает Thunderbolt";
	}
}


class PlayNice extends StatusMove {
	PlayNice() {
		super(Type.NORMAL, 0, 100);
	}

	@Override
	protected void applyOppEffects(Pokemon pokemon) {
		pokemon.setMod(Stat.ATTACK, - 1);
	}
}


class FireBlast extends SpecialMove {
	FireBlast() {
		super(Type.FIRE, 110, 85);
	}

	@Override
	protected void applyOppEffects(Pokemon pokemon) {
		if (Math.random() <= 0.1) {
			Effect.burn(pokemon);
		}
	}

	@Override
	protected String describe() {
		return "Запускает Fire Blast";
	}
}
