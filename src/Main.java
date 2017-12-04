/**
 * @author Daniel Garcia
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class Main extends Application {

    private static final double WIDTH = 600;
    private static final double HEIGHT = 720;

    Pane pane = new Pane();
    Scene scene = new Scene(pane, WIDTH, HEIGHT);

    int frameCount = 0;
    int highscore = 0;

    private class Player extends Rectangle  {
        double xVel = 5;
        double xPos = WIDTH/2 -15;
        double yPos = HEIGHT - 30;
        Player() {
            setWidth(30);
            setHeight(30);
            setX(xPos);
            setY(yPos);
            pane.getChildren().add(this);
        }
    }

    ArrayList<Obstacle> obstacles = new ArrayList<>();

    private class Obstacle extends Rectangle  {
        double yVel = 10;
        double xPos = (int) (Math.random() * (WIDTH - 50));
        double yPos = -100;
        Obstacle() {
            setFill(Color.RED);
            setWidth(30);
            setHeight(100);
            setX(xPos);
            setY(yPos);
            pane.getChildren().add(this);
        }
    }
    Text hs = new Text("Highscore: " + highscore);

    Player player = new Player();

    Timeline timeline = new Timeline();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {;
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        hs.setFont(new Font("Comic Sans MS", 16));
        hs.setX(20);
        hs.setY(20);
        pane.getChildren().add(hs);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    KeyFrame frame = new KeyFrame(Duration.seconds((double) 1/60), event -> {

        scene.setOnMouseMoved(event1 -> {
            player.xPos = event1.getSceneX();
            player.setX(player.xPos-15);
            player.yPos = event1.getSceneY();
            player.setY(player.yPos-15);
        });


        if (frameCount == 7) {
            obstacles.add(new Obstacle());
            frameCount = 0;
        }
        frameCount++;
        highscore++;
        hs.setText("Highscore: " + highscore);
        for (Obstacle obstacle : obstacles) {
            obstacle.yPos += obstacle.yVel;
            obstacle.setY(obstacle.yPos);
            if (player.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                player.setStroke(Color.BLUE);
                obstacle.setStroke(Color.BLACK);
                timeline.stop();
                scene.setOnMouseMoved(null);
            }
        }


    });

}
