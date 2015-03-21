package so.zeke.battleite.battle;

public class Chair extends Enemy {

	public Chair() {
		super("Chair", 32, 20.20);
	}

	public Action takeAction(int playerHealth) {
		return new Action(new Attack("Hat", 9, .59, Damage.Bleed));
	}

}
