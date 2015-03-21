package so.zeke.battleite.battle;

public class Wolf extends Enemy {

	public Wolf() {
		super("Wolf", 25, 36.72);
	}

	public Action takeAction(int playerHealth) {
		return new Action(new Attack("Scratch", 7, .464, Damage.Melee));
	}

}
