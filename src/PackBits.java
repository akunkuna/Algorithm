import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PackBits {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		if (args.length != 1) {
			System.out.println("args format wrong!");
			return;
		}
		
		String command = args[0];
		if (command.equals("compress")) {
			compress(reader, writer);
		} else if (command.equals("decompress")) {
			decompress(reader, writer);
		} else {
			System.out.println("there is no command: " + command);
			return;
		}
	}
	
	public static void compress(BufferedReader reader, BufferedWriter writer) {
		String cd = new File(".").getAbsoluteFile().getParent();
		String path = cd + "/src/packbits_compress_input.txt";
		
		try {
			reader = new BufferedReader(new FileReader(path));
			writer = new BufferedWriter(
					new FileWriter("bin/packbits_compress_output.txt"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				String result = "";
				Character curC = null;
				Character nextC = null;
				int s = 0;
				int d = 0;
				int i;
				for (i = 0; i < line.length() - 1; i++) {
					curC = line.charAt(i);
					nextC = line.charAt(i + 1);

					if (curC == nextC) {
						s ++;
						if (d > 0) {
							result += "-" + d + line.substring(i - d, i);
						}
						d = 0;
					} else {
						if (s > 0) {
							result += (s + 1) + curC.toString();
						} else {
							d ++;
						}
						s = 0;
					}
				}
				if (s > 0) {
					result += (s + 1) + curC.toString();
				} else {
					result += "-" + (d + 1) + line.substring(i - d);// actually i+1-(d+1)
				}
				writer.write(line + " -> " + result);
				System.out.print(line + " -> " + result);
				writer.newLine();
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
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void decompress(BufferedReader reader, BufferedWriter writer) {
		String cd = new File(".").getAbsoluteFile().getParent();
		String path = cd + "/src/packbits_decompress_input.txt";
		
		try {
			reader = new BufferedReader(new FileReader(path));
			writer = new BufferedWriter(
					new FileWriter("bin/packbits_decompress_output.txt"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				String result = "";
				int i;
				boolean isDiff = false;
				int n = 0;
				for (i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '-') {
						isDiff = true;
					} else if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
						n = n * 10 + (line.charAt(i) - '0');
					} else {
						if (isDiff) {
							result += line.substring(i, i + n);
						} else {
							for (int j = 0; j < n; j++) {
								result += line.charAt(i);
							}
						}
						isDiff = false;
						n = 0;
					}
				}
				writer.write(line + " -> " + result);
				System.out.print(line + " -> " + result);
				writer.newLine();
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
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
