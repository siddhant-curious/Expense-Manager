package siddhant.curositylabs.moneymanager;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayData extends AppCompatActivity {

    DatabaseHandler mydb;
    String item_name,item_cost,item_category,item_date;
    TextView item_name_textview,item_cost_textview,item_category_textview,item_date_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        mydb = new DatabaseHandler(this);


        // cursor starts from index -1 or so the error says and hence it movetonext is a must
        // as by current understanding the c is contains the row number and coloumns are being called voa getColoumnIndex


        Cursor c = mydb.getData();
        c.moveToNext();


        item_name = c.getString(c.getColumnIndex("item"));
        item_cost = c.getString(c.getColumnIndex("cost"));
        item_category = c.getString(c.getColumnIndex("category"));
        item_date = c.getString(c.getColumnIndex("date"));

        item_name_textview = (TextView)findViewById(R.id.item_name_show);
        item_cost_textview = (TextView)findViewById(R.id.item_price_show);
        item_category_textview = (TextView)findViewById(R.id.item_category_show);
        item_date_textview = (TextView)findViewById(R.id.item_date_show);

        item_name_textview.setText(item_name);
        item_cost_textview.setText(item_cost);
        item_category_textview.setText(item_category);
        item_date_textview.setText(item_date);

    }

}
