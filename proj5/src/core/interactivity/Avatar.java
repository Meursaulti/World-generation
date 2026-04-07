package core.interactivity;

import core.Global;
import core.entity.Point;
import tileengine.TETile;
import tileengine.Tileset;
import utils.WorldUtil;


import static utils.WorldUtil.*;
import static utils.WorldUtil.convertTile;

public class Avatar {
	private Point current;
	private TETile underTile;

	public Avatar() {
		Point current = Global.roomList.removeFirst().spawnRandomRoomTile();
		this(current);
	}

	public Avatar(Point current) {
		this.current = current;
		underTile = WorldUtil.getTile(current);
		WorldUtil.convertTile(current, Tileset.AVATAR);
	}

	public void move(int x, int y) {
		Point nextPoint = new Point(current.x() + x, current.y() + y);
		// 碰撞检测
		if (isWall(nextPoint)) {
			return;
		}

		convertTile(current, underTile);

		underTile = getTile(nextPoint);

		current = nextPoint;

		convertTile(nextPoint, Tileset.AVATAR);
	}
	public Point getCurrent() {
		return current;
	}
}
