package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.FastPowerup;
import com.codecool.snake.entities.powerups.OppositePowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.SlowDownPowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class Game extends Pane {

    public static List<SnakeHead> snakeHeads = new ArrayList<>();

        static Timer timer;

    public Game() {

        HealthBar healthBar = new HealthBar(this, "P1: ", 30,60);
        SnakeHead snakeHead = new SnakeHead(this, 500, 500, Globals.player1KeyControl, healthBar, 0);
        snakeHeads.add(snakeHead);
        setBackground(Globals.background);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        createPowerups();

    }

    public void Powerup() {
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

    public void createPowerups() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Powerup();
                    }
                });
            }
        };
        timer = new Timer(1500 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
        if(checkGameOver()) {
            timer.stop();
        }
    }

    public void start() {

        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE: Globals.gameLoop.stop();
                            Globals.stage.setScene(Globals.scene); break;
                case LEFT:  Globals.player1KeyControl.setLeftKeyPressed(true); break;
                case RIGHT: Globals.player1KeyControl.setRightKeyPressed(true); break;
                case Q: Globals.player2KeyControl.setLeftKeyPressed(true); break;
                case W: Globals.player2KeyControl.setRightKeyPressed(true); break;
                case C: Globals.player3KeyControl.setLeftKeyPressed(true); break;
                case V: Globals.player3KeyControl.setRightKeyPressed(true); break;
                case K: Globals.player4KeyControl.setLeftKeyPressed(true); break;
                case L: Globals.player4KeyControl.setRightKeyPressed(true); break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.player1KeyControl.setLeftKeyPressed(false); break;
                case RIGHT: Globals.player1KeyControl.setRightKeyPressed(false); break;
                case Q: Globals.player2KeyControl.setLeftKeyPressed(false); break;
                case W: Globals.player2KeyControl.setRightKeyPressed(false); break;
                case C: Globals.player3KeyControl.setLeftKeyPressed(false); break;
                case V: Globals.player3KeyControl.setRightKeyPressed(false); break;
                case K: Globals.player4KeyControl.setLeftKeyPressed(false); break;
                case L: Globals.player4KeyControl.setRightKeyPressed(false); break;
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

    public static boolean checkGameOver(){
        for (SnakeHead snakeHead:snakeHeads) {
            if (snakeHead.isAlive()){
                return false;
            }
        }
        return true;
    }
}
