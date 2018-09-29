import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Sample2Date implements Comparable<Sample2Date>{
	public String s;
	public Sample2Date(String s) {
		this.s = s;
	}
	
	@Override
	public int compareTo(Sample2Date other) {
		return this.s.length() - other.s.length();
	}
}

public class Sample2 {

	public static void main(String[] args) {
		//String input = "1\nSalt Lake City\nseattle\n\nDenver\nPhoenix";
		String input = "2\nHello World\n\nCodeEval\nQuick Fox\nA\nSan Francisco";
		
		//System.out.println(input);
		
		System.out.println("method 1 Result:");
		
		List<String> lines = Arrays.asList(input.split("\\n"));
		int num = -1;
		List<String> result = new LinkedList<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (i == 0) {
				num = Integer.parseInt(line);
			} else if (line.length() > 0){
				int j = 0;
				while (j < result.size()) {
					if (result.get(j).length() < line.length()) {
						break;
					} else {
						j ++;
					}
				}
				result.add(j, line);
			}
		}
		for (int i = 0; i < num; i++) {
			System.out.println(result.get(i));
		}
		
		System.out.println();
		System.out.println("method 2 Result:");
		num = -1;
		List<Sample2Date> result2 = new LinkedList<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (i == 0) {
				num = Integer.parseInt(line);
			} else if (line.length() > 0){
				result2.add(new Sample2Date(line));
			}
		}
		Collections.sort(result2, Comparator.reverseOrder());
		for (int i = 0; i < num; i++) {
			System.out.println(result2.get(i).s);
		}

	}

}
