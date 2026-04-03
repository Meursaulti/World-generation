package core.worldHelper;

import core.entity.Point;

import java.util.*;

public class Graph {
	private Map<Point, Set<Point>> adjacency;
	private int edgeCount;

	public Graph() {
		adjacency = new HashMap<>();
		edgeCount = 0;
	}
	public void addVertex(Point vertex) {
		if (adjacency.containsKey(vertex) || vertex == null) {
			return;
		}
		adjacency.put(vertex, new HashSet<>());
	}

	public void addEdge(Point v1, Point v2) {
		if (v1 == null || v2 == null) {
			return;
		}
		addVertex(v1);
		addVertex(v2);
		adjacency.get(v1).add(v2);
		adjacency.get(v2).add(v1);
		edgeCount++;
	}

	public Iterable<Point> adj(Point vertex) {
		return adjacency.get(vertex);
	}

	public Iterable<Point> vertices() {
		return adjacency.keySet();
	}

	public int V() {
		return adjacency.size();
	}

	public int E() {
		return edgeCount;
	}
}
