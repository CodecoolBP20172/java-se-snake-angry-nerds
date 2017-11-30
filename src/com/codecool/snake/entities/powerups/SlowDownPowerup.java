package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;


// a simple powerup that makes the snake grow TODO make other powerups
public class SlowDownPowerup extends GameEntity implements Interactable {

    String soundTrack = "resources/powerdown.wav";
    Media music= new Media(new File(soundTrack).toURI().toString());
    MediaPlayer mediaPlayer1 = new MediaPlayer(music);

    public SlowDownPowerup(Pane pane) {
        super(pane);
        setImage(Globals.slowDown);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        mediaPlayer1.play();
        if (snakeHead.getSpeed() == 2) {
            snakeHead.setImage(Globals.snakeHeadSlow);
            snakeHead.setSpeed(1);
            snakeHead.setTurnRate(1);
        }
        else if(snakeHead.getSpeed() == 3){
            snakeHead.setImage(Globals.snakeHead);
            snakeHead.setSpeed(2);
            snakeHead.setTurnRate(2);
        }
         else {
            snakeHead.setSpeed(1);
            snakeHead.setTurnRate(1);
        }
        destroy();
        new SlowDownPowerup(super.pane);
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
