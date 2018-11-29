package cool.modcom.com.kimsql_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class ViewProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        //we have a listview and a custom design
        //dialog object has been named "talk"
        final ProgressDialog talk = new ProgressDialog(ViewProducts.this);
        talk.setTitle("Products");
        talk.setMessage("Please wait. Loading Products...");
        talk.show();

        //create an array list
        //this arraylist will store our hashmaps

        final ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        //as the dialog spins, we go online get the products
        //once we have got the products we dismiss the dialog

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://modkenya.com/wkmani/view.php", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //use a loop in order to retrieve JSON at a certain point "i"

                for (int i = 0; i < response.length(); i++) {
                    //to safeguard code from crashing and remove the "Exception error put "try" and "catch"
                    try {


                        JSONObject productObject = response.getJSONObject(i);//starts 0
                        //get/retrieve/extract the object values
                        //HashMap - its a key and value thing. key is usually a string - stores key-value
                        HashMap<String, String> map = new HashMap<>();
                        map.put("name", productObject.getString("name"));
                        map.put("type", productObject.getString("type"));
                        map.put("cost", productObject.getString("cost"));
                        map.put("contact", productObject.getString("contact"));
                        arrayList.add(map);//add Hashmap to arraylist
                    } catch (Exception error) {
                        Toast.makeText(ViewProducts.this, "Error Loading JSON", Toast.LENGTH_SHORT).show();
                    }
                }//end of for loop
                talk.dismiss();//stop dialog
                //we now map our array list to our listview
                SimpleAdapter adapter = new SimpleAdapter(
                        ViewProducts.this,
                        arrayList,
                        R.layout.design,
                        new String[]{"name", "type", "cost", "contact"},
                        new int[]{R.id.d_name, R.id.d_type, R.id.d_cost, R.id.d_contact}

                );

                ListView productslist = findViewById(R.id.productslist);
                productslist.setAdapter(adapter);//done
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                talk.dismiss();
                Toast.makeText(ViewProducts.this, "Failed to reach server", Toast.LENGTH_SHORT).show();
            }
        });





    }
}
