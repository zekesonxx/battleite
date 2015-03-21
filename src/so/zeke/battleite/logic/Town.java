package so.zeke.battleite.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import so.zeke.battleite.battle.Battle;
import so.zeke.battleite.battle.CaveDungeon;
import so.zeke.battleite.battle.DiscordsLairDungeon;
import so.zeke.battleite.battle.Goblin;
import so.zeke.battleite.battle.GoblinTownDungeon;
import so.zeke.battleite.battle.IceCaveDungeon;
import so.zeke.battleite.battle.Imp;
import so.zeke.battleite.battle.Mouse;
import so.zeke.battleite.battle.Penguin;
import so.zeke.battleite.battle.Wolf;
import so.zeke.battleite.gui.GeneralScreen;
import so.zeke.battleite.gui.MerchantScreen;
import so.zeke.battleite.gui.TownScreen;

public abstract class Town {

	public static Player mainTown(Player player, Scanner scnr) {
		Dungeon iceCave = new IceCaveDungeon();
		Dungeon goblinTown = new GoblinTownDungeon();
		Dungeon woodCave = new CaveDungeon();
		Dungeon discord = new DiscordsLairDungeon();

		boolean inTown = true;
		while (inTown) {
			TownScreen.header(player);
			TownScreen.home();
			TownScreen.prompt();
			String input = scnr.nextLine();
			switch (input) {
				case "1":
				case "merchant":
					List<MerchantItem> items = new ArrayList<>();
					items.add(new MerchantItem(new HealthPotion(), 15));
					if (iceCave.completed) {
						items.add(new MerchantItem(new LargeHealthPotion(), 35));
					}
					if (woodCave.completed) {
						items.add(new MerchantItem(new Steak(), 55));
					}
					items.add(new MerchantItem(new Bandage(),
							(iceCave.completed ? 10 : 4)));
					items.add(new MerchantItem(new PlayerUpgrade(
							PlayerUpgrade.types.MaxHealth, 10), 80));
					MerchantScreen.top();
					MerchantScreen.itemListing(items);
					MerchantScreen.prompt();

					MerchantItem it;
					String selection = scnr.nextLine();
					try {
						int k = Integer.parseInt(selection) - 1;
						it = items.get(k);
						if (player.bits < it.price) {
							MerchantScreen.noAfford();
							break;
						} else {
							player.bits -= it.price;
						}
						MerchantScreen.purchase();
						switch (it.type) {
							case Item:
								player.inv.addItem(it.item);
								break;
							case Upgrade:
								player.upgrade(it.upgrade);
								break;
							default:
								break;

						}
					} catch (NumberFormatException e) {
						// it's a string
						switch (selection) {
							case "steal":
							case "rob":
								MerchantScreen.steal();
								System.out.println();
								break;
						}
					} catch (java.lang.IndexOutOfBoundsException e) {
						MerchantScreen.noHave();
						System.out.println();
					}

					break;
				case "2":
				case "healer":
					TownScreen.header(player);
					double per = (double) player.health / (double) player.maxHealth;
					double cost = 1.76 * (((double) 1 - per) * 10);
					if (player.bits < cost && player.health != player.maxHealth) {
						// can't afford it
						TownScreen.healerNoAfford();
						scnr.nextLine();
						break;
					}
					TownScreen.healer(per, cost);
					TownScreen.prompt();
					String yn = scnr.nextLine();
					switch (yn.toLowerCase()) {
						case "y":
						case "yes":
						case "sure":
						case "indeed":
						case "quite correct good chap":
							player.health = player.maxHealth;
							player.effects = new ArrayList<>();
							player.bits -= cost;
							break;
					}
					break;
				case "3":
				case "dungeon":
					TownScreen.header(player);
					TownScreen.dungeons(iceCave.completed, goblinTown.completed,
							woodCave.completed, discord.completed);
					TownScreen.prompt();
					String dungeon = scnr.nextLine();
					switch (dungeon.toLowerCase()) {
						case "1":
						case "penguin":
							player = iceCave.enter(player, scnr);
							break;
						case "2":
						case "goblin":
							if (iceCave.completed) {
								player = goblinTown.enter(player, scnr);
							} else {
								TownScreen.dungeonNotUnlocked();
							}
							break;
						case "3":
						case "woodlands":
							if (goblinTown.completed) {
								player = woodCave.enter(player, scnr);
							} else {
								TownScreen.dungeonNotUnlocked();
							}
							break;
						case "4":
						case "discord":
						case "lair":
							if (woodCave.completed) {
								player = discord.enter(player, scnr);
							} else {
								TownScreen.dungeonNotUnlocked();
							}
							break;
					}
					break;
				case "moves":
					GeneralScreen.moves(player);
					break;
				case "inv":
				case "i":
				case "inventory":
					GeneralScreen.inventory(player, "Use");
					String l = scnr.nextLine();
					try {
						int i = Integer.parseInt(l) - 1;
						Item item = player.inv.getItemByID(i);
						if (item.usable.equals(Item.usableWhere.Town)
								|| item.usable.equals(Item.usableWhere.Anywhere)) {
							player = item.use(player);
							player.inv.deleteItem(i);
						} else {
							System.out.println("You can't use that here.");
						}
					} catch (NumberFormatException e) {
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					}
					break;

				case "_debug_mobs":
					TownScreen.header(player);
					TownScreen.mobSelection();
					TownScreen.prompt();
					String num = scnr.nextLine();
					switch (num) {
						case "1":
							player = Battle.fight(player, new Imp(), scnr);
							break;
						case "2":
							player = Battle.fight(player, new Penguin(), scnr);
							break;
						case "3":
							player = Battle.fight(player, new Mouse(), scnr);
							break;
						case "4":
							player = Battle.fight(player, new Goblin(), scnr);
							break;
						case "5":
							player = Battle.fight(player, new Wolf(), scnr);
							break;
					}
					break;

				default:
					System.out.println("Unknown Command, sorry.");
					break;
			}
		}
		System.out.println();
		return player;
	}
}
