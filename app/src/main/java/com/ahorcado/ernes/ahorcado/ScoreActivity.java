package com.ahorcado.ernes.ahorcado;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTextView = (TextView) findViewById(R.id.score_textview);
        SharedPreferences preferences = getSharedPreferences("MYPREFERENCES", MODE_PRIVATE);
        String scores = preferences.getString("SCORES", "NO SCORES");
        scoreTextView.setText(scores);
    }
}
