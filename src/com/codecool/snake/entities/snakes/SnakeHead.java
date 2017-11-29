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
    HealthBar healthBarP1;
    HealthBar healthBarP2;
    private boolean isP1 = false;

    public SnakeHead(Pane pane, int xc, int yc, KeyControl keyControl) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        this.keyControl = keyControl;
        isAlive = true;
        if (keyControl == Globals.player1KeyControl) {
            isP1 = true;
            healthBarP1 = new HealthBar(pane, "P1: ",30,60);
        } else if (keyControl == Globals.player2KeyControl){
            healthBarP2 = new HealthBar(pane, "P2: ",30,100);
        }
        addPart(4);
    }

    public void step() {
        if (healthChanged) {
            this.healthChanged = false;
            if (isP1) {
                healthBarP1.changeHealth(this.health);
            } else {
                healthBarP2.changeHealth(this.health);
            }
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
            if (isP1) {
                healthBarP1.changeHealth(0);
            } else {
                healthBarP2.changeHealth(0);
            }
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
