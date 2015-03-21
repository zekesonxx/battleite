package so.zeke.battleite.battle;

import java.util.ArrayList;
import java.util.List;

import so.zeke.battleite.gui.BattleScreen;
import so.zeke.battleite.logic.Player;

public class Enemy {
	public Enemy(String name, int health, double reward) {
		this.health = health;
		this.maxHealth = health;
		this.name = name;
		this.reward = reward;
	}

	public int health;
	public int maxHealth;
	public double reward;
	public List<Effect> effects = new ArrayList<>();
	public String name;

	public int dealtAction(Action action) {
		switch (action.type) {
			case Attack:
				int dmg = this.actionModifier(action.attack.type,
						action.attack.calculateDamage());
				this.health -= dmg;
				if (action.attack.effect != null
						&& action.attack.effect.isGettingApplied()) {
					// effect to handle
					this.effects.add(action.attack.effect);
					BattleScreen.statusUpdate(this.name
							+ Effect.added(action.attack.effect.type));
				}
				return dmg;
			case Heal:
				return 1;
			default:
				return 0;
		}

	}

	public double reward() {
		return this.reward;
	}

	public int actionModifier(Damage type, int dmg) {
		return dmg;
	}

	public boolean tryRun(Player player) {
		return true;
	}

	public void ontick() {
		for (int i = 0; i < this.effects.size(); i++) {
			Action evt = this.effects.get(i).ontick(this);
			if (evt == null) {
				BattleScreen.statusUpdate(this.name
						+ Effect.removed(this.effects.get(i).type));
				this.effects.remove(i); // it ran out

			} else {
				this.dealtAction(evt);
			}
		}
	}

	public void death() {
	}

	public Action takeAction(int playerHealth) {
		return new Action();
	}
}
