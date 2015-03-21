import java.util.Scanner;

import so.zeke.battleite.battle.Action;
import so.zeke.battleite.battle.Attack;
import so.zeke.battleite.battle.Damage;
import so.zeke.battleite.battle.Effect;
import so.zeke.battleite.battle.EffectType;
import so.zeke.battleite.logic.HealthPotion;
import so.zeke.battleite.logic.Player;
import so.zeke.battleite.logic.Town;

public class Main {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to Battleite");
		System.out.println("In this world, you are a fighter.");
		System.out
				.println("Your goal is to defeat Eris, a wicked god nested deep in the lands\n"
						+ "It won't be a easy accomplishment, but if you persevere you shall \nsucceed. "
						+ "Along the way you'll meet many a folk, including a refugee \nfrom the war-torn planet Pandora.\n"
						+ "I send my best wishes to your attempts at success.");
		System.out
				.println("I'm sending you in with some moves and a health potion.\n"
						+ "I also got you about 6 bits. It's not much, but it'll help. Good luck.");
		System.out.println("Hit Enter to start playing Battleite.");
		scnr.nextLine();

		Player player = new Player();
		player.setMove(0, new Action(new Attack("Slash", 5, .25, new Effect(
				EffectType.Bleed, 1, 1, 0.42))));
		player.setMove(1, new Action(new Attack("Punch", 3, .56, Damage.Melee)));
		player.setMove(2, new Action(new Attack("Fireball", 6, .13, Damage.Fire,
				new Effect(EffectType.Burn, 2, 3, 0.55))));

		player.inv.addItem(new HealthPotion());
		player.ontick();

		player = Town.mainTown(player, scnr);

		scnr.close();
	}

}
