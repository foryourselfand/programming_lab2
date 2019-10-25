import java.util.Arrays;
import java.util.List;
import java.util.ListResourceBundle;

public class Messages_ru extends ListResourceBundle {
	private static List<String> teams = Arrays.asList("красных", "белых", "желтых", "зеленых", "синих", "черных", "фиолетовых", "полосатых");
	private static Object[][] contents;

	public Messages_ru() {
	}

	public Object[][] getContents() {
		return contents;
	}

	static {
		contents = new Object[][]{{"poke", "Покемон"}, {"noname", "Безымянный"}, {"burn", "воспламеняется"}, {"freeze", "замерзает"}, {"thawn", "оттаивает"}, {"paralyze", "парализован"}, {"poison", "отравлен"}, {"sleep", "засыпает"}, {"faint", "теряет сознание."}, {"bothFaint", "Оба покемона теряют сознание."}, {"HP", "здоровья"}, {"ATTACK", "атаку"}, {"DEFENSE", "защиту"}, {"SPEED", "скорость"}, {"SPECIAL_ATTACK", "специальную атаку"}, {"SPECIAL_DEFENSE", "специальную защиту"}, {"ACCURACY", "точность"}, {"EVASION", "уклоняемость"}, {"minusHP", "теряет"}, {"plusHP", "восстанавливает"}, {"minusStat", "уменьшает"}, {"plusStat", "увеличивает"}, {"attack", "атакует"}, {"struggle", "борется с соперником"}, {"noattack", "ничего не делает"}, {"confusion", "растерянно попадает по себе"}, {"noeffect", "не замечает воздействие типа"}, {"miss", "промахивается"}, {"teams", teams}, {"from", "из команды"}, {"enter", "вступает в бой!"}, {"tie", "В обеих командах закончились покемоны. Ничья!"}, {"inTeam", "В команде"}, {"empty", "не осталось покемонов."}, {"team", "Команда"}, {"wins", "побеждает в этом бою!"}, {"critical", "Критический удар!"}};
	}
}
