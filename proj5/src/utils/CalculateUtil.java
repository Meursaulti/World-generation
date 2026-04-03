package utils;

import core.entity.Point;

public class CalculateUtil {

	public static double calculateDistance(Point p1, Point p2) {
		double width = p2.x() - p1.x();
		double length = p2.y() - p1.y();
		return Math.sqrt(width * width + length * length);
	}
}
