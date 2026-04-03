package core.worldHelper;

import java.util.*;
import java.util.List;

import core.WorldGlobal;
import core.entity.Edge;
import core.entity.Point;
import tileengine.TETile;
import tileengine.Tileset;
import utils.CalculateUtil;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Corridor {
	public List<Edge> edgeList;
	public WeightedQuickUnionUF unionUF;
	private static final TETile[][] world = WorldGlobal.world;

	public Corridor() {
		edgeList = new ArrayList<>();
	}

	// 生成走廊
	public void generator(List<Room> roomList) {
		int roomsSize = roomList.size();
		generateMST(roomList, roomsSize);
		for (Edge edge : edgeList) {
			Point start = edge.getFrom().spawnRandomRoomTile();
			Point end = edge.getTo().spawnRandomRoomTile();
			int dx = end.x() > start.x() ? 1 : -1;
			int dy = end.y() > start.y() ? 1 : -1;
			int currentX = start.x();
			int currentY = start.y();
			while (currentX != end.x()) {
				currentX += dx;
				buildWallHorizontal(new Point(currentX, currentY));
			}
			while (currentY != end.y()) {
				currentY += dy;
				buildWallVertical(new Point(currentX, currentY));
			}
		}
	}

	// 生成最小生成树,树存于edgeList
	private void generateMST(List<Room> roomList, int roomsSize) {
		if (roomsSize <= 1) {
			edgeList.clear();
			return;
		}
		// 生成所有边
		List<Edge> allEdges = new ArrayList<>();
		for (int i = 0; i < roomsSize; i++) {
			for (int j = i + 1; j < roomsSize; j++) {
				Room room = roomList.get(i);
				Room targetRoom = roomList.get(j);
				double dist = CalculateUtil.
						calculateDistance(room.getSource(), targetRoom.getSource());
				allEdges.add(new Edge(room, targetRoom, dist));
			}
		}
		Collections.sort(allEdges);

		// 将房间映射于数字，并初始化并查集，相互对应
		Map<Room, Integer> roomIntegerMap = new HashMap<>();
		unionUF = new WeightedQuickUnionUF(roomsSize);
		for (int i = 0; i < roomsSize; i++) {
			roomIntegerMap.put(roomList.get(i), i);
		}
		for (Edge edge : allEdges) {
			int idxA = roomIntegerMap.get(edge.getFrom());
			int idxB = roomIntegerMap.get(edge.getTo());
			if (!unionUF.connected(idxA, idxB)) {
				unionUF.union(idxA, idxB);
				edgeList.add(edge);
			}
			if (edgeList.size() == roomsSize - 1) {
				break;
			}
		}
	}
	// 铺路方法分横铺和竖铺——对墙壁和地板作自动判断和处理
	private void buildWallHorizontal(Point point) {
		if (buildWallHelper(point)) {
			int x = point.x();
			int y = point.y();
			world[x][y] = Tileset.FLOOR;
			world[x][y+1] = Tileset.WALL;
			world[x][y-1] = Tileset.WALL;
		}
	}
	private void buildWallVertical(Point point) {
		if (buildWallHelper(point)) {
			int x = point.x();
			int y = point.y();
			world[x][y] = Tileset.FLOOR;
			world[x+1][y] = Tileset.WALL;
			world[x-1][y] = Tileset.WALL;
		}
	}
	private boolean buildWallHelper(Point point) {
		if (WorldUtil.isWall(point)) {
			world[point.x()][point.y()] = Tileset.FLOOR;
		} else if (WorldUtil.isFloor(point)) {
			return false;
		}
		return true;
	}
}
