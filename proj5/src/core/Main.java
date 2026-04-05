package core;


import core.interactivity.Menu;
import tileengine.TERenderer;

import static core.Global.*;

public class Main {

    static void main(String[] args) throws InterruptedException {

        // 初始化渲染器
        TERenderer ter = new TERenderer();
        Global.ter = ter;
        ter.initialize(WIDTH, HEIGHT);

        Menu.MainMenu();
        InputController.menuController();
    }
}
