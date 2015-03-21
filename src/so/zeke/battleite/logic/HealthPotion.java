package so.zeke.battleite.logic;

import so.zeke.battleite.battle.Action;
import so.zeke.battleite.battle.Heal;

public class HealthPotion extends Item {

	public HealthPotion() {
		super("Health Potion", "Heals 5 HP", 11, Item.usableWhere.Anywhere);
	}

	public Player use(Player player) {
		player.dealtAction(new Action(new Heal(5)));
		return player;
	}

}
