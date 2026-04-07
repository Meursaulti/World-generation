package core.ldealFeatures;

import core.Global;
import core.entity.Point;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;
import utils.CalculateUtil;
import utils.WorldUtil;

import java.util.*;

public class Monster {
	private Point current;
	private Point lastTarget;
	private TETile underTile;
	private Map<Point, Point> edgeTo;
	private final Stack<Point> forwardPath;
	private long lastTime;

	public Monster() {
		this(Global.roomList.removeLast().spawnRandomRoomTile());
	}

	public Monster(Point current) {
		this.current = current;
		lastTarget = null;
		underTile = WorldUtil.getTile(current);
		edgeTo = new HashMap<>();
		forwardPath = new Stack<>();
		lastTime = System.currentTimeMillis();
		WorldUtil.convertTile(current, Tileset.MONSTER);
	}

	public void action(Point target) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastTime > 700) {
			move(target);
			lastTime = currentTime;
			StdDraw.show();
		}
	}
	private void move(Point target) {

		if (!Objects.equals(lastTarget, target)) {
			edgeTo = CalculateUtil.calculateCompleteShortestPath(current, target);
			if (edgeTo == null) return;
			generatePath(target);
			lastTarget = target;
		}

		if (forwardPath.isEmpty()) throw new RuntimeException("出现异常，不知名原因栈为空");

		Point nextPoint = forwardPath.pop();

		WorldUtil.convertTile(current, underTile);
		underTile = WorldUtil.getTile(nextPoint);
		WorldUtil.convertTile(nextPoint, Tileset.MONSTER);

		current = nextPoint;
	}

	public boolean isEnd(Point target) {
		return Objects.equals(current, target);
	}

	private void generatePath(Point target) {
		// 清空旧路径
		forwardPath.clear();

		Point currentPoint = target;
		forwardPath.push(target);

		while (!Objects.equals(edgeTo.get(currentPoint), current)) {
			currentPoint = edgeTo.get(currentPoint);
			forwardPath.push(currentPoint);
		}
	}
}
