import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sample1 {

	public static void main(String[] args) {
		String line = "zero;two;five;seven;eight;four";
		
		Map<String, Integer> map = new HashMap<>() {
			{
				put("zero", 0);
				put("one", 1);
				put("two", 2);
				put("three", 3);
				put("four", 4);
				put("five", 5);
				put("six", 6);
				put("seven", 7);
				put("eight", 8);
				put("nine", 9);
			}
		};
		
		List<String> list = Arrays.asList(line.split(";"));
		for (int i = 0; i < list.size(); i++) {
			System.out.print(map.get(list.get(i)));
		}

	}

}
