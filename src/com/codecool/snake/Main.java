package com.codecool.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        Game game = new Game();
        Timer timer;

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();
        game.start();
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (Globals.gameOver) {
                            System.exit(0);
                        }
                        game.createPowerup();
                    }
                });
            }
        };
        timer = new Timer(1000 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

    }

}
