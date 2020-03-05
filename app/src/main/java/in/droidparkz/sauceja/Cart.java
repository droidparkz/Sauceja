package in.droidparkz.sauceja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static in.droidparkz.sauceja.Homewindow.MY_PREFS_NAME;

public class Cart extends AppCompatActivity implements View.OnClickListener {

    TextView name,price;

    String quan,total;

    Button refresh,book;

    int cost,quantity,tot;

    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        addItemsOnSpinner1();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        name = (TextView) findViewById(R.id.itemname);
        name.setText("Item name :"+prefs.getString("name", null));

        cost = prefs.getInt("price",0);

        price = (TextView)findViewById(R.id.itemprice);

        refresh = (Button)findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        book = (Button)findViewById(R.id.booknow);
        book.setOnClickListener(this);

    }

    public void addItemsOnSpinner1() {

        spinner1 = (Spinner) findViewById(R.id.spinnerquantity);
        List<String> list3 = new ArrayList<String>();
        list3.add("1");
        list3.add("2");
        list3.add("3");
        list3.add("4");
        list3.add("5");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list3);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View view) {

        if (view == refresh)
        {
            quan = String.valueOf(spinner1.getSelectedItem());
            quantity = Integer.parseInt(quan);

            tot = cost * quantity;

            total = Integer.toString(tot);

            price.setText(total);

        }

        if (view == book)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("quantity",quan);
            editor.putString("total", total);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Paymentloading.class));
        }

    }
}
