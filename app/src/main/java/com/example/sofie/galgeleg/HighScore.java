package com.example.sofie.galgeleg;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Sofie on 8/01/2018.
 */

public class HighScore extends Fragment implements OnClickListener {
    private ListView scoreList;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Game", "shown");
        View source = inflater.inflate(R.layout.activity_highscore, container, false);
        scoreList = (ListView) source.findViewById(R.id.scoreList);
        System.out.println("SCORE IS: " + loadHighscore().toString());


        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, loadHighscore().toArray());

        scoreList.setAdapter(adapter);

        return source;
    }
        @Override
        public void onClick (View v){

        }
        private ArrayList<String> loadHighscore() {
            SharedPreferences prefObj = PreferenceManager.getDefaultSharedPreferences(getActivity());
            Gson gsonObj = new Gson();
            String highScoreJson = prefObj.getString("highscore", null);

            ArrayList<String> highscores;
            if(highScoreJson != null) {
                highscores = gsonObj.fromJson(highScoreJson, new TypeToken<ArrayList<String>>(){}.getType());
            } else {
                highscores = new ArrayList<String>();
            }
            return highscores;
        }
    }
