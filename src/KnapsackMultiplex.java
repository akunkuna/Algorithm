import java.util.ArrayList;
import java.util.List;

public class KnapsackMultiplex {
	public static final List<Item> items;
	public static final List<Integer> itemsNumLimts;
	
	public static final int MAX_WEIGHT = 5;
	public static final int ITEM_NUM;
	
	public static int[][] f;
	public static int[][] f_num;
	
	static {
		items = new ArrayList<Item>();
		items.add(new Item("A", 10, 1));
		items.add(new Item("B", 20, 2));
		items.add(new Item("C", 40, 3));
		items.add(new Item("D", 80, 4));
		
		// limits for used time of each item
		itemsNumLimts = new ArrayList<Integer>();
		itemsNumLimts.add(2);
		itemsNumLimts.add(2);
		itemsNumLimts.add(2);
		itemsNumLimts.add(2);
		
		ITEM_NUM = 4;
		f = new int[ITEM_NUM+1][MAX_WEIGHT+1];
		f_num = new int[ITEM_NUM+1][MAX_WEIGHT+1];
		
		for (int i=0; i<ITEM_NUM+1; i++)
			for (int j=0; j<MAX_WEIGHT+1; j++)
				f[i][j] = 0;
		
		for (int i=0; i<ITEM_NUM+1; i++)
			for (int j=0; j<MAX_WEIGHT+1; j++)
				f_num[i][j] = 0;
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
		System.out.println("Used times:");
		System.out.print("  ");
		for (int i=0; i<MAX_WEIGHT+1; i++) {
			System.out.printf("%4d", i);
		}
		System.out.println();
		for (int i=0; i<ITEM_NUM+1; i++) {
			System.out.print(i + " ");
			for (int j=0; j<MAX_WEIGHT+1; j++) {
				System.out.printf("%4d", f_num[i][j]);
			}
			System.out.println();
		}
	}
	
	// added use time limit
	public static void cal(List<Item> items, int max) {
		for (int i=1; i<ITEM_NUM+1; i++) {
			for (int j=1; j<MAX_WEIGHT+1; j++) {
				if (items.get(i-1).weight > j) {
					f[i][j] = f[i-1][j];
				} else {
					int f1 = max (f[i-1][j], f[i-1][j-items.get(i-1).weight] + items.get(i-1).value);
					int f2 = f[i][j-items.get(i-1).weight] + items.get(i-1).value;
					int f3 = f[i][j-items.get(i-1).weight];
					int usedNum1 = 1;
					int usedNum2 = f_num[i][j-items.get(i-1).weight] + 1;
					int usedNum3 = f_num[i][j-items.get(i-1).weight];
					
					if (usedNum2 <= itemsNumLimts.get(i-1).intValue()) {
						if (f1 > f2) {
							f[i][j] = f1;
							f_num[i][j] = usedNum1;
						}else {
							f[i][j] = f2;
							f_num[i][j] = usedNum2;
						}
					} else {
						if (f1 > f3) {
							f[i][j] = f1;
							f_num[i][j] = usedNum1;
						}else {
							f[i][j] = f3;
							f_num[i][j] = usedNum3;
						}
					}
				}
			}
		}
	}
	
	public static int max(int a, int b) {
		return a > b ? a : b;
	}

}
