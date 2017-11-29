package com.codecool.snake.entities.snakes;

import com.codecool.snake.HealthBar;
import com.codecool.snake.KeyControl;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private KeyControl keyControl;
    private boolean isAlive;
    private boolean healthChanged = false;
    HealthBar healthBar;

    public SnakeHead(Pane pane, int xc, int yc, KeyControl keyControl, HealthBar healthBar) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        this.keyControl = keyControl;
        isAlive = true;
        this.healthBar = healthBar;
        addPart(4);
    }

    public void step() {
        if (healthChanged) {
            this.healthChanged = false;
            healthBar.changeHealth(this.health);
        }
        if (!isAlive) {
            return;
        }
        double dir = getRotate();
        if (this.keyControl.isLeftKeyPressed()) {
            dir = dir - turnRate;
        }
        if (this.keyControl.isRightKeyPressed()) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            healthBar.changeHealth(0);
            isAlive = false;
            System.out.println("Game Over");
            /*
            Globals.gameLoop.stop();*/
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
        healthChanged = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

}
