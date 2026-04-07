package core.interactivity;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Menu {

	private static final double CENTER_X = 47.5;

	public static void drawText(double y, String text, Color color) {
		StdDraw.setPenColor(color);
		StdDraw.text(CENTER_X, y, text);
	}

	public static void setFont(String name, int style, int size) {
		StdDraw.setFont(new Font(name, style, size));
	}

	public static void clear() {
		StdDraw.clear(StdDraw.BLACK);
		StdDraw.setPenColor(StdDraw.WHITE);
	}

	// ===== 页面 =====

	public static void MainMenu() {
		clear();

		// 标题
		setFont("Segoe UI Semilight", Font.BOLD, 50);
		drawText(50, "BYOW", Color.WHITE);

		// 选项
		setFont("Microsoft YaHei", Font.BOLD, 30);
		drawText(40, "(N) 新游戏", Color.WHITE);
		drawText(30, "(L) 加载游戏", Color.WHITE);
		drawText(20, "(Q) 退出游戏", Color.WHITE);

		// 提示
		setFont("Hiragino Sans GB", Font.BOLD, 20);
		drawText(38, "点击N以开始游戏", Color.WHITE);
		drawText(28, "点击L以加载游戏", Color.WHITE); // 你原来写错了
		drawText(18, "点击Q以退出游戏", Color.WHITE);

		// 底部
		// setFont("PingFang SC Light", Font.BOLD, 15);
		drawText(4, "提示:在游戏中按顺序输入:Q可以保存游戏", Color.WHITE);
		drawText(2, "欢迎来到BYOW，别期待会玩得开心，因为这游戏无聊的该死", Color.WHITE);

		StdDraw.show();
	}

	public static void showSeedInput() {
		clear();

		setFont("Segoe UI Semilight", Font.BOLD, 50);
		drawText(50, "BYOW" , Color.WHITE);

		setFont("Hiragino Sans GB", Font.BOLD, 35);
		drawText(35, "输入种子以生成世界", Color.WHITE);

		setFont("Hiragino Sans GB", Font.BOLD, 15);
		drawText(33, "只可输入数字并且将限制在19位以下", Color.WHITE);

		// 输入显示区域（红色）
		setFont("Hiragino Sans GB", Font.BOLD, 35);
		drawText(28, "", Color.red);
	}
	public static void inputSeed(String text) {
		showSeedInput();
		StdDraw.text(47.5, 30, text);
		StdDraw.show();
	}

	public static void end() {
		clear();
		setFont("Hiragino Sans GB", Font.BOLD, 50);
		drawText(30, "你输了！", Color.WHITE);
		StdDraw.show();
	}
}