package tileengine;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import edu.princeton.cs.algs4.StdDraw;
import utils.RandomUtils;

/**
 * The TETile object is used to represent a single tile in your world.
 * TETile 对象用于表示世界中的一个单独 tile（瓦片）。
 *
 * A 2D array of tiles make up a board, and can be drawn to the screen using the TERenderer class.
 * 一个二维 tile 数组构成整个地图（board），并可以通过 TERenderer 渲染到屏幕上。
 *
 * All TETile objects must have a character, textcolor, and background color to be used
 * to represent the tile when drawn to the screen.
 * 每个 TETile 必须包含字符、前景色（textColor）和背景色，用于屏幕显示。
 *
 * You can also optionally provide a path to an image file of an appropriate size (16x16)
 * to be drawn in place of the unicode representation.
 * 你也可以选择提供一个 16x16 的图片路径，用图片替代字符显示。
 *
 * If the image path provided cannot be found, draw will fallback to using the provided
 * character and color representation,
 * 如果图片路径无效，则 draw 方法会回退到使用字符 + 颜色进行绘制，
 *
 * so you are free to use image tiles on your own computer.
 * 因此你可以在本地自由使用图片 tile。
 *
 * The provided TETile is immutable, i.e. none of its instance variables can change.
 * 当前提供的 TETile 是不可变对象（immutable），即其成员变量不可修改。
 *
 * You are welcome to make your TETile class mutable, if you prefer.
 * 如果你愿意，也可以将其改为可变（mutable）。
 */
public class TETile {
    private final char character; // Do not rename character or the autograder will break.
    private final Color textColor;
    private final Color backgroundColor;
    private final String description;
    private final String filepath;
    private final int id;

    /**
     * Full constructor for TETile objects.
     * TETile 的完整构造函数。
     *
     * @param character The character displayed on the screen.
     *                  显示在屏幕上的字符
     * @param textColor The color of the character itself.
     *                  字符颜色（前景色）
     * @param backgroundColor The color drawn behind the character.
     *                        背景颜色
     * @param description The description of the tile, shown in the GUI on hovering over the tile.
     *                    tile 的描述（鼠标悬停时显示）
     * @param filepath Full path to image to be used for this tile. Must be correct size (16x16)
     *                 图片路径（必须是 16x16）
     */
    public TETile(char character, Color textColor, Color backgroundColor, String description,
                  String filepath, int id) {
        this.character = character;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.description = description;
        this.filepath = filepath;
        this.id = id;
    }

    /**
     * Constructor without filepath.
     * 不带 filepath 的构造函数。
     *
     * In this case, filepath will be null, so when drawing, we will not even try to draw an image,
     * 此时 filepath 为 null，绘制时不会尝试加载图片，
     *
     * and will instead use the provided character and colors.
     * 而是直接使用字符 + 颜色绘制。
     *
     * @param character The character displayed on the screen.
     * @param textColor The color of the character itself.
     * @param backgroundColor The color drawn behind the character.
     * @param description The description of the tile, shown in the GUI on hovering over the tile.
     */
    public TETile(char character, Color textColor, Color backgroundColor, String description, int id) {
        this.character = character;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.description = description;
        this.filepath = null;
        this.id = id;
    }

    /**
     * Creates a copy of TETile t, except with given textColor.
     * 创建一个 TETile 的拷贝，但使用新的 textColor。
     *
     * @param t tile to copy
     *          原 tile
     * @param textColor foreground color for tile copy
     *                  新的前景色
     */
    public TETile(TETile t, Color textColor) {
        this(t.character, textColor, t.backgroundColor, t.description, t.filepath, t.id);
    }

    /**
     * Creates a copy of TETile t, except with given character.
     * 创建一个 TETile 的拷贝，但使用新的 character。
     *
     * @param t tile to copy
     * @param c character for tile copy
     */
    public TETile(TETile t, char c) {
        this(c, t.textColor, t.backgroundColor, t.description, t.filepath, t.id);
    }

    /**
     * Draws the tile to the screen at location x, y.
     * 在 (x, y) 位置绘制该 tile。
     *
     * If a valid filepath is provided, we draw the image located at that filepath.
     * 如果 filepath 有效，则绘制对应图片。
     *
     * Otherwise, we fall back to the character and color representation.
     * 否则回退为字符 + 颜色绘制。
     *
     * Note that the image must be 16x16.
     * 注意图片必须是 16x16。
     *
     * It will not be automatically resized or truncated.
     * 不会自动缩放或裁剪。
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void draw(double x, double y) {
        if (filepath != null) {
            try {
                StdDraw.picture(x + 0.5, y + 0.5, filepath);
                return;
            } catch (IllegalArgumentException e) {
                // Exception happens because the file can't be found.
                // 异常发生：图片文件不存在
                // In this case, fail silently and use fallback rendering.
                // 此时静默失败，使用字符绘制
            }
        }

        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
        StdDraw.setPenColor(textColor);
        StdDraw.text(x + 0.5, y + 0.5, Character.toString(character()));
    }

    /**
     * Character representation of the tile. Used for drawing in text mode.
     * tile 的字符表示，用于文本模式绘制。
     *
     * @return character representation
     */
    public char character() {
        return character;
    }

    /**
     * Description of the tile.
     * tile 的描述。
     *
     * Useful for displaying mouseover text or testing equality.
     * 可用于鼠标悬停显示或测试 tile 类型是否相同。
     *
     * @return description of the tile
     */
    public String description() {
        return description;
    }

    /**
     * ID number of the tile. Used for equality comparisons.
     * tile 的 ID，用于判断相等性。
     *
     * @return id of the tile
     */
    public int id() {
        return id;
    }

    /**
     * Creates a copy of the given tile with a slightly different text color.
     * 创建一个 tile 副本，并对其颜色做轻微随机变化。
     *
     * The new color will vary within dr, dg, db range.
     * 新颜色在 dr、dg、db 范围内随机波动。
     *
     * @param t the tile to copy
     * @param dr max red variation
     * @param dg max green variation
     * @param db max blue variation
     * @param r random generator
     */
    public static TETile colorVariant(TETile t, int dr, int dg, int db, Random r) {
        Color oldColor = t.textColor;
        int newRed = newColorValue(oldColor.getRed(), dr, r);
        int newGreen = newColorValue(oldColor.getGreen(), dg, r);
        int newBlue = newColorValue(oldColor.getBlue(), db, r);

        Color c = new Color(newRed, newGreen, newBlue);

        return new TETile(t, c);
    }

    private static int newColorValue(int v, int dv, Random r) {
        int rawNewValue = v + RandomUtils.uniform(r, -dv, dv + 1);

        // make sure value doesn't fall outside of the range 0 to 255.
        // 确保颜色值在 0~255 之间
        int newValue = Math.min(255, Math.max(0, rawNewValue));
        return newValue;
    }

    /**
     * Converts the given 2D array to a String. Handy for debugging.
     * 将二维 tile 数组转换为字符串，方便调试。
     *
     * Note: y = 0 is the bottom of the world.
     * 注意：y=0 表示底部。
     *
     * So printing is reversed vertically.
     * 因此打印时需要“倒序输出”（从上往下）。
     *
     * @param world the 2D world
     * @return string representation
     */
    public static String toString(TETile[][] world) {
        int width = world.length;
        int height = world[0].length;
        StringBuilder sb = new StringBuilder();

        for (int y = height - 1; y >= 0; y -= 1) {
            for (int x = 0; x < width; x += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                sb.append(world[x][y].character());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Makes a copy of the given 2D tile array.
     * 复制一个二维 tile 数组（浅拷贝每一列）。
     *
     * @param tiles original array
     */
    public static TETile[][] copyOf(TETile[][] tiles) {
        if (tiles == null) {
            return null;
        }

        TETile[][] copy = new TETile[tiles.length][];

        int i = 0;
        for (TETile[] column : tiles) {
            copy[i] = Arrays.copyOf(column, column.length);
            i += 1;
        }

        return copy;
    }

    /**
     * Checks if two tiles are equal by comparing their IDs.
     * 通过比较 ID 判断两个 tile 是否相等。
     *
     * @param o object to compare
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return (o instanceof TETile otherTile && otherTile.id == this.id);
    }
}