package in.droidparkz.sauceja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Myorders extends AppCompatActivity {

    BottomNavigationView navbottom;

    private RecyclerView mRecyclerView;
    private OrderAdapter mOrderAdapter;
    private ArrayList<OrderItem> mOrderList;
    private RequestQueue mRequestQueue;

    String URL_ORDER = "https://sauceja.000webhostapp.com/myorder.php";

    String currentmail;

    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences sharedPreferences = getSharedPreferences("Cart",MODE_PRIVATE);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {

            currentmail = account.getEmail();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        navbottom = (BottomNavigationView) findViewById(R.id.bottomNavigation2);
        navbottom.inflateMenu(R.menu.bottom_nav_items);
        navbottom.setSelectedItemId(R.id.nav_order);

        navbottom.setOnNavigationItemSelectedListener(navListener);

        mRecyclerView = findViewById(R.id.recycler_view_myorders);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mOrderList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        loadProducts();

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

    private void loadProducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ORDER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            mOrderList.clear();

                            for (int i = 0; i <= array.length()-1; i++) {

                                JSONObject order = array.getJSONObject(i);

                                if(order.getString("email").equals(currentmail))
                                {
                                    mOrderList.add(new OrderItem(
                                            order.getString("name"),
                                            order.getString("quantity"),
                                            order.getString("price")

                                    ));
                                }
                            }

                            OrderAdapter adapter = new OrderAdapter(Myorders.this, mOrderList);
                            mRecyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
