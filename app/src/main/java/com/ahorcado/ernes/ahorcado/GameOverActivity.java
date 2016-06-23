package com.ahorcado.ernes.ahorcado;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView pointsTextView;
    Button saveScoreButon;
    EditText nameEdittext;

    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        points = getIntent().getIntExtra("points", 0);

        pointsTextView = (TextView) findViewById(R.id.points_text_view);
        pointsTextView.setText(String.valueOf(points));

        final SharedPreferences preferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        saveScoreButon = (Button) findViewById(R.id.save_score_button);
        nameEdittext = (EditText) findViewById(R.id.name_edit_text);

        saveScoreButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdittext.getText().toString();

                String previousScore = preferences.getString("SCORES", "");
                editor.putString("SCORES", name + " " + points + " POINTS \n" + previousScore);
                editor.commit();
                finish();
            }
        });

    }
}
