package so.zeke.battleite.logic;

public class ClamShell extends Item {

	public ClamShell() {
		super("Clam Shell", "A clam shell. It has a dent in it.", -1,
				Item.usableWhere.Town);
	}

	public Player use(Player player) {
		player.bits += 0.01;
		System.out.println("You have obtained a single cent.");
		return player;
	}

}
