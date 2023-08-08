# ResurgenceLib
ResurgenceLib is a library for managing the common aspects and listeners of minigame plugins. 

It comes after a difficult period in my life, hence the name.

## Why ResurgenceLib?
I don't know how many minigame libraries there are out there... so it's difficult to make a proper comparison. However, here are the reasons to use ResurgenceLib:

- Extendable API - if you need specific settings, make adjustments for your own 
- Complete handling of minigame cycling, as you wish for it - 
- Takes care of unexpected events as you choose - have a player join mid-game? Maybe leave mid-game? Server goes down mid-game? 
- Open-source - you are able to access the full source code of ResurgenceLib and maintain your own forks of it.
- Make addons for all ResurgenceLib plugins - if you're covering one, you can cover all of them without any extra effort. Just check if they implement the ResurgencePlugin interface.
- Modular - the core module of ResurgenceLib covers the base requirements that the library should meet. However, extra "helper" modules such as the GUI or Spectator library are available for use.

## Extra Libraries
There are some extra libraries that I have that not only work with ResurgenceLib, but will work well together to help with development and management of minigame plugins:

- Cloud - 
- ConfigurationMaster - allows for use of @Option annotations, which can be used within an implemented GameSettings interface to plug directly into a config file.
- ItemsAPI - allows for custom items to be used within minigames from other plugins, or even have an extra item plugin developed dedicated to item functions rather than fitting it in one plugin.
- [scoreboard-library](https://github.com/MegavexNetwork/scoreboard-library)

