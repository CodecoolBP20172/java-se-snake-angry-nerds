package com.codecool.snake;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;

public class Controls extends Menu{

    @Override
    protected Parent createContent(){

        Pane root = new Pane();
        root.setPrefSize(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        ImageView backGround = new ImageView();
        backGround.setImage(Globals.background);
        backGround.setFitWidth(Globals.WINDOW_WIDTH);
        backGround.setFitHeight(Globals.WINDOW_HEIGHT);

        ContentFrame frame1 = new ContentFrame(createTitleContent("Angry Snake"));
        ContentFrame frame2 = new ContentFrame(createImageContent(140, 140, Globals.snakeMenu));

        VBox vbox = new VBox(25, frame1, frame2);
        vbox.setAlignment(Pos.BOTTOM_LEFT);
        vbox.setTranslateX(300);
        vbox.setTranslateY(120);

/*        Label playerOneControl = new Label("PLAYER 1: RIGHT ARROW - LEFT ARROW");
        Label playerTwoControl = new Label("PLAYER 2: A - D");
        Label playerThreeControl = new Label("PLAYER 3: NUMPAD4 - NUMPAD6");
        Label playerFourControl = new Label("PLAYER 4: B - M");*/

        VBox labelBox = new VBox(15,
                new LabelItem("PLAYER 1: RIGHT ARROW - LEFT ARROW"),
                new LabelItem("PLAYER 2: A - D"),
                new LabelItem("PLAYER 3: NUMPAD4 - NUMPAD6"),
                new LabelItem("PLAYER 4: B - M"));
        labelBox.setAlignment(Pos.TOP_CENTER);
        labelBox.setTranslateX(300);
        labelBox.setTranslateY(400);

        MenuItem itemBack = new MenuItem("BACK");
        itemBack.setOnActivate(() -> {
            Globals.stage.setScene(Globals.scene);
            Globals.stage.show();
        });

        menuBox = new VBox(15,
                itemBack);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(425);
        menuBox.setTranslateY(600);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(backGround, vbox, labelBox, menuBox);
        return root;
    }

    protected static class LabelItem extends HBox {

        private Text text;

        public LabelItem(String name) {
            setAlignment(Pos.CENTER);

            text = new Text(name);
            text.setFont(FONT);
            text.setEffect(new GaussianBlur(2));

            getChildren().addAll(text);

        }
    }
}
