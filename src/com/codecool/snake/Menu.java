package com.codecool.snake;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
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

public class Menu {

    private static final Font FONT = Font.font("", FontWeight.BOLD, 18);

    private VBox menuBox;
    private int currentItem = 0;

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


    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(900, 600);

        /*ImageView backGround = new ImageView();
        backGround.setImage(Globals.snakeMenu);
        backGround.setFitWidth(900);
        backGround.setFitHeight(600);
        backGround.setOpacity(0);*/

        Rectangle backGround = new Rectangle(900, 600);
        backGround.setFill(Color.WHITESMOKE);

        ContentFrame frame1 = new ContentFrame(createTitleContent());
        ContentFrame frame2 = new ContentFrame(createImageContent(120, 120));

        VBox hbox = new VBox(15, frame1, frame2);
        hbox.setAlignment(Pos.BOTTOM_LEFT);
        hbox.setTranslateX(270);
        hbox.setTranslateY(90);

        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnActivate(() -> System.exit(0));

        MenuItem itemOnePlayer = new MenuItem("ONE PLAYER");
        itemOnePlayer.setOnActivate(() -> {
            Game game = new Game();
            Globals.stage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
            Globals.stage.show();
            game.start();
        });

        MenuItem itemMultiPlayer = new MenuItem("TWO PLAYER");
        itemMultiPlayer.setOnActivate(() -> {
            Game game = new MultiPlayerGame();
            Globals.stage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
            Globals.stage.show();
            game.start();
        });

        menuBox = new VBox(30,
                itemOnePlayer,
                itemMultiPlayer,
                /*new MenuItem("ONLINE"),
                new MenuItem("OPTIONS"),
                new MenuItem("EXTRAS*"),*/
                itemExit);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(360);
        menuBox.setTranslateY(350);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(backGround, hbox, menuBox);
        return root;
    }

    private Node createTitleContent() {
        String title = "Angry Snakes";
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

    private Node createImageContent(int width, int height) {

        ImageView image = new ImageView();
        image.setImage(Globals.snakeMenu);
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

    private MenuItem getMenuItem(int index) {
        return (MenuItem) menuBox.getChildren().get(index);
    }

    private static class ContentFrame extends StackPane {
        public ContentFrame(Node content) {
            setAlignment(Pos.CENTER);

            Rectangle frame = new Rectangle(0, 0);
            frame.setOpacity(0);
            getChildren().addAll(frame, content);
        }
    }

    private static class MenuItem extends HBox {
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

    private static class TriCircle extends Parent {
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
