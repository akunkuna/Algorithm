import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StringSearchKMP {

	public static void main(String[] args) {
		BufferedReader reader = null; 
		String search = "ABCABD";
		try {
			String cd = new File(".").getAbsoluteFile().getParent();
			System.out.println("current directory:" + cd);
			String path = cd + "/src/stringsearch_kmp_input.txt";
			reader = new BufferedReader(new FileReader(path));
			String line;
			int line_count = 0;
			while((line = reader.readLine()) != null) {
				line_count++;
				System.out.println(line_count + ": " + line);
				int found = cal(line, search);
				System.out.println("found position: " + found);
				
			}
			System.out.println("end");
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
	
	public static int cal(String line, String search) {
		int res = -1;
		if (line.equals("") || search.equals("")) {
			return res;
		}
		int[] skip = new int[search.length()];
		skip[0] = 0;
		int i;
		int j = 0;
		for (i=1; i<search.length(); i++) {
			char p = search.charAt(i);
			char q = search.charAt(j);
			if (p==q) {
				j++;
				skip[i] = j;
			} else {
				skip[i] = 0;
				j = 0;
			}
		}
		System.out.print("pattern: " + search + ", skip table: ");
		for (i=0; i<skip.length; i++) {
			System.out.print(skip[i]);
		}
		System.out.println();
		
		System.out.println("search start:");
		j = 0;
		i = 0;
 		while (i<line.length()) {
			while (j<search.length()) {
				char p = line.charAt(i);
				System.out.println("p:" + p + ", index of string:" + (i));
				char q = search.charAt(j);
				System.out.println("q:" + q + ", index of search:" + (j));
				if (p != q) {
					if (j>0 && skip[j-1]>0) {
						System.out.println("not match");
						j = skip[j-1];
					} else {
						System.out.println("not match");
						j = 0;
						i = i + 1;
					}
					break;
				} else {
					j++;
					i++;
				}
			}
			if (j == search.length()) {
				return i - j;
			} 
		}
		return res;
	}

}
