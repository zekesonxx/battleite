package so.zeke.battleite.battle;

public class Mouse extends Enemy {

	public Mouse() {
		super("Mouse", 40, 35.50);
	}

	public Action takeAction(int playerHealth) {
		Action bite = new Action(new Attack("Bite", 4, .142, Damage.Melee,
				new Effect(EffectType.Bleed, 1, 2, .3)));

		return bite;
	}

}
