package com.ahorcado.ernes.ahorcado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MultiplayerActivity extends AppCompatActivity {

    Button saveWord;
    EditText wordEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        wordEdittext = (EditText) findViewById(R.id.word_edit_text);
        saveWord = (Button) findViewById(R.id.save_word_button);
        saveWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordEdittext.getText().toString();
                wordEdittext.setText("");
                Intent intent = new Intent(MultiplayerActivity.this, GameMultiActivity.class);
                intent.putExtra("word", word.toUpperCase());
                startActivity(intent);
            }
        });
    }
}
