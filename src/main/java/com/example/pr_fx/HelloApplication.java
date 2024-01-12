package com.example.pr_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Platform{
    private final double width;
    private final double height;
    private final double middle;
    public Platform(double width,double height){
        this.width=width;
        this.height=height;
        this.middle=width/2;

    }

    public double getHeight() {
        return height;
    }

    public double getMiddle() {
        return middle;
    }

    public double getWidth() {
        return width;
    }
}
interface StickAction{
    double incrementLength();
    void makeStick();
    void rotateStick();
}
class Stick implements StickAction{
    private double length=0;
    @Override
    public double incrementLength(){
        // increment length;
        return length;
    }
    @Override
    public void makeStick(){

    }
    @Override
    public void rotateStick(){

    }
    public void resetStick(){
        this.length=0;
    }
}

interface Movements{
    void moveUp();
    void moveDown();
    void tap();
}
class ScoreBoard{
    private long score_board;
    public long getScore_board() {
        return score_board;
    }

    public void setScore_board(long score_board) {
        this.score_board = score_board;
    }
}
class Cherry{
    private boolean hit;

    public void checkHit(){

    }
    public void setHit(){

    }
    public boolean returnHit(){
        return hit;
    }
}
class Player implements Movements{

    private double positionY = 0; // Initialize the Y position
    private boolean isJumping = false; // Flag to track jumping state

    private Stick mystick;

    private Progress p1;
    private ScoreBoard sb;
    private Cherry c;
    public Player(Progress p1){
        mystick=new Stick();
        sb=new ScoreBoard();
        c=new Cherry();
        this.p1=p1;


    }
    @Override
    public void moveUp() {
        //if angle 90 do nothing else rotate 180 degree anticlockwise
    }

    @Override
    public void moveDown() {
        //if angle 270 do nothing else rotate 180 degree clockwise;
        c.checkHit();
        c.setHit();
        boolean b=c.returnHit();
        p1.incrementCherries();

    }

    @Override
    public void tap() {
        //start incrementing the stick until the user stop tapping in vertical direction------>mystick.incrementLength()
        //---------------->mystick.makestick()
        //rotate the stick 90 degree clockwise-------------->mystick.rotatestick()
        //---------------->mystick.resetstick()
        //check the length of stick be equal to distance between platforms + next platform length
        // if true check whether length equals distance between platforms + next platform length/2 ,if yes then increase the score by 2 and repeat else only repeat and increase score by 1
        moveForward();
        //else
        moveForward();//stops return back
        revive();
        score();

    }
    private static void moveForward(){
        // cover distance between platforms +distance of stick + distance of rest platform if left;

    }
    private static void score(){
        //show current score with the highest score
        // update the highest score means the progress
    }
    private static void revive(){
        // check total number of cherries and then decrement cherries
    }
}
class Progress{
    private long highest_score;
    private long cherries;

    public long getHighest_score() {
        return highest_score;
    }

    public void setHighest_score(long highest_score) {
        this.highest_score = highest_score;
    }

    public long getCherries() {
        return cherries;
    }

    public void setCherries(long cherries) {
        this.cherries = cherries;
    }
    public void incrementCherries(){

    }
}
interface MusicControl{
    void muteGame();


    void unmuteGame();


}
interface Stats{
    void viewStats();
    void exitstats();
}
class StickHeroGame  implements Stats,MusicControl{
    private List<Platform> platforms;
    private Player player1;
    private Progress p1;
    public StickHeroGame(Progress p1){
        this.p1=p1;
        platforms=new ArrayList<>();
        //atleast add 2 platforms of random width
        player1=new Player(p1);
        loadGame();
    }
    private static void loadGame(){
        //game starts with home screen
    }

    public void viewStats(){
        p1.getHighest_score();
        p1.getCherries();

    }

    public void exitstats(){

    }
    public void exitGame(Stage stage) {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Stick Hero");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText ("Do you want to save before exiting? ");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
    public void resumeGame(){

    }
    public void startGame(){

    }
    public void homeScreen(){

    }
    public void muteGame(){

    }


    public void unmuteGame(){

    }

}





public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            Scene scene =new Scene(root);
            stage.setTitle("Stick Hero");

            stage.setOnCloseRequest(event -> {
                event.consume();
                exitGame(stage);
            });

            stage.setScene (scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    private void exitGame(Stage stage) {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Stick Hero");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText ("Do you want to exit? ");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }


    public static void main(String[] args) {


        launch();
    }
}