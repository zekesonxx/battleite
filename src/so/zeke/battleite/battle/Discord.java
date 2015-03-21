package so.zeke.battleite.battle;

public class Discord extends Enemy {

	public Discord() {
		super("Discord", 250, 200);
	}

	public Action takeAction(int playerHealth) {
		if (this.health > 200) {
			return new Action(new Attack("Shower", -10, 0, Damage.Normal));
		} else if (this.health > 195) {
			return new Action(new Attack("Poke", 1, 0, Damage.Melee));
		} else if (this.health > 160) {
			return new Action(new Attack("Trick", 11, 0, Damage.Melee));
		} else if (this.health > 120) {
			return new Action(new Attack("Trick", 13, 0, Damage.Fire));
		} else {
			return new Action(new Attack("Plunderseeds", 3, 0, new Effect(
					EffectType.Burn, 6, 2, 1)));
		}
	}

}
