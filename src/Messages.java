import java.util.Arrays;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class Messages extends ListResourceBundle {
	private static ResourceBundle bundle = getBundle("Messages");
	private static List<String> teams = Arrays.asList("Red", "White", "Yellow", "Greren", "Blue", "Black", "Purple", "Striped");
	private static Object[][] contents;

	public Messages() {
	}

	static String get(String var0) {
		return bundle.getString(var0);
	}

	static Object getObj(String var0) {
		return bundle.getObject(var0);
	}

	public Object[][] getContents() {
		return contents;
	}

	static {
		contents = new Object[][]{{"poke", "Pokemon"}, {"noname", "Unnamed"}, {"burn", "is burned"}, {"freeze", "is frozen"}, {"thawn", "thawns"}, {"paralyze", "is paralyzed"}, {"poison", "is poisoned"}, {"sleep", "is sleeping"}, {"faint", "faints."}, {"bothFaint", "Both pokemons faint."}, {"HP", "hit points"}, {"ATTACK", "attack"}, {"DEFENSE", "defense"}, {"SPEED", "speed"}, {"SPECIAL_ATTACK", "special attack"}, {"SPECIAL_DEFENSE", "special defense"}, {"ACCURACY", "accuracy"}, {"EVASION", "evasion"}, {"minusHP", "loses"}, {"plusHP", "restores"}, {"minusStat", "decreases"}, {"plusStat", "increases"}, {"attack", "attacks"}, {"struggle", "struggles"}, {"noattack", "does nothing"}, {"confusion", "hits himself in confusion"}, {"noeffect", "isn't affected by"}, {"miss", "misses"}, {"teams", teams}, {"from", "from the team"}, {"enter", "enters the battle!"}, {"tie", "Both teams are out of Pokemons. It's a tie!"}, {"inTeam", "Team"}, {"empty", "loses its last Pokemon."}, {"team", "The team"}, {"wins", "wins the battle!"}, {"critical", "Critical hit!"}};
	}
}
