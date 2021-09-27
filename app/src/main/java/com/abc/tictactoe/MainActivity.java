package com.abc.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
     boolean gameActive = true;
    /*
        player representation
        0 = x
        1 = o
    */
    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    /*
        State meanings
        0 = x
        1 = o
        2 = null
    */

    int [][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                             {0,3,6},{1,4,7},{2,5,8},
                             {0,4,8},{2,4,6}};

    public void playerTap(View view) {

        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImg] == 2 && gameActive) {
            gameState[tappedImg] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.oPlay);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.xPlay);
            }

            img.animate().translationYBy(1000f).setDuration(200);
        }


        for(int[] winPosition : winPositions) {

            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                //Somebody has won
                String winnerStr = "";
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "x has won";
                } else if (gameState[winPosition[0]] == 1) {
                    winnerStr = "o's has won";
                }

                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }

        }


    }

    private void gameReset(View view) {
        gameActive = true;
        activePlayer=0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    }


    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
