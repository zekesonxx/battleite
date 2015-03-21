package so.zeke.battleite.gui;

import so.zeke.battleite.battle.Enemy;
import so.zeke.battleite.battle.Tools;
import so.zeke.battleite.logic.Player;

public abstract class BattleScreen {

	public static void battleStart(Enemy enemy) {
		System.out.println("# You have been thrown into battle with a "
				+ enemy.name + "! #");
	}

	public static void statusUpdate(String str) {
		System.out.println("| " + str);
	}

	public static void header(Player player) {
		System.out.println("| Battleite | Health " + player.health + "/"
				+ player.maxHealth + " | " + String.format("%.2f", player.bits)
				+ " bits |");
	}

	public static void battle(Player player, Enemy enemy) {
		System.out
				.println("Your Moves: " + Tools.combine(player.listMoves(), ", "));
		System.out.println("Fighting: " + enemy.name + " " + enemy.health + '/'
				+ enemy.maxHealth);
		System.out.println("Use `item` to use an item, or `run` to bail.");
	}

	public static void playerAward(double award, String name) {
		System.out.println("| Player awarded " + String.format("%.2f", award)
				+ " bits for beating " + name + "!");
	}

}
