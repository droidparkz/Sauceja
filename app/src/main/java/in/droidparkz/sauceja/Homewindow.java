package in.droidparkz.sauceja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homewindow extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationView navbottom;

    ImageView f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16;

    public static final String MY_PREFS_NAME = "Cart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homewindow);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences sharedPreferences = getSharedPreferences("Cart",MODE_PRIVATE);

        f1 = (ImageView)findViewById(R.id.food1);
        f2 = (ImageView)findViewById(R.id.food2);
        f3 = (ImageView)findViewById(R.id.food3);
        f4 = (ImageView)findViewById(R.id.food4);
        f5 = (ImageView)findViewById(R.id.food5);
        f6 = (ImageView)findViewById(R.id.food6);
        f7 = (ImageView)findViewById(R.id.food7);
        f8 = (ImageView)findViewById(R.id.food8);
        f9 = (ImageView)findViewById(R.id.food9);
        f10 = (ImageView)findViewById(R.id.food10);
        f11 = (ImageView)findViewById(R.id.food11);
        f12 = (ImageView)findViewById(R.id.food12);
        f13 = (ImageView)findViewById(R.id.food13);
        f14 = (ImageView)findViewById(R.id.food14);
        f15 = (ImageView)findViewById(R.id.food15);
        f16 = (ImageView)findViewById(R.id.food16);

        f1.setOnClickListener(this);
        f2.setOnClickListener(this);
        f3.setOnClickListener(this);
        f4.setOnClickListener(this);
        f5.setOnClickListener(this);
        f6.setOnClickListener(this);
        f7.setOnClickListener(this);
        f8.setOnClickListener(this);
        f9.setOnClickListener(this);
        f10.setOnClickListener(this);
        f11.setOnClickListener(this);
        f12.setOnClickListener(this);
        f13.setOnClickListener(this);
        f14.setOnClickListener(this);
        f15.setOnClickListener(this);
        f16.setOnClickListener(this);

        navbottom = (BottomNavigationView) findViewById(R.id.bottomNavigation1);
        navbottom.inflateMenu(R.menu.bottom_nav_items);
        navbottom.setSelectedItemId(R.id.nav_home);

        navbottom.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            return true;

                        case R.id.nav_order:
                            startActivity(new Intent(getApplicationContext(),Myorders.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.nav_support:
                            startActivity(new Intent(getApplicationContext(),Contact.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.nav_profile:
                            startActivity(new Intent(getApplicationContext(),Myprofile.class));
                            overridePendingTransition(0,0);
                            return true;
                    }

                    return true;
                }
            };

    @Override
    public void onClick(View view) {

        if (view == f1)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Appam");
            editor.putInt("price", 6);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f2)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Dosa");
            editor.putInt("price", 6);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f3)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Puttu");
            editor.putInt("price", 10);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f4)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Chappathy");
            editor.putInt("price", 7);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f5)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Biriyani");
            editor.putInt("price", 130);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f6)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Sadhya");
            editor.putInt("price", 120);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f7)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Mandhi");
            editor.putInt("price", 150);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f8)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Noodles");
            editor.putInt("price", 170);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }


        if (view == f9)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Ice-cream");
            editor.putInt("price", 25);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f10)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Shake");
            editor.putInt("price", 80);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f11)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Fruit Salad");
            editor.putInt("price", 70);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f12)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Mixed Fusion");
            editor.putInt("price", 150);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f13)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Drinks");
            editor.putInt("price", 40);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f14)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Fresh Lime");
            editor.putInt("price", 20);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f15)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Cold Coffee");
            editor.putInt("price", 30);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

        if (view == f16)
        {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "Boost");
            editor.putInt("price", 20);
            editor.apply();
            startActivity(new Intent(getApplicationContext(),Cart.class));
        }

    }
}
