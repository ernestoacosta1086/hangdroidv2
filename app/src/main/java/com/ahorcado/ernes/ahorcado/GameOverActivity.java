package com.ahorcado.ernes.ahorcado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView pointsTextView;


    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        points = getIntent().getIntExtra("points", 0);

        pointsTextView = (TextView) findViewById(R.id.points_text_view);
        pointsTextView.setText(String.valueOf(points));
    }
}
