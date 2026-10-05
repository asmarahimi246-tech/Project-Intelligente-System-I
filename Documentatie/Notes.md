# Component Overview

| Type | Component | Responsibility |
|---|---|---|
| C | `Main` | Creates a `GameApp` object and runs it. |
| C | `GameApp` | Runs the app and controls app flow. |
| C | `GameFramework` | Creates a game of the given `GameType`. |
| E | `GameType` | Lists playable games. |
| I | `Game` | Generic game interface. |
| I | `Move` | Generic move interface. |
| E | `GameResult` | Lists game results; works independently from the server. |
| E | `GameEndReason` | Lists game-ending types; works independently from the server. |
| C | `TicTacToeModel` | Holds the rules and game state. |
| C | `TicTacToeView` | Displays the game and board. |
| C | `TicTacToeController` | Bridges the model and view. |
| C | `TicTacToeMove` | Constructs a game-specific move. |
| C | `Client` | Handles the connection to the server. |
| E | `MessageType` | Lists general server message types. |
| E | `MessageSubtype` | Lists specific server message types. |
| I | `ServerListener` | Handles incoming server messages. |
| C | `ServerMessageParser` | Parses server messages into a type, subtype, and fields (`Map<String, String>`). |
| C | `ServerMessage` | Provides a constructor and getters. |
| I | `Player` | Generic player interface. |
| C | `LocalPlayer` | Creates an instance of a local player. |
| C | `AI` | Decides what move to make. |
| C | `OnlinePlayer` | Creates an instance of a player on the server. |
| C | `UI` | General UI layer. |
| C | `MainMenu` | Displays main-menu options and gets user input. |
| C | `GameMenu` | Displays game-menu options and gets user input. |

**Legend:** `C` = class · `I` = interface · `E` = enum



# App Flow

## 1. Start the app

Main → GameApp

## 2. Show the main menu

GameApp → MainMenu → "Play"

## 3. Choose a game

GameApp → GameMenu → TIC_TAC_TOE (GameType)

## 4. Create the game

GameApp → GameFramework → TicTacToeModel

## 5. MVC system

TicTacToeModel ↔ TicTacToeController ↔ TicTacToeView

- The controller checks moves and game state with the model.
- The controller gets responses from the model.
- The controller tells the view to update.
- The view updates with the new move.

## 6. Make moves

Terminal (UI) input → Controller → Model.doMove(...) → AI.pickMove() → Model.isValidMove() → Controller → View.printBoard(...)

- **Local player:** Gets move input from the UI (terminal for now).
- **AI:** Calculates a move based on the current board state.
- **Online player:** Gets move input from a server message.



# Online Play Flow

## Own Move

Terminal (UI) input --> Controller --> Model --> Client --> Server

## Opponent Move

Server --> Client --> ServerListener --> ServerMessageParser --> ServerMessage --> Controller --> Model --> View

# Server Message Flow

Server --message--> Client --> ServerMessageParser --> ServerMessage --> Other Classes

# Local Game Flow

MainMenu --"play"--> GameMenu --"tic-tac-toe" + "local"--> GameFactory --> TicTacToeModel --[player makes move]-->

    TicTacToeController --> TicTacToeMove (5 or col 2, row 2) --> TicTacToeModel --update--> TicTacToeView
      check move &                     do move                    tell View to
      game state                                                 update with move