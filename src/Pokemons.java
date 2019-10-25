class OricorioPomPom extends Pokemon {
	OricorioPomPom(String name, int level) {
		super(name, level);
		setStats(75, 70, 70, 98, 70, 93);
		setType(Type.FIRE, Type.FLYING);
		setMove(new IcyWind(), new Peck(), new Scald(), new DoubleHit());
	}
}


class Ferroseed extends Pokemon {
	Ferroseed(String name, int level) {
		super(name, level);
		setStats(44, 50, 91, 24, 86, 10);
		setType(Type.GRASS, Type.STEEL);
		setMove(new Facade(), new DoubleTeam(), new Swagger());
	}
}


class Ferrothorn extends Ferroseed {
	Ferrothorn(String name, int level) {
		super(name, level);
		setStats(74, 94, 131, 54, 116, 20);
		addMove(new IceBeam());
	}
}


class Igglybuff extends Pokemon {
	Igglybuff(String name, int level) {
		super(name, level);
		setStats(90, 30, 15, 40, 20, 15);
		setType(Type.NORMAL, Type.FAIRY);
		setMove(new Thunderbolt(), new IceBeam());
	}
}


class Jigglypuff extends Igglybuff {
	Jigglypuff(String name, int level) {
		super(name, level);
		setStats(115, 45, 20, 45, 25, 20);
		addMove(new PlayNice());
	}
}

class Wigglytuff extends Jigglypuff {
	Wigglytuff(String name, int level) {
		super(name, level);
		setStats(140, 70, 45, 85, 50, 45);
		addMove(new FireBlast());
	}
}