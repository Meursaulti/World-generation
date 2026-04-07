package core.ldealFeatures;

import core.Global;
import core.entity.Point;
import core.interactivity.Menu;
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
	private final List<Point> forwardPath;
	private long lastTime;

	public Monster() {
		Point current = Global.roomList.removeLast().spawnRandomRoomTile();
		this(current);
	}

	public Monster(Point current) {
		this.current = current;
		lastTarget = null;
		underTile = WorldUtil.getTile(current);
		edgeTo = new HashMap<>();
		forwardPath = new ArrayList<>();
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
		if (Objects.equals(current, target)) {
			Menu.end();
		}
		// 如果玩家与上一步位置不同即更新路径
		if (!Objects.equals(target, lastTarget)) {
			edgeTo = CalculateUtil.calculateCompleteShortestPath(current, target);
			generatePath(target);
			lastTarget = target;
		}
		Point nextStep = getNextStep();
		// 将当前点的瓦块换回, 再记录下一步的瓦块，再将玩家更新至下一步
		WorldUtil.convertTile(current, underTile);
		underTile = WorldUtil.getTile(nextStep);
		WorldUtil.convertTile(nextStep, Tileset.MONSTER);
		current = nextStep;
	}

	private Point getNextStep() {
		return forwardPath.removeFirst();
	}

	private void generatePath(Point target) {
		forwardPath.clear();
		Point lastStep = edgeTo.get(target);
		forwardPath.addFirst(lastStep);
		while (edgeTo.get(lastStep) != current) {
			lastStep = edgeTo.get(lastStep);
			forwardPath.addFirst(lastStep);
		}
	}
}
