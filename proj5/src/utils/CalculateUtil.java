package utils;

import core.Global;
import core.entity.Point;
import core.world.Graph;

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
			Map<Point, Point> edgeTo = new HashMap<>();
			edgeTo.put(start, target);
			return edgeTo;
		}
		// 设定F为当前实际前驱距离加当前点对于目标的预估距离之和
		PriorityQueue<Map.Entry<Point, Double>> minHeap = new PriorityQueue<>(Comparator.comparing(Map.Entry :: getValue));
		Map<Point, Double> heuristicEstimate = new HashMap<>();
		Map<Point, Integer> distTo = new HashMap<>();
		Map<Point, Point> edgeTo = new HashMap<>();
		Set<Point> visited = new HashSet<>();
		Graph graph = Global.graph;

		// 计算F, 并将F放入最小堆
		double estimate = calculateManhattan(start, target);
		Map.Entry<Point, Double> startF = new AbstractMap.SimpleEntry<>(start, estimate + 0);
		// 放入到达当前点的实际距离
		distTo.put(start, 0);
		// 缓存当前点的预计距离,避免重复计算
		heuristicEstimate.put(start, estimate);
		minHeap.offer(startF);

		while (!minHeap.isEmpty()) {
			// 弹出堆中F最小的一对映射
			Map.Entry<Point, Double> currentEntry = minHeap.poll();
			Point currentPoint = currentEntry.getKey();
			int currentG = distTo.get(currentPoint);
			// 如果堆中F > 存储对应点的F那么视为过期节点跳过
			if (visited.contains(currentPoint)) {
				continue;
			}
			visited.add(currentPoint);
			// 如果目标节点被弹出即结束
			if (Objects.equals(currentPoint, target)) break;

			for (Point neighborPoint : graph.adj(currentPoint)) {

				if (!WorldUtil.isFloorPlus(neighborPoint) && !WorldUtil.isAvatar(neighborPoint)) continue;

				if (!heuristicEstimate.containsKey(neighborPoint)) {
					heuristicEstimate.put(neighborPoint, calculateManhattan(neighborPoint, target));
				}
				// 赋值于实际距离，预估距离，F
				int neighborG = currentG + 1;
				double estimate_N = heuristicEstimate.get(neighborPoint);
				double neighborF = neighborG + estimate_N;
				Map.Entry<Point, Double> neighborEntry = new AbstractMap.SimpleEntry<>(neighborPoint ,neighborF);

				// 如果未记载或者该点的F小于记载的F即存储/更新
				if (!distTo.containsKey(neighborPoint) || neighborG < distTo.get(neighborPoint)) {
					edgeTo.put(neighborPoint, currentPoint);
					distTo.put(neighborPoint, neighborG);
					heuristicEstimate.put(neighborPoint,estimate_N);
					minHeap.offer(neighborEntry);
				}
			}
		}
		return edgeTo;
	}
}
