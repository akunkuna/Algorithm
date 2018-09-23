
public class GridPaint {
	public int row = 0;
	public int col = 0;
	public int [][] grid;
	public int sum = 0;

	public static void main(String[] args) {
		if (args.length < 2) {
			return;
		}
		
		GridPaint gp = new GridPaint();
		
		gp.row = Integer.parseInt(args[0]);
		gp.col = Integer.parseInt(args[1]);
		System.out.println("row:" +  gp.row + " ,col:" + gp.col);
		
		// 0: null, 9: surrounding, 1: black, 2: white
		// initial the grid
		gp.grid = new int[gp.row+2][gp.col+2];
		for (int i = 0; i < gp.row + 2; i++) {
			for (int j = 0; j < gp.col + 2; j++) {
				if (i == 0 || j == 0 || i == gp.row + 1 || j == gp.col + 1) {
					gp.grid[i][j] = 9;
				} else {
					gp.grid[i][j] = 0;
				}
			}
		}		
		gp.print();
		
		System.out.println("Calculating...");
		gp.paintGrid(1, 1);	
		System.out.println("possible pattern: " + gp.sum);

	}
	
	// paint form left to right, from up to down
	// return the possible pattern when paint the position of (x,y)
	public void paintGrid(int x, int y) {
		if (x == 0 || x > row || y == 0 || y > col) {
			return;
		}		
		int nextX;
		int nextY;		
		if (y == col) {
			nextX = x + 1;
			nextY = 1;
		} else {
			nextX = x;
			nextY = y + 1;
		}
		
		for (int c = 1; c <= 2; c++ ) {
			grid[x][y] = c;
			if (x == 1) {
				// not need to check the first row
				paintGrid(nextX, nextY);
			} else if (x < row) {
				// middle rows
				// only need to check the up grid of (x,y)
				if (hasDiffColorGrid(x - 1 , y)) {
					paintGrid(nextX, nextY);
				}
			} else if (x == row && y == col) {
				// the end position
				// need to check the up, left grid and itself of (x,y)
				if (hasDiffColorGrid(x - 1 , y)
						&& hasDiffColorGrid(x , y - 1)
						&& hasDiffColorGrid(x , y)) {
					sum++;
					print();
				}
			} else {
				// last row except end position
				// need to check the up, left grid of (x,y)
				if (hasDiffColorGrid(x - 1 , y)
						&& hasDiffColorGrid(x , y - 1)) {
					paintGrid(nextX, nextY);
				}
			}
		}
		// clear the grid of (x,y)
		grid[x][y] = 0;
	}
	
	// check surrounding grid of the position of (x,y)
	public boolean hasDiffColorGrid(int x, int y) {
		if (x == 0 || x > row || y == 0 || y > col) {
			// (x,y) is surrounding grid, no need to check
			return true;
		}
		// up
		if (grid[x-1][y] != 9 && grid[x-1][y] != grid[x][y]) return true;
		// left
		if (grid[x][y-1] != 9 && grid[x][y-1] != grid[x][y]) return true;
		// down
		if (grid[x+1][y] != 9 && grid[x+1][y] != grid[x][y]) return true;
		// right
		if (grid[x][y+1] != 9 && grid[x][y+1] != grid[x][y]) return true;
		
		return false;
	}
	
	public void print() {
		System.out.println("print the grid:");
		for (int i = 0; i < row + 2; i++) {
			for (int j = 0; j < col + 2; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

}
