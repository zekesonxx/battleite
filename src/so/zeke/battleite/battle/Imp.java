package so.zeke.battleite.battle;

import so.zeke.battleite.gui.BattleScreen;

public class Imp extends Enemy {

	public Imp() {
		super("Imp", 20, 7);
	}

	public void death() {
		BattleScreen.statusUpdate("[Imp] Noooo");
	}

	public Action takeAction(int playerHealth) {
		if (this.health >= 5) {
			return new Action(new Attack("Scratch", 3, 0, Damage.Normal));
		} else {
			return new Action(new Attack("Super Scratch", 5, 0, Damage.Normal));
		}

	}

}
