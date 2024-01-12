
package com.example.pr_fx;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

//import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class Scene2Controller extends AnchorPane implements Initializable {





//    Media media = new Media(getClass().getResource("C:/JAVA DATA/IdeaProjects/2022373_2022462/src/main/resources/bg_night.mp3").toExternalForm());
//    MediaPlayer mediaPlayer = new MediaPlayer(media);
    private Button exitButton;
    private boolean isGameRunning = false;



    @FXML
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private AnchorPane pane;
    private int previousWidth=0;
    private final ArrayList<Rectangle> rectangles=new ArrayList<>();
    private AnimationTimer gameLoop;
    private ImageView player;
    private  Rectangle stick;
    double time=0;
    Rotate rotate ;
    ArrayList<Rectangle> rec=new ArrayList<>();

    private boolean isSpacePressed =false;

    public void setPane(AnchorPane pane, int width) {
        this.pane = pane;
        this.previousWidth=width;




    }


    private Rectangle currentPillar;
    private Rectangle nextPillar;
    private double gapBetweenPillars = 100;




    public void exitGame(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Stick Hero");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText("Do you want to exit? ");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) pane.getScene().getWindow();

            stage.close();
        }
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource ("Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene (scene);
        stage.show();
        //createObstacles();
    }




    private void movePlayer() {
        if (player != null) {
            player.setLayoutX(player.getLayoutX()  );
        }
    }

    void loadPlayer() {
        try {
            // Load player image (adjust the path accordingly)
            Image playerImage = new Image(new FileInputStream("C:\\JAVA DATA\\IdeaProjects\\2022373_2022462\\src\\main\\resources\\Player.png"));

            // Create an ImageView for the player
            player = new ImageView(playerImage);

            // Set initial position
            player.setLayoutX(-50);
            player.setLayoutY(112);
            player.setFitWidth(200); // Set your desired width
            player.setFitHeight(250); // Set your desired height

            // Add player to the pane
            pane.getChildren().add(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void update() {
        if (isSpacePressed) {
            extendStick();
        }
        movePlayer();

    }

    private void extendStick() {
        stick.setVisible(true);
        stick.setHeight(stick.getHeight() + 1);
        double currentLayoutY = player.getLayoutY() + player.getFitHeight() / 2;

        stick.setLayoutY(currentLayoutY - stick.getHeight()+63);
//        stick.setLayoutX(player.getLayoutX() + player.getFitWidth());
//        stick.setLayoutY(player.getLayoutY() + player.getFitHeight() / 2);
        stick.setLayoutX(player.getLayoutX() + player.getFitWidth() -85);


    }




    private void rotateAndLand() {
        if (stick.isVisible()) {
            // Rotate the stick
            stick.setRotate(stick.getRotate() + 90);


            // Check for landing on the platform
            for (Rectangle platform : rectangles) {
                if (stick.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    // Stick landed on the platform
                    stick.setVisible(false);
                    stick.setHeight(0);
                    stick.setRotate(0);

                    break;
                }
            }
        }

//        if (stick.isVisible()) {
//            // Rotate the stick using a RotateTransition
//            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), stick);
//            rotateTransition.setByAngle(90);
//
//
//            rotateTransition.setOnFinished(event -> {
//                // Check for landing on the platform
//                for (Rectangle platform : rectangles) {
//                    if (stick.getBoundsInParent().intersects(platform.getBoundsInParent())) {
//                        // Stick landed on the platform
//                        stick.setVisible(false);
//                        stick.setHeight(0);
//                        stick.setRotate(0);
//                        break;
//                    }
//                }
//            });
//            rotateTransition.play();
//        }


    }




    @FXML
    void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.E) {
            isSpacePressed = true;
        }
    }

    @FXML
    void handleKeyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.E ) {
            isSpacePressed = false;
            dropStick();
        }

    }

    private void dropStick() {
        double startX = stick.getX();
        double startY = stick.getY() + stick.getHeight()/2;
        double endX = startX + stick.getHeight();
        double endY = stick.getY() + stick.getWidth();
        double midPointY = stick.getHeight();


        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), stick);
        rotateTransition.setByAngle(90);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), stick);
        translateTransition.setByY(startY);
        translateTransition.setByX((stick.getHeight() / 2));


        ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, translateTransition);
        TranslateTransition p1 = new TranslateTransition();

        TranslateTransition translateTransitionPlayer = new TranslateTransition(Duration.millis(500), player);
        double stickheight = stick.getHeight();
        double distBtwPlatform = rectangles.get(1).getX() - rectangles.get(0).getWidth();

        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {

           if(stick.getHeight()>rectangles.get(1).getWidth()+distBtwPlatform){
                player.setLayoutX(player.getLayoutX()+stick.getHeight());
//                player.setLayoutY(500);
//               translateTransitionPlayer.setByX(player.getLayoutX()+stick.getHeight());
               translateTransitionPlayer.setByY(500);
            }
           else if(stick.getHeight()>=distBtwPlatform){
               player.setLayoutX(player.getLayoutX()+rectangles.get(1).getX());
              // translateTransitionPlayer.setByX(player.getLayoutX()+rectangles.get(1).getX()+rectangles.get(1).getWidth());
           }
            else{
                player.setLayoutX(player.getLayoutX()+stick.getHeight());
//                player.setLayoutY(500);
               //translateTransitionPlayer.setByX(player.getLayoutX()+stick.getHeight());
               translateTransitionPlayer.setByY(500);
                 }

         //   translateTransitionPlayer.play();
            //stick.setHeight(0);

            moveObstacles();
            player.setX(-rectangles.get(0).getWidth()-stickheight);
            createObstacles();

            //stick.setX(rectangles.get(0).getWidth());
            RotateTransition rotateTransitionagain = new RotateTransition(Duration.millis(500), stick);
            rotateTransitionagain.setByAngle(270);
//

            ParallelTransition parallelTransitionagain = new ParallelTransition(rotateTransitionagain);
            TranslateTransition p1again = new TranslateTransition();

            parallelTransitionagain.play();

            TranslateTransition translateTransitionagain = new TranslateTransition(Duration.millis(500), stick);
//            translateTransitionagain.setByY(startY);
            System.out.println(stick.getX());
            translateTransitionagain.setByX(-stick.getHeight()/2);
            System.out.println(stick.getX());

            ParallelTransition parallelTransitionagain2 = new ParallelTransition(translateTransitionagain);
            parallelTransitionagain2.play();
            //stick.setHeight(0);
        });

    }
    private void createObstacles() {
        Random rand = new Random();
        double height = 200;
        double width = rand.nextInt(60) + 40;
        double availableWidth = pane.getWidth() - width - rectangles.get(0).getWidth();
        double space = rand.nextInt((int) Math.abs(availableWidth));
        double xPos = (int) (rectangles.get(0).getWidth() + Math.abs(space));
        Rectangle rectangle = new Rectangle(xPos, 300, width, height);
        rectangle.setFill(Color.BLACK);
        rectangles.add(rectangle);
        pane.getChildren().add(rectangle);
//        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), rectangle);//-------------------------------------
//        transition.setToX(rectangle.getX()); // Set the destination X coordinate--------------------------------------------
//        transition.play();//---------------------------------------------------------------------------------------------


    }
    private void moveObstacles() {
        ArrayList<Rectangle> outOfScreen = new ArrayList<>();
        double size = rectangles.get(0).getX();

         rectangles.get(0).setX(rectangles.get(0).getWidth());

         rectangles.get(1).setX(size);

        Rectangle removedObstacle = rectangles.get(0);
        rectangles.remove(0);
        pane.getChildren().remove(removedObstacle);
//        createObstacles();//---------------------------------------------------------------------------------------------------------------->>>
//        System.out.println(obstacles);
//        System.out.println(rectangles);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //mediaPlayer.play();
        createpiller();
        loadPlayer();
        loadStick();
        startGameLoop();
        gameLoop=new AnimationTimer() {
            @Override
            public void handle(long l) {
                movePlayer();
                update();
            }
        };


        isGameRunning = true;
        gameLoop.start();
    }

    void loadStick() {
        stick = new Rectangle(0, 0, 5, 0);
        stick.getStyleClass().add("stick");
        pane.getChildren().add(stick);
        stick.setVisible(false);


    }

    private void startGameLoop() {
        if (gameLoop == null) {
            gameLoop = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    update();
                }
            };
        }
        isGameRunning = true;
        gameLoop.start();
    }

    private void stopGameLoop() {
        isGameRunning = false;
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }
    public void switchToScene3(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource ("Scene3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene (scene);
        stage.show();


    }
    public void createpiller(){
        Rectangle rectangle1 = new Rectangle(0, 300, 70, 200);
        rectangle1.setFill(Color.BLACK);
        rectangles.add(rectangle1);
        pane.getChildren().add(rectangle1);
        Rectangle rectangle2 = new Rectangle(400, 300, 70, 200);
        rectangle2.setFill(Color.BLACK);
        rectangles.add(rectangle2);
//        previouswidth=rectangle2.getWidth();
        pane.getChildren().add(rectangle2);





    }
}