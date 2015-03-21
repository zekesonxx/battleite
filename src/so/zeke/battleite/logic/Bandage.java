package so.zeke.battleite.logic;

import java.util.List;

import so.zeke.battleite.battle.Action;
import so.zeke.battleite.battle.Effect;
import so.zeke.battleite.battle.EffectType;
import so.zeke.battleite.battle.Heal;

public class Bandage extends Item {

	public Bandage() {
		super("Bandage", "Patches any bleeding and heals 1 HP", 4,
				Item.usableWhere.Anywhere);
	}

	public Player use(Player player) {
		player.dealtAction(new Action(new Heal(1)));
		List<Effect> effects = player.effects;
		for (int i = 0; i < effects.size(); i++) {
			Effect effect = effects.get(i);
			if (effect.type == EffectType.Bleed) {
				player.effects.remove(i);
			}
		}
		return player;
	}

}
