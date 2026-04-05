package core.ldealFeatures;

import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {
	private static Thread playThread = null;
	private static volatile boolean isRunning = false;

	public static void play(String filename) {
		try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filename))) {
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			audioStream.close();

			clip.addLineListener(event -> {
				if (event.getType() == LineEvent.Type.STOP) {
					clip.close();
				}
			});

			clip.start();
		} catch (Exception e) {
			System.err.println("无法播放音频: " + filename);
			e.printStackTrace();
		}
	}
	public static void BGM(String filename) {
		// 停止之前的音乐
		stop();

		isRunning = true;
		playThread = new Thread(()-> {
			try {
				File file = new File(filename);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

				try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
					line.open(format);
					line.start();

					byte[] buffer = new byte[4096];
					int bytesRead;

					while (isRunning) {
						audioStream.close();
						audioStream = AudioSystem.getAudioInputStream(file);

						while ((bytesRead = audioStream.read(buffer)) != -1 && isRunning) {
							line.write(buffer, 0, bytesRead);
						}
					}
					line.drain();
				}
				audioStream.close();
			} catch (Exception e) {
				System.err.println("背景音乐播放失败: " + filename);
				e.printStackTrace();
			}
		});
		playThread.setDaemon(true); // 主程序退出时自动结束
		playThread.start();
	}

	// 停止背景音乐
	public static void stop() {
		isRunning = false;
		if (playThread != null) {
			try {
				playThread.join(500); // 等最多0.5秒
			} catch (InterruptedException ignored) {}
		}
	}
}
