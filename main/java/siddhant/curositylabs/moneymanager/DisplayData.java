package siddhant.curositylabs.moneymanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DisplayData extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DatabaseHandler mydb;
    String item_name,item_cost,item_category;
    List<String> itemNamesList;
    ArrayList itemDetails;
    Long item_date;

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        dateView = (TextView) findViewById(R.id.editDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);




        mydb = new DatabaseHandler(this);

        itemNamesList = new ArrayList<String>();
        itemDetails = new ArrayList();

        // cursor starts from index -1 or so the error says and hence it movetonext is a must
        // as by current understanding the c is contains the row number and coloumns are being called via getColoumnIndex


        Cursor c = mydb.getData();

        while(c.moveToNext()) {


            item_name = c.getString(c.getColumnIndex("item"));

            itemNamesList.add(item_name);

            item_cost = c.getString(c.getColumnIndex("cost"));
            item_category = c.getString(c.getColumnIndex("category"));
            item_date = c.getLong(c.getColumnIndex("date"));



            Date date = new Date(item_date);
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String formatted = format.format(date);

            ItemDetails itobj = new ItemDetails();
            itobj.setItemName(item_name);
            itobj.setDate(formatted);
            itobj.setItemCategory(item_category);
            itobj.setItemCost(item_cost);

            itemDetails.add(itobj);


        }


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_list_display_data, itemNamesList);
        ListView listView = (ListView) findViewById(R.id.data_list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);



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

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "yo bitch", Toast.LENGTH_SHORT)
                .show();

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };
}
