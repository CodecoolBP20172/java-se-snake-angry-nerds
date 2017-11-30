package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeBody extends GameEntity implements Animatable {

    private GameEntity parent;
    private Queue<Vec2d> history = new LinkedList<>();
    private static final int historySize = 7;
    private SnakeHead snakeHead;

    public SnakeBody(Pane pane, GameEntity parent) {
        super(pane);
        this.parent = parent;
        findAndSetSnakeHead();

        setImage(Globals.snakeBody);

        // place it visually below the current tail
        List<Node> children = pane.getChildren();
        children.add(children.indexOf(parent), this);

        double xc = parent.getX();
        double yc = parent.getY();
        setX(xc);
        setY(yc);
        for (int i = 0; i < historySize; i++) {
            history.add(new Vec2d(xc, yc));
        }
    }

    public GameEntity getBodyParent() {
        return parent;
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public void step() {
        if (!headIsAlive()) {
            return;
        }
        Vec2d pos = history.poll(); // remove the oldest item from the history
        setX(pos.x);
        setY(pos.y);
        history.add(new Vec2d(parent.getX(), parent.getY())); // add the parent's current position to the beginning of the history
    }

    public boolean headIsAlive() {
        return getSnakeHead().isAlive();
    }

    private void findAndSetSnakeHead() {
        if (this.parent instanceof SnakeHead) {
            this.snakeHead = (SnakeHead)parent;
        } else {
            this.snakeHead = ((SnakeBody)this.parent).getSnakeHead();
        }
    }

}
