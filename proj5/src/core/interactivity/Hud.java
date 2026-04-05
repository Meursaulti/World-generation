package core.interactivity;

import core.Global;
import core.entity.Point;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

import static core.Global.*;
import static utils.WorldUtil.getTile;

public class Hud {
	public static void tooltip(Point point) {
		if (point.x() < 0 || point.x() >= WIDTH ||
				point.y() < 0 || point.y() >= HEIGHT) {
			return;
		}
		StdDraw.clear(new Color(0, 0, 0));
		ter.drawTiles(world);
		StdDraw.setPenColor(StdDraw.WHITE);

		StdDraw.text(3.5, 54, getTile(point).description());
		StdDraw.show();
	}
}
