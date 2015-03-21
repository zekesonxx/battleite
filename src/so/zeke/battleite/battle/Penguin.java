package so.zeke.battleite.battle;


public class Penguin extends Enemy {

	public Penguin() {
		super("Penguin", 56, 25);
	}

	public int dealtAction(Action action) {
		return super.dealtAction(action);
	}

	public int actionModifier(Damage type, int dmg) {
		if (type == Damage.Fire) {
			return dmg * 2;
		} else if (type == Damage.Ice) { // 75%
			return (int) ((double) dmg * 0.75);
		} else {
			return dmg;
		}
	}

	public Action takeAction(int playerHealth) {
		return new Action(new Attack("Freeze", 6, .352, Damage.Ice));
	}

}
