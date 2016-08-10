package siddhant.curositylabs.moneymanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayData extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DatabaseHandler mydb;
    String item_name,item_cost,item_category,item_date;
    List<String> itemNamesList;
    ArrayList itemDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
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
            item_date = c.getString(c.getColumnIndex("date"));


            ItemDetails itobj = new ItemDetails();
            itobj.setItemName(item_name);
            itobj.setDate(item_date);
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
}
