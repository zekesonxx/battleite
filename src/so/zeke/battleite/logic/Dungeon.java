package so.zeke.battleite.logic;

import java.util.Scanner;

import so.zeke.battleite.battle.Battle;
import so.zeke.battleite.battle.Enemy;
import so.zeke.battleite.battle.Imp;

public class Dungeon {

	public int min;
	public int max;
	public Class<Enemy> boss;
	public boolean completed = false;

	public Dungeon(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public Player enter(Player player, Scanner scnr) {
		boolean battling = true;
		int completed = 0;
		int total = this.min + (int) (Math.random() * ((this.max - this.min) + 1));
		while (battling) {
			Battle battle = new Battle(player, this.getMob(completed + 1, total),
					scnr);
			if (battle.playerWon) {
				// The player won the last battle, s/he didn't run or die.
				completed++;
				if (total == completed) {
					Battle bossbattle = new Battle(player, this.getBoss(completed), scnr);
					if (bossbattle.playerWon) {
						player = reward(player);
						this.completed = true;
					}
					battling = false;
				}
			} else {
				battling = false;
			}
		}

		return player;
	}

	public Player reward(Player player) {
		return player;
	}

	public Enemy getBoss(int completed) {
		return new Imp();
	}

	public Enemy getMob(int iteration, int total) {
		return new Imp();
	}

}
