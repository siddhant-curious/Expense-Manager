package siddhant.curositylabs.moneymanager;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHandler(this);

    }

    public void showData(View v)
    {
        Intent i = new Intent(MainActivity.this, DisplayData.class);
        startActivity(i);
    }

    public void addData(View v)
    {
        Intent k = new Intent(MainActivity.this, InsertData.class);
        startActivity(k);
    }

    public void deleteData(View v)
    {
        myDB.deleteTable();
    }

    public void selectData(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }



}
