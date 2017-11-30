package com.codecool.snake;

import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HealthBar {
    private Text healthText = new Text();
    private String player;

    public HealthBar(Pane pane, String player, int x, int y) {
        this.player = player;
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        healthText.setId(player);
        healthText.setEffect(is);
        healthText.setText(player + "100");
        healthText.setX(x);
        healthText.setY(y);
        healthText.setFill(Color.GREEN);
        healthText.setFont(Font.font(null, FontWeight.BOLD, 36));
        pane.getChildren().add(healthText);
    }


    public void changeHealth(int health) {
        healthText.setText(player + String.valueOf(health));
        if (health <= 60) {
            healthText.setFill(Color.YELLOW);
        }
        if (health <= 30) {
            healthText.setFill(Color.RED);
        }
    }
}
