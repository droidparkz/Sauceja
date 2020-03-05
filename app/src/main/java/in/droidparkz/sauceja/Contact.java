package in.droidparkz.sauceja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Contact extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationView navbottom;

    ImageView phone,gmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences sharedPreferences = getSharedPreferences("Cart",MODE_PRIVATE);

        navbottom = (BottomNavigationView) findViewById(R.id.bottomNavigation3);
        navbottom.inflateMenu(R.menu.bottom_nav_items);
        navbottom.setSelectedItemId(R.id.nav_support);

        navbottom.setOnNavigationItemSelectedListener(navListener);

        phone = (ImageView) findViewById(R.id.supportphone);
        phone.setOnClickListener(this);

        gmail = (ImageView) findViewById(R.id.supportgmail);
        gmail.setOnClickListener(this);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            startActivity(new Intent(getApplicationContext(),Homewindow.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.nav_order:
                            startActivity(new Intent(getApplicationContext(),Myorders.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.nav_support:
                            return true;

                        case R.id.nav_profile:
                            startActivity(new Intent(getApplicationContext(),Myprofile.class));
                            overridePendingTransition(0,0);
                            return true;
                    }

                    return true;
                }
            };

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View view) {

        if (view == phone) {
            String number = "8138822508";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(("tel:" + number)));
            startActivity(intent);
        }

        if(view == gmail){
            Intent intent=new Intent(Intent.ACTION_SEND);
            String[] recipients={"projectsauceja@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Sauceja App");
            intent.putExtra(Intent.EXTRA_TEXT,"");
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));
        }


    }
}

