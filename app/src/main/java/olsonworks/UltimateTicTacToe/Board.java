package olsonworks.UltimateTicTacToe;


/**
 * Created by Peter Olson on 4/29/2015.
 */
public class Board {

    private int curPlayer;
    private SubGame[] games;
    //Which subGame you must play in next, -1 if any
    private int nextGame;

    //Create new blank Board
    public Board(){
        curPlayer = 1;
        games = new SubGame[9];
        nextGame = -1;

        for (int i = 0; i < games.length; i++) {
            games[i] = new SubGame();
        }
    }

    //Create new board as copy of incoming Board
    public Board(Board template){
        setCurPlayer(template.getCurPlayer());
        setGames(template.getGames());
    }

    //This makes the actual moves, and returns the next SubGame that must be played in. Returns -1 for "Freemove"
    public int makeMove(int subGame,int location, int player){
        if (isLegalMove(location,player)){
            //Makes move
            games[subGame].makeMove(location, player);

            //Changes current Player
            if (curPlayer == 1){
                curPlayer = 2;
            }else{
                curPlayer = 1;
            }

            //Checks if this makes a "Freemove", and sets where the next move will be
            if(games[location].isWon()){
                nextGame = -1;
            }else{
                nextGame = location;
            }
        }

        return nextGame;
    }

    //Make sure it is a legal move
    public boolean isLegalMove(int subGame,int location){
        return games[subGame].isLegalMove(location);
    }

    //Returns a 3x3x3x3 array of integers that represents the board state
    public int[][][][] indexBoard(){
        int[][][][] stateSpace = new int[3][3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        stateSpace[i][j][k][l] = games[(i*3)+j].getTile((k*3)+l);
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
    public int getCurPlayer() {
        return curPlayer;
    }

    public void setCurPlayer(int curPlayer) {
        this.curPlayer = curPlayer;
    }

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

