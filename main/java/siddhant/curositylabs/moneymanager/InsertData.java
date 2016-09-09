package siddhant.curositylabs.moneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;


public class InsertData extends AppCompatActivity {

    EditText item_name_input, item_cost_input, item_category_input;
    DatabaseHandler mydb;
    String item_name,item_category;
    Double item_cost;
    boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        mydb= new DatabaseHandler(this);

    }

    public boolean insertItem(View v)
    {
        item_name_input = (EditText)findViewById(R.id.input_name);
        item_cost_input = (EditText)findViewById(R.id.input_cost);
        item_category_input = (EditText)findViewById(R.id.input_category);

//        Date date_input = new Date();
//        insert_date = date_input.toString();



        item_name =item_name_input.getText().toString();
        if(item_name.trim().equals("")){
            item_name_input.setError("Required Field");
            return false;

        }
        item_category = item_category_input.getText().toString();
        if(item_category.trim().equals("")){
            item_category_input.setError("Required Field");
            return false;
        }

        if(item_cost_input.getText().toString().trim().equals("")){
            item_cost_input.setError("Required Field");
            return false;
        }

        item_cost = Double.parseDouble(item_cost_input.getText().toString());

        status=mydb.insertData(item_name, item_cost, item_category);

        if(status==true)
        {
            Toast.makeText(getApplicationContext(),"Item Added",Toast.LENGTH_SHORT).show();
            Intent ne = new Intent(InsertData.this,DisplayData.class);
            startActivity(ne);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error! Item Couldn't be added",Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
