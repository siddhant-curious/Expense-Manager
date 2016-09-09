package siddhant.curositylabs.moneymanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DisplayData extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DatabaseHandler mydb;
    String item_name,item_cost,item_category;
    TextView TodayISpent;
    List<String> itemNamesList;
    ArrayList itemDetails;
    Long item_date;
    Double Sum;

    private Calendar calendar;
    public TextView dateView;
    public int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(DisplayData.this, InsertData.class);
                startActivity(intent);
            }
        });
        // using dateView as both the click and show
        dateView = (TextView) findViewById(R.id.editDate);

        TodayISpent = (TextView) findViewById(R.id.todayIS);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        //initial call to show current day values
        getDateAction(year,month,day);





    }

    public void getDateAction(int year,int month,int day) {

        showDate(year, month+1, day);
        String str = "" + (month+1) + " " + day + " " + year + " 0:0:0.0 UTC";
        SimpleDateFormat df = new SimpleDateFormat("MM dd yyyy HH:mm:ss.SSS zzz");
        Date date1 =null;
        try {
            date1 = df.parse(str);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        long epoch = date1.getTime();
        Log.i("hello wow", String.valueOf(epoch));


        mydb = new DatabaseHandler(this);
        itemNamesList = new ArrayList<String>();
        itemDetails = new ArrayList();

        populateList(epoch);


    }


    public void populateList(Long epoch)
    {
        // cursor starts from index -1 or so the error says and hence it movetonext is a must
        // as by current understanding the c is contains the row number and coloumns are being called via getColoumnIndex
        Sum = 0.0;
        Cursor c = mydb.getData(epoch);
        while(c.moveToNext()) {

            item_name = c.getString(c.getColumnIndex("item"));
            // to show only the names in the list view
            itemNamesList.add(item_name);
            // getting rest of the data from database
            item_cost = c.getString(c.getColumnIndex("cost"));
            item_category = c.getString(c.getColumnIndex("category"));
            item_date = c.getLong(c.getColumnIndex("date"));

            Sum = Sum + Double.parseDouble(item_cost);
            String temp = String.valueOf(item_date);
            // item_date contains the epoch time
            // need to convert the epoch time to string
            Date date = new Date(item_date);
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //format.setTimeZone(TimeZone.getTimeZone("Etc/UTC")); if need to set a specific time
            String formatted = format.format(date);
            // passing all the details to a class (custom data structure) like struct
            ItemDetails itobj = new ItemDetails();
            itobj.setItemName(item_name);
            itobj.setDate(formatted);
            // showing epoch time in cateogry for debugging purposes
            itobj.setItemCategory(temp);
            itobj.setItemCost(item_cost);

            itemDetails.add(itobj);


        }


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_list_display_data, itemNamesList);
        ListView listView = (ListView) findViewById(R.id.data_list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
      // Log.v("msg","total sum is"+Sum);
        TodayISpent.setText("Today I Spent "+String.valueOf(Sum));
    }








    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Clicked "+ itemNamesList.get(position), Toast.LENGTH_SHORT).show();

        ItemDetails specific_detail = (ItemDetails) itemDetails.get(position);

        Intent showDetails = new Intent(this, showDetailsItem.class);

        Bundle dataBundle = new Bundle();
        dataBundle.putString("ItemName", specific_detail.getItemName());
        dataBundle.putString("ItemCost", specific_detail.getItemCost());
        dataBundle.putString("ItemCategory", specific_detail.getItemCategory());
        dataBundle.putString("ItemDate", specific_detail.getDate());

        showDetails.putExtras(dataBundle);

        startActivity(showDetails);
    }

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void setDate(View view)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
    }

}
