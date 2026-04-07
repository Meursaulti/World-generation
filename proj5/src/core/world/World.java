package core.world;

import core.Global;
import core.entity.Point;
import core.ldealFeatures.Light;
import tileengine.TETile;
import tileengine.Tileset;
import utils.WorldUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

	public static void generateWorld(int wide, int height, Random rng) {

		// 初始化世界
		TETile[][] world = new TETile[wide][height];
		Global.world = world;

		for (int i = 0; i < wide; i++) {
			for (int j = 0; j < height; j++) {
				world[i][j] = Tileset.NOTHING;
			}
		}

		List<Room> roomList = new ArrayList<>();
		for (int i = 0; i < 1000000; i++) {
			boolean flag = false;
			Point point = new Point(rng.nextInt(4, 86), rng.nextInt(4, 46));
			Room room = new Room(point, rng.nextInt(6, 10), rng.nextInt(6, 10));
			for (Room otherRoom : roomList) {
				if (room.isOverlaps(otherRoom)) flag = true;
			}
			if (flag) continue;
			room.roomGenerator(world);
			roomList.add(room);
		}
		// 将房间列表加载入
		Global.roomList = roomList;
		Corridor corridor = new Corridor();
		corridor.generator(roomList);

		// 启动灯
		for (Point light : Global.lightList) {
			Light.propagateLightFromTile(light);
		}
	}
}
