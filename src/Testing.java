public class Testing {
	public static void main(String[] args) {
		int[][] effects = Type.effects;
		for (int i = 0; i < effects.length; i++) {
			for (int j = 0; j < effects[0].length; j++) {
				System.out.print(effects[i][j] + " ");
			}
			System.out.println();
		}

		Type type = Type.NORMAL;
		double effect = type.getEffect(Type.FIRE, Type.ROCK, Type.STEEL);
		System.out.println(effect);
	}
}
