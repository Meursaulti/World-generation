package core;

import core.interactivity.Hud;
import core.interactivity.Menu;
import core.entity.Point;
import core.world.World;
import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.Tileset;
import utils.FileUtils;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

import static core.Global.seed;
import static utils.WorldUtil.*;

public class InputController {

	// 总控台
	public static void menuController() {
		while (true) {
			if (StdDraw.hasNextKeyTyped()) {
				char c = StdDraw.nextKeyTyped();

				if (c == 'n' || c == 'N') {
					Menu.showSeedInput();
					StdDraw.show();
					seedManager();
					break;
				}
				if (c == 'l' || c == 'L') {
					FileUtils.loading();
					break;
				}
				if (c == 'q' || c == 'Q') {
					System.exit(0);
				}

			}
		}
	}

	// 装饰器提供出生点选择的功能
	public static void CharacterControllerDecorator(Point spawnPoint) {
		World.generateWorld(Global.WIDTH, Global.HEIGHT, Global.random);
		TETile underTile = getTile(spawnPoint);
		covertTile(spawnPoint, Tileset.AVATAR);
		Global.ter.renderFrame(Global.world);
		characterController(spawnPoint, underTile);
	}

	public static void CharacterControllerDecorator() {
		World.generateWorld(Global.WIDTH, Global.HEIGHT, Global.random);
		Point spawnPoint = Global.roomList.getFirst().spawnRandomRoomTile();
		TETile underTile = getTile(spawnPoint);
		covertTile(spawnPoint, Tileset.AVATAR);
		Global.ter.renderFrame(Global.world);
		characterController(spawnPoint, underTile);
	}

	// 人物移动控制器
	public static void characterController(Point current, TETile tile) {
		TETile underTile = tile;
		StringBuilder stringBuilder = new StringBuilder();
		Point lastPoint = null;
		long lastTime = 0;
		int count = 0;
		while (true) {

			int x = current.x();
			int y = current.y();

			if (StdDraw.hasNextKeyTyped()) {

				char c = StdDraw.nextKeyTyped();

				if (c == 'w' || c == 'W') {
					y += 1;
				}
				if (c == 'a' || c == 'A') {
					x -= 1;
				}
				if (c == 's' || c == 'S') {
					y -= 1;
				}
				if (c == 'd' || c == 'D') {
					x += 1;
				}
				if ((c == 'q' || c == 'Q') && !stringBuilder.isEmpty()) {
					FileUtils.safe(current);
					System.exit(0);
				}
				if (!stringBuilder.isEmpty()) {
					stringBuilder.setLength(0);
				}
				if (c == ':' ) {
					stringBuilder.append(c);
				}
				Point nextPoint = new Point(x, y);

				if (isWall(nextPoint)) continue;

				covertTile(current, underTile);

				underTile = getTile(nextPoint);

				current = nextPoint;

				covertTile(nextPoint, Tileset.AVATAR);
				// 移动后重新渲染
				Global.ter.renderFrame(Global.world);
			}
			Point point = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
			long currentTime = System.currentTimeMillis();
			if (!Objects.equals(lastPoint, point)) {
				lastPoint = point;
				lastTime = currentTime;
			}
			if (currentTime - lastTime >= 1000) {
				lastTime = currentTime;
				Hud.tooltip(point);
			}
		}
	}
	public static void seedManager() {
		StringBuilder stringBuilder = new StringBuilder();
		while (true) {
			if (StdDraw.hasNextKeyTyped()) {
				char c = StdDraw.nextKeyTyped();
				if (c == 's' || c == 'S' || c == '\n') {
					seed = Long.parseLong(stringBuilder.toString());
					Global.random = new Random(seed);
					CharacterControllerDecorator();
					break;
				}
				if (c < 48 || c > 57) {
					continue;
				}
				if (stringBuilder.length() + 1 >= 19) {
					continue;
				}
				stringBuilder.append(c);
				Menu.showSeedInput();
				StdDraw.text(47.5, 30, stringBuilder.toString());
				StdDraw.show();
			}
		}
	}
}
