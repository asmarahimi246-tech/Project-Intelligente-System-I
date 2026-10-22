package app.network;


public class AppController {

    public void handleGame(ServerMessage message) {
        switch (message.getAddon()) {
            case DRAW -> handleDraw(message);
            case LOSS -> handleLoss(message);
            case WIN -> handleWin(message);
            case MATCH -> handleMatch(message);
            default -> System.out.println(
                    "Unhandled message: " + message.getAddon()
            );
        }
    }

    public MessageType handleMessage(ServerMessage message) {
        return switch (message.getType()) {
            case OK ->  MessageType.OK;
            case ERR -> MessageType.ERR;
            case SVR -> MessageType.SVR;
        };
    }

    private void handleMatch(ServerMessage message) {
        String turnMsg = message.getValue("TURNMESSAGE");
        String playerToMove = message.getValue("PLAYERTOMOVE");
        String opponent = message.getValue("OPPONENT");

        System.out.println("Turn Message: " + turnMsg);
        System.out.println("Player to Move: " + playerToMove);
        System.out.println("Opponent: " + opponent);
    }

    private void handleDraw(ServerMessage message) {
        getGameInfo(message);
    }

    private void handleLoss(ServerMessage message) {
        getGameInfo(message);
    }

    private void handleWin(ServerMessage message) {
        getGameInfo(message);
    }

    private String getComment(ServerMessage message) {
        return message.getValue("COMMENT");
    }

    private int getP1Score(ServerMessage message) {
        return Integer.parseInt(message.getValue("PLAYERONESCORE"));
    }

    private int getP2Score(ServerMessage message) {
        return Integer.parseInt(message.getValue("PLAYERTWOSCORE"));
    }

    private void getGameInfo(ServerMessage message) {
        getP1Score(message);
        getP2Score(message);
        getComment(message);
    }
}

//ServerMessage message = handler.parserMessage(rawMessage);
//gameController.handleMessage(message);