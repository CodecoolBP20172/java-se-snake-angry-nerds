package com.codecool.snake;

public class KeyControl {

    private boolean leftKeyPressed;
    private boolean rightKeyPressed;

    public KeyControl() {
        this.leftKeyPressed = false;
        this.rightKeyPressed = false;
    }

    public boolean isLeftKeyPressed() {
        return leftKeyPressed;
    }

    public void setLeftKeyPressed(boolean leftKeyPressed) {
        this.leftKeyPressed = leftKeyPressed;
    }

    public boolean isRightKeyPressed() {
        return rightKeyPressed;
    }

    public void setRightKeyPressed(boolean rightKeyPressed) {
        this.rightKeyPressed = rightKeyPressed;
    }

}
