package so.zeke.battleite.battle;

import so.zeke.battleite.gui.BattleScreen;
import so.zeke.battleite.logic.ClamShell;
import so.zeke.battleite.logic.Dungeon;
import so.zeke.battleite.logic.LargeHealthPotion;
import so.zeke.battleite.logic.Player;
import so.zeke.battleite.logic.Steak;

public class DiscordsLairDungeon extends Dungeon {

	public DiscordsLairDungeon() {
		super(4, 6);
	}

	public Enemy getMob(int iteration, int total) {
		switch (iteration) {
			case 1:
				return new MilkCloud();
			case 2:
				return new MilkCloud();
			case 3:
				return new Chair();
			case 4:
				return new Chair();
			case 5:
				return new MilkCloud();
		}
		return null;
	}

	public Enemy getBoss(int total) {
		if (this.completed) {
			BattleScreen
					.statusUpdate("Discord: Look, we both know you're here to kill me. Let's just get it over with.");
		} else {
			BattleScreen.statusUpdate("Unknown Voice: My my, what do we have here.");
			BattleScreen
					.statusUpdate("Unknown Voice: A little adventurer has gotten into my lair!");
			BattleScreen
					.statusUpdate("Unknown Voice: Oh aren't you just the absolutely dumbest thing I've ever seen");
			BattleScreen
					.statusUpdate("Unknown Voice: The last time someone tried to kill me it took six people.");
			BattleScreen
					.statusUpdate("Unknown Voice: And they just trapped me in stone, not very killing-like.");
			BattleScreen
					.statusUpdate("Discord: I'm Discord, blah blah chaos and disharmony all that stuff.");
		}
		return new Discord();
	}

	public Player reward(Player player) {
		if (this.completed) {
			BattleScreen
					.statusUpdate("Discord: How many tea parties do you have to ruin? She got these really fancy crackers for this one too.");
			BattleScreen
					.statusUpdate("Discord: They have fancy spices on them and everything.");
			BattleScreen
					.statusUpdate("Discord: You already took my good loot, have a consolation prize.");
			player.bits += 0.01;
		} else {
			BattleScreen
					.statusUpdate("Discord: Well, darn. I was looking forward to my afternoon tea with Fluttershy.");
			BattleScreen
					.statusUpdate("Discord: No matter, you did beat me. Here, have this crap that was sitting in the corner");
			player.inv.addItem(new Steak());
			player.inv.addItem(new Steak());
			player.inv.addItem(new LargeHealthPotion());
			player.inv.addItem(new ClamShell());
		}
		return player;
	}

}
