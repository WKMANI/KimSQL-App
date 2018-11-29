package cool.modcom.com.kimsql_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BagsFragment extends Fragment {
    //Fragments can be attached in an activity
    //to create a Fragment onCreateView is used as the class entry point
    //activities use onCreate itself as class entry point


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //connect this fragment to its layout
        View xml = inflater.inflate(R.layout.bags_fragment, container, false);

        return xml; //returns the layout

    }
}
