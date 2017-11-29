package com.codecool.snake.entities.snakes;

import com.codecool.snake.HealthBar;
import com.codecool.snake.KeyControl;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.lang.reflect.*;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private KeyControl keyControl;
    private boolean isAlive;
    private boolean isP1 = false;
    private boolean healthChanged = false;
    private Text healthTextP1 = new Text();
    private Text healthTextP2 = new Text();

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
            this.isP1 = true;
        }
        addPart(4);
        addHealthText();
    }

    public void step() {
        if (healthChanged) {
            this.healthChanged = false;
            changeHealthText();
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

    private void addHealthText() {
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        if (isP1) {
            healthTextP1.setId("P2");
            healthTextP1.setEffect(is);
            healthTextP1.setText("P1: " + String.valueOf(this.health));
            healthTextP1.setX(30);
            healthTextP1.setY(60);
            healthTextP1.setFill(Color.GREEN);
            healthTextP1.setFont(Font.font(null, FontWeight.BOLD, 36));
            pane.getChildren().add(healthTextP1);
        } else {
            healthTextP2.setId("P2");
            healthTextP2.setEffect(is);
            healthTextP2.setText("P2: " + String.valueOf(this.health));
            healthTextP2.setX(30);
            healthTextP2.setY(100);
            healthTextP2.setFill(Color.GREEN);
            healthTextP2.setFont(Font.font(null, FontWeight.BOLD, 36));
            pane.getChildren().add(healthTextP2);
        }
    }

    private void changeHealthText() {
        if (isP1) {
            healthTextP1.setText("P1: " + String.valueOf(this.health));
            if (this.health < 70) {
                healthTextP1.setFill(Color.YELLOW);
            }
        } else {
            healthTextP2.setText("P2: " + String.valueOf(this.health));
        }


    }
}
