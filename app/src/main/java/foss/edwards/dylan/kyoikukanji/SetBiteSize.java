package foss.edwards.dylan.kyoikukanji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SetBiteSize extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_bite_size);

        SharedPreferences biteSizeSave = getSharedPreferences("bite_size", 0);
        int biteSizeSize = (biteSizeSave.getInt("biteSize", 10));

        TextView textViewCurrentSize = (TextView) findViewById(R.id.textViewCurrentSize);
        textViewCurrentSize.setText("Currently: " + biteSizeSize);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bite_size, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSave(View v) {
        EditText editTextnewSize = (EditText) findViewById(R.id.editTextNewSize);
        int newSize = Integer.parseInt(editTextnewSize.getText().toString());
        SharedPreferences biteSizeSave = getSharedPreferences("bite_size", 0);
        SharedPreferences.Editor editor = biteSizeSave.edit();
        editor.putInt("biteSize", newSize);
        editor.commit();
        Intent intent = new Intent(this, StartPage.class);
        startActivity(intent);//TODO go back a better way
    }

    public void onClickExit(View v) {
        Intent intent = new Intent(this, StartPage.class);
        startActivity(intent);//TODO go back a better way
    }

}
