package core.interactivity;

import core.Global;
import core.entity.Point;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Objects;

import static core.Global.*;
import static utils.WorldUtil.getTile;

public class Hud {

	private static long lastTime;
	private static Point lastPoint;

	public static void tooltip(Point point) {
		if (point.x() < 0 || point.x() >= WIDTH ||
				point.y() < 0 || point.y() >= HEIGHT) {
			return;
		}

		StdDraw.clear(StdDraw.BLACK);

		ter.drawTiles(world);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(3.5, 54, getTile(point).description());

		StdDraw.show();
	}

	public static void show(Point point) {
		long currentTime = System.currentTimeMillis();

		if (!Objects.equals(point, lastPoint)) {
			lastTime = currentTime;
			lastPoint = point;
		}

		if (currentTime - lastTime > 1000) {
			tooltip(point);
			lastTime = currentTime;
			lastPoint = point;
		}
	}

	public static void refresh() {
		lastTime = System.currentTimeMillis();
	}
}
