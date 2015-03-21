package so.zeke.battleite.gui;

import java.util.List;

import so.zeke.battleite.battle.Action;
import so.zeke.battleite.battle.Effect;
import so.zeke.battleite.logic.Item;
import so.zeke.battleite.logic.Player;

public abstract class GeneralScreen {

	public static void prompt() {
		System.out.print("> ");
	}

	public static void moves(Player player) {
		List<Action> moves = player.getMoves();
		System.out.println("| Your " + moves.size() + " moves");
		for (int i = 0; i < moves.size(); i++) {
			System.out.print("| ");
			Action move = moves.get(i);
			switch (move.type) {
				case Attack:
					System.out.println(move.name + ": Deals " + move.attack.baseDamage
							+ " dmg, " + (int) (move.attack.critChance * 100)
							+ "% crit chance");
					if (move.attack.effect != null) {
						System.out.println("|   "
								+ (int) (move.attack.effect.probability * 100)
								+ "% chance to deal " + move.attack.effect.intensity + " "
								+ Effect.wordify(move.attack.effect.type) + " damage for "
								+ move.attack.effect.length + " turns.");
					}
					break;
				case Heal:
					System.out.println(move.name + ": Heals " + move.heal.amount + " HP");
					break;
				default:
					System.out.println("## Unknown Action: " + move.name + " ##");
			}

		}
	}

	public static void inventory(Player player, String k) {
		List<Item> items = player.inv.items;
		System.out.println(" |  Your inventory");
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			System.out.print("<" + (i + 1) + "> " + item.name);
			System.out.println();
			String[] lines = item.description.split("\\r?\\n");
			for (int j = 0; j < lines.length; j++) {
				System.out.println(" | " + lines[j]);
			}
		}
		System.out.print(k + " which item? ");
	}
}
