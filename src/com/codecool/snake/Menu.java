package com.codecool.snake;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    protected static final Font FONT = Font.font("", FontWeight.BOLD, 18);

    protected VBox menuBox;
    protected int currentItem = 0;

    public Scene createScene() {

        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (currentItem < menuBox.getChildren().size() - 1) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                getMenuItem(currentItem).activate();
            }
        });
        return scene;
    }

    protected Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        /*ImageView backGround = new ImageView();
        backGround.setImage(Globals.snakeMenu);
        backGround.setFitWidth(900);
        backGround.setFitHeight(600);
        backGround.setOpacity(0);*/

        Rectangle backGround = new Rectangle(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        backGround.setFill(Color.WHITESMOKE);

        ContentFrame frame1 = new ContentFrame(createTitleContent("Angry Snake"));
        ContentFrame frame2 = new ContentFrame(createImageContent(140, 140, Globals.snakeMenu));

        VBox hbox = new VBox(25, frame1, frame2);
        hbox.setAlignment(Pos.BOTTOM_LEFT);
        hbox.setTranslateX(300);
        hbox.setTranslateY(120);

        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnActivate(() -> System.exit(0));

        MenuItem singlePlayer = new MenuItem("SINGLE PLAYER");
        singlePlayer.setOnActivate(() -> {
            Game game = new Game();
            Timer timer;
            Globals.numOfPlayers = 1;
            Globals.stage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
            Globals.stage.show();
            game.start();
        });

        MenuItem itemMultiPlayer = new MenuItem("MULTIPLE PLAYER");
        itemMultiPlayer.setOnActivate(() -> {
            Globals.stage.setScene(new MultiMenu().createScene());
            Globals.stage.show();
        });



        menuBox = new VBox(30,
                singlePlayer,
                itemMultiPlayer,
                itemExit);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(375);
        menuBox.setTranslateY(400);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(backGround, hbox, menuBox);
        return root;
    }

    protected Node createTitleContent(String theTitle) {
        String title = theTitle;
        HBox letters = new HBox(0);
        letters.setAlignment(Pos.CENTER);
        for (int i = 0; i < title.length(); i++) {
            Text letter = new Text(title.charAt(i) + "");
            letter.setFont(Font.font("", FontWeight.BOLD, 50));
            letter.setEffect(new GaussianBlur(2));
            letter.setFill(Color.BLACK);
            letters.getChildren().add(letter);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(2), letter);
            tt.setDelay(Duration.millis(i * 50));
            tt.setToY(-100);
            tt.setAutoReverse(true);
            tt.setCycleCount(TranslateTransition.INDEFINITE);
            tt.play();
        }

        return letters;
    }

    protected Node createImageContent(int width, int height, Image theImage) {

        ImageView image = new ImageView();
        image.setImage(theImage);
        image.setFitHeight(height);
        image.setFitWidth(width);
        image.setOpacity(0);

        FadeTransition ft = new FadeTransition(Duration.seconds(2), image);
        ft.setDelay(Duration.millis(500));
        ft.setToValue(1);
        ft.setAutoReverse(true);
        ft.setCycleCount(TranslateTransition.INDEFINITE);
        ft.play();

        return image;
    }

    protected MenuItem getMenuItem(int index) {
        return (MenuItem) menuBox.getChildren().get(index);
    }

    protected static class ContentFrame extends StackPane {
        public ContentFrame(Node content) {
            setAlignment(Pos.CENTER);
            getChildren().addAll(content);
        }
    }

    protected static class MenuItem extends HBox {
        private TriCircle c1 = new TriCircle(), c2 = new TriCircle();
        private Text text;
        private Runnable script;

        public MenuItem(String name) {
            super(15);
            setAlignment(Pos.CENTER);

            text = new Text(name);
            text.setFont(FONT);
            text.setEffect(new GaussianBlur(2));

            getChildren().addAll(c1, text, c2);
            setActive(false);

        }

        public void setActive(boolean b) {
            c1.setVisible(b);
            c2.setVisible(b);
            text.setFill(b ? Color.BLACK : Color.GREY);
        }

        public void setOnActivate(Runnable r) {
            script = r;
        }

        public void activate() {
            if (script != null)
                script.run();
        }
    }

    protected static class TriCircle extends Parent {
        public TriCircle() {
            Shape shape1 = Shape.subtract(new Circle(5), new Circle(2));
            shape1.setFill(Color.BLACK);

            Shape shape2 = Shape.subtract(new Circle(5), new Circle(2));
            shape2.setFill(Color.BLACK);
            shape2.setTranslateX(5);

            Shape shape3 = Shape.subtract(new Circle(5), new Circle(2));
            shape3.setFill(Color.BLACK);
            shape3.setTranslateX(2.5);
            shape3.setTranslateY(-5);

            getChildren().addAll(shape1, shape2, shape3);

            setEffect(new GaussianBlur(2));
        }
    }
}
