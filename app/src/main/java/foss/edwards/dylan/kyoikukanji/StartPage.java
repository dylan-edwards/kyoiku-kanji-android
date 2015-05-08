package foss.edwards.dylan.kyoikukanji;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


public class StartPage extends ActionBarActivity {

    final int[] cardsInGradeList = {80,160,200,200,185,181};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_page, menu);
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
            String[] aboutInfo = new String[5];
            aboutInfo[0] = getString(R.string.app_name);
            aboutInfo[1] = "Version: " + getString(R.string.version);
            aboutInfo[2] = "Developer: " + getString(R.string.dev_name);
            aboutInfo[3] = "Email: " + getString(R.string.dev_email);
            aboutInfo[4] = "Code: "  + getString(R.string.web_address);
            String aboutMessage = aboutInfo[0] + "\n" + aboutInfo[1] + "\n" + aboutInfo[2] + "\n" + aboutInfo[3] + "\n" + aboutInfo[4] + "\n";
            about(aboutMessage);
            return true;
        }
        if (id == R.id.action_set_bite_size) {
            Intent intent = new Intent(this, SetBiteSize.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void nextScreen(View v) {
        int grade = Integer.parseInt(v.getTag().toString());
        //numberOfCardsInGrade and randamOrder and testMode
        CheckBox randomCheck     = (CheckBox) findViewById(R.id.randomOrderCheckbox);
        boolean randomOrder      = randomCheck.isChecked();
        CheckBox checkTestMode   = (CheckBox) findViewById(R.id.checkTestMode);
        boolean testMode         = checkTestMode.isChecked();
        CheckBox checkBiteSize   = (CheckBox) findViewById(R.id.checkBoxBiteSized);
        boolean biteSize         = checkBiteSize.isChecked();
        int numberOfCardsInGrade = cardsInGradeList[grade - 1];

        Intent intent = new Intent(this, CardsPage.class);
        intent.putExtra("grade", grade);
        intent.putExtra("numCards", numberOfCardsInGrade);
        intent.putExtra("testMode", testMode);
        if (biteSize) {
            intent.putExtra("random", true);
            intent.putExtra("biteSize", biteSize);
        } else if (!biteSize) {
            intent.putExtra("random", randomOrder);
            intent.putExtra("biteSize", biteSize);
        }
        startActivity(intent);
    }

    public void about(String aboutMessage) {
        //TODO about dialog

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About");
        final TextView textView = new TextView(this);
        textView.setText(aboutMessage);
        builder.setView(textView);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
