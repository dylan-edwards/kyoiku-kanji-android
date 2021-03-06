package foss.edwards.dylan.kyoikukanji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;


public class CardsPage extends ActionBarActivity {

    public int       grade                = 0;
    public int       numberOfCardsInGrade = 0;
    public boolean   randomOrder          = false;
    public boolean   testMode             = false;
    public boolean   biteSize             = false;
    public int       currentItem          = 1;
    public String[]  kanjiList;
    public String[]  translationList;
    public String[]  onyomiList;
    public String[]  kunyomiList;
    public Integer[] indexList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_page);
        //receive intent
        Intent intent        = getIntent();
        grade                = intent.getIntExtra("grade", 1);
        numberOfCardsInGrade = intent.getIntExtra("numCards", 0);
        randomOrder          = intent.getBooleanExtra("random", false);
        testMode             = intent.getBooleanExtra("testMode", false);
        biteSize             = intent.getBooleanExtra("biteSize", false);

        setupLists(grade);
        //set up indexList
        indexList = new Integer[numberOfCardsInGrade];
        for (int i = 0; i < numberOfCardsInGrade; i++) {
            indexList[i] = new Integer(i);
        }
        if (randomOrder) {
            Collections.shuffle(Arrays.asList(indexList));
        }
        if (biteSize) {
            //get shared prefs and set numberofCards to biteSize size
            SharedPreferences biteSizeSaves = getSharedPreferences("bite_size", 0);
            numberOfCardsInGrade = biteSizeSaves.getInt("biteSize", 10);
        }

        TextView textViewGrade = (TextView) findViewById(R.id.textViewGrade);
        textViewGrade.setText("Grade: " + grade);

        if (testMode) {
            TextView kanji = (TextView) findViewById(R.id.textViewKanji);
            kanji.setClickable(true);
        }
        updateScreen();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cards_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPressNext(View v) {
        if (currentItem == numberOfCardsInGrade) {
            currentItem = currentItem; //heh
        } else {
            currentItem++;
            updateScreen();
        }
    }
    public void onPressPrev(View v) {
        if (currentItem == 1) {
            currentItem = currentItem;
        } else {
            currentItem--;
            updateScreen();
        }
    }

    public void setupLists(int grade) {
        if (grade == 1) {
            Resources res = getResources();
            kanjiList = res.getStringArray(R.array.kanjiList1);
            translationList = res.getStringArray(R.array.translationList1);
            onyomiList = res.getStringArray(R.array.onyomiList1);
            kunyomiList = res.getStringArray(R.array.kunyomiList1);
        } else if (grade == 2) {
            Resources res = getResources();
            kanjiList = res.getStringArray(R.array.kanjiList2);
            translationList = res.getStringArray(R.array.translationList2);
            onyomiList = res.getStringArray(R.array.onyomiList2);
            kunyomiList = res.getStringArray(R.array.kunyomiList2);
        } else if (grade == 3) {
            Resources res = getResources();
            kanjiList = res.getStringArray(R.array.kanjiList3);
            translationList = res.getStringArray(R.array.translationList3);
            onyomiList = res.getStringArray(R.array.onyomiList3);
            kunyomiList = res.getStringArray(R.array.kunyomiList3);
        } else if (grade == 4) {
            Resources res = getResources();
            kanjiList = res.getStringArray(R.array.kanjiList4);
            translationList = res.getStringArray(R.array.translationList4);
            onyomiList = res.getStringArray(R.array.onyomiList4);
            kunyomiList = res.getStringArray(R.array.kunyomiList4);
        } else if (grade == 5) {
            Resources res = getResources();
            kanjiList = res.getStringArray(R.array.kanjiList5);
            translationList = res.getStringArray(R.array.translationList5);
            onyomiList = res.getStringArray(R.array.onyomiList5);
            kunyomiList = res.getStringArray(R.array.kunyomiList5);
        } else if (grade == 6) {
            Resources res = getResources();
            kanjiList = res.getStringArray(R.array.kanjiList6);
            translationList = res.getStringArray(R.array.translationList6);
            onyomiList = res.getStringArray(R.array.onyomiList6);
            kunyomiList = res.getStringArray(R.array.kunyomiList6);
        }
    }


    public void updateScreen() {

        TextView kanji = (TextView) findViewById(R.id.textViewKanji);
        kanji.setText(kanjiList[indexList[currentItem-1].intValue()]);
        TextView onyomi = (TextView) findViewById(R.id.textViewOnyomi);
        onyomi.setText("onyomi: " + onyomiList[indexList[currentItem-1].intValue()]);
        TextView kunyomi = (TextView) findViewById(R.id.textViewKunyomi);
        kunyomi.setText("kunyomi: " + kunyomiList[indexList[currentItem-1].intValue()]);
        TextView translation = (TextView) findViewById(R.id.textViewTranslation);
        translation.setText(translationList[indexList[currentItem-1].intValue()]);
        TextView curretKanjiNumber = (TextView) findViewById(R.id.textViewCurrentKanjiNumber);
        curretKanjiNumber.setText("" + currentItem + " / " + numberOfCardsInGrade);
        if (testMode) {
            hideCard();
        }


    }

    public void hideCard() {
        TextView kunyomi = (TextView) findViewById(R.id.textViewKunyomi);
        kunyomi.setVisibility(View.INVISIBLE);
        TextView onyomi  = (TextView) findViewById(R.id.textViewOnyomi);
        onyomi.setVisibility(View.INVISIBLE);
        TextView translation = (TextView) findViewById(R.id.textViewTranslation);
        translation.setVisibility(View.INVISIBLE);
    }

    public void revealCard(View v) {
        TextView kunyomi = (TextView) findViewById(R.id.textViewKunyomi);
        kunyomi.setVisibility(View.VISIBLE);
        TextView onyomi  = (TextView) findViewById(R.id.textViewOnyomi);
        onyomi.setVisibility(View.VISIBLE);
        TextView translation = (TextView) findViewById(R.id.textViewTranslation);
        translation.setVisibility(View.VISIBLE);
    }

}
