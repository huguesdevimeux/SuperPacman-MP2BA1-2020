 >For a better reading experience of this document
    - Extensions are flagged with "[extension]" or "[extension area]"
    - Classes, abstract classes, interfaces and enumerations are flagged with
    "(class)", "(abstract class)", "(interface)", and "(enum)", respectively. 
    
# Table of content : 
- [Modifications of the provided code](#modifications-of-the-provided-code)
- [Added classes and interfaces](#added-classes-and-interfaces)
  * [Actors (superpacman/actor)](#actors-superpacmanactor)
    + [1. ghosts](#1-ghosts)
    + [2. collectables](#2-collectables)
    + [3. other](#3-other)
  * [Areas (superpacman/area)](#areas-superpacmanarea)
  * [Behaviors (superpacman/behavior)](#behaviors-superpacmanbehavior)
  * [Handlers (superpacman/handler)](#handlers-superpacmanhandler)
  * [MazeUtils (superpacman/mazeutils)](#mazeutils-superpacmanmazeutils)
  * [Miscleanous graphics (superpacman/SuperPacmanGraphics)](#miscleanous-graphics-superpacmansuperpacmangraphics)
  * [Superpacman game (game/superpacman)](#superpacman-game-gamesuperpacman)
- [Extensions](#extensions)
  * [Actors](#actors)
  * [Random Levels](#random-levels)
  * [GUIs](#guis)

## Modifications of the provided code 


- Added font arcade to the ressources folder. 
- Added sprites
- Added new constructor for areaBehavior, to be able to create a behavior without image (for procedural maze generation)
- Added some setters for areagame.actor.Text. 

_Code added by us is with [CREATED BY STUDENTS] doc._ 

## Added classes and interfaces 

### Actors (superpacman/actor)

#### 1. ghosts
- (abstract class) Ghost - general class from which different ghosts will inherit. usees generic methods to deal with ghosts behaviors
- (abstract class) SmartMovingGhost - same as Ghost class but for smart moving ghosts
 * class Blinky 
 * class Inky 
 * class Pinky
 * class Spooky [extension]
 - these classes are very similar and all these ghosts are actors. Only Blinky extends from Ghost, the others from SmartMovingGhost
 it seem reasonable to separate ghost from smart moving ghost (which extends ghost) to differentiate the different types of ghosts
 
#### 2. collectables

* class CollectableAreaEntity - allows to characterise collectables - a general class from which all colllectables can inherit
* class Bonus
* class Cherry
* class Diamond
*class Jamila [extension] - increases HP and reduces speed
*class Strawberry [extension] - increases player Speed and allows player to interact with Jamila
*class Enclosure [extension] - creation of fire once you eat a Jamila or a Strawberry 
*class Key - signal for the gates

#### 3. other

*class Gate 
*class Portal[extension] - allows to teleport somewhere on the map
*class Wall
*class SuperPacmanPlayer
*class SuperPacmanPlayerHandler - manages interactions between the player and the collectables


### Areas (superpacman/area)

- class Level0 
- class Level1
- class Level2

- class NonRandomArea extended by Level0,1,2
- class RandomArea


we differentiated these two classes as we have created an extension - procedural levels that generate randomly
hence the random and non random areas

- class SuperPacmanArea

### Behaviors (superpacman/behavior)

- class NonRandomBehavior
- class RandomBehavior
likewise, we differentiate random and non random behaviors

- abstract class SuperPacmanBehavior
- enum SuperPacmanCellType - allows for the automatic creation of players based on the celltypes on the map
- class SuperPacmanCell

### Handlers (superpacman/handler)

- interface SuperPacmanInteractionVisitor
implemented by SuperPacon] - manages the pause status of the game
- class GameOverGUI[extension] - manages end of game statusmanPlayerHandler

### MazeUtils (superpacman/mazeutils)
- Class `RandomPacmanMazeStructure` : Self-explanatory. Generate the maze structure, that will be used by RandomPacmanMap. 
- Class `RandomPacmanMap` : Generate the pacman map, by generating every specifities, such as bonuses placement, doors placement, ghosts spawn, etc. 

### Miscleanous graphics (superpacman/SuperPacmanGraphics)

- class SuperPacmanPlayerStatusGUI
- class PauseGUI [extension] - manages the pause status of the game
- class GameOverGUI[extension] - manages end of game status

seemed reasonable to create these classes as GUI as they simply popup texts when certain conditions are met
(pause or game over)

### Superpacman game (game/superpacman) 
- class SuperPacman - general class that will initialise player in the area, be it a random area or not


## Extensions

### Actors 
*Jamila
*Strawberry
*Portal
*Enclosure
*Spooky

### Random Levels
generated a possibly infinite amount of levels that will be accessible through the menu

### GUIs
*ability to pause the game (SPACE bar and unpause it: P)
*GameOver text when the player dies
*Menu, to select to choose from the "campaign" or an infinite amount of levels
