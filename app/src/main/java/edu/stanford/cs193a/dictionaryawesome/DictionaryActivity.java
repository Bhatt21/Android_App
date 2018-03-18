

package edu.stanford.cs193a.dictionaryawesome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;
import stanford.androidlib.*;

public class DictionaryActivity extends SimpleActivity {

    private Map<String, String> dictionary;
    private int points;
    private int highscore=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);


        dictionary = new HashMap<>();
        readFileData();
        chooseWords();


        $LV(R.id.word_list).setOnItemClickListener(this);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("points", points);
        outState.putString("the_word", $TV(R.id.the_word).getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        points = savedInstanceState.getInt("points", /* default */ 0);

    }
    @Override
    public void onItemClick(ListView list, int index) {
        String defnClicked = list.getItemAtPosition(index).toString();
        String theWord = $TV(R.id.the_word).getText().toString();
        String correctDefn = dictionary.get(theWord);
        if (defnClicked.equals(correctDefn)) {
            toast("Correct!");
            points++;
            $TV(R.id.pooints).setText(points+"");
            if(points>highscore)
                highscore=points;

        } else {
            points--;
            toast("Wrong Answer");
            $TV(R.id.pooints).setText(points+"");
        }
        chooseWords();
    }


    private void readFileData() {

        Scanner scan = new Scanner(
                getResources().openRawResource(R.raw.grewords));
        readFileHelper(scan);


        try {
            Scanner scan2 = new Scanner(openFileInput("added_words.txt"));
            readFileHelper(scan2);
        } catch (Exception e) {

        }


    }


    private void readFileHelper(Scanner scan) {
        while (scan.hasNextLine()) {

            String line = scan.nextLine();
            String[] parts = line.split("\t");
            if (parts.length < 2) continue;
            dictionary.put(parts[0], parts[1]);
        }
    }


    private void chooseWords() {

        List<String> words = new ArrayList<>(dictionary.keySet());
        Random randy = new Random();
        int randomIndex = randy.nextInt(words.size());
        String theWord = words.get(randomIndex);
        String theDefn = dictionary.get(theWord);


        List<String> defns = new ArrayList<>(dictionary.values());
        defns.remove(theDefn);
        Collections.shuffle(defns);
        defns = defns.subList(0, 3);
        defns.add(theDefn);
        Collections.shuffle(defns);


        $TV(R.id.the_word).setText(theWord);
        SimpleList.with(this).setItems(R.id.word_list, defns);
    }




    public void goback(View view) {
        Intent intent=new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }
}
