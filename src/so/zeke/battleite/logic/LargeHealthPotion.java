package so.zeke.battleite.logic;

import so.zeke.battleite.battle.Action;
import so.zeke.battleite.battle.Heal;

public class LargeHealthPotion extends Item {

	public LargeHealthPotion() {
		super("Large Health Potion", "Heals 15 HP", 20, Item.usableWhere.Anywhere);
	}

	public Player use(Player player) {
		player.dealtAction(new Action(new Heal(15)));
		return player;
	}

}