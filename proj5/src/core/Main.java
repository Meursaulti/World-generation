package core;

import core.entity.Point;

import utils.WorldUtil;
import tileengine.TERenderer;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

import static core.Global.*;
import static utils.WorldUtil.*;

public class Main {

    private static final int SEED = 4844;

    private static TETile underTile;
    private static Point current;

    public static void main(String[] args) throws InterruptedException {

        // 初始化渲染器
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // 初始化随机数生成器
        Random rng = new Random(SEED);
        Global.random = rng;

        // 生成世界
        World.generateWorld(WIDTH, HEIGHT, rng);

        // 人物移动
        Point source = roomList.getFirst().spawnRandomRoomTile();
        current = new Point(source.x()+1, source.y()-1);
        underTile = getTile(current);
        move(0, 0);
        ter.renderFrame(world);
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (c == 'w') { move(0, 1); }
                if (c == 'a') { move(-1, 0);  }
                if (c == 's') { move(0, -1); }
                if (c == 'd') { move(1, 0);  }
                ter.renderFrame(world);
            }
        }
    }
    public static void move(int dx, int dy) {

        Point nextPoint = new Point(current.x() + dx, current.y() + dy);
        if (WorldUtil.isWall(nextPoint)) {
            return;
        }
        TETile nextTile = getTile(nextPoint);
        covertTile(nextPoint, Tileset.AVATAR);
        covertTile(current, underTile);
        current = nextPoint;
        underTile = nextTile;
    }
}
