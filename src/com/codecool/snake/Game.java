package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.FastPowerup;
import com.codecool.snake.entities.powerups.OppositePowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.SlowDownPowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Random;

public class Game extends Pane {


    public Game() {
        new SnakeHead(this, 500, 500);
    }

    public void createPowerup() {
        Random random = new Random();
        int  number = random.nextInt(100);
        if (number < 50) {
            new SimplePowerup(this);
        }
        else if (number > 49 && number < 65) {
            new OppositePowerup(this);
            new SimplePowerup(this);
        }
        else if (number > 64 && number < 80) {
            new SlowDownPowerup(this);
            new SimplePowerup(this);
        }
        else if (number > 79 && number < 100) {
            new FastPowerup(this);
            new SimplePowerup(this);
        }

    }

    public void start() {

        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
        Globals.mediaPlayer1.play();
    }

    public void setBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
