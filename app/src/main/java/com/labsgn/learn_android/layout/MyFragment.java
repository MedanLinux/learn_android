package com.labsgn.learn_android.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.labsgn.learn_android.R;
import com.labsgn.learn_android.utlis.Logger;
import com.labsgn.learn_android.utlis.VolleySingleton;

/**
 * Created by rhony on 27/02/16.
 */
public class MyFragment extends Fragment {

    private TextView textView;
    private String url = "http://google.com";

    public static MyFragment getInstance(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.main_fragment, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        Bundle bundle = getArguments();
        if (bundle != null) {
            textView.setText("Selected page : " + bundle.getInt("position"));
        }

        //Todo 4. Setup requestQueue dari volleySingleton
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.log(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Logger.log(error.toString());
                    }
                }
        );

        requestQueue.add(request);

        //return layout;
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
