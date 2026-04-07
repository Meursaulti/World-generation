package core;

import core.interactivity.Avatar;
import core.interactivity.Hud;
import core.interactivity.Menu;
import core.entity.Point;
import core.ldealFeatures.AudioPlayer;
import core.ldealFeatures.Monster;
import core.world.World;
import edu.princeton.cs.algs4.StdDraw;
import utils.FileUtils;

import java.util.Random;

import static core.Global.*;

public class InputController {

	// 总控台
	public static void menuController() {
		while (true) {
			if (StdDraw.hasNextKeyTyped()) {
				char c = StdDraw.nextKeyTyped();

				if (c == 'n' || c == 'N') {
					// 播放点击音乐
					AudioPlayer.play(CLICK_SOUND_FILE);
					// 切换至输入种子页面
					Menu.showSeedInput();
					StdDraw.show();
					seedManager();
					break;
				}
				if (c == 'l' || c == 'L') {
					AudioPlayer.play(ENTER_GAME_SOUND);
					FileUtils.loading();
					break;
				}
				if (c == 'q' || c == 'Q') {
					// 播放点击音乐
					AudioPlayer.play(CLICK_SOUND_FILE);

					System.exit(0);
				}

			}
		}
	}

	// 装饰器提供出生点选择的功能
	public static void CharacterControllerDecorator(Point spawnPoint) {
		World.generateWorld(Global.WIDTH, Global.HEIGHT, Global.random);
		characterController(spawnPoint);
	}

	public static void CharacterControllerDecorator() {
		World.generateWorld(Global.WIDTH, Global.HEIGHT, Global.random);
		characterController(roomList.getFirst().spawnRandomRoomTile());
	}

	// 人物移动控制器
	public static void characterController(Point spawnPoint) {
		// 生成角色
		Monster monster = new Monster();
		Avatar avatar = new Avatar(spawnPoint);
		// 渲染世界
		Global.ter.renderFrame(Global.world);

		StringBuilder stringBuilder = new StringBuilder();
		Point lastPoint = null;
		long lastTime = 0;
		while (true) {

			int x = 0;
			int y = 0;

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
					AudioPlayer.play(CLICK_SOUND_FILE);
					FileUtils.safe(avatar.getCurrent());
					System.exit(0);
				}
				if (!stringBuilder.isEmpty()) {
					stringBuilder.setLength(0);
				}
				if (c == ':' ) {
					stringBuilder.append(c);
				}
				// 移动后重新渲染
				avatar.move(x, y);
				Global.ter.renderFrame(Global.world);
				// 移动后刷新HUD
				Hud.refresh();
			}
			//HUD
			Point MousrePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
			Hud.show(MousrePoint);

			// 如果相碰结束游戏
			if (monster.isEnd(avatar.getCurrent())) {
				Menu.end();
				break;
			}
			// 怪物移动
			monster.action(avatar.getCurrent());
		}
	}

	public static void seedManager() {
		StringBuilder stringBuilder = new StringBuilder();
		while (true) {
			if (StdDraw.hasNextKeyTyped()) {
				char c = StdDraw.nextKeyTyped();

				if ((c == 's' || c == 'S' || c == '\n') && !(stringBuilder.isEmpty())) {
					AudioPlayer.play(ENTER_GAME_SOUND);
					seed = Long.parseLong(stringBuilder.toString());
					// 将种子载入全局变量
					Global.random = new Random(seed);
					// 启动人物控制器
					CharacterControllerDecorator();
					break;
				}
				if (c == 8 && !stringBuilder.isEmpty()) {
					stringBuilder.deleteCharAt(stringBuilder.length()-1);
					Menu.inputSeed(stringBuilder.toString());
					continue;
				}
				if (c < 48 || c > 57) {
					continue;
				}
				if (stringBuilder.length() + 1 >= 19) {
					continue;
				}
				stringBuilder.append(c);
				Menu.inputSeed(stringBuilder.toString());
			}
		}
	}
}
