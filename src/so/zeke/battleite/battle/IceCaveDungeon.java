package so.zeke.battleite.battle;

import so.zeke.battleite.gui.BattleScreen;
import so.zeke.battleite.logic.ClamShell;
import so.zeke.battleite.logic.Dungeon;
import so.zeke.battleite.logic.Player;

public class IceCaveDungeon extends Dungeon {

	public IceCaveDungeon() {
		super(2, 2);
	}

	public Enemy getBoss(int completed) {
		return new Penguin();
	}

	public Enemy getMob(int iteration, int total) {
		return new Imp();
	}

	public Player reward(Player player) {
		player.bits += 40;
		BattleScreen
				.statusUpdate("Player awarded 40 bits for completing the Penguin Dungeon");
		if (this.completed == false) {
			BattleScreen.statusUpdate("You found a clam shell");
			player.inv.addItem(new ClamShell());
			BattleScreen
					.statusUpdate("You look through the Penguin's possessions and find instructions on his Freeze attack");
			player.setMove(3, new Action(new Attack("Freeze", 6, .13, Damage.Ice)));
			BattleScreen
					.statusUpdate("There are new items available from the Merchant.");
		}
		return player;
	}

}
