package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

import static javafx.scene.paint.Color.*;

public class GameOver {

    public GameOver() {
        System.out.println("gameover");
        Pane pane = new Pane();
        /*setX(Globals.WINDOW_WIDTH/2+30);
        setY(Globals.WINDOW_HEIGHT/2);
        setImage(Globals.gameOverImg);
        pane.getChildren().add(this);*/
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        Text t = new Text();
        t.setEffect(is);
        t.setX(Globals.WINDOW_WIDTH/2);
        t.setY(Globals.WINDOW_HEIGHT/2-30);
        t.setText("GAME OVER");
        t.setFill(RED);
        t.setFont(Font.font(null, FontWeight.BOLD, 36));
        pane.getChildren().add(t);
    }
}
