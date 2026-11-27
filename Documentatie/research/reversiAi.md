## Reversi AI

### Valid moves

A valid move always closes in the opponent

how to find:

1. Find opponent adjacent empty squares
2. Trace from empty piece going past the line of pieces of the opponent
3. check if your own piece is there

in code do recursive 8 directional ray cast from empty, following opponent to empty

### General strategie

The game is counter intuitive 

you want as little pieces during the early and mid game (so the opponent doesnt get space to flip them)

but in the late game you want as much as possible

(Yuna’s thinking: Maybe this isnt a game time problem but more a board possition problem where its unfavorable to have pieces in the center, but the opertunitie to place pieces on the edges outway the negatives)

### Global heuristics

Used for evaluating the board state for the current player

list of heuristics (with + being possitive for the player and - negative) (training is needed to determine how negative or possitive each is)

*   +/- Difference in coins between players \[1\]
*   \+ Amount of possible moves \[1\]
*   \+ Stability \[1\]
*    - Discs next to empty squares \[1\]
*   \+ Closeness to center \[Yuna\]

Sources : 

1. An Analysis of Othello AI Strategies

it be smart to use phase change heuristics with a form of the following

$$
PhaseBasedHeuristic=Heuristic+x*RoundsPlayed^{z}
$$

### Board

The board has the following strategie important tiles:

|  | a | b | c | d | e | f | g | h |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | D | C | A | B | B | A | C | D |
| 2 | C | X |  |  |  |  | X | C |
| 3 | A |  |  |  |  |  |  | A |
| 4 | B |  |  |  |  |  |  | B |
| 5 | B |  |  |  |  |  |  | B |
| 6 | A |  |  |  |  |  |  | A |
| 7 | C | X |  |  |  |  | X | C |
| 8 | D | C | A | B | B | A | C | D |

In this list are strategic example for each group of tiles (with + being possitive for the player and - negative):

*   \+ D are corner tiles wich are usefull for locking pieces in
*   \+ A tiles can help with getting D tiles
*   \- C are close to the corner and allow the opponent to take a D square
*   \- X is the same as C but way worse

### General AI Strategie

Optimize the following heuristic:

*   deltaMobility = (Amount of possible moves)/(Amount of possible opponent moves)
*   Closeness to center

### General AI Structure (TODO)

```scss
abMinimax(Board board, Move[] validMoves):
  calculateHeuristic()
  
  if (ai):
    #sort the validMoves based on value of strategic tiles on board
    goodTiles[] = validMoves.take(3)

    for goodTile in goodTiles
      next_board = board.copy()
      board.placeTile()
      
    
  
  else if (opponent):
```

### Things is should research (TODO)

| Algorithm / Technique | Role in a Reversi Bot | Why It Matters |
| --- | --- | --- |
| **Negamax + Alpha-Beta Pruning** | Core game-tree search | Simplifies Minimax into one recursive function (`-negamax(...)`) and cuts away branches that cannot beat the best move already found. |
| **Iterative Deepening (IDDFS)** | Time & depth control | Searches depth `1`, `2`, `3`… sequentially. Gives you a valid best move at any timeout and feeds best-move ordering into the next deeper pass. |
| **Principal Variation Search (PVS / NegaScout)** | Search tree narrowing | Assumes the first ordered move is best and searches remaining moves with a fast zero-width window (`alpha, alpha + 1`), re-searching only if a move surprises. |
| **ProbCut (Multi-ProbCut)** | Selective forward pruning | Uses a shallow search to predict whether a deep branch is almost certainly outside `[alpha, beta]` and prunes it early—a breakthrough pioneered specifically in Reversi. |
| **Exact Endgame Solver** | Last 14–22 empty squares | Switches off heuristic evaluation near the end of the game and solves to `depth = remaining_moves` using **Fastest-First move ordering** (playing into regions with the fewest empty neighbors first to maximize cutoffs). |

### Sources:

[ProbCut](https://chessprogramming.org/ProbCut)