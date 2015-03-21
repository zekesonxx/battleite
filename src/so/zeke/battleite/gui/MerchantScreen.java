package so.zeke.battleite.gui;

import java.util.List;

import so.zeke.battleite.logic.MerchantItem;
import so.zeke.battleite.logic.PlayerUpgrade;

public abstract class MerchantScreen {

	public static void top() {
		System.out.println("WHAT YU WANT");
	}

	public static void noAfford() {
		System.out.println("I AIN'T A CHARITY");
	}

	public static void steal() {
		System.out.println("WHO STOLE. I'LL KILL EM.");
	}

	public static void purchase() {
		System.out.println("GET GONE. DON'T YOU TELL NOBODY 'BOUT THIS PLACE");
	}

	public static void noHave() {
		System.out.println("I AIN'T GOT DAT. PICK SOMETIN ELSE.");
	}

	public static void itemListing(List<MerchantItem> items) {
		for (int i = 0; i < items.size(); i++) {
			MerchantItem item = items.get(i);
			switch (item.type) {
				case Item:
					System.out.println("<" + (i + 1) + "> "
							+ (item.quantity > 1 ? item.quantity + "x " : "")
							+ item.item.name + " | " + String.format("%.2f", item.price)
							+ " bits");
					String[] lines = item.item.description.split("\\r?\\n");
					for (int j = 0; j < lines.length; j++) {
						System.out.println(" | " + lines[j]);
					}
					break;
				case Upgrade:
					System.out.println("<" + (i + 1) + "> "
							+ PlayerUpgrade.stringify(item.upgrade.type) + ", "
							+ String.format("%.2f", item.price) + " bits");
					System.out.println(" | " + item.upgrade.description());
					break;
				default:
					break;
			}
		}
	}

	public static void prompt() {
		System.out.print("Pick a item > ");
	}

	public static void invalid() {
		System.out.println("Invalid choice.");
	}

}
