package core.world;

import core.Global;
import core.entity.Point;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Objects;

public class Room {
	private int sourceX;
	private int sourceY;
	private int wide;
	private int height;
	private int passageX;
	private int passageY;

	public Room(int sourceY, int sourceX) {
		this.sourceX = sourceX;
		this.sourceY = sourceY;
	}

	public Room(int x, int y, int wide, int height) {
		sourceX = x;
		sourceY = y;
		this.wide = wide;
		this.height = height;
	}

	public void roomGenerator(TETile[][] world) {
		roomGenerator(world, Tileset.FLOOR);
	}

	public void roomGenerator(TETile[][] world, TETile tile) {
		int endX = sourceX + wide;
		int endY = sourceY + height;
		for (int i = sourceX; i < endX; i++) {
			for (int j = sourceY; j < endY; j++) {
				if (i > sourceX && i < endX - 1 && j > sourceY && j < endY - 1) {
					world[i][j] = tile;
				} else world[i][j] = Tileset.WALL;
			}
		}
		if (Global.random.nextBoolean()) {
			Global.lightList.add(spawnRandomRoomTile());
		}
	}

//	private void generateArchway(TETile[][] world, Random random) {
//		int endX = sourceX + wide - 1;
//		int endY = sourceY + height - 1;
//		int side = random.nextInt(4); // 0:下 1:上 2:左 3:右
//		switch (side) {
//			case 0: // 下边
//				passageX = random.nextInt(sourceX + 1, endX);
//				passageY = sourceY;
//				break;
//			case 1: // 上边
//				passageX = random.nextInt(sourceX + 1, endX);
//				passageY = endY;
//				break;
//			case 2: // 左边
//				passageX = sourceX;
//				passageY = random.nextInt(sourceY + 1, endY);
//				break;
//			case 3: // 右边
//				passageX = endX;
//				passageY = random.nextInt(sourceY + 1, endY);
//				break;
//		}
//		world[passageX][passageY] = Tileset.FLOOR;
//	}
	public Point spawnRandomRoomTile() {
		int x = Global.random.nextInt(sourceX+1, sourceX+wide-1);
		int y = Global.random.nextInt(sourceY+1, sourceY+height-1);
		return new Point(x, y);
	}

	public boolean isOverlaps(Room other) {
		int otherSourceX = other.getSourceX();
		int otherSourceY = other.getSourceY();
		int otherXPlusWide = otherSourceX + other.getWide();
		int otherYPlusHeight = otherSourceY + other.getHeight();
		return !(sourceX >= otherXPlusWide || sourceX + wide <= otherSourceX
				|| sourceY >= otherYPlusHeight || sourceY + height <= otherSourceY);
	}

	public int getSourceX() {
		return sourceX;
	}

	public int getSourceY() {
		return sourceY;
	}

	public int getWide() {
		return wide;
	}

	public int getHeight() {
		return height;
	}

	public Point getSource() {
		return new Point(sourceX, sourceY);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Room other = (Room) obj;
		return sourceX == other.getSourceX() && sourceY == other.getSourceY();
	}

	@Override
	public int hashCode() {
		return Objects.hash(sourceX, sourceY);
	}
}
