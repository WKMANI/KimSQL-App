package cool.modcom.com.kimsql_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class RegForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_form);
        final EditText fullname = findViewById(R.id.fullname);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//post to PHP online

//will be used to send data
                AsyncHttpClient client = new AsyncHttpClient();

                RequestParams params = new RequestParams();
                params.add("fullname", fullname.getText().toString());
                params.add("email", email.getText().toString());
                params.add("password", password.getText().toString());

//map how values will be sent to PHP
                final ProgressDialog dialog = new ProgressDialog(RegForm.this);
                dialog.setTitle("Dialog");
                dialog.setMessage("Please wait... Uploading your record");
                dialog.show();

//modkenya.com/cpanel
//modkenya
//Je^^kksr7tMF

                client.post("http://modkenya.com/wkmani/insert2.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        dialog.dismiss();
                        Toast.makeText(RegForm.this, "Saved", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        dialog.dismiss();
                        Toast.makeText(RegForm.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}


