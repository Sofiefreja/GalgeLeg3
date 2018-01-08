package com.example.sofie.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import static com.example.sofie.galgeleg.MainMenu.logic;

/**
 * Created by sofie on 23-10-2017.
 */

public class Words extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private ListView lv;
    private ArrayList<String> possibleWords;
    Toolbar toolbar;
    private Button startGame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View source = inflater.inflate(R.layout.activity_words, container, false);

        Spinner spinner = (Spinner) source.findViewById(R.id.spinner);
        startGame = (Button) source.findViewById(R.id.startGame2);

        startGame.setOnClickListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Words, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    spinner.setOnItemSelectedListener(this);

        startGame.setOnClickListener(this);

        return source;

    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String sSelected = parent.getItemAtPosition(pos).toString();
        Toast.makeText(getActivity(),sSelected,Toast.LENGTH_SHORT).show();
        logic.setWord(sSelected);
        System.out.println(sSelected);
        System.out.println(logic.getTheWord());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View v) {
       if(v == startGame) {
           Game fragment = new Game();
           Bundle arguments = new Bundle();
           fragment.setArguments(arguments);

           getFragmentManager().beginTransaction()
                   .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                   .replace(R.id.fragments, fragment).addToBackStack(null).commit();
       }
    }

}


