package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH || getX() < 0 ||
            getY() > Globals.WINDOW_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    public boolean snakeIntersects(GameEntity b) {
        Bounds headBounds = getBoundsInParent();
        Bounds bodyBounds = b.getBoundsInParent();
        Point2D headCenter = new Point2D((headBounds.getMaxX() + headBounds.getMinX()) * 0.5,
                (headBounds.getMaxY() + headBounds.getMinY()) * 0.5);
        Point2D bodyCenter = new Point2D((bodyBounds.getMaxX() + bodyBounds.getMinX()) * 0.5,
                (bodyBounds.getMaxY() + bodyBounds.getMinY()) * 0.5);
        final double radius = (bodyBounds.getMaxX() - bodyBounds.getMinX()) * 0.5;
        return headCenter.subtract(bodyCenter).magnitude() < 2.0 * radius;
    }
}
