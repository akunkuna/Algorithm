import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sum3 {

	public static void main(String[] args) {
		BufferedReader reader = null;
		System.out.println("read input file");
		String cd = new File(".").getAbsoluteFile().getParent();
		String path = cd + "/src/3sum_input.txt";
		//System.out.println(path);
		int[] input = null;
		int target = 0;
		List<List<Integer>> result;
		
		try {
			reader = new BufferedReader(new FileReader(path));
			String line;
			if((line = reader.readLine()) != null) {
				String[] ss = line.split(" ");
				input = new int[ss.length];
				for (int i = 0; i < ss.length; i++) {
					input[i] = Integer.parseInt(ss[i]);
				}
			}
			if ((line = reader.readLine()) != null) {
				target = Integer.parseInt(line);
			}
			
			System.out.print("input:");
			for (int i = 0; i < input.length; i++) {
				System.out.print(input[i] + " ");
			}
			System.out.println();
			System.out.print("target:" + target);
			
			result = sum3Cal(input, target);
			
			System.out.println();
			System.out.println("result:");
			for (int i = 0; i < result.size(); i++) {
				List<Integer> list = result.get(i);
				for (int j = 0; j < list.size(); j++) {
					System.out.print(list.get(j) + " ");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public static List<List<Integer>> sum2Cal(int[] input, int target, int l, int r) {
		// key: input value. value: input index
		Map<Integer, Integer> map = new HashMap<>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		
		for (int i = l; i <= r; i++) {
			if (map.containsKey(target - input[i]) && map.get(target - input[i]) <= r) {
				List<Integer> temp = new ArrayList<>();
				temp.add(target - input[i]);
				temp.add(input[i]);
				res.add(temp);
			} else {
				map.put(input[i], i);
			}
		}
		
		return res;
	}
	
	public static List<List<Integer>> sum3Cal(int[] input, int target) {
		Arrays.sort(input);
		List<List<Integer>> res3sum = new ArrayList<List<Integer>>();
		for (int i = 0; i < input.length; i++) {
			if (i > 0 && input[i] == input[i-1]) {
				continue;
			}
			List<List<Integer>> res2sum = new ArrayList<List<Integer>>();
			res2sum = sum2Cal(input, target - input[i], i + 1, input.length - 1);
			for (int j = 0; j < res2sum.size(); j++) {
				List<Integer> temp = res2sum.get(j);
				if (temp.size() == 2) {
					temp.add(input[i]);
					res3sum.add(temp);
					//temp.clear(); -> bug!!!
				}
			}
		}		
		return res3sum;
	}

}











