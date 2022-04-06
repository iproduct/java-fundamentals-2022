package course.java.algorithms.labirint;

import java.util.Arrays;

public class Labirint {
	public static int[][] labirint = {
			{-1,-2,-1,-1,-1,-2},
			{-1,-2,-2,-2,-1,-2},
			{-1,-1,-1,-2,-1,-1},
			{-1,-2,-1,-2,-1,-1},
			{-1,-2,-1,-1,-1,-2}
	};
	
	public static final Cell START = new Cell(0, 0);
	public static final Cell END = new Cell(2, 0);
	
	public static Cell[] findPath(int[][] lab, Cell start, Cell target, int len) {
		int temp = lab[start.y][start.x];
		lab[start.y][start.x] = len;
		System.out.println(start);
		printLabirint(lab);
		// Recursion bottom
		if(start.equals(target)) {
			return new Cell[] { target };
		}
		
		// Recursion Step 
		Cell[] emptyNeighbours = findEmptyNeighbours(lab, start);
		Cell[] minPath = null;
		
		for(Cell nbr: emptyNeighbours) {
			if(lab[nbr.y][nbr.x] == -1 || lab[nbr.y][nbr.x] > len + 1) {
				Cell[] path = findPath(lab, nbr, target, len+1);
				if(minPath == null || (path != null && path.length < minPath.length)) {
					minPath = path;
				}
			}
		}

		lab[start.y][start.x] = temp;

		Cell[] resultPath = null;
		// prepend start cell and copy minPath
		if (minPath != null) {
			resultPath = new Cell[minPath.length + 1];
			resultPath[0] = start;
			int pos = 1;
			for(Cell c: minPath) {
				resultPath[pos++] = c;
			}
		}
		
		return resultPath;
	}
	
	public static Cell[] findEmptyNeighbours(int[][] lab, Cell cell) {
		Cell[] result = new Cell[4];
		int pos = 0;
		if(cell.x > 0 && lab[cell.y][cell.x - 1] != -2) {
			result[pos++] = new Cell(cell.x - 1, cell.y);
		}
		if(cell.y > 0 && lab[cell.y - 1][cell.x] != -2) {
			result[pos++] = new Cell(cell.x, cell.y - 1);
		}
		if(cell.x < lab[0].length - 1 && lab[cell.y][cell.x + 1] != -2) {
			result[pos++] = new Cell(cell.x + 1, cell.y);
		}
		if(cell.y < lab.length-1 && lab[cell.y + 1][cell.x] != -2) {
			result[pos++] = new Cell(cell.x, cell.y + 1);
		}
		return Arrays.copyOf(result, pos);
	}
	
	public static void printLabirint(int[][] lab) {
		for(int[] row: lab) {
			for(int cell: row) {
				System.out.printf("%3s |", cell);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		printLabirint(labirint);
		Cell[] path = findPath(labirint, START, END, 0);
		System.out.println("Path Found:");
		for(Cell c: path) {
			System.out.print("[" + c.x + "," + c.y + "]->");
		}
		System.out.println();
		
//		Cell[] neighbours = findEmptyNeighbours(labirint, new Cell(4, 2));
//		System.out.println(Arrays.toString(neighbours));
	}

}
