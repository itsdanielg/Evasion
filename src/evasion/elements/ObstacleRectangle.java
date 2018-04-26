/**
 * @author Daniel Garcia
 */

package evasion.elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObstacleRectangle extends Rectangle implements Obstacle  {

    double yVel;
    double xPos;
    double yPos;

    public ObstacleRectangle() {
        yVel = 10;
        xPos = (int) (Math.random() * (WIDTH - 50));
        yPos = -100;
        setWidth(30);
        setHeight(100);
        setStroke(Color.GRAY);
        setStrokeWidth(3);
        setFill(Color.RED);
        setX(xPos);
        setY(yPos);
    }

    @Override
    public double getYVel() {
        return yVel;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    @Override
    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

}