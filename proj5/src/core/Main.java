package core;

import core.worldHelper.Corridor;
import core.worldHelper.Graph;
import core.worldHelper.Room;
import core.worldHelper.WorldUtil;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int WIDTH = 95;
    private static final int HEIGHT = 55;
    private static final int SEED = 18548;
    public static void main(String[] args) {

        // 初始化渲染器
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // 初始化随机数生成器
        Random rng = new Random(SEED);
        WorldGlobal.random = rng;
        // 初始化世界
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        WorldGlobal.world = world;

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }

        List<Room> roomList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            boolean flag = false;
            Room room = new Room(rng.nextInt(4, 86), rng.nextInt(4, 46),
                    rng.nextInt(6, 10), rng.nextInt(6, 10));
            for (Room otherRoom : roomList) {
                if (room.isOverlaps(otherRoom)) flag = true;
            }
            if (flag) continue;
            room.roomGenerator(world);
            roomList.add(room);
        }
        Corridor corridor = new Corridor();
        corridor.generator(roomList);

        ter.renderFrame(world);
    }
}
