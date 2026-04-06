package utils;

import core.Global;
import core.entity.Point;
import core.world.Graph;
import tileengine.TETile;


public class WorldUtil {

	private static final TETile[][] world = Global.world;

	public static Graph TileLoader(int wide, int length) {
		Graph graph = new Graph();
		 for (int x = 4; x < wide; x++) {
			 for (int y = 4; y < length; y++) {
				 Point current = new Point(x, y);
				 if (y != length-1) {
					 Point top = new Point(x, y+1);
					 graph.addEdge(current, top);
				 }
				 if (x != wide-1) {
					 Point right = new Point(x+1, y);
					 graph.addEdge(current, right);
				 }
			 }
		 }
		 return graph;
	}
	public static boolean isWall(Point point) {
		return world[point.x()][point.y()].id() == 1;
	}
	public static boolean isFloor(Point point) {
		int id = world[point.x()][point.y()].id();
		return id == 2;
	}
	public static boolean isFloorPlus(Point point) {
		int id = world[point.x()][point.y()].id();
		return id == 2 || id >= 13 && id <= 17;
	}
	public static boolean isNothing(Point point) {
		return world[point.x()][point.y()].id() == 3;
	}
	public static boolean isAvatar(Point point) {
		return world[point.x()][point.y()].id() == 0;
	}

	public static Point[] getNeighbors(Point point) {
		Point top = new Point(point.x(), point.y() + 1);
		Point down = new Point(point.x(), point.y() - 1);
		Point left = new Point(point.x() - 1, point.y());
		Point right = new Point(point.x() + 1, point.y());
		return new Point[]{top, down, left, right};
	}

	public static int getTileId(Point point) {
		return world[point.x()][point.y()].id();
	}

	public static TETile getTile(Point point) {
		return world[point.x()][point.y()];
	}

	public static void convertTile(Point point, TETile tile) {
		world[point.x()][point.y()] = tile;
	}

}
