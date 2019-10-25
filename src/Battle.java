import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Battle {
	private Team allies;
	private Team foes;
	private String allyName;
	private String foeName;

	public Battle() {
		ArrayList arrayList = new ArrayList();
		arrayList.addAll((List)Messages.getObj("teams"));
		Collections.shuffle(arrayList);
		this.allies = new Team((String)arrayList.get(0));
		this.foes = new Team((String)arrayList.get(1));
	}

	public void addAlly(Pokemon var1) {
		this.allies.add(var1);
	}

	public void addFoe(Pokemon var1) {
		this.foes.add(var1);
	}

	private boolean checkFirst(Pokemon var1, Pokemon var2) {
		if (var1.getPreparedMove().getPriority() == var2.getPreparedMove().getPriority()) {
			if (var1.getStat(Stat.SPEED) == var2.getStat(Stat.SPEED)) {
				return Math.random() >= 0.5D;
			} else {
				return var1.getStat(Stat.SPEED) > var2.getStat(Stat.SPEED);
			}
		} else {
			return var1.getPreparedMove().getPriority() > var2.getPreparedMove().getPriority();
		}
	}

	public void go() {
		do {
			this.allies.next();
			this.foes.next();

			do {
				this.allies.poke().prepareMove();
				this.foes.poke().prepareMove();
				Pokemon var1 = this.checkFirst(this.allies.poke(), this.foes.poke()) ? this.allies.poke() : this.foes.poke();
				Pokemon var2 = this.checkFirst(this.allies.poke(), this.foes.poke()) ? this.foes.poke() : this.allies.poke();
				if (var1.attack(var2) || var2.attack(var1)) {
					break;
				}

				this.allies.poke().turn();
				if (!this.allies.poke().isAlive()) {
					break;
				}

				this.foes.poke().turn();
			} while(this.foes.poke().isAlive());
		} while(this.allies.hasNext() && this.foes.hasNext());

		if (!this.allies.hasNext() && !this.foes.hasNext()) {
			System.out.println(Messages.get("tie"));
		} else {
			String var3 = (this.allies.hasNext() ? this.allies : this.foes).getName();
			String var4 = (this.foes.hasNext() ? this.allies : this.foes).getName();
			System.out.println(Messages.get("inTeam") + " " + var4 + " " + Messages.get("empty"));
			System.out.println(Messages.get("team") + " " + var3 + " " + Messages.get("wins"));
		}

	}

	public static void main(String[] args) {
		Battle battle = new Battle();
		battle.addAlly(new Pokemon("Весельчак У", 20) {
			{
				this.setStats(10.0D, 20.0D, 10.0D, 10.0D, 10.0D, 20.0D);
				this.setMove(new FireMove(Type.DRAGON, 5.0D, 1.0D));
			}
		});
		battle.addFoe(new Pokemon("Тутан Хамон", 25) {
			{
				this.setStats(10.0D, 10.0D, 20.0D, 10.0D, 10.0D, 10.0D);
				this.addMove(new FireMove(Type.DARK, 4.0D, 0.8D));
			}
		});
		battle.go();
	}
}
