/**
 * CS 193A, Winter 2017, Marty Stepp
 * This activity helps the user add a word to the app.
 * After it is finished, it returns to the StartWordActivity.
 */

package edu.stanford.cs193a.dictionaryawesome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.PrintStream;

import stanford.androidlib.SimpleActivity;

public class AddWordActivity extends SimpleActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        Intent intent = getIntent();

    }


    public void addThisWordClick(View view) {

        String newWord = $ET(R.id.new_word).getText().toString();
        String newDefn = $ET(R.id.new_defn).getText().toString();


        PrintStream output = new PrintStream(openFileOutput("added_words.txt", MODE_APPEND));
        output.println(newWord + "\t" + newDefn);
        output.close();


        Intent goBack = new Intent();

        setResult(RESULT_OK, goBack);
        finish();


    }
}
