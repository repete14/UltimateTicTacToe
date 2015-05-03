package olsonworks.UltimateTicTacToe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    Board gameBoard;
    Boolean gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Make official gameBoard
        gameBoard = new Board();
        gameOver = false;

        //Just a test by making random moves until game is over
        while(! gameOver){
            makeRandomMove(gameBoard);
            logBoard(gameBoard);
            gameOver = gameBoard.checkWin();
        }

        Log.d("GAME MOVE:", "game over");


    }

    //get random board move
    public void makeRandomMove(Board gameBoard){
        List moves = gameBoard.listAvailableMoves();
        int move = (int)moves.get((int)(Math.random()*moves.size()));
        int moveGame = (int)(move/10);
        int moveTile = (move % 10);
        Log.d("GAME MOVE:", "making move at " + moveGame + " " + moveTile);
        gameBoard.makeMove(moveGame,moveTile,gameBoard.getCurPlayer());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
