package network;

public class ServerMessageParser {
}

/*
> parses/cut up server messages
> could be split into several classes but not necessary

EX:
input String from server: SVR GAME MATCH {PLAYERTOMOVE: "<naam speler1>", GAMTYPE: "<speltype>", OPPONENT: "<naam tegenstander>"}
output ServerMessage object with:
    > type = svr
    > subtype = match
    > fields:
        > playerToMove = <player name>
        > game = <game name>
        > opponent = <opponent name>

input String from server: SVR GAME WIN/LOSS/DRAW {PLAYERONESCORE: "<score speler1>", PLAYERTWOSCORE: "<score speler2>", COMMENT: "<commentaar op resultaat>"}
output ServerMessage object with:
    > type = svr
    > subtype = win/loss/draw (GAME_RESULT)
    > fields:
        > player1Score = <player 1 score>
        > player2Score = <player 2 score>
        > comment/message: <comment ex: "You won!"> --> printed/handled by !! VIEW !!


*/