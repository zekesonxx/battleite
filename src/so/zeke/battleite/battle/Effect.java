package so.zeke.battleite.battle;

import so.zeke.battleite.gui.BattleScreen;
import so.zeke.battleite.logic.Player;

public class Effect {

	public EffectType type;
	public int intensity;
	public int length;
	public double probability;

	public Effect(EffectType type, int intensity, int length, double probability) {
		this.type = type;
		this.intensity = intensity;
		this.length = length;
		this.probability = probability;
	}

	public boolean isGettingApplied() {
		return (Math.random() <= probability);
	}

	private Action handletick(String name) {
		switch (this.type) {
			case Bleed:
				BattleScreen.statusUpdate(name + " took " + this.intensity
						+ " bleed damage");
				return new Action(new Attack("Bleed Damage", this.intensity, 0,
						Damage.Bleed));
			case Burn:
				BattleScreen.statusUpdate(name + " took " + this.intensity
						+ " burn damage");
				return new Action(new Attack("Burn Damage", this.intensity, 0,
						Damage.Fire));
			default:
				return new Action();
		}
	}

	public Action ontick(Enemy enemy) {
		if (this.length == 0) {
			return null;
		}
		this.length--;
		return this.handletick(enemy.name);

	}

	public Action ontick(Player player) {
		if (this.length == 0) {
			return null;
		}
		this.length--;
		return this.handletick("You");

	}

	// Static Methods

	/**
	 * Turns a type into it's relevant verb.
	 * 
	 * @param type
	 *          Type of effect
	 * @return Verb
	 */
	public static String wordify(EffectType type) {
		switch (type) {
			case Bleed:
				return "Bleed";
			case Burn:
				return "Burn";
			default:
				return "unknown effect";
		}
	}

	public static String added(EffectType type) {
		switch (type) {
			case Bleed:
				return " has started bleeding.";
			case Burn:
				return " got burned.";
			default:
				return " got whatever.";
		}
	}

	public static String removed(EffectType type) {
		switch (type) {
			case Bleed:
				return " is no longer bleeding.";
			case Burn:
				return " is no longer burned.";
			default:
				return " is no longer whatever.";
		}
	}

}
