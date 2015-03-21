package so.zeke.battleite.logic;

import java.util.ArrayList;
import java.util.List;

import so.zeke.battleite.battle.Action;
import so.zeke.battleite.battle.Attack;
import so.zeke.battleite.battle.Effect;
import so.zeke.battleite.gui.BattleScreen;

public class Player {
	public int health = 50;
	public int maxHealth = 50;

	public double bits = 6.00;

	public List<Action> moves = new ArrayList<>();
	public List<Effect> effects = new ArrayList<>();

	public Inventory inv = new Inventory();

	public void setMove(int index, Action newmove) {
		this.moves.add(index, newmove);
	}

	public List<Action> getMoves() {
		return this.moves;
	}

	public Action getMove(int index) {
		this.cooldown(index);
		return this.moves.get(index);
	}

	public String getMoveName(int index) {
		return this.moves.get(index).name;
	}

	public String[] listMoves() {
		String[] out = new String[this.moves.size()];
		for (int i = 0; i < this.moves.size(); i++) {
			out[i] = this.moves.get(i).name;
		}
		return out;
	}

	public Action getMoveByName(String name) {
		for (int i = 0; i < this.moves.size(); i++) {
			Action move = this.moves.get(i);
			if (move.name.toLowerCase().trim().equals(name.trim())) {
				this.cooldown(i);
				return move;
			}
		}
		return null; // no matches
	}

	public int dealtAction(Action action) {
		switch (action.type) {
			case Attack:
				Attack attack = action.attack();
				int dmg = attack.calculateDamage();
				this.health -= dmg;
				if (action.attack.effect != null
						&& action.attack.effect.isGettingApplied()) {
					// effect to handle
					this.effects.add(action.attack.effect);
					BattleScreen.statusUpdate("You"
							+ Effect.added(action.attack.effect.type));
				}
				return dmg;
			case Heal:
				this.health += action.heal.amount;
				if (this.health > this.maxHealth) {
					// Don't want players overhealing.
					this.health = this.maxHealth;
				}
				return 0 - action.heal.amount;
			default:
				return 0;
		}
	}

	public void cooldown(int i) {
		this.moves.get(i).curCooldown = this.moves.get(i).cooldown;
	}

	public void upgrade(PlayerUpgrade upgrade) {
		switch (upgrade.type) {
			case MaxHealth:
				this.maxHealth += (int) upgrade.change;
				break;
			case Damage:
				break;
		}
	}

	public void ontick() {
		// Move Cooldowns
		for (int i = 0; i < this.moves.size(); i++) {
			Action move = this.moves.get(i);
			if (move.curCooldown != 0) {
				this.moves.get(i).curCooldown--;
			}
		}

		// Effects
		for (int i = 0; i < this.effects.size(); i++) {
			Action evt = this.effects.get(i).ontick(this);
			if (evt == null) {
				BattleScreen.statusUpdate("You"
						+ Effect.removed(this.effects.get(i).type));
				this.effects.remove(i); // it ran out

			} else {
				this.dealtAction(evt);
			}
		}
	}
}
