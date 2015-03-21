package so.zeke.battleite.battle;

public class Bear extends Enemy {

	public Bear() {
		super("Bear", 70, 45.45);
	}

	public int dealtAction(Action action) {
		return super.dealtAction(action);
	}

	public int actionModifier(Damage type, int dmg) {
		if (type == Damage.Ice) { // 135% effective.
			return (int) ((double) dmg * 1.35);
		} else {
			return dmg;
		}
	}

	public Action takeAction(int playerHealth) {
		return new Action(new Attack("Scratch", 9, .75, Damage.Melee));
	}

}
