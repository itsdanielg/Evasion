/**
 * @author Daniel Garcia
 */

package evasion.main;

import evasion.elements.ObstacleFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import evasion.elements.Obstacle;
import evasion.elements.Player;
import evasion.elements.ObstacleQueue;

public class Main extends Application {

    private static final double WIDTH = 600;
    private static final double HEIGHT = 720;
    private static final double FRAMES = 60;
    private static final double SPAWN_FRAME = 15;
    private static final boolean RANDOM_OBSTACLE = false;

    Stage mainStage;
    Scene mainScene;
    StackPane overlayPane;
    Pane mainPane;

    private Button startGameButton;
    private Button resetGameButton;

    private int frameCount;
    private int highscoreCounter;

    ObstacleQueue<Obstacle> obstacles = new ObstacleQueue<>();

    Text highscoreText;

    Player player = new Player();

    Timeline timeline = new Timeline();

    KeyFrame frame;

    @Override
    public void start(Stage tempStage) {
        mainStage = new Stage();
        mainStage.setTitle("Evasion");

        resetGameButton = new Button();

        VBox startPane = new VBox();
        startPane.setAlignment(Pos.CENTER);
        startGameButton = new Button("Start Game");
        startGameButton.setFont(new Font(60));
        startPane.getChildren().add(startGameButton);
        startPane.setPadding(new Insets(20));
        startGameButton.setOnAction(e -> {
            startGame();
        });
        mainStage.setScene(new Scene(startPane, WIDTH, HEIGHT));
        mainStage.showAndWait();
    }

    public void startGame() {
        frameStart();

        frameCount = 0;
        highscoreCounter = 0;

        overlayPane = new StackPane();
        mainPane = new Pane();

        mainScene = new Scene(overlayPane, WIDTH, HEIGHT);

        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        mainPane.getChildren().add(player);
        highscoreText = new Text("Highscore: " + highscoreCounter);
        highscoreText.setFont(new Font("Comic Sans MS", 16));

        overlayPane.getChildren().add(mainPane);
        overlayPane.getChildren().add(highscoreText);
        overlayPane.setCursor(Cursor.NONE);
        overlayPane.setAlignment(Pos.TOP_LEFT);
        mainStage.setScene(mainScene);
    }

    public void frameStart() {
        frame = new KeyFrame(Duration.seconds(1/FRAMES), frameEvent -> {
            mainScene.setOnMouseMoved(moveMouseEvent -> {
                player.setXPos(moveMouseEvent.getSceneX());
                player.setX(player.getXPos() - 15);
                player.setYPos(moveMouseEvent.getSceneY());
                player.setY(player.getYPos() - 15);
            });
            if (frameCount == SPAWN_FRAME) {
                Obstacle obstacle;
                if (RANDOM_OBSTACLE) {
                    obstacle = ObstacleFactory.getObstacle((int) (Math.random() * 3));
                }
                else {
                    obstacle = ObstacleFactory.getObstacle(0);
                }
                mainPane.getChildren().add((Shape) obstacle);
                obstacles.add(obstacle);
                frameCount = 0;
            }
            frameCount++;
            highscoreCounter++;
            highscoreText.setText("Highscore: " + highscoreCounter);
            for (Obstacle obstacle : obstacles) {
//                obstacle.setYPos(obstacle.getYPos() + obstacle.getYVel());
                obstacle.setY(obstacle.getY() + obstacle.getYVel());
                if (player.getBoundsInParent().intersects(((Shape)obstacle).getBoundsInParent())) {
                    player.setStroke(Color.BLUE);
                    ((Shape)obstacle).setStroke(Color.BLACK);
                    timeline.stop();
                    mainScene.setOnMouseMoved(null);
                    resetGameMenu();
                }

            }
            Obstacle lastObstacle = obstacles.peekFromQueue();
            if (lastObstacle != null) {
                if (lastObstacle.getYPos() == 720) {
                    mainPane.getChildren().remove(lastObstacle);
                    obstacles.pullFromQueue();
                }
            }
        });
    }

    public void resetGameMenu() {
        VBox resetPane = new VBox();
        resetPane.setAlignment(Pos.CENTER);
        resetPane.setStyle("-fx-background-color: rgba(237, 147, 147, 0.5);");
        overlayPane.getChildren().add(resetPane);
        overlayPane.setCursor(Cursor.DEFAULT);

        resetGameButton.setText("Reset Game");
        resetGameButton.setFont(new Font(60));
        resetPane.getChildren().add(resetGameButton);
        overlayPane.getChildren().remove(highscoreText);
        resetPane.getChildren().add(highscoreText);
        highscoreText.setText("Final Score: " + highscoreCounter);
        highscoreText.setFont(new Font(36));
        resetGameButton.setOnAction(e -> {
            resetPane.getChildren().removeAll(resetGameButton, highscoreText);
            overlayPane.getChildren().removeAll(resetPane);
            resetGame();
        });
    }

    public void resetGame() {
        obstacles.clear();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(player);
        overlayPane.getChildren().add(highscoreText);
        highscoreText.setFont(new Font("Comic Sans MS", 16));
        highscoreCounter = 0;
        frameCount = 0;
        overlayPane.setCursor(Cursor.NONE);
        timeline.play();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
