package so.zeke.battleite.battle;

import java.util.Scanner;

import so.zeke.battleite.gui.BattleScreen;
import so.zeke.battleite.gui.GeneralScreen;
import so.zeke.battleite.logic.Inventory;
import so.zeke.battleite.logic.Item;
import so.zeke.battleite.logic.Player;

public class Battle {

	public static Player fight(Player player, Enemy enemy, Scanner scnr) {
		Battle battle = new Battle(player, enemy, scnr);
		return battle.player;
	}

	public boolean battling = true;
	public boolean playerWon = false;

	public Player player;
	public Enemy enemy;

	public Battle(Player player, Enemy enemy, Scanner scnr) {
		BattleScreen.battleStart(enemy);
		this.player = player;
		this.enemy = enemy;

		while (this.battling) {
			BattleScreen.header(player);
			BattleScreen.battle(this.player, this.enemy);
			GeneralScreen.prompt();
			this.input(scnr.nextLine(), scnr);
		}
		System.out.println();
	}

	public void input(String input, Scanner scnr) {
		Action k = this.chatHandler(input, this.player, this.enemy, scnr);
		if (k == null) {
			// ponder tongue
			return;
		} else if (k.curCooldown != 0) {
			BattleScreen.statusUpdate("That move is on cooldown");
		} else {
			switch (k.type) {
				case Attack:
					int dmg = attack(k, false);
					BattleScreen.statusUpdate("Dealt " + dmg + " damage to "
							+ this.enemy.name);
					if (checkDeaths()) {
						return;
					}
					break;
				case Heal:
					break;
				case Pass:
					// tongue pondering of the finest
					break;
			}

			Action action = this.enemy.takeAction(this.player.health);

			int enemydmg = attack(action, true);
			BattleScreen.statusUpdate("Enemy attacked with " + action.attack.name
					+ ", dealing " + enemydmg + " damage");
			player.ontick();
			enemy.ontick();

			if (checkDeaths()) {
				return;
			}

		}
	}

	private boolean checkDeaths() {
		if (this.enemy.health <= 0) {
			this.playerWon = true;
			this.ondeath();
			return true;
		} else if (this.player.health <= 0) {
			this.playerDeath();
			return true;
		} else {
			return false;
		}
	}

	private void playerDeath() {
		this.battling = false;
		this.player.inv = new Inventory();
		this.player.bits = this.player.bits / 8;
		this.player.health = this.player.maxHealth / 3;
		BattleScreen.statusUpdate("The " + this.enemy.name + " has killed you.");
		BattleScreen
				.statusUpdate("A townsman found your corpse, and rushed you to Dr. Ked");
		BattleScreen
				.statusUpdate("Using his \"medical\" skills, he revived you. And looted your corpse.");
		BattleScreen
				.statusUpdate("You hear faintly in the background: \"Th--- -ou f-- si-- --e --yperio- -ew-U system.");
	}

	private void ondeath() {
		this.enemy.death();
		BattleScreen.statusUpdate("You beat the " + this.enemy.name + "!");
		double award = this.enemy.reward();
		this.player.bits += award;
		BattleScreen.playerAward(award, this.enemy.name);
		this.battling = false;
	}

	private Action chatHandler(String input, Player player, Enemy enemy,
			Scanner scnr) {
		Action out = null;
		String[] cmd = input.toLowerCase().split("\\s+");
		switch (cmd[0]) {
			case "run":
				boolean canRun = enemy.tryRun(player);
				if (canRun) {
					BattleScreen.statusUpdate("Got away safely.");
					this.battling = false;
				} else {
					BattleScreen.statusUpdate("Can't run!");
				}
				break;
			case "item":
			case "i":
			case "inv":
			case "inventory":
				GeneralScreen.inventory(player, "Use");
				String l = scnr.nextLine();
				try {
					int i = Integer.parseInt(l) - 1;
					Item item = player.inv.getItemByID(i);
					if (item.usable.equals(Item.usableWhere.Battle)
							|| item.usable.equals(Item.usableWhere.Anywhere)) {
						player = item.use(player);
						player.inv.deleteItem(i);
					} else {
						BattleScreen.statusUpdate("You can't use that here!");
					}
				} catch (NumberFormatException e) {
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
				} catch (NullPointerException e) {
				}
				break;
			case "exit":
			case "stop":
				BattleScreen.statusUpdate("Bye bye");
				System.exit(0);
				break;
			case "action":
			case "attack":
			case "fight":
				try {
					// Used a number, like `attack 2`
					int k = Integer.parseInt(cmd[1]) - 1;
					out = player.getMove(k);
				} catch (NumberFormatException e) {
					// Used a word, like `attack slash`
					out = player.getMoveByName(cmd[1]);

				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					BattleScreen.statusUpdate("Please input a valid action");
				}

				break;
			case "skip":
			case "pass":
				BattleScreen.statusUpdate("Player skipped their turn");
				out = new Action();
				break;
			case "moves":
				GeneralScreen.moves(player);
				break;
			default:
				Action attempt = player.getMoveByName(cmd[0]);
				if (attempt != null) {
					out = attempt;
				} else {
					BattleScreen.statusUpdate("Unknown command, sorry.");
				}
				break;
		}
		return out;
	}

	private int attack(Action action, boolean p) {
		if (p) {
			return this.player.dealtAction(action);
		} else {
			return this.enemy.dealtAction(action);
		}
	}

}
