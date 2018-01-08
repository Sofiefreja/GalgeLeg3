package com.example.sofie.galgeleg;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.sofie.galgeleg.MainMenu.logic;


public class Welcome extends Fragment implements Runnable {

   Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       Log.d("Welcome", "shown");

       System.out.println("Game starting");
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    logic.hentOrdFraDr();
                    return "Ordene blev korrekt hentet fra DR's server";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt: "+e;
                }
            }
            @Override
            protected void onPostExecute(Object resultat) {
                System.out.println("Resultatet er " + resultat);
            }

        }.execute();
        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.fail6);
        //tv1.setText("Hello");
        if (savedInstanceState == null) {
            handler.postDelayed(this, 3000);
        }

        return iv;

    }

    public void run() {
        Fragment fragment = new MainMenu();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragments, fragment).commit();
    }
}