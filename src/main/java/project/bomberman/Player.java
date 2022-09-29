package project.bomberman;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import static project.bomberman.Main.*;
import static project.bomberman.checkCollision.*;
import static project.bomberman.Constant.*;

public class Player extends DynamicEntity{
    Player(int x, int y, int vx, int vy, String path) {
        super(x, y, vx, vy, path);
        image = new Image(path + "_right_1.png");
        UpImageOrder = -1;
        DownImageOrder = -1;
        LeftImageOrder = -1;
        RightImageOrder = 0;
    }

    void handleEvent(KeyEvent event) {
        switch (event.getCode()) {
            case W -> {
                vy = -DEFAULT_VEL;
                vx = 0;
                update(path, "up", UpImageOrder);
            }
            case S -> {
                vy = DEFAULT_VEL;
                vx = 0;
                update(path, "down", DownImageOrder);
            }
            case D -> {
                vx = DEFAULT_VEL;
                vy = 0;
                update(path, "right", RightImageOrder);
            }
            case A -> {
                vx = -DEFAULT_VEL;
                vy = 0;
                update(path, "left", LeftImageOrder);
            }
        }
        y += vy;
        x += vx;
        if (x < 0 || x > SCREEN_SIZEX - SIZEX) {
            x -= vx;
        }
        else if (y < 0 || y > SCREEN_SIZEY - SIZEY) {
            y -= vy;
        }
        else {
            for (Entity i : StaticEntities) {
                if (Collision(this.getPos(), i.getPos()) == true) {
                    x -= vx;
                    y -= vy;
                    break;
                }
            }
        }
    }
}
