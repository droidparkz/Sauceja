package in.droidparkz.sauceja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class Myprofile extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationView navbottom;

    GoogleSignInClient mGoogleSignInClient;

    TextView name,email;

    String currentmail,currentname;

    ImageView profileimage;

    Uri personPhoto;

    RelativeLayout logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {

            currentmail = account.getEmail();
            currentname = account.getDisplayName();
            personPhoto = account.getPhotoUrl();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SharedPreferences sharedPreferences = getSharedPreferences("Cart",MODE_PRIVATE);

        navbottom = (BottomNavigationView) findViewById(R.id.bottomNavigation4);
        navbottom.inflateMenu(R.menu.bottom_nav_items);
        navbottom.setSelectedItemId(R.id.nav_profile);

        profileimage = (ImageView) findViewById(R.id.profileuserimage);
        Picasso.with(this).load(personPhoto).fit().centerInside().into(profileimage);

        name = (TextView) findViewById(R.id.profilebottomtxt);
        email = (TextView) findViewById(R.id.profiledevicename);

        name.setText(currentname);
        email.setText(currentmail);

        navbottom.setOnNavigationItemSelectedListener(navListener);

        logout = (RelativeLayout) findViewById(R.id.profilesignout);
        logout.setOnClickListener(this);

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
                            startActivity(new Intent(getApplicationContext(),Contact.class));
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.nav_profile:
                            return true;
                    }

                    return true;
                }
            };

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Myprofile.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Myprofile.this, Loginwindow.class));
                        finish();
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if (view == logout)
        {
            signOut();
        }

    }
}

