package com.labsgn.learn_android.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.labsgn.learn_android.R;
import com.labsgn.learn_android.pojo.Movie;
import com.labsgn.learn_android.utlis.AdapterBoxOffice;
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

import static com.labsgn.learn_android.utlis.Constant.URL_TYPE;
import static com.labsgn.learn_android.utlis.Constant.URL_TYPE.BOX_OFFICE;
import static com.labsgn.learn_android.utlis.Constant.getUrl;
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
   // private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    private ArrayList<Movie> listMovies = new ArrayList<>();
    private java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private RecyclerView listMovieHits;

    private AdapterBoxOffice adapterBoxOffice;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        //Todo 2. Setting Recycler View
        View view = inflater.inflate(R.layout.fragment_box_office, container, false);
        listMovieHits = (RecyclerView) view.findViewById(R.id.listMovieHits);
        listMovieHits.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Todo 11. Setup adapterBoxOffice
        adapterBoxOffice = new AdapterBoxOffice(getActivity());
        listMovieHits.setAdapter(adapterBoxOffice);

        //Todo 14
        sendJsonRequest();

        return view;
    }

    private static String getRequestUrl(URL_TYPE url_type , int limit){
        String url = getUrl(url_type);
        return url+limit;
    }

    private void sendJsonRequest(){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                getRequestUrl(BOX_OFFICE, 30),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Todo 13
                        listMovies = parseJsonResponse(response);
                        adapterBoxOffice.setListMovies(listMovies);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Logger.log_e("FragmentBoxOffice",error.toString());
                    }
                }
        );
        requestQueue.add(request);
    }

    //Todo 12. Modifikasi method untuk di buat ke dalam list
    private ArrayList<Movie> parseJsonResponse(JSONObject response) {
        ArrayList<Movie> listMovies = new ArrayList<>();
        if (response != null || response.length() > 0){

            long   id            = 0;
            int    audienceScore = -1;
            String title         = Constant.NA.NA,
                   releaseDate   = Constant.NA.NA,
                   synopsis      = Constant.NA.NA,
                   urlThumbnail  = Constant.NA.NA;

            try {
                JSONArray arrayMovie = response.getJSONArray(KEY_MOVIES);

                for (int i=0; i<arrayMovie.length(); i++){

                    JSONObject currentMovie = arrayMovie.getJSONObject(i);

                    if (currentMovie.has(KEY_ID) && !currentMovie.isNull(KEY_ID )){
                        id = currentMovie.getLong(KEY_ID);
                    }

                    if (currentMovie.has(KEY_TITLE) && !currentMovie.isNull(KEY_TITLE)){
                        title = currentMovie.getString(KEY_TITLE);
                    }

                    if (currentMovie.has(KEY_RELEASE_DATES) && !currentMovie.isNull(KEY_RELEASE_DATES)){
                        JSONObject objectReleaseDate = currentMovie.getJSONObject(KEY_RELEASE_DATES);
                        if (objectReleaseDate != null && objectReleaseDate.has(KEY_THEATER)
                                && !objectReleaseDate.isNull(KEY_THEATER))
                        {
                            releaseDate = objectReleaseDate.getString(KEY_THEATER);
                        }
                    }

                    if (currentMovie.has(KEY_RATINGS) && !currentMovie.isNull(KEY_RATINGS)){
                        JSONObject objectRatings = currentMovie.getJSONObject(KEY_RATINGS);
                        if (objectRatings != null && objectRatings.has(KEY_AUDIENCE_SCORE)
                                && !objectRatings.isNull(KEY_AUDIENCE_SCORE))
                        {
                            audienceScore = objectRatings.getInt(KEY_AUDIENCE_SCORE);
                        }
                    }

                    if (currentMovie.has(KEY_SYNOPSIS) && !currentMovie.isNull(KEY_SYNOPSIS)){
                        synopsis = currentMovie.getString(KEY_SYNOPSIS);
                    }

                    if (currentMovie.has(KEY_POSTERS) && !currentMovie.isNull(KEY_POSTERS)){
                        JSONObject objectPosters = currentMovie.getJSONObject(KEY_POSTERS);
                        if (objectPosters != null && objectPosters.has(KEY_THUMBNAIL)
                                && !objectPosters.isNull(KEY_THUMBNAIL))
                        {
                            urlThumbnail = objectPosters.getString(KEY_THUMBNAIL);
                        }
                    }

                    Movie movie = new Movie();
                    movie.setId(id);
                    movie.setTitle(title);
                    Date date = null;
                    try{
                        date = dateFormat.parse(releaseDate);
                    }catch (ParseException e){
                        Logger.log_e("FragmentBoxOffice", e.toString());
                    }
                    movie.setReleaseDateTheater(date);
                    movie.setAudienceScore(audienceScore);
                    movie.setSynopsis(synopsis);
                    movie.setUrlThumbnail(urlThumbnail);

                    if (id != -1 && !title.equals(Constant.NA.NA)){
                        listMovies.add(movie);
                    }
                }
                //Logger.log_i("FragmentBoxOffice",listMovies.toString());

            }
            catch (JSONException e) {
                Logger.log_e("FragmentBoxOffice",e.toString());
            }
        }
        return listMovies;
    }
}
