package com.codecool.snake.entities.powerups;

import com.codecool.snake.Game;
import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends GameEntity implements Interactable {

    static String eat = "resources/eat.mp3";
    static Media eating = new Media(new File(eat).toURI().toString());
    static MediaPlayer mediaPlayer2 = new MediaPlayer(eating);

    public SimplePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupBerry);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(4);
        mediaPlayer2.play();
        destroy();
    }

    public void createNew() {
        new SimplePowerup(super.pane);
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
