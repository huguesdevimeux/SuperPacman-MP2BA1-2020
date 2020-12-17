
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
- Added new constructor for `areaBehavior`, to be able to create a behavior without image (for procedural maze generation)
- Added some setters for `areagame.actor.Text`. 

_Code added by us is with [CREATED BY STUDENTS] doc._ 

## Added classes and interfaces 

### Actors (superpacman/actor)

#### 1. ghosts
- (abstract class) `Ghost` - general class from which different ghosts will inherit. usees generic methods to deal with ghosts behaviors
- (abstract class) `SmartMovingGhost` - same as Ghost class but for smart moving ghosts
 * class `Blinky` 
 * class `Inky` 
 * class `Pinky`
 * class `Spooky` [extension]
 
 These classes are very similar and all these ghosts are actors. Only `Blinky` extends from `Ghost`, the others from `SmartMovingGhost`
 it seem reasonable to separate ghost from smart moving ghost (which extends ghost) to differentiate the different types of ghosts
 
#### 2. collectables

* class `CollectableAreaEntity` - allows to characterise collectables - a general class from which all colllectables can inherit
* class `Bonus`
* class `Cherry`
* class `Diamond`
* class `Jamila` [extension] - increases HP and reduces speed
* class `Strawberry` [extension] - increases player Speed and allows player to interact with Jamila
* class `Enclosure` [extension] - creation of fire once you eat a Jamila or a Strawberry 
* class `Key` - signal for the gates

#### 3. other

* class `Gate` 
* class `Portal`[extension] - allows to teleport somewhere on the map
* class `Wall`
* class `SuperPacmanPlayer`
* class `SuperPacmanPlayerHandler` - manages interactions between the player and the collectables


### Areas (superpacman/area)

- class `Level0` 
- class `Level1`
- class `Level2`

- class `NonRandomArea` extended by Level0,1,2
- class `RandomArea`


We differentiated these two classes as we have created an extension - procedural levels that generate randomly
hence the random and non random areas

- class `SuperPacmanArea` Main class for all the areas of the game

### Behaviors (superpacman/behavior)

- class `NonRandomBehavior`
- class `RandomBehavior`
likewise, we differentiate random and non random behaviors

- abstract class `SuperPacmanBehavior`
- enum `SuperPacmanCellType` - allows for the automatic creation of players based on the `celltypes` on the map
- class `SuperPacmanCell`

### Handlers (superpacman/handler)

- interface `SuperPacmanInteractionVisitor`
implemented by `SuperPacman`] - manages the pause status of the game
- class `GameOverGUI`[extension] - manages end of game `statusmanPlayerHandler`

### MazeUtils (superpacman/mazeutils)
- Class `RandomPacmanMazeStructure` : Self-explanatory. Generate the maze structure, that will be used by `RandomPacmanMap`. 
- Class `RandomPacmanMap` : Generate the pacman map, by generating every specifities, such as bonuses placement, doors placement, ghosts spawn, etc. 

### Miscleanous graphics (superpacman/SuperPacmanGraphics)

- class `SuperPacmanPlayerStatusGUI`
- class `PauseGUI` [extension] - manages the pause status of the game
- class `GameOverGUI`[extension] - manages end of game status

seemed reasonable to create these classes as GUI as they simply popup texts when certain conditions are met
(pause or game over)

### Superpacman game (game/superpacman) 
- class `SuperPacman` - general class that will initialise player in the area, be it a random area or not


## Extensions

### Random Levels
Generated a possibly infinite amount of levels that will be accessible through the menu.

The implementation is quite wide, so here is a resume of the differnent thigs implemented. 
Features : 
- Procedural mazes. To fit pacman maze requirements: 
  - Removal of dead ends (a few are randomly left untouched).
  - Symetry of the random-maps: the mazes are symetric along Y axis, like the real game. 
- Procedural items spawn:
  - Bonuses are placed **only in dead ends (like the real game!)**
  - Random placement of cherries, according to a rate. 
- Procedural ghost spawn
  - Ghosts spawn according to a rate that increase at every level. 
- Increasing difficulty as the player goes up in the levels, which makes : 
  - The height of the map increase linearly. 
  - The amount of coins to collect to open the gates to the next level increases linearly.
  - The number of ghosts increases linearly.
  - 
### Actors 
* `Jamila` - increases HP by one, decreases player's and ghosts' speed (player needs to eat the nearest strawberry to be collectable)
* `Strawberry` - increases the player's speed and allows access to a Jamila
* `Portal` - teleports the player somewhere on the map
* `Enclosure` - fire that ignites whenever the player decides to eat a strawberry or when the player picks up a Jamila - TIP: be __strategic__, the player can't move over fire
* `Spooky` - smart ghost that appears in __Level 2__, will move at constant speed and has NO refuge position


### GUIs
* Ability to pause the game (SPACE bar and unpause it: P)
* GameOver text when the player dies
* Opening Menu, to select to choose from the "campaign" or an infinite amount of levels. + Some cools effect on the title :D
