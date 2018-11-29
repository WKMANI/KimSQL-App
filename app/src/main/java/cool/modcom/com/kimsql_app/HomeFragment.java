package cool.modcom.com.kimsql_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class HomeFragment extends Fragment {
    //Fragments can be attached in an activity
    //to create a Fragment onCreateView is used as the class entry point
    //activities use onCreate itself as class entry point


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //connect this fragment to its layout
        final View xml = inflater.inflate(R.layout.home_fragment, container, false);
        final ProgressDialog talk = new ProgressDialog(getActivity());//(ViewProducts.this); is only done on activities not Fragments
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
                        Toast.makeText(getActivity(), "Error Loading JSON", Toast.LENGTH_SHORT).show();
                    }
                }//end of for loop
                talk.dismiss();//stop dialog
                //we now map our array list to our listview
                SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                        arrayList,
                        R.layout.design,
                        new String[]{"name", "type", "cost", "contact"},
                        new int[]{R.id.d_name, R.id.d_type, R.id.d_cost, R.id.d_contact}

                );

                ListView homeList = xml.findViewById(R.id.homeList);
                homeList.setAdapter(adapter);//done

                homeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Check out
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                talk.dismiss();
                Toast.makeText(getActivity(), "Failed to reach server", Toast.LENGTH_SHORT).show();
            }
        });

        return xml; //returns the layout

    }
}
