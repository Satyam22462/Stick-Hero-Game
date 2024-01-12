# Stick-Hero-Game

## GROUP=46

### SATYAM(2022462)
### PRATHAM MITTAL(2022373)




Game Scenes:

The game seems to have at least three scenes represented by different FXML files (Scene1.fxml, Scene2.fxml, and Scene3.fxml).
Users can switch between scenes using buttons, likely for transitioning between different parts of the game.
Player Controls:

The player character is controlled using the 'E' key. Pressing and releasing 'E' is used to control the stick's length and make the player character move.
Stick Mechanic:

The game involves extending a stick from the player character to cross platforms.
The stick is a Rectangle element that becomes visible and extends in height when the 'E' key is pressed.
The stick's length is increased gradually as long as the 'E' key is held down.
Platform Generation:

Platforms are represented by Rectangle elements and seem to be dynamically generated.
Platforms appear to move horizontally, creating a challenging environment for the player.
Game Loop:

The game logic is executed in a loop using JavaFX's AnimationTimer. The handle method is called repeatedly to update the game state.
Transition Animations:

The code uses JavaFX transitions, such as TranslateTransition and RotateTransition, to animate the stick's movement and rotation.
Obstacle Movement:

There's logic to move obstacles (platforms) horizontally, creating a scrolling effect as the game progresses.
Scene Switching:

The game allows the player to switch between scenes, possibly representing different stages or levels.
Exit Confirmation:

There's a confirmation dialog when the player attempts to exit the game.
Audio (Commented Out):

There's commented-out code related to playing background music (Media and MediaPlayer), which suggests the potential for audio features.
