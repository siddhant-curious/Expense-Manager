package siddhant.curositylabs.moneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class showDetailsItem extends AppCompatActivity {

    String ItemName,ItemCost,ItemCategory,ItemDate;
    TextView name,cost,cat,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_item);
        Intent intent = getIntent();
        ItemName = intent.getStringExtra("ItemName");
        ItemCost = intent.getStringExtra("ItemCost");
        ItemCategory = intent.getStringExtra("ItemCategory");
        ItemDate = intent.getStringExtra("ItemDate");

        name = (TextView)findViewById(R.id.itemName);
        cost = (TextView)findViewById(R.id.itemCost);
        cat = (TextView)findViewById(R.id.itemCategory);
        date = (TextView)findViewById(R.id.itemDate);

        name.setText(ItemName);
        cost.setText(ItemCost);
        cat.setText(ItemCategory);
        date.setText(ItemDate);



    }
}
