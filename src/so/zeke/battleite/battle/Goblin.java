package so.zeke.battleite.battle;

public class Goblin extends Enemy {

	public Goblin() {
		super("Goblin", 15, 5.83);
	}

	public Action takeAction(int playerHealth) {
		Action club = new Action(new Attack("Club", 3, .21, Damage.Melee));
		Action maim = new Action(new Attack("Maim", 10, 0, Damage.Bleed,
				new Effect(EffectType.Bleed, 4, 5, 1)));
		int ran = (int) (Math.random() * 100);
		if (ran < 32 && playerHealth > 15) {
			this.reward += 5;
			return maim;
		} else {
			return club;
		}
	}
}
