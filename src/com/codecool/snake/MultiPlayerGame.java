package com.codecool.snake;

import com.codecool.snake.entities.snakes.SnakeHead;

public class MultiPlayerGame extends Game {

    public MultiPlayerGame(){
        super();
        SnakeHead snake2 = new SnakeHead(this, 300, 300, Globals.player2KeyControl);
        snakeHeads.add(snake2);

    }
}
