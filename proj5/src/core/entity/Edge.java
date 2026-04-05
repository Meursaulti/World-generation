package core.entity;

import core.world.Room;

import java.util.Objects;

/**
 * 表示图中一条边的类。
 * 该边连接两个房间，并有权重（即距离）。
 * 实现了 {@link Comparable} 接口，以便根据权重进行自然排序。
 */
public class Edge implements Comparable<Edge> {
	private final Room from;
	private final Room to;
	private final double weight;

	/**
	 * 构造函数。
	 *
	 * @param from   边的起始房间
	 * @param to     边的终止房间
	 * @param weight 边的权重（两点间距离）
	 */
	public Edge(Room from, Room to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	// Getter 方法
	public Room getFrom() {
		return from;
	}

	public Room getTo() {
		return to;
	}

	public double getWeight() {
		return weight;
	}

	/**
	 * @param other 要比较的另一条边
	 * @return 如果当前边的权重小于、等于或大于另一条边，则分别返回负整数、零或正整数。
	 */
	@Override
	public int compareTo(Edge other) {
		// 使用 Double.compare 来安全地处理浮点数比较，避免 NaN 和精度问题。
		return Double.compare(this.weight, other.weight);
	}

	/**
	 * 提供一个良好的 toString 方法，便于调试。
	 */
	@Override
	public String toString() {
		return String.format("Edge{from=%s, to=%s, weight=%.2f}",
				from.getSource(), to.getSource(), weight);
	}

	/**
	 * 重写 equals 方法，基于边的两个端点和权重进行判断。
	 * 注意：在无向图中，(A, B) 和 (B, A) 应被视为同一条边。
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Edge edge = (Edge) o;
		// 检查无向边的两种可能性
		return (Double.compare(edge.weight, weight) == 0) &&
				((from.equals(edge.from) && to.equals(edge.to)) ||
						(from.equals(edge.to) && to.equals(edge.from)));
	}

	/**
	 * 重写 hashCode 方法，与 equals 保持一致。
	 */
	@Override
	public int hashCode() {
		// 将两个房间的哈希码组合起来，并加上权重。
		// 为了处理无向边，我们对 from 和 to 的哈希码进行排序后再组合。
		int fromHash = from.hashCode();
		int toHash = to.hashCode();
		int combinedHash = fromHash < toHash ?
				Objects.hash(fromHash, toHash, weight) :
				Objects.hash(toHash, fromHash, weight);
		return combinedHash;
	}
}