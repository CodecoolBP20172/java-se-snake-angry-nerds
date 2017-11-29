package com.codecool.snake;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MultiMenu extends Menu {

    @Override
    protected Parent createContent(){

        Pane root = new Pane();
        root.setPrefSize(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        Rectangle backGround = new Rectangle(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        backGround.setFill(Color.WHITESMOKE);

        ContentFrame frame1 = new ContentFrame(createTitleContent());
        ContentFrame frame2 = new ContentFrame(createImageContent(140, 140));

        VBox hbox = new VBox(25, frame1, frame2);
        hbox.setAlignment(Pos.BOTTOM_LEFT);
        hbox.setTranslateX(300);
        hbox.setTranslateY(120);

        MenuItem itemBack = new MenuItem("BACK");
        itemBack.setOnActivate(() -> {
            Globals.stage.setScene(Globals.scene);
            Globals.stage.show();

        });

        MenuItem itemTwoPlayers = new MenuItem("TWO PLAYERS");
        itemTwoPlayers.setOnActivate(() -> {
            Game game = new MultiPlayerGame(2);
            Globals.stage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
            Globals.stage.show();
            game.start();
        });

        MenuItem itemThreePlayers = new MenuItem("THREE PLAYERS");
        itemThreePlayers.setOnActivate(() -> {
            Game game = new MultiPlayerGame(3);
            Globals.stage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
            Globals.stage.show();
            game.start();
        });

        MenuItem itemFourPlayers = new MenuItem("FOUR PLAYERS");
        itemFourPlayers.setOnActivate(() -> {
            Game game = new MultiPlayerGame(4);
            Globals.stage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
            Globals.stage.show();
            game.start();
        });

        menuBox = new VBox(15,
                itemTwoPlayers,
                itemThreePlayers,
                itemFourPlayers,
                new MenuItem("CONTROLS"),
                itemBack);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(375);
        menuBox.setTranslateY(400);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(backGround, hbox, menuBox);
        return root;
    }
}
