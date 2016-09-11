package siddhant.curositylabs.moneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class showDetailsItem extends AppCompatActivity {

    String ItemName,ItemCost,ItemCategory,ItemDate;
    TextView name,cost,cat,date;
    int primayKey,month,day,year;
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_item);
        Intent intent = getIntent();

        //so bloody stupid..what the actual fuck? I should've just passed the fucking primary key and called the fucking details.. so fucking stupid!
        primayKey = intent.getIntExtra("PrimaryKey",0);
        ItemName = intent.getStringExtra("ItemName");
        ItemCost = intent.getStringExtra("ItemCost");
        ItemCategory = intent.getStringExtra("ItemCategory");
        ItemDate = intent.getStringExtra("ItemDate");
        month = intent.getIntExtra("month",0);
        day = intent.getIntExtra("day",0);
        year = intent.getIntExtra("year",0);

        Log.d("revived","Day "+day);
        Log.d("revived","Month "+month);
        Log.d("revived","Year "+year);

        name = (TextView)findViewById(R.id.itemName);
        cost = (TextView)findViewById(R.id.itemCost);
        cat = (TextView)findViewById(R.id.itemCategory);
        date = (TextView)findViewById(R.id.itemDate);

        name.setText(ItemName);
        cost.setText(ItemCost);
        cat.setText(ItemCategory);
        date.setText(ItemDate);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.displayitem_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_delete:
                db = new DatabaseHandler(this);
                db.deleteItem(primayKey);
                Intent parent = new Intent(showDetailsItem.this,DisplayData.class);
                Bundle dataBundle  = new Bundle();
                dataBundle.putInt("day",day);
                Log.d("sent2 ","Day "+day);
                dataBundle.putInt("month",month);
                dataBundle.putInt("year",year);
                parent.putExtras(dataBundle);
                startActivity(parent);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }
}
