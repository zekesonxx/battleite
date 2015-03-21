package so.zeke.battleite.battle;

public class Heal {

	public String name;
	public int amount;

	public Heal(int amount) {
		this("Heal", amount);
	}

	public Heal(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

}
