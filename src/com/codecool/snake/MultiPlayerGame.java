package com.codecool.snake;

import com.codecool.snake.entities.snakes.SnakeHead;

public class MultiPlayerGame extends Game {

    public MultiPlayerGame(){
        super();
        HealthBar healthBarP2 = new HealthBar(this, "P2: ", 30,100);
        SnakeHead snake2 = new SnakeHead(this, 300, 300, Globals.player2KeyControl, healthBarP2);
        snakeHeads.add(snake2);

    }
}
