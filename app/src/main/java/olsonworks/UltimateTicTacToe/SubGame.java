package olsonworks.UltimateTicTacToe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Olson on 4/29/2015.
 *
 * This class is the container for all information of any subgame of tic tac toe in the larger game.
 */
public class SubGame {

    //declaration of variables
    /*first are the 9  moves on the board stored as an array.
     * 0 1 2
     * 3 4 5
     * 6 7 8
     * 0 = no move played here
     * 1 = player 1
     * 2 = player 2
     *Next is the state of the game, either completed or not (might just change this to a get later, but for now this might be a better solution
     *Lastly is the record of who won this sub game (see above)
     */
    private int[] tiles = new int[9];
    private boolean won;
    private int winner;

    //What to do when a new SubGame is created with no inputs (blank)
    public SubGame(){

        //turn each of the members of the array to state 0, and set there to be no winner
        for (int i = 0; i < tiles.length; i++) {
            tiles[i]=0;
        }
        won = false;
    }

    //create a new SubGame by copying a different SubGame.
    // Called just as normally making a new instance, but just with an argument of the SubGame to be copied.
    // I can see this coming in handy if the AI wants to make dummy games to think through it's moves.
    public SubGame(SubGame reference){
        tiles = reference.getTiles();
        won = reference.isWon();
        winner = reference.getWinner();
    }

    //The only thing it should really have to do (returns false if that is an illegal move):
    public boolean makeMove(int location, int player){
        //Make sure it is a legal move
        if(! isLegalMove(location)) return false;

        //Make the intended move
        tiles[location] = player;

        //check for win. This might not be easy to make elegant. There really must be a mathy way to do this
        won = checkWin();
        if (won) winner = player;

        return true;
    }

    //Double checks to make sure move is legal
    public boolean isLegalMove(int location){
        if(won || tiles[location] != 0) return false;
        return true;
    }

    //check for win. This might not be easy to make elegant. There really must be a mathy way to do this
    public boolean checkWin(){
        //check rows
        if(tiles[0] != 0 && tiles[0] == tiles[1] && tiles[1] == tiles[2]) {return true;}
        if(tiles[3] != 0 && tiles[3] == tiles[4] && tiles[4] == tiles[5]) {return true;}
        if(tiles[6] != 0 && tiles[6] == tiles[7] && tiles[7] == tiles[8]) {return true;}
        //check columns
        if(tiles[0] != 0 && tiles[0] == tiles[3] && tiles[3] == tiles[6]) {return true;}
        if(tiles[1] != 0 && tiles[1] == tiles[4] && tiles[4] == tiles[7]) {return true;}
        if(tiles[2] != 0 && tiles[2] == tiles[5] && tiles[5] == tiles[8]) {return true;}
        //check diagonals
        if(tiles[0] != 0 && tiles[0] == tiles[4] && tiles[4] == tiles[8]) {return true;}
        if(tiles[2] != 0 && tiles[2] == tiles[4] && tiles[4] == tiles[6]) {return true;}

        return false;
    }

    //will return a list of all available moves, but I have to remember how lists are implemented first...
    public List listAvailableMoves(){
        List moves = new ArrayList();

        for (int i = 0; i < 9; i++) {
            if (isLegalMove(i)){
                moves.add(i);
            }
        }

        return moves;
    }


    //getters and setters for local variables
    public int[] getTiles() {
        return tiles;
    }

    public int getTile(int location) {return tiles[location];}

    public void setTiles(int[] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            this.tiles[i] = tiles[i];
        }
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
