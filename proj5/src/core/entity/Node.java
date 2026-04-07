package core.entity;

public class Node implements Comparable<Node> {
	private Point p;
    private int g;
	private double f;

	public Node(Point p, int g, double f) {
		this.p = p;
		this.g = g;
		this.f = f;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}

	@Override
	public int compareTo(Node o) {
		if (o == null) {
			return 1;
		}
		return Double.compare(this.f, o.f);
	}
}