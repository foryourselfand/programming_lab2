public class Main {
	public static void main(String[] args) {
		Battle battle = new Battle();

		Pokemon p1 = new OricorioPomPom("Birdie", 6);
		Pokemon p2 = new Ferroseed("Egg", 7);
		Pokemon p3 = new Ferrothorn("MyEx", 9);

		Pokemon p4 = new Igglybuff("Cutie", 7);
		Pokemon p5 = new Jigglypuff("SuperCutie", 8);
		Pokemon p6 = new Wigglytuff("Wiee", 7);

		battle.addAlly(p1);
		battle.addAlly(p2);
		battle.addAlly(p3);

		battle.addFoe(p4);
		battle.addFoe(p5);
		battle.addFoe(p6);

		battle.go();
	}
}
