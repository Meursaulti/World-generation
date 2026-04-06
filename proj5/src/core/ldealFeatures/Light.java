package core.ldealFeatures;

import core.Global;
import core.entity.Point;
import core.world.Graph;
import core.world.Room;
import tileengine.TETile;
import tileengine.Tileset;
import utils.CalculateUtil;
import utils.WorldUtil;

import java.util.*;

public class Light {

	/**
	 * 从指定瓦片位置开始，使用 BFS 向外传播光照效果
	 */
	public static void propagateLightFromTile(Point light) {
		List<Point> lightZone = new LinkedList<>();
		updateTileLightRadius(light, lightZone);
		while (!lightZone.isEmpty()) {
			if (updateTileLightRadius(lightZone.removeFirst(), lightZone)) {
				break;
			}
		}
	}

	private static boolean updateTileLightRadius(Point lightZone, List<Point> lightZoneList) {
		Point startPoint = new Point(lightZone.x() - 1, lightZone.y() - 1);
		Point endPoint = new Point(lightZone.x() + 1, lightZone.y() + 1);
		boolean isNull = false;

		for (int x = startPoint.x(); x <= endPoint.x(); x++) {
			for (int y = startPoint.y(); y <= endPoint.y(); y++) {
				Point current = new Point(x, y);
				if (WorldUtil.isFloor(current)) {
					TETile tile = getLowerLightZone(lightZone);
					if (tile == null) {
						isNull = true;
						break;
					}
					WorldUtil.convertTile(current, tile);
					lightZoneList.add(current);
				}
			}
		}
		return isNull;
	}

	private static TETile getLowerLightZone(Point point) {
		int level = WorldUtil.getTileId(point) - Tileset.LIGHT.id() + 1 ;
		return switch (level) {
			case 1 -> Tileset.LIGHT_ZONE_1;
			case 2 -> Tileset.LIGHT_ZONE_2;
			case 3 -> Tileset.LIGHT_ZONE_3;
			case 4 -> Tileset.LIGHT_ZONE_4;
			default -> null;
		};
	}


}
