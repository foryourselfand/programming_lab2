import java.util.LinkedList;
import java.util.Queue;

class Team {
	private Queue<Pokemon> team = new LinkedList();
	private Pokemon pokemon;
	private String name;

	Team(String name) {
		this.name = name;
	}

	void add(Pokemon pokemon) {
		this.team.add(pokemon);
	}

	Pokemon next() {
		if (this.pokemon == null || ! this.pokemon.isAlive()) {
			this.pokemon = this.team.poll();
			this.pokemon.restore();
			System.out.print(this.pokemon + " " + Messages.get("from") + " " + this.name);
			System.out.println(" " + Messages.get("enter"));
		}

		return this.pokemon;
	}

	boolean hasNext() {
		return ! this.team.isEmpty() || this.pokemon.isAlive();
	}

	Pokemon poke() {
		return this.pokemon;
	}

	String getName() {
		return this.name;
	}
}
