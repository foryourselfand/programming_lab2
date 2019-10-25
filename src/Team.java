import java.util.LinkedList;
import java.util.Queue;

class Team {
	private Queue<Pokemon> team = new LinkedList();
	private Pokemon pokemon;
	private String name;

	Team(String var1) {
		this.name = var1;
	}

	void add(Pokemon var1) {
		this.team.add(var1);
	}

	Pokemon next() {
		if (this.pokemon == null || !this.pokemon.isAlive()) {
			this.pokemon = (Pokemon)this.team.poll();
			this.pokemon.restore();
			System.out.print(this.pokemon + " " + Messages.get("from") + " " + this.name);
			System.out.println(" " + Messages.get("enter"));
		}

		return this.pokemon;
	}

	boolean hasNext() {
		return !this.team.isEmpty() || this.pokemon.isAlive();
	}

	Pokemon poke() {
		return this.pokemon;
	}

	String getName() {
		return this.name;
	}
}
