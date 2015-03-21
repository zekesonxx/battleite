package so.zeke.battleite.battle;

import so.zeke.battleite.gui.BattleScreen;
import so.zeke.battleite.logic.Dungeon;
import so.zeke.battleite.logic.Player;
import so.zeke.battleite.logic.Steak;

public class CaveDungeon extends Dungeon {

	public CaveDungeon() {
		super(3, 5);
	}

	public Enemy getBoss(int total) {
		return new Bear();
	}

	public Enemy getMob(int interation, int total) {
		int mob = 1 + (int) (Math.random() * ((3 - 1) + 1));
		switch (mob) {
			case 1:
				return new Wolf();
			case 2:
			case 3:
				return new Mouse();
			default:
				return null;
		}
	}

	public Player reward(Player player) {
		BattleScreen
				.statusUpdate("Player awarded 100 bits for finishing the Woodland Cave");
		player.bits += 100;
		if (!this.completed) {
			BattleScreen
					.statusUpdate("You looked through the bear's stash and found some food");
			player.inv.addItem(new Steak());
		}
		return player;
	}

}
