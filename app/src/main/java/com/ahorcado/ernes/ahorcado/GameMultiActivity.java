package com.ahorcado.ernes.ahorcado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Screen Single Player
 */
public class GameMultiActivity extends AppCompatActivity {

    EditText letterEditText;
    Button checkLetterButton;
    LinearLayout lettersLinearLayout;
    ImageView gameStatusImageView;
    TextView letterFailedTextView;

    String word;
    int failCounter = 0;
    int gueserLetters = 0;
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_multi);

        word = getIntent().getStringExtra("word");

        gameStatusImageView = (ImageView) findViewById(R.id.game_status_image_view);
        letterEditText = (EditText) findViewById(R.id.letter_edit_text);
        lettersLinearLayout = (LinearLayout) findViewById(R.id.letters_linear_layout);
        letterFailedTextView = (TextView) findViewById(R.id.failed_letters_text_view);

        createTextViews(word);

        checkLetterButton = (Button) findViewById(R.id.check_letter_button);
        checkLetterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String letter = letterEditText.getText().toString().toUpperCase();

                if (!letter.equals("")) {
                    checkLetterInWord(letter.charAt(0));

                } else {
                    Toast.makeText(GameMultiActivity.this, "Debes introducir un caracter", Toast.LENGTH_SHORT).show();
                }

                letterEditText.setText("");
            }
        });
    }

    private void createTextViews(String sendWord) {

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.letters_linear_layout);

        for (int i = 0; i < sendWord.length(); i++) {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            layoutLetters.addView(textView);
        }
    }

    /**
     * Checking if the letter introduced matches any letter in the word to guess
     *
     * @param letter, letter introduced by the user
     */
    public void checkLetterInWord(char letter) {
        boolean letterFailed = true;

        for (int i = 0; i < word.length(); i++) {
            if (letter == word.charAt(i)) {
                Log.d("MYLOG", "There is the equal " + letter);
                letterFailed = false;
                showLetterAtIndex(i, letter);
                gueserLetters++;
            }
        }
        if (letterFailed) {
            letterFailed(letter);
        }
        if (gueserLetters == word.length()) {
            finish();
        }
    }

    /**
     * Clear the screen to begin another play section
     */
    public void clearScreen() {
        letterFailedTextView.setText("");
        gueserLetters = 0;
        failCounter = 0;

        for (int i = 0; i < lettersLinearLayout.getChildCount(); i++) {
            TextView textView = (TextView) lettersLinearLayout.getChildAt(i);
            textView.setText("_");
        }

        gameStatusImageView.setImageResource(R.drawable.android_0);
    }

    /**
     * Show letter failed in textview and choose the correct picture to show
     *
     * @param letter, letter checked
     */
    private void letterFailed(char letter) {
        String previousFailedLetter = letterFailedTextView.getText().toString();
        letterFailedTextView.setText(previousFailedLetter + Character.toString(letter));
        failCounter++;
        if (failCounter == 1) {
            gameStatusImageView.setImageResource(R.drawable.android_1);
        } else if (failCounter == 2) {
            gameStatusImageView.setImageResource(R.drawable.android_2);
        } else if (failCounter == 3) {
            gameStatusImageView.setImageResource(R.drawable.android_3);
        } else if (failCounter == 4) {
            gameStatusImageView.setImageResource(R.drawable.android_4);
        } else if (failCounter == 5) {
            gameStatusImageView.setImageResource(R.drawable.android_5);
        } else if (failCounter == 6) {
            gameStatusImageView.setImageResource(R.drawable.android_6);
        } else {
            //TODO GAME OVER
            Intent intent = new Intent(GameMultiActivity.this, GameOverActivity.class);
            intent.putExtra("points", points);
            points = 0;
            startActivity(intent);
        }
    }

    /**
     * Display the letter guessed by the user
     *
     * @param position, position of the letter
     * @param letter,   letter in the word
     */
    public void showLetterAtIndex(int position, char letter) {
        TextView textView = (TextView) lettersLinearLayout.getChildAt(position);
        textView.setText(Character.toString(letter));
    }

}
