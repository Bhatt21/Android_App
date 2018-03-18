package edu.stanford.cs193a.dictionaryawesome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import stanford.androidlib.SimpleActivity;

public class StartMenuActivity extends SimpleActivity {
    private static final int REQ_CODE_ADD_WORD = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }


    public void playTheGameClick(View view) {

        Intent intent = new Intent(this, DictionaryActivity.class);
        startActivity(intent);
    }

 void addANewWordClick(View view) {

        Intent intent = new Intent(this, AddWordActivity.class);

        startActivityForResult(intent, REQ_CODE_ADD_WORD);


    }


}
