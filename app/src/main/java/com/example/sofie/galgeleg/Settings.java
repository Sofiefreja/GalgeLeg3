package com.example.sofie.galgeleg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sofie on 23-10-2017.
 */

public class Settings extends Fragment implements View.OnClickListener {
    private Button wordBut, hang_s, hang_ss;
    private TextView manChoice;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Settings", "shown");

        View src = inflater.inflate(R.layout.activity_settings, container, false);

        wordBut = (Button) src.findViewById(R.id.wordBut);

        wordBut.setOnClickListener(this);



        return src;

    }

   public void onClick(View v) {

        if (v == wordBut) {

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, new Words()).addToBackStack(null).commit();
        }


    }
}