package com.codecool.snake;


import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Globals.stage = primaryStage;
        Globals.scene = new Menu().createScene();
        primaryStage.setScene(Globals.scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
