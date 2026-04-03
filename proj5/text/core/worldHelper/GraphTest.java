package core.worldHelper;

import core.entity.Point;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class GraphTest {

	@Test
	public void addVertexTest() {
		Graph graph = new Graph();
		// 添加null
		graph.addVertex(null);
		assertThat(graph.V()).isEqualTo(0);
		// 添加节点
		Point p1 = new Point(11, 12);
		Point p2 = new Point(12, 13);
		Point p3 = new Point(13, 14);
		graph.addVertex(p1);
		graph.addVertex(p2);
		graph.addVertex(p3);
		assertThat(graph.V()).isEqualTo(3);
		assertThat(graph.vertices()).containsExactly(p1, p2, p3);
	}
	@Test
	public void addEdgeTest() {
		Graph graph = new Graph();
		// 添加null
		graph.addEdge(null, null);
		graph.addEdge(new Point(2, 3), null);
		assertThat(graph.E()).isEqualTo(0);
		// 添加边
		Point p1 = new Point(11, 12);
		Point p2 = new Point(12, 13);
		Point p3 = new Point(13, 14);
		graph.addEdge(p1, p2);
		graph.addEdge(p2, p3);
		graph.addEdge(p3, p1);
		assertThat(graph.E()).isEqualTo(3);
		assertThat(graph.adj(p1)).contains(p2);
		assertThat(graph.adj(p2)).contains(p3);
	}

}
