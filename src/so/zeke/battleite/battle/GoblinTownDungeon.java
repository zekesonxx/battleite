package so.zeke.battleite.battle;

import so.zeke.battleite.gui.BattleScreen;
import so.zeke.battleite.logic.Bandage;
import so.zeke.battleite.logic.Dungeon;
import so.zeke.battleite.logic.HealthPotion;
import so.zeke.battleite.logic.LargeHealthPotion;
import so.zeke.battleite.logic.Player;

public class GoblinTownDungeon extends Dungeon {

	public GoblinTownDungeon() {
		super(3, 7);
	}

	public Enemy getMob(int iteration, int total) {
		return new Goblin();
	}

	public Enemy getBoss(int total) {
		return new Goblin();
	}

	public Player reward(Player player) {
		player.bits += 70;
		if (this.completed == false) {
			BattleScreen
					.statusUpdate("A goblin merchant trades you his wares for his life.");
			player.inv.addItem(new HealthPotion());
			player.inv.addItem(new HealthPotion());
			player.inv.addItem(new LargeHealthPotion());
			player.inv.addItem(new Bandage());
			player.bits += 45;
			BattleScreen
					.statusUpdate("The town wizard has increased your Freeze and Fireball damage");
			player.moves.set(2, new Action(new Attack("Fireball", 11, .13,
					Damage.Fire, new Effect(EffectType.Burn, 2, 3, 0.55))));
			player.setMove(3, new Action(new Attack("Freeze", 9, .13, Damage.Ice)));
		} else {
			BattleScreen
					.statusUpdate("They haven't rebuilt much since your last attack, however you find a health potion in some of the rubble.");
			if (Math.random() >= 0.50) {
				player.inv.addItem(new LargeHealthPotion());
			} else {
				player.inv.addItem(new HealthPotion());
			}
		}
		BattleScreen
				.statusUpdate("Player awarded 70 bits for completing the Goblin Dungeon.");
		return player;
	}

}
