package utils;

import core.Global;
import core.entity.Point;
import core.InputController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

/**
 * 一个简单的文件操作工具库。
 * A library of simple file operations.
 *
 * 可以根据需要自由修改此文件。
 * Feel free to modify this file.
 */
public class FileUtils {

    /**
     * 将指定内容写入到给定文件名的文件中。
     * Writes the specified contents to a file with the given filename.
     *
     * @param filename 文件名（写入目标）
     *                 The name of the file to write to.
     * @param contents 要写入的内容
     *                 The contents to write to the file.
     *
     * @throws RuntimeException 如果写入过程中发生 IOException，则抛出运行时异常
     *                          Thrown if an IOException occurs during the write operation.
     */
    public static void writeFile(String filename, String contents) {
        try {
            Files.writeString(new File(filename).toPath(), contents);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 读取指定文件名的文件内容。
     * Reads the contents of a file with the given filename.
     *
     * @param filename 文件名（读取来源）
     *                 The name of the file to read from.
     * @return 文件内容字符串
     *         The contents of the file as a String.
     *
     * @throws RuntimeException 如果读取过程中发生 IOException，则抛出运行时异常
     *                          Thrown if an IOException occurs during the read operation.
     */
    public static String readFile(String filename) {
        try {
            return Files.readString(new File(filename).toPath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 检查指定文件是否存在。
     * Checks if a file with the given filename exists.
     *
     * @param filename 文件名
     *                 The name of the file to check.
     * @return 如果文件存在返回 true，否则返回 false
     *         Returns true if the file exists, false otherwise.
     */
    public static boolean fileExists(String filename) {
        return new File(filename).exists();
    }

    public static void safe(Point current) {
	    String contents = current.x() +
			    " " +
			    current.y() +
			    " " +
			    Global.seed;
        writeFile("safe.txt", contents);
    }
    public static void loading(){
        String readline = readFile("safe.txt");
        String[] strings = readline.split(" ");
        int x = Integer.parseInt(strings[0]);
        int y = Integer.parseInt(strings[1]);
        long seed = Long.parseLong(strings[2]);
        Global.random = new Random(seed);
        Global.seed = seed;
        InputController.CharacterControllerDecorator(new Point(x, y));
    }
}