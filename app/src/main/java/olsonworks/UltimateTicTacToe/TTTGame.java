package olsonworks.UltimateTicTacToe;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Olson on 5/3/2015.
 */
public class TTTGame{

    private Board gameBoard;
    private Boolean gameOver;
    private int gameType; //0=PvP, 1=PvE, 2=EvE
    private int curPlayer;
    private AIPlayer AIPlayer1;
    private AIPlayer AIPlayer2;


    public void TTTGame(int type) {
        //Make official gameBoard
        gameBoard = new Board();
        gameOver = false;

        //Setup game characteristics
        gameType = type;
        curPlayer = 1;

        //make any AIs that are needed
        if (gameType > 0){
            AIPlayer1 = new AIPlayer(1,0);
            if (gameType == 2){
                AIPlayer2 = new AIPlayer(2,0);
            }
        }
    }

    public void takeTurn() {
        //Just a test by making random moves until game is over
        if (!gameOver){
            makeRandomMove(gameBoard);
            logBoard(gameBoard);
            gameOver = gameBoard.checkWin();

            //Changes current Player
            if (curPlayer == 1){
                curPlayer = 2;
            }else{
                curPlayer = 1;
            }
        }else {
            Log.d("GAME MOVE:", "game over");
        }
    }

    public void takeTurn(int gameChoice, int tileChoice){
        //same as above, but with given inputs
        if (!gameOver){
            gameBoard.makeMove(curPlayer,gameChoice,tileChoice);
            logBoard(gameBoard);
            gameOver = gameBoard.checkWin();

            //Changes current Player
            if (curPlayer == 1){
                curPlayer = 2;
            }else{
                curPlayer = 1;
            }
        }else {
            Log.d("GAME MOVE:", "game over");
        }
    }

    //get random board move
    public void makeRandomMove(Board gameBoard){
        List moves = gameBoard.listAvailableMoves();
        int move = (int)moves.get((int)(Math.random()*moves.size()));
        int moveGame = (int)(move/10);
        int moveTile = (move % 10);
        Log.d("GAME MOVE:", "making move at " + moveGame + " " + moveTile);
        gameBoard.makeMove(moveGame,moveTile,curPlayer);
    }


    public void logBoard(Board board){
        String output = "";
        int[][][][] boardSpace = board.indexBoard();
        Log.d("GAME MOVE:", "new move");
        Log.d("GAME MOVE:", "-------------");
        for (int iGameY = 0; iGameY < 3; iGameY++) {
            for (int iTileY = 0; iTileY < 3; iTileY++) {
                output = "|";
                for (int iGameX = 0; iGameX < 3; iGameX++) {
                    for (int iTileX = 0; iTileX < 3; iTileX++) {
                        switch (boardSpace[iGameX][iGameY][iTileX][iTileY]){
                            case 1:
                                output += "X";
                                break;
                            case 2:
                                output += "O";
                                break;
                            default:
                                output += "*";
                                break;
                        }
                    }
                    output +="|";
                }
                Log.d("GAME MOVE:",output);
            }
            Log.d("GAME MOVE:", "-------------");
        }
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }
}

