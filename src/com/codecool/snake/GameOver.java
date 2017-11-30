package com.codecool.snake;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GameOver extends Menu {

    protected Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        Rectangle backGround = new Rectangle(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        backGround.setFill(Color.WHITESMOKE);
        ContentFrame frame1 = new ContentFrame(createTitleContent("Game Over"));
        ContentFrame frame2 = new ContentFrame(createImageContent(140, 140, Globals.gameOverImg));

        VBox hbox = new VBox(25, frame1, frame2);
        hbox.setAlignment(Pos.BOTTOM_LEFT);
        hbox.setTranslateX(350);
        hbox.setTranslateY(120);

        MenuItem itemBack = new MenuItem("BACK TO MAIN MENU");
        itemBack.setOnActivate(() -> {
            Globals.stage.setScene(Globals.scene);
            Globals.stage.show();

        });

        MenuItem restart = new MenuItem("RESTART");
        restart.setOnActivate(() -> {
            if (Globals.numOfPlayers == 1) {
                Globals.game = new Game();
                Globals.stage.setScene(new Scene(Globals.game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
                Globals.stage.show();
                Globals.game.start();
            } else {
                Globals.game = new MultiPlayerGame(Globals.numOfPlayers);
                Globals.stage.setScene(new Scene(Globals.game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
                Globals.stage.show();
                Globals.game.start();
            }
        });
        menuBox = new VBox(15,
                restart,
                itemBack);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(375);
        menuBox.setTranslateY(400);

        getMenuItem(0).setActive(true);
        root.getChildren().addAll(backGround, hbox, menuBox);
        return root;
    }
}
