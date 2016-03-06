package com.labsgn.learn_android.utlis;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.labsgn.learn_android.R;
import com.labsgn.learn_android.pojo.Movie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rhony on 06/03/16.
 * Todo 4. Setup adapter untuk FragmentBoxOffice
 */
public class AdapterBoxOffice extends RecyclerView.Adapter<AdapterBoxOffice.ViewHolderBoxOffice> {

    private LayoutInflater layoutInflater;
    private ArrayList<Movie> listMovies = new ArrayList<>();
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //Todo 6. Setup constructor
    public AdapterBoxOffice(Context context){
        layoutInflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    //Todo 7
    @Override
    public ViewHolderBoxOffice onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_movie_box_office, parent, false);
        ViewHolderBoxOffice viewHolder = new ViewHolderBoxOffice(view);
        return viewHolder;
    }

    //Todo 9
    @Override
    public void onBindViewHolder(final ViewHolderBoxOffice holder, int position) {
        Movie currentMovie = listMovies.get(position);
        holder.movieTitle.setText(currentMovie.getTitle());

        //Todo 17. Mengkonversi string ke format Date
        //holder.movieReleaseDate.setText(currentMovie.getReleaseDateTheater().toString());
        Date movieReleaseDate = currentMovie.getReleaseDateTheater();
        if (movieReleaseDate != null){
            String formattedDate = dateFormat.format(movieReleaseDate);
            holder.movieReleaseDate.setText(formattedDate);
        }else{
            holder.movieReleaseDate.setText(Constant.NA.NA);
        }

        //Todo 18. Antisipasi juga jika tidak ada rating maka ratingBar di transparan 50%
        int audienceScore = currentMovie.getAudienceScore();
        if (audienceScore <= 0){
            holder.movieAudienceScore.setRating(0.0f);
            holder.movieAudienceScore.setAlpha(0.5f);
        }else{
            holder.movieAudienceScore.setRating(currentMovie.getAudienceScore()/20.0f);
            holder.movieAudienceScore.setAlpha(1.0f);
        }

        String urlThumbnail = currentMovie.getUrlThumbnail();
        loadImages(urlThumbnail, holder);
    }

    //Todo 16. Membuat method baru untuk me-load images thumbnail ke viewholder
    private void loadImages(String urlThumbnail, final ViewHolderBoxOffice holder){
        if (!urlThumbnail.equals(Constant.NA.NA)){
            imageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.movieThumbnail.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Logger.log_e("AdapterBoxOffice",error.toString());
                }
            });
        }
    }

    //Todo 10. Tampilkan daftar movie sebanyak listMovies
    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    //Todo 8
    public void setListMovies(ArrayList<Movie> movieArrayList){
        this.listMovies = movieArrayList;
        notifyItemChanged(0, movieArrayList.size());
    }

    //Todo 5. Membuat sub class
    static class ViewHolderBoxOffice extends RecyclerView.ViewHolder{

        private ImageView movieThumbnail;

        private TextView
                movieTitle,
                movieReleaseDate;

        private RatingBar movieAudienceScore;

        public ViewHolderBoxOffice(View itemView){
            super(itemView);
            movieThumbnail  = (ImageView) itemView.findViewById(R.id.movieThumbnail);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.movieReleaseDate);
            movieAudienceScore = (RatingBar) itemView.findViewById(R.id.movieAudienceScore);
        }
    }

}
