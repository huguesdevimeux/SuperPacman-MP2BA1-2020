# SuperPacMan : The game.

First and foremost, we hope this does not cause any offense: we created an actor entitled Jamila in the shape of an animated heart as a tribute to our professor
What the actor does is increase your health points - allowing the player to survive in his hostile environment - (a little bit like us students at EPFL)


## Table of content
- [Keybinding](#keybinding)
- [Main gameplay features](#main-gameplay-features)
- [Gamemodes](#gamemodes)
  * [Story mode](#story-mode)
  * [Infinite mode](#infinite-mode)
- [Walkthrough](#walkthrough)
  * [Level 0](#level-0)
  * [Level 1](#level-1)
  * [Level 2](#level-2)
- [Images](#images)

## Keybinding

- SPACE bar : pause
- P key : unpause
- Q key : quit game
- E key : collect strawberry
- LEFT arrow : change orientation and move to left
- RIGHT arrow : change orientation and move to right
- UP arrow : change orientation and move upwards
- DOWN arrow : change orientation and move downwards

## Main gameplay features

- **Pacman player** : its goal is to consume collectables, eat ghosts when they're afraid and get from one level to the next

- **Blinky** : moves randomly on the map and only appears in __Level 1__

- **Pinky and Inky** : smart moving ghosts. they will chase the player and attempt to make him fail. they appear in __Level 1__ and __Level 2__

- **Spooky** : appears only in __Level 2__. this ghost is smart and does not get afraid - it consequently doesn't have a refuge position. it will chase the player continuosly

- **Bonus** : when collected, bonuses scare ghosts and increase their speed

- **Portal** : allows player to teleport somewhere on the map

- **Gate** : will open when certain keys are collected

- **Key** : signals for gates. collecting them opens gates

- **Cherry** : increases score by 200

- **Opening menu** : a menu will popup before being able to play, allowing the user to pick a gamemode

- **Pause menu** : presse space anytime to pause the game. 

- **Game over** : If you happen to die, there will be a cool game over screen.

### Strategy

- **Strawberry**: strawberries are signals for Jamilas. They also increase the player's speed (provides a burst of energy!). Strawberries can be collected whenever the player decides to interact with one, by pressing E

- **Jamila** : increases HP by one. Decreases both the ghosts' and player's speeds. can only be accessed when you have eaten the nearest strawberry (you will see the signal is activated when there is an animation on the Jamila)

- **Enclosure** : shape of a fire that will "enclose" the player. When eating a strawberry or collecting a Jamila, a fire will ignite, blocking access to the player and the ghosts.(the fire is too hot for the player and ghosts to walk through!) this is both an advantage and an obstacle for the player. it will have more time to collect diamonds and collectables but it will be harder to get accross the map. __This__ is why you should be strategic about when to eat a strawberry

## Gamemodes

### Infinite mode

The game has an "infinite" mode, which is basically a succession of maps generated procedurally, until the player goes out of lives. 
To see details, of the implementation, see Conception. 

### Story mode

Play an amazing adventure through 3 levele pre-determinded, Level0, Level1, Level2!

## Walkthrough

##### Level 0
  
  * eat the key to open the gates that will lead to Level1
 
  * you will first have to go through a portal that will teleport you somewhere on the map
  
  * you have the option to interact with other actors (coin, strawberry and Jamila)
 
  * you must press E when interacting with a strawberry to collect it
 
  * to complete the level, you must go through the doors once the gate is open


##### Level 1
 
* you must eat all the diamonds to open the gates that allow you to access the next level
 
* like in level0, you will encounter numerous actors : coins, portals, strawberries
 
* ghosts are now implemented into this level: 2 will chase you as you wander around the map and 2 will move randomly
 
* the ghosts have two states: a normal state and a scared state. when they are in their normal state, if they interact with the player, the player returns to its spawn position, the ghosts to their refuge position and the total amount of health points of the player decreases by one (he starts with 5 HP)

* you can scare the ghosts by eating a coin, they will run away. their speed will increase

* eat a strawberry and the player's speed increases

* the respective speeds will reset once the player interacts with a Jamila.(REMINDER strawberries are signals for the Jamilas). 
 
* when you ingest either a Jamila or a strawberry, a fire will be created

* the user selects when he interacts with a strawberry (press E) so a strategy can be put in place ;)
 
* strawberries and Jamilas are placed in corners so you must move strategically ;) (TIP: do not consume a Jamila if you already have the maximum amount of lives)
 
* you have completed the level once the gates open and you go through the doors
 
 
##### Level 2
* To complete level2, you must eat all the diamonds. you must open all the gates to do that (they use different signals (see page 20 of handout))

* differing from level1, you only have smartmoving ghosts

* just like the previous level, there are still portals, strawberries, Jamilas and fires that work exactly the same way (so do the speeds and scared states)

* there is an additional smart ghost that will chase the player with constant speed AND he will not get afraid (he doesn't have a refuge position because he is braver than the others :o )

* you have completed the level once you eat all the diamonds, open all the gates and pass through the doors

**There is no game Over display when reaching the level 2 doors as we do not expect the corrector to reach the end of the three levels**
   

## Images

Most of the images are provided in the game's archive
> other sources : 
>* https://www.pinclipart.com/pindetail/mTxmx_strawberry-clipart-pacman-pacman-fruit-png-download/
>* https://grappe.itch.io/pixelportal

__GOOD LUCK AND HAVE FUN__
