0a. Contents
    1: Modifications to the provided code
    2. Added classes and interfaces (includes extension descriptions)
    3. Deviations from the project description
    4. List of all extensions
    
0b. For a better reading experience of this document
    - Extensions are flagged with "[extension]" or "[extension area]"
    - Classes, abstract classes, interfaces and enumerations are flagged with
    "(class)", "(abstract class)", "(interface)", and "(enum)", respectively. 

1. Modifications of the provided code 

        removed multipe images, fonts and others from resources folder
        
2. Added classes and interfaces 

2a. In ch.epfl.cs107.play.game.superpacman.actor : ACTORS

GHOSTS
- (abstract class) Ghost - general class from which different ghosts will inherit. usees generic methods to deal with ghosts behaviors
- (abstract class) SmartMovingGhost - same as Ghost class but for smart moving ghosts
 * class Blinky 
 * class Inky 
 * class Pinky
 * class Spooky [extension]
 - these classes are very similar and all these ghosts are actors. Only Blinky extends from Ghost, the others from SmartMovingGhost
 it seem reasonable to separate ghost from smart moving ghost (which extends ghost) to differentiate the different types of ghosts
 
COLLECTABLES

* class CollectableAreaEntity - allows to characterise collectables - a general class from which all colllectables can inherit
* class Bonus
* class Cherry
* class Diamond
*class Jamila [extension] - increases HP and reduces speed
*class Strawberry [extension] - increases player Speed and allows player to interact with Jamila
*class Enclosure [extension] - creation of fire once you eat a Jamila or a Strawberry 
*class Key - signal for the gates

OTHER

*class Gate 
*class Portal[extension] - allows to teleport somewhere on the map
*class Wall
*class SuperPacmanPlayer
*class SuperPacmanPlayerHandler - manages interactions between the player and the collectables


2b. In ch.epfl.cs107.play.game.superpacman.area

*class Level0 
*class Level1
*class Level2

*class NonRandomArea extended by Level0,1,2
*class RandomArea
we differentiated these two classes as we have created an extension - procedural levels that generate randomly
hence the random and non random areas

*class SuperPacmanArea

2c. In ch.epfl.cs107.play.game.superpacman.behavior

*class NonRandomBehavior
*class RandomBehavior
likewise, we differentiate random and non random behaviors

*abstract class SuperPacmanBehavior
*enum SuperPacmanCellType - allows for the automatic creation of players based on the celltypes on the map
*class SuperPacmanCell

2d. In ch.epfl.cs107.play.game.superpacman.handler

*interface SuperPacmanInteractionVisitor
implemented by SuperPacmanPlayerHandler

2e. In ch.epfl.cs107.play.game.superpacman.MazeUtils



2f. In ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics

*class SuperPacmanPlayerStatusGUI
+class PauseGUI [extension] - manages the pause status of the game
*class GameOverGUI[extension] - manages end of game status
seemed reasonable to create these classes as GUI as they simply popup texts when certain conditions are met
(pause or game over)

2h. In ch.epfl.cs107.play.game.superpacman 
*class SuperPacman - general class that will initialise player in the area, be it a random area or not


3. EXTENSIONS

3a. Actors 
*Jamila
*Strawberry
*Portal
*Enclosure
*Spooky

3b. Random Levels
generated a possibly infinite amount of levels that will be accessible through the menu

3c. GUIs
*ability to pause the game (SPACE bar and unpause it: P)
*GameOver text when the player dies
*Menu, to select to choose from the "campaign" or an infinite amount of levels
