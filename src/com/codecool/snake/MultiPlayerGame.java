package com.codecool.snake;

import com.codecool.snake.entities.snakes.SnakeHead;

public class MultiPlayerGame extends Game {

    public MultiPlayerGame(int numberOfSnakes){
        super();

        for (int i = 1; i < numberOfSnakes; i++) {
            HealthBar healthBar = new HealthBar(this, String.format("P%d: ", i+1), 30,60 + i*40);
            snakeHeads.add(new SnakeHead(this, i*200, 650, Globals.keyControls.get(i), healthBar));
        }
    }
}
