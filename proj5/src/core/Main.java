package core;


import core.interactivity.Menu;
import core.ldealFeatures.AudioPlayer;
import tileengine.TERenderer;

import static core.Global.*;

public class Main {

    static void main(String[] args) throws InterruptedException {
        // 播放音乐
        AudioPlayer.BGM(BACKGROUND_MUSIC_FILE);
        // 初始化渲染器
        TERenderer ter = new TERenderer();
        Global.ter = ter;
        ter.initialize(WIDTH, HEIGHT);

        Menu.MainMenu();
        InputController.menuController();
    }
}
