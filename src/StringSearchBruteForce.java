import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StringSearchBruteForce {

	public static void main(String[] args) {
		BufferedReader reader = null; 
		String search = "ABC";
		try {
			String cd = new File(".").getAbsoluteFile().getParent();
			System.out.println("current directory:" + cd);
			String path = cd + "/src/stringsearch_bruteforce_input.txt";
			reader = new BufferedReader(new FileReader(path));
			String line;
			int line_count = 0;
			while((line = reader.readLine()) != null) {
				line_count++;
				System.out.println(line_count + ": " + line + ", found position: " + cal(line, search));
				
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
		for (int i=0; i<line.length()-search.length()+1; i++) {
			int j=0;
			for (; j<search.length(); j++) {
				char p = line.charAt(i+j);
				char q = search.charAt(j);
				if (p != q) {
					break;
				}
			}
			if (j == search.length()) {
				return i;
			}
		}
		return res;
	}

}
