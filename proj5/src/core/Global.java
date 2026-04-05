package core;

import core.world.Room;
import tileengine.TERenderer;
import tileengine.TETile;

import java.util.List;
import java.util.Random;

public class Global {
	public static final int WIDTH = 95;
	public static final int HEIGHT = 55;

	public static TERenderer ter = null;
	public static Random random =null;
	public static TETile[][] world = null;
	public static List<Room> roomList = null;
	public static  long seed = 0;
}
