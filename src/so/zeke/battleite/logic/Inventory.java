package so.zeke.battleite.logic;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	public List<Item> items = new ArrayList<>();

	public int size() {
		return this.items.size();
	}

	public Item getItemByID(int i) {
		return this.items.get(i);
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void setItem(int i, Item item) {
		this.items.add(i, item);
	}

	public void deleteItem(int i) {
		this.items.remove(i);
	}

}
