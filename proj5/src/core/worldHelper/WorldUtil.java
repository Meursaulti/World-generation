package core.worldHelper;

import core.WorldGlobal;
import core.entity.Point;
import tileengine.TETile;

public class WorldUtil {

	private static final TETile[][] world = WorldGlobal.world;

	public static Graph TileLoader(int wide, int length) {
		Graph graph = new Graph();
		 for (int x = 5; x < wide; x++) {
			 for (int y = 5; y < length; y++) {
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
		return world[point.x()][point.y()].id() == 2;
	}
}
