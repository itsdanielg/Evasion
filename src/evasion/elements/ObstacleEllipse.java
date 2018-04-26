/**
 * @author Daniel Garcia
 */

package evasion.elements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class ObstacleEllipse extends Ellipse implements Obstacle  {

    double yVel;
    double xPos;
    double yPos;

    public ObstacleEllipse() {
        yVel = 10;
        xPos = (int) (Math.random() * (WIDTH - 50));
        yPos = -100;
        setRadiusX(15);
        setRadiusY(50);
        setStroke(Color.GRAY);
        setStrokeWidth(3);
        setFill(Color.RED);
        setCenterX(xPos);
        setCenterY(yPos);
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

    @Override
    public void setY(double y) {
        setCenterY(y);
    }

    @Override
    public double getY() {
        return getY();
    }

}