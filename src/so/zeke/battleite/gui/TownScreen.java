package so.zeke.battleite.gui;

import so.zeke.battleite.logic.Player;

public class TownScreen {

	public static void header(Player player) {
		System.out.print("| Battleite | Health " + player.health + "/"
				+ player.maxHealth + " | " + String.format("%.2f", player.bits)
				+ " bits |");
		System.out.println(" " + player.inv.size() + " items in your bag |");

	}

	public static void healerNoAfford() {
		System.out.println("I ain't made of money. Come back when ya got some.");
	}

	public static void healer(double per, double cost) {

		String bits = String.format("%.2f", cost);
		if (per == 1) {
			System.out
					.println("Hello, I'm Dr. Ked. I don't have a medical license, \nbut I still got a lot of medical.");
			System.out.println("Come to me and I'll fix you up, more or less.");
		} else if (per >= 0.75) {
			System.out.println("Ah, 'tis but a flesh wound. I can fix you up for "
					+ bits + " bits.");
		} else if (per >= 0.60) {
			System.out.println("You're not looking too good. Toss me " + bits
					+ " bucks and I'll get ya back up");
		} else if (per >= 0.45) {
			System.out.println("Back from a night of battling evil I see?");
			System.out.println("Clear ya up for only " + bits);
		} else if (per >= 0.25) {
			System.out
					.println("I'm legally obligated to tell you I ain't a real doctor.");
			System.out.println("However, I can probably save you. " + bits + " bits");
		} else if (per <= 0.25) {
			System.out
					.println("Well, according to these charts, you should be dead.");
			System.out.println("Nonetheless, I'll save ya. (" + bits + " bits)");
		}
	}

	public static void home() {
		System.out.println("Places to go: <1> Merchant <2> Dr. Ked <3> Dungeon");
		System.out.println("Things to do: moves, inv");
	}

	public static void dungeons(boolean goblin, boolean cave, boolean discord,
			boolean beatDiscord) {
		System.out.print("<1> Ice Cave");
		if (goblin) {
			System.out.print(" <2> Goblin Town");
		}
		if (cave) {
			System.out.print(" <3> Woodlands Cave");
		}
		if (discord && beatDiscord) {
			System.out.print(" <4> Discord's Lair");
		} else if (discord) {
			System.out.print(" <4> The Lair of Chaos");
		}
		System.out.println();
	}

	public static void dungeonNotUnlocked() {
		System.out.println("You can't play dungeons you haven't unlocked!");
	}

	public static void mobSelection() {
		System.out.println("<1> Imp <2> Penguin <3> Mouse <4> Goblin <5> Wolf");
	}

	public static void prompt() {
		System.out.print("> ");
	}

}
