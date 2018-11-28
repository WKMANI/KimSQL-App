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

public class AddProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        final EditText name = findViewById(R.id.name);
        final EditText type = findViewById(R.id.type);
        final EditText cost = findViewById(R.id.cost);
        final EditText description = findViewById(R.id.description);
        final EditText contact = findViewById(R.id.contact);
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //post to PHP online

                //will be used to send data
                AsyncHttpClient client = new AsyncHttpClient();

                RequestParams params = new RequestParams();
                params.add("name", name.getText().toString());
                params.add("type", type.getText().toString());
                params.add("cost", cost.getText().toString());
                params.add("description", description.getText().toString());
                params.add("contact", contact.getText().toString());

                //map how values will be sent to PHP
                final ProgressDialog dialog = new ProgressDialog(AddProduct.this);
                dialog.setTitle("Dialog");
                dialog.setMessage("Please wait... Uploading your record");
                dialog.show();

                //modkenya.com/cpanel
                //modkenya
                //Je^^kksr7tMF

                client.post("http://modkenya.com/wkmani/insert.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        dialog.dismiss();
                        Toast.makeText(AddProduct.this, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        dialog.dismiss();
                        Toast.makeText(AddProduct.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}
