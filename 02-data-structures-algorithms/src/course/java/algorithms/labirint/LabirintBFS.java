package course.java.algorithms.labirint;

import java.util.*;

public class LabirintBFS {
	public static int[][] labyrinth = {
			{-1, -2, -1, -1, -1, -2},
			{-1, -2, -2, -2, -1, -2},
			{-1, -1, -1, -1, -1, -1},
			{-1, -2, -1, -2, -1, -1},
			{-1, -2, -1, -1, -1, -2}
	};

	public static final Cell START = new Cell(0, 0);
	public static final Cell END = new Cell(2, 0);
	public static Scanner sc = new Scanner(System.in);
	public static Deque<List<Cell>> paths = new ArrayDeque<>();

	public static void findPath(int[][] lab, Cell start, Cell target, List<List<Cell>> results) {
		paths.addLast(List.of(start));

		while(!paths.isEmpty()) {
			var currentPath = paths.removeFirst(); // Change Stack == DFS or Queue == BFS
			System.out.println(currentPath);
//			printLabirint(lab);
//			sc.nextLine();
			var lastCell = currentPath.get(currentPath.size()-1);

			// Solution found
			if (lastCell.equals(target)) {
				results.add(List.copyOf(currentPath));
				System.out.printf("Solution found: %s%n", currentPath);
			}

			// Find next Cells
			var emptyNeighbours = findEmptyNeighbours(lab, lastCell);
			for(Cell nbr: emptyNeighbours) {
				if(!currentPath.contains(nbr)) {
					var newPath = new ArrayList<>(currentPath);
					newPath.add(nbr);
					paths.addLast(newPath);
				}
			}
		}

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
		List<List<Cell>> results = new ArrayList<>();
		findPath(labyrinth, START, END, results);
		System.out.println("Paths Found:");
		for(var path: results) {
			System.out.println(path);
		}
		System.out.println();
		
//		Cell[] neighbours = findEmptyNeighbours(labirint, new Cell(4, 2));
//		System.out.println(Arrays.toString(neighbours));
	}

}
