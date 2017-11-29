package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class OppositePowerup extends GameEntity implements Interactable {

    public OppositePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupOpposite);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if(Globals.oppositeDirection) {
            Globals.oppositeDirection = false;
        }
        else {
            Globals.oppositeDirection = true;
        }
        destroy();
        new OppositePowerup(super.pane);
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
