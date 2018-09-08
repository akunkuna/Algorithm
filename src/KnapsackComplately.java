import java.util.ArrayList;
import java.util.List;

public class KnapsackComplately {
public static final List<Item> items;
	
	public static final int MAX_WEIGHT = 5;
	public static final int ITEM_NUM;
	
	public static int[][] f;
	
	static {
		items = new ArrayList<Item>();
		items.add(new Item("A", 10, 1));
		items.add(new Item("B", 20, 2));
		items.add(new Item("C", 40, 3));
		items.add(new Item("D", 80, 4));
		
		ITEM_NUM = 4;
		f = new int[ITEM_NUM+1][MAX_WEIGHT+1];
		
		for (int i=0; i<ITEM_NUM+1; i++)
			for (int j=0; j<MAX_WEIGHT+1; j++)
				f[i][j] = 0;
	}

	public static void main(String[] args) {
		System.out.println("Items:");
		for (Item it : items) {
			System.out.println(it);
		}
		
		cal(items, MAX_WEIGHT);
		
		System.out.println("Result:");
		System.out.print("  ");
		for (int i=0; i<MAX_WEIGHT+1; i++) {
			System.out.printf("%4d", i);
		}
		System.out.println();
		for (int i=0; i<ITEM_NUM+1; i++) {
			System.out.print(i + " ");
			for (int j=0; j<MAX_WEIGHT+1; j++) {
				System.out.printf("%4d", f[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void cal(List<Item> items, int max) {
		for (int i=1; i<ITEM_NUM+1; i++) {
			for (int j=1; j<MAX_WEIGHT+1; j++) {
				if (items.get(i-1).weight > j) {
					f[i][j] = f[i-1][j];
				} else {
					// it looks like that f[i][j-items.get(i-1).weight] must bigger than
					// f[i-1][j-items.get(i-1).weight]
					/*int f1 = max (f[i-1][j], f[i-1][j-items.get(i-1).weight] + items.get(i-1).value);
					int f2 = f[i][j-items.get(i-1).weight] + items.get(i-1).value;
					f[i][j] = max (f1, f2);*/
					f[i][j] = max (f[i-1][j], f[i][j-items.get(i-1).weight] + items.get(i-1).value);
					
				}
			}
		}
	}
	
	public static int max(int a, int b) {
		return a > b ? a : b;
	}

}
