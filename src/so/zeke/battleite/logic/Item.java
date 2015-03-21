package so.zeke.battleite.logic;

public class Item {

	public static enum usableWhere {
		Battle, Town, Anywhere, Nowhere
	}

	public double value;

	public usableWhere usable;
	public String name;
	public String description;
	public double worth;

	public Item(String name, String description, double worth, usableWhere usable) {
		this.name = name;
		this.description = description;
		this.worth = worth;
		this.usable = usable;
	}

	public Player use(Player player) {
		return player;
	}

}
