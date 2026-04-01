package core;

import core.worldHelper.Room;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int WIDTH = 95;
    private static final int HEIGHT = 55;
    public static void main(String[] args) {

        // build your own world!
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
//
        Random rng = new Random(18548);
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            boolean flag = false;
            Room room = new Room(rng.nextInt(5, 85), rng.nextInt(5, 45),
                    rng.nextInt(6, 10), rng.nextInt(6, 10));
            for (Room otherRoom : rooms) {
                if (room.isOverlaps(otherRoom)) flag = true;
            }
            if (flag) continue;
            room.roomGenerator(world, rng);
            rooms.add(room);
        }

        ter.renderFrame(world);
    }
}
