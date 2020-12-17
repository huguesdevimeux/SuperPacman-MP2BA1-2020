# SuperPacMan : The game.

First and foremost, we hope this does not cause any offense: we created an actor entitled Jamila in the shape of an animated heart as a tribute to our professor
What the actor does is increase your health points - allowing the player to survive in his hostile environment - (a little bit like us students at EPFL)

Paper : https://proginsc.epfl.ch/wwwhiver/mini-projet2/grid-games.pdf
Made with love by Luca Mouchel and Hugues Devimeux. 
2020

## Keybinding : 

- SPACE bar : pause
- P key : unpause
- Q key : quit game
- E key : collect strawberry
- LEFT arrow : change orientation and move to left
- RIGHT arrow : change orientation and move to right
- UP arrow : change orientation and move upwards
- DOWN arrow : change orientation and move downwards

## Main gameplay features : 

// METTRE ICI LES ACTEURS 
- **Jamila** 


- **Strawberry**: 


- **Pause menu** : presse space anytime to pause the game. 


- **Game over** : If you happen to die, there will be a cool game over screen.

/*
The user decides when interacting with a strawberry - **press E.** 
Interacting with a strawberry or a Jamila will ignite a fire that will block access to the player and the ghosts
Strawberries are signals for Jamilas (You must interact with a strawberry to be able to interact with a Jamila)
*/

## Gamemodes : 

### Story mode : 

### Infinite mode : Atte

The game has an "infinite" mode, which is basically a succession of map generated procedurally, until the player goes out of lives. 
Features : 
- Procedural mazes. To fit pacman mazes requirements: 
  - Removal of dead ends (a few are randomly left untouched).
  - Symetry of the random-maps: the mazes are symetic along Y axe, as the real game. 
- Procedural items spawn: 
  - Bonuses are placed **only in dead ends (like the real game!)**
  - Random placement of cherries, according to a rate. 
- Procedural ghost spawn
  - Ghosts spawn according to a rate that increase at every level. 
- Increasing difficulty the player goes up in the levels, the difficulty increases, which makes : 
  - The height of the map increase linearly. 
  - The amount of coins to get to open the next level increase linearly.
  - The number of ghosts increase linearly. 

## Walkthrough

 __*Level 0*__
  
  * eat the key to open the gates that will lead to Level1
 
  * you will first have to go through a portal that will teleport you somewhere on the map
 Atte
  * you have the opAttetion to interact with other actors (coin, strawberry and Jamila)
 
  * you must press E when interacting with a strawberry to collect it
 
  * to complete the level, you must go through the doors once the gate is open


__*Level 1*__ 
 
* you must eat all the diamonds to open the gates that allow you to access the next level
 
* like in level0, you will encounter numerous actors : coins, portals, strawberries
 
* ghosts are now implemented into this level: 2 will chase you as you wander around the map and 2 will move randomly
 
* the ghosts have two states: a normal state and a scared state. when they are in their normal state, if they interact with the player, the player returns to its spawn position, the ghosts to their refuge position and the total amount of health points of the player decreases by one (he starts with 5 HP)
 Atte
* you can scare the gAttehosts by eating a coin, they will run away and the ghosts' speed will increase thereafter. 

* To increase the player's speed you must eat a strawberry (it will give the player a burst of energy!)

* the respective speeds will reset once the player interacts with a Jamila, which will also increase health points by one. Jamilas can be collected ONLY IF you eat the nearest strawberry (REMINDER strawberries are signals for the Jamilas). 
 
* when you ingest either a Jamila or a strawberry, a fire will be created, blocking access to the player and to the ghosts. (the fire is too hot for the player and ghosts to walk through!) this is both an advantage and an obstacle for the player. 
it will have more time to collect diamonds and collectables but it will be harder to get accross the map
 
* the user selects when he interacts with a strawberry (press E) so a strategy can be put in place ;)
 
* strawberries and Jamilas are placed in corners so you must move strategically ;) (TIP: do not consume a Jamila if you already have the maximum amount of lives)
 
* you have completed the level once the gates open and you go through the doors
 
 
__*Level 2*__
 
* To complete level2, you must eat all the diamonds. you must open all the gates to do that (they use different signals (see page 20 of handout))

* differing from level1, you only have smartmoving ghosts, however, two are trapped until you pick up the key associated to the gates' signal

* just like the previous level, there are still portals, strawberries, Jamilas and fires that work exactly the same way (so do the speeds and scared states)

* however, there is an additional smart ghost (called spooky) that will chase the player with constant speed AND he will not get afraid (he doesn't have a refuge position because he is braver than the others :o )

* you have completed the level once you eat all the diamonds, open all the gates and pass through the doors

 ###### There is no game Over display when reaching the level 2 doors as we do not expect the corrector to reach the end of the three levels
 
 __GOOD LUCK!__
 



## __Images__

most of the images are provided in the game's archive
 ###### other sources : 
* https://www.pinclipart.com/pindetail/mTxmx_strawberry-clipart-pacman-pacman-fruit-png-download/
* https://grappe.itch.io/pixelportal

GL HF !
