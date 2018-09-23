import java.util.HashMap;
import java.util.Map;

public class NearestRGB {

	public static void main(String[] args) {
		String input = "#01ABF1";
		String result = "#";
		Map<Character, Integer> map = new HashMap<>() {
			{
				put('0', 0);
				put('1', 1);
				put('2', 2);
				put('3', 3);
				put('4', 4);
				put('5', 5);
				put('6', 6);
				put('7', 7);
				put('8', 8);
				put('9', 9);
				put('A', 10);
				put('B', 11);
				put('C', 12);
				put('D', 13);
				put('E', 14);
				put('F', 15);
			}
		};
		
		String[] s = new String[3];
		for (int i = 0, j = 1; i < 3 && j + 2 <= input.length(); i++, j+=2) {
			s[i] = input.substring(j, j + 2);
		}
		
		for (int i = 0; i < 3; i++) {
			System.out.println("input RGB " + (i + 1) + ":" + s[i]);
		}
		
		for (int i = 0; i < 3; i++) {
			Character c1  = s[i].charAt(0);
			Character c2  = s[i].charAt(1);
			int v_c1c2 = map.get(c1)*16 + map.get(c2);
			int v_c1c1 = map.get(c1)*16 + map.get(c1);
			
			int diff = v_c1c2 - v_c1c1;
			if (Math.abs(diff) <= 8) {
				// c1c2: BC, c1c1: BB, diff: 1
				s[i] = c1.toString() + c1.toString();
			} else if (diff > 8) {
				if (c1 < 'F') {
					// c1c2: 1A, c1c1: 11, diff: 9
					Character c = (char) (c1 + 1);
					s[i] = c.toString() + c.toString();
				} else {
					// Impossible
					// c1c2: F, c1c1: FF, diff: 
					s[i] = c1.toString() + c1.toString();
				}
			} else if (diff < -8) {
				if (c1 > '0') {
					// c1c2: F1, c1c1: FF, diff: -14
					Character c = (char) (c1 - 1);
					s[i] = c.toString() + c.toString();
				} else {
					// Impossible
					// c1c2: 0, c1c1: 00, diff: 
					s[i] = c1.toString() + c1.toString();
				}
			}
		}
		
		for (int i = 0; i < 3; i++) {
			result += s[i];
			System.out.println("output RGB " + (i + 1) + ":" + s[i]);
		}
		System.out.println("result:" + result);

	}

}
