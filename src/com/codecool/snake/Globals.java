package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image gameOverImg = new Image("gameover.jpg");
    public static Stage stage;
    public static Scene scene;

    public static Integer numOfPlayers;

    public static Game game;
    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeHeadSlow = new Image("snake_head_slow_2.png");
    public static Image snakeHeadFast = new Image("snake_head_fast.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image speedUp = new Image("SpeedUp.png");
    public static Image slowDown = new Image("SpeedDown.png");
    public static Image powerupOpposite = new Image("opposite.png");
    public static Image background = new Image("green.jpg");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean gameOver = false;
    public static Image snakeMenu = new Image("snake.png");
    //.. put here the other images you want to use

    public static KeyControl player1KeyControl = new KeyControl();
    public static KeyControl player2KeyControl = new KeyControl();
    public static KeyControl player3KeyControl = new KeyControl();
    public static KeyControl player4KeyControl = new KeyControl();
    public static List<KeyControl> keyControls = addKeyControls();

    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static String soundTrack = "resources/snake.mp3";
    public static Media music= new Media(new File(soundTrack).toURI().toString());
    public static MediaPlayer mediaPlayer1 = new MediaPlayer(music);

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

    public static List<KeyControl> addKeyControls() {
        ArrayList<KeyControl> keyControls = new ArrayList <>();
        keyControls.add(player1KeyControl);
        keyControls.add(player2KeyControl);
        keyControls.add(player3KeyControl);
        keyControls.add(player4KeyControl);
        return keyControls;
    }
}
