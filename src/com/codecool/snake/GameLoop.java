package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.powerups.SimplePowerup;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
            if (Game.checkGameOver()){
                Globals.gameLoop.stop();
                Game.timer.stop();
                for (GameEntity entity : Globals.gameObjects){
                    entity.destroy();
                }
                for (KeyControl playerControl: Globals.keyControls) {
                    playerControl.setRightKeyPressed(false);
                    playerControl.setLeftKeyPressed(false);
                }
                Globals.stage.setScene(new GameOver().createScene());
                break;
            }
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }
}
