# Gambler's Choice

Gambler's Choice is an implementation of a two main objects. The first is a user login system and the second is a poker game.
In this implementation the user is able to **Register** a new account or **login** to an existing account. The user will then have a balanace assigned to them that is subject to change through two ways. Either an **admin** account edits the user balance or the user may win money through the **poker game**.

## Features

This game contains a multitude of features implemented with Clean Architecture. 
Some of the most prominent ones are:

- Register an account
- Login to an existing account
- Store the username, password, account type, and balance in another file
- As an Admin account, manage other accounts
- While logged into an account with more than 100 balance, play a game of Poker
- During a poker game, bet, check, fold, call, or leave
- Properly calculate money and assign it after each game ends


## How to run

First ensure that you have Java 11 or newer on your device.

Next, to run the project please download the git repository.

Using your preferred IDE (Intellij Recommended), click on the Main class and run from there.

## Notable Packages

### Future_Features
This package contains future classes that are planned to be merged into our project. These features were completed without clean architecture and have not been integrated into our main project.

Of these we have the files for a computer guided opponents and a hint system.

### Deprecated
This package contains previous implementations of files that have been replaced by newer ones.

## Future Ideas
Currently our implementation does not support multiplayer with our login system. Some of our next steps would be to implement a "pass and play" system where users could make their move and pass it along to the next player.

A more ambitious idea however would be to run our game on a server and have each player connect to said server to make moves.
