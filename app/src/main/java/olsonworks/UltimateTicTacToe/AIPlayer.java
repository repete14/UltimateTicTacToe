package olsonworks.UltimateTicTacToe;

import android.util.Log;

import java.util.List;

/**
 * Created by Peter Olson on 5/3/2015.
 */
public class AIPlayer {
    private int playerNumber;
    private int AIType; //0=random

    public AIPlayer(int player, int type){
        playerNumber = player;
        AIType = type;
    }

    public void makeMove(Board gameBoard){
        List moves = gameBoard.listAvailableMoves();
        int move = (int)moves.get((int)(Math.random()*moves.size()));
        int moveGame = (int)(move/10);
        int moveTile = (move % 10);
        Log.d("GAME MOVE:", "making move at " + moveGame + " " + moveTile);
        gameBoard.makeMove(moveGame,moveTile,playerNumber);
    }

}
