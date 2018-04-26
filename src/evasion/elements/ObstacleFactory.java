package evasion.elements;

/**
 * @author Daniel Garcia, 111157499
 */
public class ObstacleFactory {

    public static Obstacle getObstacle(int randInt) {
        if (randInt == 0) {
            return new ObstacleRectangle();
        }
        else if (randInt == 1) {
            return new ObstacleEllipse();
        }
        else if (randInt == 2) {
            return new ObstacleRectangle();
        }
        else return null;
    }

}
