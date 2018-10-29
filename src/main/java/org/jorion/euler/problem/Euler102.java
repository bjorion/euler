package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.List;

import org.jorion.euler.util.Utils;

/**
 * Three distinct points are plotted at random on a Cartesian plane, for which -1000 ≤ x, y ≤ 1000, such that a triangle is formed.
 * <p>
 * Consider the following two triangles:
 *
 * <pre>
 * A(-340,495), B(-153,-910), C(835,-947)
 *
 * X(-175,41), Y(-421,-714), Z(574,-645)
 * </pre>
 *
 * <p>
 * It can be verified that triangle ABC contains the origin, whereas triangle XYZ does not.
 * <p>
 * Using triangles.txt (right click and 'Save Link/Target As...'), a 27K text file containing the co-ordinates of one thousand "random" triangles,
 * find the number of triangles for which the interior contains the origin.
 * <p>
 * NOTE: The first two examples in the file represent the triangles in the example given above.
 */
public class Euler102 {

	// --- Methods ---
	public static void main(String[] args)
			throws Exception {

		long res;
		long delta;

		List<String> lines = Utils.readLines("p102_triangles.txt");
		List<Triangle> triangles = new ArrayList<>();
		for (String line : lines) {
			String[] vals = line.split(",");
			Point a = new Point(vals[0], vals[1]);
			Point b = new Point(vals[2], vals[3]);
			Point c = new Point(vals[4], vals[5]);
			triangles.add(new Triangle(a, b, c));
		}

		Utils.start();
		res = calc1(triangles);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	/**
	 * Let A, B and C be the three summits of the triangle. We compute AB, BC and AC the three lines (that include the triangle sides).
	 */
	private static long calc1(List<Triangle> triangles) {

		System.out.println(triangles.size());
		return 0;
	}

	static class Triangle {

		final Point a;

		final Point b;

		final Point c;

		public Triangle(Point a, Point b, Point c) {

			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

	static class Point {

		final int x;

		final int y;

		public Point(int x, int y) {

			this.x = x;
			this.y = y;
		}

		public Point(String x, String y) {

			this.x = Integer.parseInt(x);
			this.y = Integer.parseInt(y);
		}
	}
}