/**
 * @author Daniel Garcia
 */

package evasion.elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {

    private static final double WIDTH = 600;
    private static final double HEIGHT = 720;

    double xPos = WIDTH/2 -15;
    double yPos = HEIGHT - 30;

    public Player() {
        setWidth(30);
        setHeight(30);
        setFill(Color.BLACK);
        setX(xPos);
        setY(yPos);
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

}
