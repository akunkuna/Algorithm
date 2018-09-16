import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Sum2 {

	public static void main(String[] args) {
		BufferedReader reader = null;
		System.out.println("read input file");
		String cd = new File(".").getAbsoluteFile().getParent();
		String path = cd + "/src/2sum_input.txt";
		//System.out.println(path);
		int[] input = null;
		int target = 0;
		int[] result;
		
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
			
			result = cal(input, target);
			
			System.out.println();
			System.out.print("result:");
			for (int i = 0; i < result.length; i++) {
				System.out.print(result[i] + " ");
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
	
	public static int[] cal(int[] input, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int[] res = new int[2];
		
		for (int i = 0; i < input.length; i++) {
			if (map.containsKey(target - input[i])) {
				res[0] = target - input[i];
				res[1] = input[i];
				return res;
			} else {
				map.put(input[i], i);
			}
		}
		
		return null;
	}

}











