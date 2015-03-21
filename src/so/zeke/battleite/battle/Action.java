package so.zeke.battleite.battle;

public class Action {

	public enum kind {
		Attack, Heal, Pass // ponder tongue
	}

	public String name;
	public kind type;
	public Attack attack;
	public Heal heal;
	public int cooldown;
	public int curCooldown = 0;

	public Action() {
		this.type = kind.Pass;
		this.name = "Pass";
	}

	public Action(Attack attack) {
		this(attack, 0);
	}

	public Action(Attack attack, int cooldown) {
		this.type = kind.Attack;
		this.name = attack.name;
		this.attack = attack;
		this.cooldown = cooldown;
	}

	public Action(Heal heal) {
		this(heal, 0);
	}

	public Action(Heal heal, int cooldown) {
		this.name = heal.name;
		this.type = kind.Heal;
		this.heal = heal;
		this.cooldown = cooldown;
	}

	public Attack attack() {
		return this.attack;
	}

	public Heal heal() {
		return this.heal;
	}

}
