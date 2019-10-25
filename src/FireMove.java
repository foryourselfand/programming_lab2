class FireMove extends SpecialMove {
	public FireMove(Type var1, double var2, double var4) {
		super(var1, var2, var4);
	}

	public void applyOppEffects(Pokemon pokemon) {
		Effect var2 = (new Effect()).condition(Status.FREEZE).chance(0.8D).attack(0.0D).turns(-1);
		pokemon.setCondition(var2);
	}
}
