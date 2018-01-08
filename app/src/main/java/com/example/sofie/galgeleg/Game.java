package com.example.sofie.galgeleg;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



import static android.content.ContentValues.TAG;
import static com.example.sofie.galgeleg.MainMenu.logic;

/**
 * Created by sofie on 23-10-2017.
 */

public class Game extends Fragment implements View.OnClickListener {
    private int seconds = 0;
    private boolean running;
    TextView timeView=null;
    Handler handler = null;
    private Button gameButton;
    private EditText et;
    private ImageView gameImageView;
    private TextView gameTextView;

    public static int numberOfVictories;
    static Integer[] imageIDs = {
            R.drawable.hangman,
            R.drawable.fail1_s,
            R.drawable.fail2_s,
            R.drawable.fail3_s,
            R.drawable.fail4_s,
            R.drawable.fail5_s,
            R.drawable.fail6_s,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Game", "shown");
        View source = inflater.inflate(R.layout.activity_game, container, false);

        gameButton = (Button) source.findViewById(R.id.submit);
        gameTextView = (TextView) source.findViewById(R.id.gametv);
        gameImageView = (ImageView) source.findViewById(R.id.gameiv);
        et = (EditText) source.findViewById(R.id.et);

        gameButton.setOnClickListener(this);
      // et.setOnClickListener(R.id.et);

        handler = new Handler();
       // numberOfVictories = sharedP.getInt
        startGame();

        return source;
    }


    @Override
    public void onClick(View v) {

        if (v == gameButton) {

            String letter = et.getText().toString();
            if (letter.length() != 1 && letter.length() != logic.getTheWord().length()) {
                et.setError("Write one letter or the whole word");
                return;
            }
            if (letter.length() == logic.getTheWord().length()) {
                logic.guesWord(letter);
                updateScreen();
            } else {
                logic.guessLetter(letter);
                et.setText("");
                et.setError(null);
                updateScreen();
            }
        }
    }



    public void startGame() {
       // logic.tara();

        gameTextView.setText("Welcome to hangman!" +
                "\nThis is the word you have to guess: "+logic.getWord());
        et.setHint("Write letter here.");
        gameImageView.setImageResource(imageIDs[logic.getWrongLetters()]);
        System.out.println(logic.getTheWord());
;    }

    private void endGame() {

        EndGame fragment = new EndGame();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);

        getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragments, fragment).addToBackStack(null).commit();

    }

    private void updateScreen() {

        gameTextView.setText("Guess the word: " + logic.getWord());
        if (logic.LastLetter() == true) {
            gameTextView.append("\n\nYou have " + logic.getWrongLetters() + " wrong:" + logic.getBrugteBogstaver());
        } else if (logic.LastLetter() == false) {
            gameTextView.append("\n\nYou have " + logic.getWrongLetters() + " wrong:" + logic.getBrugteBogstaver());
            gameImageView.setImageResource(imageIDs[logic.getWrongLetters()]);

        }

        if (logic.gameWon()) {
            gameTextView.append("\nYou win!");

            endGame();
        }
        if (logic.gameLost()) {
            gameTextView.setText("You lost... The word was : " + logic.getTheWord());
            endGame();
        }
    }


}
