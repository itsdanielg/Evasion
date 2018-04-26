/**
 * @author Daniel Garcia
 */

package evasion.elements;

public interface Obstacle {

    double WIDTH = 600;

    double getYVel();
    double getYPos();
    double getY();
    void setYPos(double yPos);
    void setY(double y);

}