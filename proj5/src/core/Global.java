package core;

import core.worldHelper.Room;
import tileengine.TETile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Global {
	public static final int WIDTH = 95;
	public static final int HEIGHT = 55;

	public static Random random =null;
	public static TETile[][] world = null;
	public static List<Room> roomList = new ArrayList<>();
}
