package core;

import core.world.Room;
import tileengine.TERenderer;
import tileengine.TETile;

import java.util.List;
import java.util.Random;

public class Global {
	public static final int WIDTH = 95;
	public static final int HEIGHT = 55;
 	public static final String BACKGROUND_MUSIC_FILE = "src/static/music/M800003zMV1i3tBVma_processed.wav";
	public static final String CLICK_SOUND_FILE = "src/static/music/switch-button.wav";
	public static final String ENTER_GAME_SOUND = "src/static/music/the-rattling-of-a-metal-object.wav";

	public static TERenderer ter = null;
	public static Random random =null;
	public static TETile[][] world = null;
	public static List<Room> roomList = null;
	public static  long seed = 0;
}
