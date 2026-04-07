package utils;

import core.entity.Node;
import core.entity.Point;

import java.util.*;

public class CalculateUtil {

	/**
	 *
	 * @param p1
	 * @param p2
	 * @return 欧几里得距离
	 */
	public static double calculateEuclidean(Point p1, Point p2) {
		double width = p2.x() - p1.x();
		double length = p2.y() - p1.y();
		return Math.sqrt(width * width + length * length);
	}

	/**
	 *
	 * @param p1
	 * @param p2
	 * @return 曼哈顿距离
	 */
	public static double calculateManhattan(Point p1, Point p2) {
		double width = p2.x() - p1.x();
		double length = p2.y() - p1.y();
		return Math.abs(width) + Math.abs(length);
	}
	/**
	 *
	 * @param start
	 * @param target
	 * @return A*算法取起点和目标点算出最短路径并返回所有记载的路径
	 */
	public static Map<Point, Point> calculateCompleteShortestPath(Point start, Point target) {
		if (Objects.equals(start, target)) {
			return null;
		}
		// 设定F为当前实际前驱距离加当前点对于目标的预估距离之和
		PriorityQueue<Node> minHeap = new PriorityQueue<>();
		Map<Point, Integer> distTo = new HashMap<>();
		Map<Point, Point> edgeTo = new HashMap<>();
		Set<Point> closed = new HashSet<>();

		// 计算F, 并将F放入最小堆
		// 放入到达当前点的实际距离
		distTo.put(start, 0);
		// 缓存当前点的预计距离,避免重复计算
		minHeap.offer(new Node(start, 0, calculateManhattan(start, target)));
		while (!minHeap.isEmpty()) {
			// 弹出堆中F最小的一对映射
			Node currentNode = minHeap.poll();
			Point currentPoint = currentNode.getP();
			int currentG = currentNode.getG();
			// 如果堆中F > 存储对应点的F那么视为过期节点跳过
			if (currentG > distTo.get(currentPoint)) {
				continue;
			}
			if (closed.contains(currentPoint)) continue;
			closed.add(currentPoint);
			// 如果目标节点被弹出即结束
			if (Objects.equals(currentPoint, target)) break;


			for (Point neighborPoint : WorldUtil.getNeighbors(currentPoint)) {

				if (WorldUtil.isNothing(neighborPoint) || WorldUtil.isWall(neighborPoint)) continue;

				// 赋值于实际距离，预估距离，F
				int neighborG = currentG + 1;
				double estimate_N = calculateManhattan(neighborPoint, target);
				double neighborF = neighborG + estimate_N;

				// 如果未记载或者该点的F小于记载的F即存储/更新
				if (!distTo.containsKey(neighborPoint) || neighborG < distTo.get(neighborPoint)) {
					edgeTo.put(neighborPoint, currentPoint);
					distTo.put(neighborPoint, neighborG);
					minHeap.offer(new Node(neighborPoint, neighborG, neighborF));
				}
			}
		}
		return edgeTo;
	}
}
