package in.droidparkz.sauceja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static in.droidparkz.sauceja.Homewindow.MY_PREFS_NAME;

public class Paymentwindow extends AppCompatActivity implements View.OnClickListener {

    EditText cardno,cardcvv,cardname;

    TextView expiry;

    String cno,cvv,cname,cdate,ctemp,cdatetemp;

    String Getemail,Getitem,Getquantity,Getprice;

    GoogleSignInClient mGoogleSignInClient;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "Paymentwindow";

    String DataParseUrl = "https://sauceja.000webhostapp.com/order.php";

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentwindow);

        cardno = (EditText) findViewById(R.id.paymentwindowcardnumber1);
        cardcvv = (EditText) findViewById(R.id.paymentwindowcardcvv1);
        expiry = (TextView) findViewById(R.id.paymentwindowcarddate1);
        cardname = (EditText) findViewById(R.id.paymentwindowcardname1);
        cardname.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        expiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Paymentwindow.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yy: " + month + "/" + day + "/" + year);

                String year1 = String.valueOf(year).substring(2);

                String date1 = month + "/" + year1;
                expiry.setText(date1);
            }
        };

        next = (Button) findViewById(R.id.paymentwindowbutton);
        next.setOnClickListener(this);

    }

    public void cardinfo()
    {
        ctemp = cardno.getText().toString();
        cvv = cardcvv.getText().toString().trim();
        cdatetemp = expiry.getText().toString().trim();
        cname = cardname.getText().toString().trim();

        if(ctemp.length()!= 16){
            Toast.makeText(this,"Please enter Valid Card Number",Toast.LENGTH_LONG).show();
            return;
        }

        cno = ctemp.substring(0,4) + "  " + ctemp.substring(4,8) + "  " + ctemp.substring(8,12) + "  " + ctemp.substring(12,16);

        cdate = "Valid Upto :" + cdatetemp;

        if(TextUtils.isEmpty(cno)){
            Toast.makeText(this,"Please enter Card Number",Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(cvv)){
            Toast.makeText(this,"Please enter CVV",Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(cdate)){
            Toast.makeText(this,"Please enter Card Validity",Toast.LENGTH_LONG).show();
            return;
        }

        else if(TextUtils.isEmpty(cname)){
            Toast.makeText(this,"Please select Name of Card Holder",Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            GetData();
        }

    }

    public void GetData(){

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {

            Getemail = account.getEmail();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Getitem = prefs.getString("name", null);
        Getquantity = prefs.getString("quantity", null);
        Getprice = prefs.getString("total", null);

        SendDataToServer();

    }

    public void SendDataToServer(){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("email", Getemail));
                nameValuePairs.add(new BasicNameValuePair("item", Getitem));
                nameValuePairs.add(new BasicNameValuePair("quantity", Getquantity));
                nameValuePairs.add(new BasicNameValuePair("price", Getprice));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(DataParseUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Submit Successfully";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(Paymentwindow.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Paymentwindow.this, Paymentconfirm.class);
                startActivity(intent);

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();
    }

    @Override
    public void onClick(View view) {

        if(view == next)
        {
            cardinfo();
        }

    }

}
