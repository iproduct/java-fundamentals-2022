package course.java.algorithms.labirint;

import java.util.*;

public class Labirint {
	public static int[][] labyrinth = {
			{-1, -2, -1, -1, -1, -2},
			{-1, -2, -2, -2, -1, -2},
			{-1, -1, -1, -1, -1, -1},
			{-1, -2, -1, -2, -1, -1},
			{-1, -1, -1, -1, -1, -2}
	};

	public static final Cell START = new Cell(0, 0);
	public static final Cell END = new Cell(2, 0);
	public static Scanner sc = new Scanner(System.in);

	public static void findPath(int[][] lab, Cell start, Cell target, List<Cell> currentPath, Collection<List<Cell>> results) {
		int temp = lab[start.y][start.x];
		lab[start.y][start.x] = currentPath.size();
		currentPath.add(start);
		System.out.println(start);
		printLabirint(lab);
		sc.nextLine();

		// Recursion bottom
		if (start.equals(target)) {
			results.add(List.copyOf(currentPath));
			System.out.printf("Solution found: %s%n", currentPath);
		}
		// Recursion Step 
		var emptyNeighbours = findEmptyNeighbours(lab, start);

		for(Cell nbr: emptyNeighbours) {
			if(lab[nbr.y][nbr.x] == -1) {
				findPath(lab, nbr, target, currentPath, results);
			}
		}

		currentPath.remove(currentPath.size()-1);
		lab[start.y][start.x] = temp;
	}
	
	public static List<Cell> findEmptyNeighbours(int[][] lab, Cell cell) {
		List<Cell> nextCells = new ArrayList<>();
		int pos = 0;
		if(cell.y > 0 && lab[cell.y - 1][cell.x] != -2) {
			nextCells.add(new Cell(cell.x, cell.y - 1));
		}
		if(cell.y < lab.length-1 && lab[cell.y + 1][cell.x] != -2) {
			nextCells.add(new Cell(cell.x, cell.y + 1));
		}
		if(cell.x > 0 && lab[cell.y][cell.x - 1] != -2) {
			nextCells.add(new Cell(cell.x - 1, cell.y));
		}
		if(cell.x < lab[0].length - 1 && lab[cell.y][cell.x + 1] != -2) {
			nextCells.add(new Cell(cell.x + 1, cell.y));
		}
		return nextCells;
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
		printLabirint(labyrinth);
		TreeSet<List<Cell>> results = new TreeSet<>();
		findPath(labyrinth, START, END, new ArrayList<Cell>(), results);
		System.out.println("Paths Found:");
		for(var path: results) {
			System.out.println(path);
		}


		System.out.println();
		
//		Cell[] neighbours = findEmptyNeighbours(labirint, new Cell(4, 2));
//		System.out.println(Arrays.toString(neighbours));
	}

}
