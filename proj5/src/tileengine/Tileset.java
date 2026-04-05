package tileengine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 * 包含一组常量 tile 对象，避免在代码的不同地方重复创建相同的 tile。
 *
 * You are free to (and encouraged to) create and add your own tiles to this file.
 * 你可以（并且鼓励你）在这个文件中创建并添加你自己的 tile。
 *
 * This file will be turned in with the rest of your code.
 * 该文件会和你的其他代码一起提交。
 *
 * Ex:
 * 示例：
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode characters.
 * 由于使用了 Unicode 字符，代码风格检查工具（style checker）在检查该文件时可能会崩溃。
 *
 * This is OK.
 * 这是正常现象，不用担心。
 */
public class Tileset {

    // 玩家 / 角色
    public static final TETile AVATAR = new TETile('@', Color.white, Color.black, "你", 0);

    // 墙
    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "墙", 1);

    // 地板
    public static final TETile FLOOR = new TETile('·', new Color(128, 192, 128), Color.black, "地板", 2);

    // 空（未使用 / 空白区域）
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "虚无", 3);

    // 草地
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "草地", 4);

    // 水
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "水", 5);

    // 花
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "花", 6);

    // 上锁的门
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "上锁的门", 7);

    // 解锁的门
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "解锁的门", 8);

    // 沙地
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "沙地", 9);

    // 山
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "山", 10);

    // 树
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "树", 11);

    // 单元格
    public static final TETile CELL = new TETile('█', Color.white, Color.black, "单元格", 12);
}