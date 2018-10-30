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

		long res; // 228
		long delta;

		List<String> lines = Utils.readLines("p102_triangles.txt");

		Utils.start();
		res = calc1(lines);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);

		Utils.start();
		res = calc2(lines);
		delta = Utils.stop();
		Utils.print("Streams   ", res, delta);

		Utils.start();
		res = calc3(lines);
		delta = Utils.stop();
		Utils.print("Parallel  ", res, delta);
	}

	/**
	 * Let A, B and C be the three summits of the triangle. We compute AB, BC and AC the three lines (that include the triangle sides). For each line,
	 * we check if the origin and the 3rd summit are on the same side. If it's always yes, then the origin is inside the triangle.
	 * <p>
	 * Hypothesis:
	 * <ul>
	 * <li>if the origin belongs to the side of a triangle, then it is not inside
	 * <li>the triangles are "well-behaved", that the 3 points are never aligned
	 * </ul>
	 *
	 * @param lines A list of 6 integers coordinates of the summits
	 * @return the number of triangles containing the origin
	 */
	private static long calc1(List<String> lines) {

		List<Triangle> triangles = new ArrayList<>();
		for (String line : lines) {
			String[] vals = line.split(",");
			Point a = new Point(vals[0], vals[1]);
			Point b = new Point(vals[2], vals[3]);
			Point c = new Point(vals[4], vals[5]);
			triangles.add(new Triangle(a, b, c));
		}

		long res = 0;
		for (Triangle t : triangles) {
			if (t.containsOrigin()) {
				res++;
			}
		}
		return res;
	}

	/**
	 * Same that {@code calc1} but with streams.
	 */
	private static long calc2(List<String> lines) {

		List<Triangle> triangles = new ArrayList<>();
		for (String line : lines) {
			String[] vals = line.split(",");
			Point a = new Point(vals[0], vals[1]);
			Point b = new Point(vals[2], vals[3]);
			Point c = new Point(vals[4], vals[5]);
			triangles.add(new Triangle(a, b, c));
		}

		long res = triangles.stream().filter(t -> t.containsOrigin()).count();
		return res;
	}

	/**
	 * Same that {@code calc1} but with parallel streams.
	 */
	private static long calc3(List<String> lines) {

		List<Triangle> triangles = new ArrayList<>();
		for (String line : lines) {
			String[] vals = line.split(",");
			Point a = new Point(vals[0], vals[1]);
			Point b = new Point(vals[2], vals[3]);
			Point c = new Point(vals[4], vals[5]);
			triangles.add(new Triangle(a, b, c));
		}

		long res = triangles.stream().parallel().filter(t -> t.containsOrigin()).count();
		return res;
	}

	/**
	 * Triangle class, made of 3 Points.
	 */
	static class Triangle {

		final Point a, b, c;

		final Line ab, bc, ac;

		Triangle(Point a, Point b, Point c) {

			this.a = a;
			this.b = b;
			this.c = c;

			this.ab = new Line(a, b);
			this.bc = new Line(b, c);
			this.ac = new Line(a, c);
		}

		boolean containsOrigin() {

			boolean ok = (Math.signum(ab.computeSidePoint(c)) == Math.signum(ab.computeSideOrigin()));
			if (ok) {
				ok = (Math.signum(bc.computeSidePoint(a)) == Math.signum(bc.computeSideOrigin()));
			}
			if (ok) {
				ok = (Math.signum(ac.computeSidePoint(b)) == Math.signum(ac.computeSideOrigin()));
			}
			return ok;
		}

		@Override
		public String toString() {

			return "Triangle[A=" + a + ", B=" + b + ", C=" + c + "]";
		}
	}

	/**
	 * Line class, made of 2 Points.
	 */
	static class Line {

		final double a;

		final double b;

		final double c;

		Line(Point p1, Point p2) {

			if (p1.x != p2.x) {
				a = -((double) (p1.y - p2.y)) / (p1.x - p2.x);
				b = 1;
				c = -((double) (p1.x * p2.y - p1.y * p2.x)) / (p1.x - p2.x);
			}
			// vertical line
			else {
				a = 1;
				b = 0;
				c = -p1.x;
			}
		}

		double computeSidePoint(Point p) {

			double res = a * p.x + b * p.y + c;
			if (res == 0) {
				System.out.println(String.format("WARNING: line %s contains the point %s", this.toString(), p.toString()));
			}
			return res;
		}

		double computeSideOrigin() {

			return c;
		}

		@Override
		public String toString() {

			return "Line[" + a + "x + " + b + "y + " + c + "]";
		}
	}

	/**
	 * Point class.
	 */
	static class Point {

		final int x;

		final int y;

		Point(int x, int y) {

			this.x = x;
			this.y = y;
		}

		Point(String x, String y) {

			this.x = Integer.parseInt(x);
			this.y = Integer.parseInt(y);
		}

		@Override
		public String toString() {

			return "Point[x=" + x + ", y=" + y + "]";
		}
	}
}