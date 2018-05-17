package org.jorion.euler.problem;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jorion.euler.util.Utils;

/**
 * Su Doku (Japanese meaning number place) is the name given to a popular puzzle concept.
 * Its origin is unclear, but credit must be attributed to Leonhard Euler who invented a similar, and much more difficult, puzzle idea called Latin Squares.
 * The objective of Su Doku puzzles, however, is to replace the blanks (or zeros) in a 9 by 9 grid in such that each row, column, and 3 by 3 box contains each of the digits 1 to 9.
 * Below is an example of a typical starting puzzle grid and its solution grid.
 * <pre>
 * 0 0 3 0 2 0 6 0 0    4 8 3 9 2 1 6 5 7
 * 9 0 0 3 0 5 0 0 1    9 6 7 3 4 5 8 2 1
 * 0 0 1 8 0 6 4 0 0    2 5 1 8 7 6 4 9 3
 * </pre>
 * <p>
 * A well constructed Su Doku puzzle has a unique solution and can be solved by logic,
 * although it may be necessary to employ "guess and test" methods in order to eliminate options (there is much contested opinion over this).
 * The complexity of the search determines the difficulty of the puzzle;
 * the example above is considered easy because it can be solved by straight forward direct deduction.
 * <p>
 * The 6K text file, sudoku.txt (right click and 'Save Link/Target As...'), contains fifty different Su Doku puzzles ranging in difficulty,
 * but all with unique solutions (the first puzzle in the file is the example above).
 * <p>
 * By solving all fifty puzzles find the sum of the 3-digit numbers found in the top left corner of each solution grid;
 * for example, 483 is the 3-digit number found in the top left corner of the solution grid above.
 */
public class Euler096 {

	// --- Constants ---
	private static final int ASCII_0 = 48;

	private static final int SIZE = 9;

	// --- Methods ---
	public static void main(String[] args) throws Exception {

		long res = 0;
		long delta = 0;

		URL url = Euler096.class.getClassLoader().getResource("p096_sudoku.txt");
		List<String> lines = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(url.toURI()))) {
			//br returns as stream and convert it into a List
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int n = 0; n < lines.size(); n = n + (SIZE + 1)) {
			int[][] mat = toMatrix(lines, n);
			Utils.start();
			res += calc1(mat);
			delta += Utils.stop();
		}
		Utils.print("Brute force ", res, delta);
	}

	/**
	 * Check all possibilities without any "intelligence".
	 */
	private static long calc1(int[][] mat) {

		boolean ok = false, success = true;
		int x = 0, y = 0;

		// printMatrix(mat);
		while (y < SIZE && success) {
			while (x < SIZE && success) {
				ok = false;
				int v = mat[y][x];
				// fixed value
				if (v > 0) {
					ok = true;
				}
				// empty cell
				else {
					// try all values from 1 to 9
					while (-v < SIZE && !ok) {
						v -= 1;
						if (checkVal(mat, x, y, v)) {
							ok = true;
							mat[y][x] = v;
						}
					}
					// none of the values are possible
					if (!ok) {
						// restore null value
						mat[y][x] = 0;
						// go back one cell
						Point p = goBack(mat, x, y);
						success = (p != null);
						if (success) {
							x = p.x;
							y = p.y;
						}
					}
				}
				if (ok) {
					x += 1;
				}
			}
			if (ok) {
				x = 0;
				y += 1;
			}
		}
		// System.out.println("---------");
		// printMatrix(mat);

		int res = (success) ? Math.abs(mat[0][0]) * 100 + Math.abs(mat[0][1]) * 10 + Math.abs(mat[0][2]) : -1;
		return res;
	}

	private static Point goBack(int[][] mat, int x, int y) {

		boolean ok = false;
		int xx = -1, yy = -1;

		while (y >= 0 && !ok) {
			while (x >= 0 && !ok) {
				x -= 1;
				if (x >= 0) {
					if (mat[y][x] <= 0) {
						xx = x;
						yy = y;
						ok = true;
					}
				}
			}
			x = SIZE;
			y -= 1;
		}
		return (xx >= 0 && yy >= 0) ? new Point(xx, yy) : null;
	}

	private static boolean checkVal(int[][] mat, int x, int y, int v) {

		boolean ok = checkSquare(mat, x, y, v) && checkRow(mat, x, y, v) && checkCol(mat, x, y, v);
		return ok;
	}

	/**
	 * @return true if the given value does not exist yet in the local square
	 */
	private static boolean checkSquare(int[][] mat, int x, int y, int v) {

		boolean ok = true;
		int x0 = x - x % 3;
		int y0 = y - y % 3;
		for (int i = y0; i < y0 + 3; i++) {
			for (int j = x0; j < x0 + 3; j++) {
				if (i != y || j != x) {
					if (Math.abs(mat[i][j]) == Math.abs(v)) {
						ok = false;
						break;
					}
				}
			}
			if (!ok) {
				break;
			}
		}
		return ok;
	}

	/**
	 * @return true if the given value does not exist in the row
	 */
	private static boolean checkRow(int[][] mat, int x, int y, int v) {

		boolean ok = true;
		for (int j = 0; j < SIZE; j++) {
			if (j != x && Math.abs(mat[y][j]) == Math.abs(v)) {
				ok = false;
				break;
			}
		}
		return ok;
	}

	/**
	 * @return true if the given value does not exist in the column
	 */
	private static boolean checkCol(int[][] mat, int x, int y, int v) {

		boolean ok = true;
		for (int i = 0; i < SIZE; i++) {
			if (i != y && Math.abs(mat[i][x]) == Math.abs(v)) {
				ok = false;
				break;
			}
		}
		return ok;
	}

	private static int[][] toMatrix(List<String> lines, int index) {

		index++;
		int[][] mat = new int[SIZE][SIZE];
		for (int y = 0; y < SIZE; y++) {
			String line = lines.get(index + y);
			for (int x = 0; x < SIZE; x++) {
				mat[y][x] = line.charAt(x) - ASCII_0;
			}
		}
		return mat;
	}

	@SuppressWarnings("unused")
	private static void printMatrix(int[][] mat) {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(Math.abs(mat[i][j]));
			}
			System.out.println();
		}
	}
}
