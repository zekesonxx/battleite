package so.zeke.battleite.battle;

public class MilkCloud extends Enemy {

	public MilkCloud() {
		super("Chocolate Milk Cloud", 25, 16);
	}

	public Action takeAction(int playerHealth) {
		return new Action(new Attack("Rain", 6, .64, Damage.Normal));
	}

}
