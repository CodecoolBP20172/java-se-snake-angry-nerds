package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public SimpleEnemy(Pane pane) {
        super(pane);
        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();
        setCoordinates(rnd);
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            heading = Utils.directionToVector(getRotate()-180, 1);
            setRotate(getRotate()-180);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    private void setCoordinates(Random rnd){
        while (true){
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
            if (checkSnakes(this)){
                break;
            }
        }
    }

    private boolean checkSnakes(GameEntity gameEntity){
        for (SnakeHead head : Game.snakeHeads) {
            if (head.snakeIntersects(this)) {
                return false;
            }
            for (SnakeBody body : head.getBodyParts()) {
                if (body.snakeIntersects(this)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        Random rnd = new Random();
        setCoordinates(rnd);
        double direction = rnd.nextDouble() * 360;
        heading = Utils.directionToVector(direction, 1);
        setRotate(direction);
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
