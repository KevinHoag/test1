package project.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static project.bomberman.Constant.*;

public class Main extends Application {
    Group root;
    Scene scene;
    Canvas canvas = new Canvas(SCREEN_SIZEX, SCREEN_SIZEY);
    GraphicsContext gc;

    char[][] map = new char[20][20];
    Image img2 = new Image("Wall.png");
    Image img3 = new Image("Grass.png");

    Player bomber = new Player(0, 0, 10, 10, "bomber");
    static List<Entity> StaticEntities = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) {
        root = new Group();
        scene = new Scene(root);
        gc = canvas.getGraphicsContext2D();

        long lastTime = System.nanoTime();

        stage.setX(SCREEN_POSX);
        stage.setY(SCREEN_POSY);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();

        creatmap();
        drawmap();
        bomber.render(gc);
        boolean quit = false;
        while (!quit) {

        }
    }

    void creatmap() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++)
                map[i][j] = ' ';
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int tam = (int) (Math.random() * 3);
                if (tam == 1 && i != 0 && j != 0) {
                    Entity obj = new Entity(j * 50, i * 40, "wall.png");
                    StaticEntities.add(obj);
                    map[i][j] = '*';
                }
            }
        }
    }

    void drawmap() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == '*') gc.drawImage(img2, j * SIZEX, i * SIZEY, SIZEX, SIZEY);
                else gc.drawImage(img3, j * SIZEX, i * SIZEY, SIZEX, SIZEY);
            }
        }
    }

    void update() {

    }
}
