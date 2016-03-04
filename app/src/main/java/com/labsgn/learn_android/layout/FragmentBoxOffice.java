package com.labsgn.learn_android.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.labsgn.learn_android.R;
import com.labsgn.learn_android.pojo.Movie;
import com.labsgn.learn_android.utlis.Constant;
import com.labsgn.learn_android.utlis.Logger;
import com.labsgn.learn_android.utlis.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_AUDIENCE_SCORE;
import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_ID;
import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_MOVIES;
import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_POSTERS;
import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_RATINGS;
import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_RELEASE_DATES;
import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_SYNOPSIS;
import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_THEATER;
import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_THUMBNAIL;
import static com.labsgn.learn_android.utlis.Keys.EndpointBoxOffice.KEY_TITLE;

//Todo 7. import key supaya kode lebih rapi & bersih :)


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBoxOffice#newInstance} factory method to
 * create an instance of this fragment.
 * <p>
 */
public class FragmentBoxOffice extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    //Todo 13
    private ArrayList<Movie> listMovies = new ArrayList<>();
    private java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public FragmentBoxOffice() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBoxOffice.
     */
    public static FragmentBoxOffice newInstance(String param1, String param2) {
        FragmentBoxOffice fragment = new FragmentBoxOffice();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        //Todo 4. Membuat method request
        sendJsonRequest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_box_office, container, false);
    }

    private static String getRequestUrl(Constant.URL_TYPE url_type , int limit){
        String url = Constant.getUrl(url_type);
        return url+limit;
    }

    private void sendJsonRequest(){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                getRequestUrl(Constant.URL_TYPE.BOX_OFFICE, 10),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Todo 5. Setup method
                        parseJsonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Logger.log_e("ERROR ::: "+error.toString());
                    }
                }
        );
        requestQueue.add(request);
    }

    private void parseJsonResponse(JSONObject response) {
        if (response == null || response.length() == 0){
            return;
        }
        //Todo 8
        try {
                StringBuilder data = new StringBuilder();
                JSONArray arrayMovie = response.getJSONArray(KEY_MOVIES);

                for (int i=0; i<arrayMovie.length(); i++){

                    JSONObject currentMovie = arrayMovie.getJSONObject(i);

                    long id = currentMovie.getLong(KEY_ID);

                    String title = currentMovie.getString(KEY_TITLE);

                    JSONObject objectReleaseDate = currentMovie.getJSONObject(KEY_RELEASE_DATES);
                    String releaseDate = null;
                    if (objectReleaseDate.has(KEY_THEATER)){
                        releaseDate = objectReleaseDate.getString(KEY_THEATER);
                    }
                    else releaseDate = "N/A";

                    JSONObject objectRatings = currentMovie.getJSONObject(KEY_RATINGS);
                    int audienceScore = -1;
                    if (objectRatings.has(KEY_AUDIENCE_SCORE)){
                        audienceScore = objectRatings.getInt(KEY_AUDIENCE_SCORE);
                    }

                    String synopsis = currentMovie.getString(KEY_SYNOPSIS);

                    JSONObject objectPosters = currentMovie.getJSONObject(KEY_POSTERS);
                    String urlThumbnail = null;
                    if (objectPosters.has(KEY_THUMBNAIL)){
                        urlThumbnail = objectPosters.getString(KEY_THUMBNAIL);
                    }
                    else urlThumbnail = "N/A";

                    //Todo 14. Simpan current Movie ke class movie
                    Movie movie = new Movie();
                    movie.setId(id);
                    movie.setTitle(title);
                    Date date = dateFormat.parse(releaseDate);
                    movie.setReleaseDateTheater(date);
                    movie.setAudienceScore(audienceScore);
                    movie.setSynopsis(synopsis);
                    movie.setUrlThumbnail(urlThumbnail);

                    //Todo 15. Simpan data movie ke list movie
                    listMovies.add(movie);

                }
                //Todo 16. Cetak data movie
                //Logger.log_i(data.toString());
                Logger.log_i(listMovies.toString());

        }
        catch (JSONException | ParseException e) {
            Logger.log_e(e.toString());
        }
    }
}
