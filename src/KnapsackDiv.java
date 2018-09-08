import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KnapsackDiv {
	
	public static final List<Item> items;
	
	public static final int MAX_WEIGHT = 5;
	
	static {
		items = new ArrayList<Item>();
		items.add(new Item("A", 360, 4));
		items.add(new Item("B", 400, 5));
		items.add(new Item("C", 200, 2));
		items.add(new Item("D", 300, 2));
	}

	public static void main(String[] args) {
		System.out.println("Items:");
		for (Item it : items) {
			System.out.println(it);
		}
		/*System.out.println("After sorting by value/weight:");
		Collections.sort(items, Comparator.reverseOrder());
		for (Item it : items) {
			System.out.println(it);
		}*/	
		System.out.println("Total value: " + cal(items, MAX_WEIGHT));
	}

	public static int cal(List<Item> items, int max) {
		int sumValue = 0;
		if (items.size() < 0) {
			return sumValue;
		}
		Collections.sort(items, Comparator.reverseOrder());
		int sumWeight = 0;
		for (Item it : items) {
			if (sumWeight > max) {
				break;
			}
			int v = 0;
			int w = 0;
			if (it.weight + sumWeight <= max) {
				w = it.weight;
				v = it.value;
			} else {
				w = max - sumWeight;
				v = (w*it.value)/it.weight;
			}
			sumValue += v;
			sumWeight += w;
			System.out.println(it.name + ", " + w + " selected.");
		}
		return sumValue;
	}

}
