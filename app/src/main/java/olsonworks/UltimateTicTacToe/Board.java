package olsonworks.UltimateTicTacToe;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Olson on 4/29/2015.
 */
public class Board {


    private SubGame[] games;
    //Which subGame you must play in next, -1 if any
    private int nextGame;

    //Create new blank Board
    public Board(){
        games = new SubGame[9];
        nextGame = -1;

        for (int i = 0; i < games.length; i++) {
            games[i] = new SubGame();
        }
    }

    //Create new board as copy of incoming Board
    public Board(Board template){
        setGames(template.getGames());
    }

    //This makes the actual moves, and returns the next SubGame that must be played in. Returns -1 for "Freemove"
    public int makeMove(int subGame,int location, int player){
        if (isLegalMove(location,player)){
            //Makes move
            games[subGame].makeMove(location, player);


            //Checks if this makes a "Freemove", and sets where the next move will be
            if(games[location].isWon()){
                nextGame = -1;
            }else{
                nextGame = location;
            }
        }else{
            Log.d("GAME MOVE:","Illegal move");
        }

        return nextGame;
    }

    //Make sure it is a legal move
    public boolean isLegalMove(int subGame,int location){
        return games[subGame].isLegalMove(location);
    }

    //will return a list of all available moves, but I have to remember how lists are implemented first...
    public List listAvailableMoves(){
        List moves = new ArrayList();

        //iterates through possible moves and adds them to list if legal move
        for (int i = 0; i < 9; i++) {
            //checks to see if this game is the allowed game, or you have an any move
            if (nextGame == i || nextGame == -1) {
                for (int j = 0; j < 9; j++) {
                    //it the move legal?
                    if (games[i].isLegalMove(j)) {
                        //add to list of available moves, with the first digit as game, second digit as tile
                        moves.add((i * 10) + j);
                    }
                }
            }
        }
        return moves;
    }

    //Returns a 3x3x3x3 array of integers that represents the board state
    public int[][][][] indexBoard(){
        int[][][][] stateSpace = new int[3][3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        stateSpace[i][j][k][l] = games[(j*3)+i].getTile((l*3)+k);
                    }
                }
            }
        }

        return stateSpace;
    }

    public boolean checkWin(){
        //check rows
        if(games[0].isWon()&& games[0].getWinner() == games[1].getWinner() && games[1].getWinner() == games[2].getWinner()) {return true;}
        if(games[3].isWon() && games[3].getWinner() == games[4].getWinner() && games[4].getWinner() == games[5].getWinner()) {return true;}
        if(games[6].isWon() && games[6].getWinner() == games[7].getWinner() && games[7].getWinner() == games[8].getWinner()) {return true;}
        //check columns
        if(games[0].isWon() && games[0].getWinner() == games[3].getWinner() && games[3].getWinner() == games[6].getWinner()) {return true;}
        if(games[1].isWon() && games[1].getWinner() == games[4].getWinner() && games[4].getWinner() == games[7].getWinner()) {return true;}
        if(games[2].isWon() && games[2].getWinner() == games[5].getWinner() && games[5].getWinner() == games[8].getWinner()) {return true;}
        //check diagonals
        if(games[0].isWon() && games[0].getWinner() == games[4].getWinner() && games[4].getWinner() == games[8].getWinner()) {return true;}
        if(games[2].isWon() && games[2].getWinner() == games[4].getWinner() && games[4].getWinner() == games[6].getWinner()) {return true;}

        return false;
    }

    //Getters and setters
    public SubGame[] getGames() {
        return games;
    }

    public void setGames(SubGame[] games) {
        for (int i = 0; i < games.length; i++) {
            this.games[i] = new SubGame(games[i]);
        }
    }

    public int getNextGame() {
        return nextGame;
    }

    public void setNextGame(int nextGame) {
        this.nextGame = nextGame;
    }
}

