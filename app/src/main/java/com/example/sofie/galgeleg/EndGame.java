package com.example.sofie.galgeleg;


import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.example.sofie.galgeleg.MainMenu.logic;

/**
 * Created by sofie on 23-10-2017.
 */

public class EndGame extends Fragment implements View.OnClickListener {

    private Button endButton1, endButton2;
    private TextView endTextView, endTextView2;
    private ImageView endiv;
    KonfettiView konfettiView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("EndGame", "shown");
        View source = inflater.inflate(R.layout.activity_end, container, false);
        //System.out.println("EndGame class");
        final MediaPlayer lose = MediaPlayer.create(getActivity(), R.raw.horn);
        endButton1 = (Button) source.findViewById(R.id.endBut);
        endButton2 = (Button) source.findViewById(R.id.endBut2);
        endTextView = (TextView) source.findViewById(R.id.endtv);
        endTextView2 = (TextView) source.findViewById(R.id.endtv2);
        endiv = (ImageView) source.findViewById(R.id.endiv);

        endButton1.setText("Yes");
        endButton2.setText("No");
        endTextView2.setText("Want to play again?");
        endiv.setImageResource(Game.imageIDs[logic.getWrongLetters()]);

        if (logic.LastLetter() == true) {

            konfettiView = (KonfettiView) source.findViewById(R.id.confetti);
            konfettiView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    konfettiView.build()
                            .addColors(Color.RED,Color.GREEN)
                            .setSpeed(1f,5f)
                            .setFadeOutEnabled(true)
                            .setTimeToLive(2000L)
                            .addShapes(Shape.RECT, Shape.CIRCLE)
                            .setPosition(0f,-359f,-359f,0f)
                            .stream(200,5000L);
                }
            });
            endTextView.setText("You won the game! You used " + logic.getGuesses() + " guesses to guess the word: " + logic.getTheWord() + "!");
            System.out.println("Love");
        } else if (logic.LastLetter() == false) {
            lose.start();
            endTextView.setText("You lost the game! The word was: " + logic.getTheWord() + " in time!");
        }

        endButton1.setOnClickListener(this);
        endButton2.setOnClickListener(this);

        return source;

    }

    @Override
    public void onClick(View v) {

        if (v == endButton1) {
            getFragmentManager().popBackStackImmediate();
        } else if (v == endButton2) {
            getFragmentManager().popBackStackImmediate("MainMenu", 0);
        }

    }

}
