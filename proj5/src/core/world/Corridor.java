package core.world;

import java.util.*;
import java.util.List;

import core.Global;
import core.entity.Edge;
import core.entity.Point;
import tileengine.TETile;
import tileengine.Tileset;
import utils.CalculateUtil;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import utils.WorldUtil;

import static utils.WorldUtil.convertTile;

public class Corridor {
	public List<Edge> edgeList;
	public WeightedQuickUnionUF unionUF;
	private static final TETile[][] world = Global.world;

	public Corridor() {
		edgeList = new ArrayList<>();
	}

	// 生成走廊
	public void generator(List<Room> roomList) {
		int roomsSize = roomList.size();
		generateMST(roomList, roomsSize);
		for (Edge edge : edgeList) {
			for (Point point : generatorPath(edge.getFrom(), edge.getTo())) {
				applyPathPointsToMap(point);
			}
		}
	}

	public List<Point> generatorPath(Room r1, Room r2) {
		List<Point> path = new ArrayList<>();
		Point start = r1.spawnRandomRoomTile();
		Point end = r2.spawnRandomRoomTile();
		int dx = end.x() > start.x() ? 1 : -1;
		int dy = end.y() > start.y() ? 1 : -1;
		int currentX = start.x();
		int currentY = start.y();

		while (currentX != end.x() || currentY != end.y()) {
			if (currentX != end.x() && currentY != end.y()) {
				if (Global.random.nextBoolean()) {
					currentX += dx;
				} else {
					currentY += dy;
				}
			} else if (currentX != end.x()) {
				currentX += dx;
			} else {
				currentY += dy;
			}
			path.add(new Point(currentX, currentY));
		}
		return path;
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
						calculateEuclidean(room.getSource(), targetRoom.getSource());
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
	// 铺路方法----对墙壁和地板作自动判断和处理
	private void applyPathPointsToMap(Point point) {
		if (WorldUtil.isFloor(point)){
			return;
		}
		for (Point neighborPoint : WorldUtil.getNeighbors(point)) {
			if (WorldUtil.isNothing(neighborPoint)) {
				convertTile(neighborPoint, Tileset.WALL);
			}
		}
		convertTile(point, Tileset.FLOOR);
	}

}
