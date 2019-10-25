public class StatusMove extends Move {
	public StatusMove() {
	}

	public StatusMove(Type var1, double var2, double var4) {
		super(var1, var2, var4);
	}

	public StatusMove(Type var1, double var2, double var4, int var6, int var7) {
		super(var1, var2, var4, var6, var7);
	}

	public final void attack(Pokemon pokemon1, Pokemon pokemon2) {
		for (int var3 = 0; var3 < this.hits; ++ var3) {
			if (this.checkAccuracy(pokemon1, pokemon2)) {
				System.out.println(pokemon1 + " " + this.describe() + ". ");
				if (this.type.getEffect(pokemon2.getTypes()) > 0.0D) {
					this.applyOppEffects(pokemon2);
				} else {
					System.out.println(pokemon2 + " " + Messages.get("noeffect") + " " + this.type);
				}

				if (this.type.getEffect(pokemon1.getTypes()) > 0.0D) {
					this.applySelfEffects(pokemon1);
				}
			} else {
				System.out.println(pokemon1 + " " + Messages.get("miss"));
			}
		}

	}

	protected void applySelfEffects(Pokemon pokemon) {
	}

	protected void applyOppEffects(Pokemon pokemon) {
	}
}