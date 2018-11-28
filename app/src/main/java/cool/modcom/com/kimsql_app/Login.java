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

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         AsyncHttpClient client = new AsyncHttpClient();
                                         RequestParams params = new RequestParams();
                                         params.add("email", email.getText().toString());
                                         params.add("password", password.getText().toString());

                                         final ProgressDialog dialog = new ProgressDialog(Login.this);
                                         dialog.setTitle("Dialog");
                                         dialog.setMessage("Please wait. Signing in...");
                                         dialog.show();

                                         client.post("http://modkenya.com/wkmani/login.php", params, new AsyncHttpResponseHandler() {
                                             @Override
                                             public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                                 dialog.dismiss();
                                                 String response = new String(responseBody);
                                                 //trim removes spaces before the number
                                                 if (response.trim().equals("1")) {
                                                     Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                                                 } else {
                                                     Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                                 }
                                             }

                                             @Override
                                             public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


                                             }
                                         });

                                     }
                                 }

        );
    }
}
