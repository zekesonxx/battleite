package so.zeke.battleite.logic;

public class MerchantItem {

	public enum types {
		Item, Upgrade
	}

	public types type;
	public Item item;
	public PlayerUpgrade upgrade;
	public double price;
	public int quantity;

	public MerchantItem(Item item, double price) {
		this(item, price, 1);
	}

	public MerchantItem(Item item, double price, int quantity) {
		this.type = types.Item;
		this.item = item;
		this.price = price;
		this.quantity = quantity;
	}

	public MerchantItem(PlayerUpgrade upgrade, double price) {
		this(upgrade, price, 1);
	}

	public MerchantItem(PlayerUpgrade upgrade, double price, int quantity) {
		this.type = types.Upgrade;
		this.upgrade = upgrade;
		this.price = price;
		this.quantity = quantity;
	}

}
