package com.example.sofie.galgeleg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.example.sofie.galgeleg.logic.HangManLogic;

import static com.example.sofie.galgeleg.Game.numberOfVictories;

/**
 * Created by sofie on 23-10-2017.
 */

public class MainMenu extends Fragment implements View.OnClickListener {
    private Button settings, play, highScores, help;
    private TextView frametv;
    static HangManLogic logic = new HangManLogic();
    TextView victories;
    SharedPreferences shared;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
    View src = i.inflate(R.layout.activity_mainmenu, container, false);
    play = (Button) src.findViewById(R.id.startGame);
    settings = (Button) src.findViewById(R.id.settings);
    help = (Button) src.findViewById(R.id.help);
    highScores = (Button) src.findViewById(R.id.highscores);

       /* victories = (TextView) src.findViewById(R.id.victories);
        sharedP = getSharedPreferences("Victories", Context.MODE_PRIVATE);
        victories =s */


        play.setText("Play Hangman");
        settings.setText("Settings");
        help.setText("Help");
        highScores.setText("HighScores");


        play.setOnClickListener(this);
        settings.setOnClickListener(this);
        help.setOnClickListener(this);
        highScores.setOnClickListener(this);
        victories = (TextView) src.findViewById(R.id.victories);

        shared = PreferenceManager.getDefaultSharedPreferences(getActivity());
        numberOfVictories = shared.getInt("NumberofVictories",  numberOfVictories);
        victories.setText("Number of victories " +  numberOfVictories);

        victories = (TextView) src.findViewById(R.id.victories);
        return src;
}

    public void onClick(View v) {
        if (v == play) {

            Game fragment = new Game();
            Bundle arguments = new Bundle();
            fragment.setArguments(arguments);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment).addToBackStack(null).commit();
        } else if (v == settings) {

            Settings fragment = new Settings();
            Bundle arguments = new Bundle();
            fragment.setArguments(arguments);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment).addToBackStack(null).commit();

        } else if(v == help) {
            Help fragment = new Help();
            Bundle arguments = new Bundle();
            fragment.setArguments(arguments);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment).addToBackStack(null).commit();
        } else if(v == highScores) {
            HighScore fragment = new HighScore();
            Bundle arguments = new Bundle();
            fragment.setArguments(arguments);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment).addToBackStack(null).commit();

        }

    }
    public void onResume() {
        super.onResume();
        victories.setText("Number of victories " + numberOfVictories);
    }
}
