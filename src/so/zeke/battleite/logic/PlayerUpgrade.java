package so.zeke.battleite.logic;

public class PlayerUpgrade {

	public static String stringify(types type) {
		switch (type) {
			case Damage:
				return "Damage Boost";
			case MaxHealth:
				return "Max Health Increase";
			default:
				return null;

		}
	}

	public String description() {
		switch (this.type) {
			case Damage:
				return "Boosts your damage by " + this.change + " dmg.";
			case MaxHealth:
				return "Increases your max health by " + this.change + " HP.";
			default:
				return "";

		}
	}

	public static enum types {
		MaxHealth, Damage
	}

	public double change;
	public types type;

	public PlayerUpgrade(types type, double change) {
		this.change = change;
		this.type = type;
	}

}
