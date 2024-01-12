package com.example.pr_fx;

import com.example.pr_fx.Scene2Controller; // Adjust the import based on your actual package structure
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scene2ControllerTest {

    private Scene2Controller scene2Controller;

    @BeforeEach
    public void setUp() {
        // Initialize or mock the necessary objects for testing
        scene2Controller = new Scene2Controller();
    }

    @Test
    public void testStickHeightIncreasesOnKeyPress() {
        // Assume initial stick height is 0
        Rectangle stick = new Rectangle(0, 0, 5, 0);
        scene2Controller.loadStick();

        // Trigger the key press event (mocking)
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.E, false, false, false, false);

        // Pressing 'E' key should increase the stick height
        scene2Controller.handleKeyPress(keyEvent);

        // Assert that the stick height has increased
        assertEquals(1, stick.getHeight(), "Stick height should increase when 'E' key is pressed.");
    }

        @Test
        public void testPlayerMovesForwardWithStickIncreaseAndRotate() {
            // Assume initial stick height is 0
            Rectangle stick = new Rectangle(0, 0, 5, 0);
            scene2Controller.loadStick();

            // Assume initial player position
            ImageView player = new ImageView();
            player.setLayoutX(0);
            scene2Controller.loadPlayer();

            // Trigger the key press event to increase stick height (mocking)
            KeyEvent keyPressEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.E, false, false, false, false);
            scene2Controller.handleKeyPress(keyPressEvent);

            // Assert that the stick height has increased
            assertEquals(1, stick.getHeight(), "Stick height should increase when 'E' key is pressed.");

            // Trigger the key release event to rotate and move player (mocking)
            KeyEvent keyReleaseEvent = new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.E, false, false, false, false);
            scene2Controller.handleKeyRelease(keyReleaseEvent);

            // Assert that the player moves forward and the stick rotates
            assertEquals(1, player.getLayoutX(), "Player should move forward when 'E' key is released.");
            assertEquals(90, stick.getRotate(), "Stick should rotate when 'E' key is released.");
        }
    }

