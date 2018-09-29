import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TurnLightOff {

	public static void main(String[] args) {
		BufferedReader reader = null;
		String cd = new File(".").getAbsoluteFile().getParent();
		String path = cd + "/src/turnLightOff_input2.txt";
		int col = -1;
		int row = -1;
		int result = -1;
		char[][] matrix;
		int[][] press;
		
		try {
			reader = new BufferedReader(new FileReader(path));
			
			String line = "";
			String[] temp;			
			line = reader.readLine();
			temp = line.split(" ");
			row = Integer.parseInt(temp[0]);
			col = Integer.parseInt(temp[1]);
			System.out.println(row + " " + col);
			
			matrix = new char[row + 2][col + 2];
			for (int i = 0; i < row + 2; i ++) {
				for (int j = 0; j < col + 2; j ++) {
					if (i == 0 || i == row + 1 || j == 0 || j == col + 1) {
						// surrounding
						matrix[i][j] = '.';
					} else if (j == 1){
						line = reader.readLine();
						temp = line.split(" ");
						matrix[i][j] = temp[j - 1].charAt(0);
					} else {
						matrix[i][j] = temp[j - 1].charAt(0);
					}
				}
			}
			press = new int[row + 2][col + 2];
			for (int i = 0; i < row + 2; i ++) {
				for (int j = 0; j < col + 2; j ++) {
					if (i == 0 || i == row + 1 || j == 0 || j == col + 1) {
						// surrounding
						press[i][j] = 0;
					} else {
						press[i][j] = 0;
					}
				}
			}
			
			System.out.println("input matrix: ");
			for (int i = 0; i < row + 2; i ++) {
				for (int j = 0; j < col + 2; j ++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("input press: ");
			for (int i = 0; i < row + 2; i ++) {
				for (int j = 0; j < col + 2; j ++) {
					System.out.print(press[i][j] + " ");
				}
				System.out.println();
			}
			
			int[] firstRowPattern = new int[col + 1];
			for (int i = 0; i < col + 1; i ++) {
				firstRowPattern[i] = 0;
			}
			
			int n = 1;
			while (firstRowPattern[col] != 1) {
				for (int i = 1; i <= col; i ++) {
					press[1][i] = firstRowPattern[i - 1];
				}
				
				int res = guess(matrix, row, col, press);
				if (res != -1) {
					if (result == -1) {
						result = res;
					} else if (res < result){
						result = res;
					}
				}
				System.out.println("first row pattern: " + n + ", result: " + res);
				n ++;
				
				int i = 0; 
				while (firstRowPattern[i] != 0) {
					firstRowPattern[i] = 0;
					i ++;
				}
				firstRowPattern[i] = 1;
				
			}
			
			System.out.println("Minimal times: " + result);
			
			
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
	
	public static int guess(char[][] matrix, int row, int col, int[][] press) {
		// calculate press according to first row of press and matrix
		for (int i = 2; i <= row; i++) {
			for (int j = 1; j <=col; j++) {
				int value = matrix[i-1][j] == '0' ? 1 : 0;
				press[i][j] = (value + press[i-1][j] + press[i-1][j-1] + press[i-1][j+1] + press[i-2][j])%2;
			}
		}
		
		// decision if all the lights can be turned off
		// only to check the last row of matrix
		for (int j = 1; j <= row; j++) {
			int i = row;
			int value = matrix[i][j] == '0' ? 1 : 0;
			if ((value + press[i][j] + press[i][j-1] + press[i][j+1] + press[i-1][j]) % 2 != 0) {
				return -1;
			}
		}
		// calculte the times to turn off all lights
		return calNonZero(press, row, col);
	}
	
	public static int calNonZero (int[][] press, int row, int col) {
		int sum = 0;
		for (int i = 1; i <=row; i++) {
			for (int j = 1; j <=col; j++) {
				if (press[i][j] != 0) {
					sum ++;
				}
			}	
		}
		return sum;
	}

}
