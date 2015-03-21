package so.zeke.battleite.battle;

public class Attack {
	public Attack(String name, int damage, double crit) {
		this(name, damage, crit, Damage.Normal, null);
	}

	public Attack(String name, int damage, double crit, Effect effect) {
		this(name, damage, crit, Damage.Normal, effect);
	}

	public Attack(String name, int damage, double crit, Damage type) {
		this(name, damage, crit, type, null);
	}

	public Attack(String name, int damage, double crit, Damage type, Effect effect) {
		this.name = name;
		this.baseDamage = damage;
		this.critChance = crit;
		this.type = type;
		this.effect = effect;
	}

	public int baseDamage = 0;
	public String name = "Bacon";
	public double critChance = 0.0;
	public Damage type = Damage.Normal;
	public Effect effect;

	public int calculateDamage() {
		boolean isCrit = (Math.random() <= this.critChance);
		if (isCrit) {
			return (int) Math.round(this.baseDamage * 1.35);
		} else {
			return this.baseDamage;
		}
	}
}
