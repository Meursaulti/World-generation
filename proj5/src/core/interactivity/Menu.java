package core.interactivity;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Menu {

	public static void MainMenu() {
		StdDraw.setPenColor(StdDraw.WHITE);
		// 标题字体
		Font font = new Font("Segoe UI Semilight", Font.BOLD, 50);
		StdDraw.setFont(font);
		StdDraw.text(47.5, 50, "BYOW");
		// 选项字体
		font = new Font("Microsoft YaHei", Font.BOLD, 30);
		StdDraw.setFont(font);
		StdDraw.text(47.5, 40, "(N) 新游戏");
		StdDraw.text(47.5, 30, "(L) 加载游戏");
		StdDraw.text(47.5, 20, "(Q) 退出游戏");
		// 提示语字体
		font = new Font("Hiragino Sans GB", Font.BOLD, 20);
		StdDraw.setFont(font);
		StdDraw.text(47.5, 38, "点击N以开始游戏");
		StdDraw.text(47.5, 28, "点击Q以加载游戏");
		StdDraw.text(47.5, 18, "点击Q以退出游戏");
		// 开发者有话说
		font = new Font("PingFang SC Light", Font.BOLD, 15);
		StdDraw.setFont(font);
		StdDraw.text(47.5, 4, "提示:在游戏中按顺序输入:Q可以保存游戏");
		StdDraw.text(47.5, 2, "欢迎来到BYOW，别期待会玩得开心，因为这游戏无聊的该死");
		StdDraw.show();
	}
	public static void showSeedInput() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.clear(StdDraw.BLACK);
		// 标题
		Font font = new Font("Segoe UI Semilight", Font.BOLD, 50);
		StdDraw.setFont(font);
		StdDraw.text(47.5, 50, "BYOW");
		// 提示语
		font = new Font("Hiragino Sans GB", Font.BOLD, 35);
		StdDraw.setFont(font);
		StdDraw.text(47.5, 35, "输入种子以生成世界");
		font = new Font("Hiragino Sans GB", Font.BOLD, 20);
		StdDraw.setFont(font);
		StdDraw.text(47.5, 33, "只可输入数字并且将限制在19位以下");
		StdDraw.setPenColor(Color.RED);
	}
}
