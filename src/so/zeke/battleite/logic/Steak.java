package so.zeke.battleite.logic;

import java.util.ArrayList;

public class Steak extends Item {

	public Steak() {
		super("Steak",
				"It's a cooked steak. \nHeals you completely and cures all effects",
				30, Item.usableWhere.Anywhere);
	}

	public Player use(Player player) {
		player.health = player.maxHealth;
		player.effects = new ArrayList<>();
		return player;
	}

}
