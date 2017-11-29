package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Game extends Pane {

    public static List<SnakeHead> snakeHeads = new ArrayList<>();

    public Game() {

        SnakeHead snakeHead = new SnakeHead(this, 500, 500, Globals.player1KeyControl);
        snakeHeads.add(snakeHead);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.player1KeyControl.setLeftKeyPressed(true); break;
                case RIGHT: Globals.player1KeyControl.setRightKeyPressed(true); break;
                case A: Globals.player2KeyControl.setLeftKeyPressed(true); break;
                case D: Globals.player2KeyControl.setRightKeyPressed(true); break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.player1KeyControl.setLeftKeyPressed(false); break;
                case RIGHT: Globals.player1KeyControl.setRightKeyPressed(false); break;
                case A: Globals.player2KeyControl.setLeftKeyPressed(false); break;
                case D: Globals.player2KeyControl.setRightKeyPressed(false); break;

            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public static boolean checkGameOver(){
        for (SnakeHead snakeHead:snakeHeads) {
            if (snakeHead.isAlive()){
                return false;
            }
        }
        return true;
    }
}
